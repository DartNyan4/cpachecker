/*
 *  CPAchecker is a tool for configurable software verification.
 *  This file is part of CPAchecker.
 *
 *  Copyright (C) 2007-2014  Dirk Beyer
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
package org.sosy_lab.cpachecker.core;

import static com.google.common.base.Preconditions.*;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import org.sosy_lab.common.io.PathTemplate;
import org.sosy_lab.cpachecker.core.counterexample.RichModel;
import org.sosy_lab.cpachecker.core.interfaces.PresenceCondition;
import org.sosy_lab.cpachecker.cpa.arg.ARGPath;
import org.sosy_lab.cpachecker.util.Pair;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.Nullable;

public class CounterexampleInfo {

  private final boolean spurious;
  private final boolean isPreciseCounterExample;

  private final ARGPath targetPath;
  private final RichModel model;

  // list with additional information about the counterexample
  private final Collection<Pair<Object, PathTemplate>> furtherInfo;

  private static final CounterexampleInfo SPURIOUS = new CounterexampleInfo(true,
      null, null, false);

  private CounterexampleInfo(boolean pSpurious, ARGPath pTargetPath, RichModel pModel,
      boolean pIsPreciseCEX) {

    spurious = pSpurious;
    targetPath = pTargetPath;
    model = pModel;
    isPreciseCounterExample = pIsPreciseCEX;

    if (!spurious) {
      furtherInfo = Lists.newArrayListWithExpectedSize(1);
    } else {
      furtherInfo = null;
    }
  }

  public static CounterexampleInfo spurious() {
    return SPURIOUS;
  }

  public Optional<PresenceCondition> getPresenceCondition() {
    if (furtherInfo != null) {
      for (Object o: furtherInfo) {
        if (o instanceof PresenceCondition) {
          return Optional.of((PresenceCondition) o);
        }
      }
    }
    return Optional.absent();
  }

  public boolean isPreciseCounterExample() {
    checkState(!spurious);
    return isPreciseCounterExample;
  }

  /**
   * Creates a feasible counterexample whose target path is marked as being imprecise.
   */
  public static CounterexampleInfo feasible(ARGPath pTargetPath, RichModel pModel) {
    return new CounterexampleInfo(false, checkNotNull(pTargetPath), checkNotNull(pModel), false);
  }

  /**
   * Creates a feasible counterexample whose target path is marked as being precise.
   */
  public static CounterexampleInfo feasiblePrecise(ARGPath pTargetPath, RichModel pModel) {
    return new CounterexampleInfo(false, checkNotNull(pTargetPath), checkNotNull(pModel), true);
  }

  public boolean isSpurious() {
    return spurious;
  }

  public ARGPath getTargetPath() {
    checkState(!spurious);
    assert targetPath != null;

    return targetPath;
  }

  public RichModel getTargetPathModel() {
    checkState(!spurious);

    return model;
  }

  /**
   * Add some additional information about the counterexample.
   *
   * @param info The information.
   * @param dumpFile The file where "info.toString()" should be dumped (may be null).
   */
  public void addFurtherInformation(Object info, @Nullable PathTemplate dumpFile) {
    checkState(!spurious);

    furtherInfo.add(Pair.of(checkNotNull(info), dumpFile));
  }

  /**
   * Get all additional information stored in this object.
   * A file where to dump it may be associated with each object, but this part
   * of the pair may be null.
   */
  public Collection<Pair<Object, PathTemplate>> getAllFurtherInformation() {
    checkState(!spurious);

    return Collections.unmodifiableCollection(furtherInfo);
  }
}
