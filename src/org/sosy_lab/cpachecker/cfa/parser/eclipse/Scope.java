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
package org.sosy_lab.cpachecker.cfa.parser.eclipse;

import org.sosy_lab.cpachecker.cfa.ast.c.CFunctionDeclaration;
import org.sosy_lab.cpachecker.cfa.ast.c.CSimpleDeclaration;
import org.sosy_lab.cpachecker.cfa.types.c.CComplexType;

/**
 * Provides a symbol table that maps variable and functions to their declaration
 * (if a name is visible in the current scope).
 */
interface Scope {

  boolean isGlobalScope();

  boolean variableNameInUse(String name, String origName);

  CSimpleDeclaration lookupVariable(String name);

  CFunctionDeclaration lookupFunction(String name);

  /**
   * Look up {@link CComplexType}s by their name.
   * @param name The fully qualified name (e.g., "struct s").
   * @return The CComplexType instance or null.
   */
  CComplexType lookupType(String name);

  void registerDeclaration(CSimpleDeclaration declaration);
}