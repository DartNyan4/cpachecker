/*
 *  CPAchecker is a tool for configurable software verification.
 *  This file is part of CPAchecker.
 *
 *  Copyright (C) 2007-2012  Dirk Beyer
 *  All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 *  CPAchecker web page:
 *    http://cpachecker.sosy-lab.org
 */
package org.sosy_lab.cpachecker.cpa.usage;

import static com.google.common.collect.FluentIterable.from;

import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nonnull;
import org.sosy_lab.cpachecker.cfa.model.CFAEdge;
import org.sosy_lab.cpachecker.core.interfaces.AbstractState;
import org.sosy_lab.cpachecker.cpa.lock.AbstractLockState;
import org.sosy_lab.cpachecker.cpa.lock.LockState;
import org.sosy_lab.cpachecker.cpa.usage.storage.UsagePoint;
import org.sosy_lab.cpachecker.util.AbstractStates;
import org.sosy_lab.cpachecker.util.identifiers.AbstractIdentifier;
import org.sosy_lab.cpachecker.util.identifiers.SingleIdentifier;

public class UsageInfo implements Comparable<UsageInfo> {

  public static enum Access {
    WRITE,
    READ;
  }

  private static class UsageCore {
    private final LineInfo line;
    private final Access accessType;
    private AbstractState keyState;
    private List<CFAEdge> path;
    private final SingleIdentifier id;

    private boolean isLooped;

    private UsageCore() {
      // Only for unsupported usage
      line = null;
      accessType = Access.WRITE;
      keyState = null;
      isLooped = false;
      id = null;
    }

    private UsageCore(@Nonnull Access atype, @Nonnull LineInfo l, SingleIdentifier ident) {
      line = l;
      accessType = atype;
      keyState = null;
      isLooped = false;
      id = ident;
    }
  }

  private static final UsageInfo IRRELEVANT_USAGE = new UsageInfo();

  private final UsageCore core;

  // Can not be immutable due to reduce/expand - lock states are modified (may be smth else)
  private final Map<Class<? extends CompatibleState>, CompatibleState> compatibleStates =
      new LinkedHashMap<>();

  private UsageInfo() {
    core = new UsageCore();
  }

  private UsageInfo(@Nonnull Access atype, @Nonnull LineInfo l, SingleIdentifier ident) {
    core = new UsageCore(atype, l, ident);
  }

  private UsageInfo(UsageCore pCore) {
    core = pCore;
  }

  public static UsageInfo createUsageInfo(
      @Nonnull Access atype, int l, @Nonnull UsageState state, AbstractIdentifier ident) {
    if (ident instanceof SingleIdentifier) {
      UsageInfo result =
          new UsageInfo(
              atype,
              new LineInfo(l, AbstractStates.extractLocation(state)),
              (SingleIdentifier) ident);
      FluentIterable<CompatibleState> states =
          AbstractStates.asIterable(state).filter(CompatibleState.class);
      if (states.allMatch(s -> s.isRelevantFor(result.core.id))) {
        states.forEach(s -> result.compatibleStates.put(s.getClass(), s.prepareToStore()));
        return result;
      }
    }
    return IRRELEVANT_USAGE;
  }

  public @Nonnull LineInfo getLine() {
    return core.line;
  }

  public @Nonnull SingleIdentifier getId() {
    assert (core.id != null);
    return core.id;
  }

  public void setAsLooped() {
    core.isLooped = true;
  }

  public boolean isLooped() {
    return core.isLooped;
  }

  public boolean isRelevant() {
    return this != IRRELEVANT_USAGE;
  }

  @Override
  public int hashCode() {
    return Objects.hash(core.accessType, core.line, compatibleStates);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    UsageInfo other = (UsageInfo) obj;
    return core.accessType == other.core.accessType
        && Objects.equals(core.line, other.core.line)
        && Objects.equals(compatibleStates, other.compatibleStates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    if (core.id != null) {
      sb.append("Id ");
      sb.append(core.id.toString());
      sb.append(", ");
    }
    sb.append("line ");
    sb.append(core.line.toString());
    sb.append(" (" + core.accessType + ")");
    sb.append(", " + getLockState());

    return sb.toString();
  }

  public String getWarningMessage() {
    StringBuilder sb = new StringBuilder();

    sb.append(core.accessType);
    sb.append(" access to ");
    sb.append(core.id);
    AbstractLockState locks = getLockState();
    if (locks == null) {
      // Lock analysis is disabled
    } else if (locks.getSize() == 0) {
      sb.append(" without locks");
    } else {
      sb.append(" with ");
      sb.append(locks);
    }

    return sb.toString();
  }

  public void setKeyState(AbstractState state) {
    core.keyState = state;
  }

  public void setRefinedPath(List<CFAEdge> p) {
    core.keyState = null;
    core.path = p;
  }

  public AbstractState getKeyState() {
    return core.keyState;
  }

  public List<CFAEdge> getPath() {
    // assert path != null;
    return core.path;
  }

  @Override
  public int compareTo(UsageInfo pO) {
    int result;

    if (this == pO) {
      return 0;
    }
    Set<Class<? extends CompatibleState>> currentStateTypes = compatibleStates.keySet();
    Set<Class<? extends CompatibleState>> otherStateTypes = pO.compatibleStates.keySet();
    Preconditions.checkArgument(
        currentStateTypes.equals(otherStateTypes),
        "Different compatible states in usages are not supported");
    for (Entry<Class<? extends CompatibleState>, CompatibleState> entry :
        compatibleStates.entrySet()) {
      // May be sorted not in the convenient order: Locks last
      Class<? extends CompatibleState> currentClass = entry.getKey();
      CompatibleState currentState = entry.getValue();
      if (currentState != null) {
        // Revert order to negate the result:
        // Usages without locks are more convenient to analyze
        result = pO.compatibleStates.get(currentClass).compareTo(currentState);
        if (result != 0) {
          return result;
        }
      }
    }

    result = this.core.line.compareTo(pO.core.line);
    if (result != 0) {
      return result;
    }
    result = this.core.accessType.compareTo(pO.core.accessType);
    if (result != 0) {
      return result;
    }
    /* We can't use key states for ordering, because the treeSets can't understand,
     * that old refined usage with zero key state is the same as new one
     */
    if (this.core.id != null && pO.core.id != null) {
      // Identifiers may not be equal here:
      // if (a.b > c.b)
      // FieldIdentifiers are the same (when we add to container),
      // but full identifiers (here) are not equal
      // TODO should we distinguish them?

    }
    return 0;
  }

  public UsageInfo copy() {
    UsageInfo result = new UsageInfo(core);
    result.compatibleStates.putAll(this.compatibleStates);
    return result;
  }

  public UsageInfo expand(LockState expandedState) {
    UsageInfo result = copy();

    result.compatibleStates.put(LockState.class, expandedState);
    return result;
  }

  public AbstractLockState getLockState() {
    for (CompatibleState state : compatibleStates.values()) {
      if (state instanceof AbstractLockState) {
        return (AbstractLockState) state;
      }
    }
    return null;
  }

  public UsagePoint createUsagePoint() {
    List<CompatibleNode> nodes =
        from(compatibleStates.values()).transform(CompatibleState::getTreeNode).toList();

    return new UsagePoint(nodes, core.accessType);
  }
}
