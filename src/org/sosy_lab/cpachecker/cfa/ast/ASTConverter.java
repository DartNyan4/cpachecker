package org.sosy_lab.cpachecker.cfa.ast;

import java.util.ArrayList;
import java.util.List;

import org.sosy_lab.cpachecker.cfa.ast.IASTEnumerationSpecifier.IASTEnumerator;
import org.sosy_lab.cpachecker.exceptions.CFAGenerationRuntimeException;

public class ASTConverter {

  public static IASTExpression convert(org.eclipse.cdt.core.dom.ast.IASTExpression e) {
    assert !(e instanceof IASTExpression);

    if (e == null) {
      return null;
    
    } else if (e instanceof org.eclipse.cdt.core.dom.ast.IASTArraySubscriptExpression) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTArraySubscriptExpression)e);
      
    } else if (e instanceof org.eclipse.cdt.core.dom.ast.IASTBinaryExpression) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTBinaryExpression)e);
      
    } else if (e instanceof org.eclipse.cdt.core.dom.ast.IASTCastExpression) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTCastExpression)e);
      
    } else if (e instanceof org.eclipse.cdt.core.dom.ast.IASTExpressionList) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTExpressionList)e);
      
    } else if (e instanceof org.eclipse.cdt.core.dom.ast.IASTFieldReference) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTFieldReference)e);
            
    } else if (e instanceof org.eclipse.cdt.core.dom.ast.IASTFunctionCallExpression) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTFunctionCallExpression)e);
      
    } else if (e instanceof org.eclipse.cdt.core.dom.ast.IASTIdExpression) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTIdExpression)e);
      
    } else if (e instanceof org.eclipse.cdt.core.dom.ast.IASTLiteralExpression) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTLiteralExpression)e);
      
    } else if (e instanceof org.eclipse.cdt.core.dom.ast.IASTUnaryExpression) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTUnaryExpression)e);
      
    } else if (e instanceof org.eclipse.cdt.core.dom.ast.IASTTypeIdExpression) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTTypeIdExpression)e);
        
    } else {
      throw new CFAGenerationRuntimeException("", e);
    }
  }
  
  private static IASTArraySubscriptExpression convert(org.eclipse.cdt.core.dom.ast.IASTArraySubscriptExpression e) {
    return new IASTArraySubscriptExpression(e.getRawSignature(), convert(e.getFileLocation()), convert(e.getExpressionType()), convert(e.getArrayExpression()), convert(e.getSubscriptExpression()));
  }

  private static IASTBinaryExpression convert(org.eclipse.cdt.core.dom.ast.IASTBinaryExpression e) {
    return new IASTBinaryExpression(e.getRawSignature(), convert(e.getFileLocation()), convert(e.getExpressionType()), convert(e.getOperand1()), convert(e.getOperand2()), e.getOperator());
  }
  
  private static IASTCastExpression convert(org.eclipse.cdt.core.dom.ast.IASTCastExpression e) {
    return new IASTCastExpression(e.getRawSignature(), convert(e.getFileLocation()), convert(e.getExpressionType()), convert(e.getOperand()), convert(e.getTypeId()));
  }
  
  private static IASTExpressionList convert(org.eclipse.cdt.core.dom.ast.IASTExpressionList e) {
    List<IASTExpression> list = new ArrayList<IASTExpression>(e.getExpressions().length);
    for (org.eclipse.cdt.core.dom.ast.IASTExpression c : e.getExpressions()) {
      list.add(convert(c));
    }
    return new IASTExpressionList(e.getRawSignature(), convert(e.getFileLocation()), convert(e.getExpressionType()), list);
  }
  
  private static IASTFieldReference convert(org.eclipse.cdt.core.dom.ast.IASTFieldReference e) {
    return new IASTFieldReference(e.getRawSignature(), convert(e.getFileLocation()), convert(e.getExpressionType()), convert(e.getFieldName()), convert(e.getFieldOwner()), e.isPointerDereference());
  }
  
  private static IASTFunctionCallExpression convert(org.eclipse.cdt.core.dom.ast.IASTFunctionCallExpression e) {
    return new IASTFunctionCallExpression(e.getRawSignature(), convert(e.getFileLocation()), convert(e.getExpressionType()), convert(e.getFunctionNameExpression()), convert(e.getParameterExpression()));
  }
  
  private static IASTIdExpression convert(org.eclipse.cdt.core.dom.ast.IASTIdExpression e) {
    return new IASTIdExpression(e.getRawSignature(), convert(e.getFileLocation()), convert(e.getExpressionType()), convert(e.getName()));
  }

  private static IASTLiteralExpression convert(org.eclipse.cdt.core.dom.ast.IASTLiteralExpression e) {
    return new IASTLiteralExpression(e.getRawSignature(), convert(e.getFileLocation()), convert(e.getExpressionType()), e.getKind(), String.valueOf(e.getValue()));
  }

  private static IASTUnaryExpression convert(org.eclipse.cdt.core.dom.ast.IASTUnaryExpression e) {
    return new IASTUnaryExpression(e.getRawSignature(), convert(e.getFileLocation()), convert(e.getExpressionType()), convert(e.getOperand()), e.getOperator());
  }

  private static IASTTypeIdExpression convert(org.eclipse.cdt.core.dom.ast.IASTTypeIdExpression e) {
    return new IASTTypeIdExpression(e.getRawSignature(), convert(e.getFileLocation()), convert(e.getExpressionType()), e.getOperator(), convert(e.getTypeId()));
  }
  
  
  public static IASTStatement convert(org.eclipse.cdt.core.dom.ast.IASTStatement s) {
    return new IASTStatement(s.getRawSignature(), convert(s.getFileLocation()));
  }
  
  
  private static IASTDeclaration convert(org.eclipse.cdt.core.dom.ast.IASTDeclaration d) {
    if (d instanceof org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition)d);

    } else if (d instanceof org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration)d);
    
    } else {
      throw new CFAGenerationRuntimeException("", d);
    }
  }
  
  public static IASTFunctionDefinition convert(org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition f) {
    return new IASTFunctionDefinition(f.getRawSignature(), convert(f.getFileLocation()), convert(f.getDeclSpecifier()), convert(f.getDeclarator()));
  }
  
  public static IASTSimpleDeclaration convert(org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration d) {
    List<IASTDeclarator> list = new ArrayList<IASTDeclarator>(d.getDeclarators().length);
    for (org.eclipse.cdt.core.dom.ast.IASTDeclarator c : d.getDeclarators()) {
      list.add(convert(c));
    }
    return new IASTSimpleDeclaration(d.getRawSignature(), convert(d.getFileLocation()), convert(d.getDeclSpecifier()), list);
  }
  
  
  private static IASTDeclarator convert(org.eclipse.cdt.core.dom.ast.IASTDeclarator d) {
    if (d == null) {
      return null;
    
    } else if (d instanceof org.eclipse.cdt.core.dom.ast.IASTArrayDeclarator) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTArrayDeclarator)d);
      
    } else if (d instanceof org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator)d);
    
    } else {
      List<IASTPointerOperator> list = new ArrayList<IASTPointerOperator>(d.getPointerOperators().length);
      for (org.eclipse.cdt.core.dom.ast.IASTPointerOperator c : d.getPointerOperators()) {
        list.add(convert(c));
      }
      return new IASTVariableDeclarator(d.getRawSignature(), convert(d.getFileLocation()), convert(d.getInitializer()), convert(d.getName()), convert(d.getNestedDeclarator()), list);
    }
  }
  
  private static IASTArrayDeclarator convert(org.eclipse.cdt.core.dom.ast.IASTArrayDeclarator d)  {
    List<IASTArrayModifier> arrayList = new ArrayList<IASTArrayModifier>(d.getArrayModifiers().length);
    for (org.eclipse.cdt.core.dom.ast.IASTArrayModifier c : d.getArrayModifiers()) {
      arrayList.add(convert(c));
    }
    List<IASTPointerOperator> pointerList = new ArrayList<IASTPointerOperator>(d.getPointerOperators().length);
    for (org.eclipse.cdt.core.dom.ast.IASTPointerOperator c : d.getPointerOperators()) {
      pointerList.add(convert(c));
    }
    return new IASTArrayDeclarator(d.getRawSignature(), convert(d.getFileLocation()), convert(d.getInitializer()), convert(d.getName()), convert(d.getNestedDeclarator()), pointerList, arrayList);
  }
  
  private static IASTFunctionDeclarator convert(org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator d) {
    assert d instanceof org.eclipse.cdt.core.dom.ast.IASTStandardFunctionDeclarator;
    
    List<IASTPointerOperator> pointerList = new ArrayList<IASTPointerOperator>(d.getPointerOperators().length);
    for (org.eclipse.cdt.core.dom.ast.IASTPointerOperator c : d.getPointerOperators()) {
      pointerList.add(convert(c));
    }
    
    org.eclipse.cdt.core.dom.ast.IASTStandardFunctionDeclarator sd = (org.eclipse.cdt.core.dom.ast.IASTStandardFunctionDeclarator)d;
    List<IASTParameterDeclaration> paramsList = new ArrayList<IASTParameterDeclaration>(sd.getParameters().length);
    for (org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration c : sd.getParameters()) {
      paramsList.add(convert(c));
    }
    return new IASTStandardFunctionDeclarator(d.getRawSignature(), convert(d.getFileLocation()), convert(d.getInitializer()), convert(d.getName()), convert(d.getNestedDeclarator()), pointerList, paramsList, sd.takesVarArgs());
  }
  
  
  private static IASTDeclSpecifier convert(org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier d) {
    if (d instanceof org.eclipse.cdt.core.dom.ast.IASTCompositeTypeSpecifier) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTCompositeTypeSpecifier)d);
    
    } else if (d instanceof org.eclipse.cdt.core.dom.ast.IASTElaboratedTypeSpecifier) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTElaboratedTypeSpecifier)d);
      
    } else if (d instanceof org.eclipse.cdt.core.dom.ast.IASTEnumerationSpecifier) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTEnumerationSpecifier)d);
      
    } else if (d instanceof org.eclipse.cdt.core.dom.ast.IASTNamedTypeSpecifier) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTNamedTypeSpecifier)d);
      
    } else if (d instanceof org.eclipse.cdt.core.dom.ast.IASTSimpleDeclSpecifier) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTSimpleDeclSpecifier)d);
           
    } else {
      throw new CFAGenerationRuntimeException("", d);
    }
  }
  
  private static IASTCompositeTypeSpecifier convert(org.eclipse.cdt.core.dom.ast.IASTCompositeTypeSpecifier d) {
    List<IASTDeclaration> list = new ArrayList<IASTDeclaration>(d.getMembers().length);
    for (org.eclipse.cdt.core.dom.ast.IASTDeclaration c : d.getMembers()) {
      list.add(convert(c));
    }
    return new IASTCompositeTypeSpecifier(d.getRawSignature(), convert(d.getFileLocation()), d.getStorageClass(), d.isConst(), d.isInline(), d.isVolatile(), d.getKey(), list, convert(d.getName()));
  }
  
  private static IASTElaboratedTypeSpecifier convert(org.eclipse.cdt.core.dom.ast.IASTElaboratedTypeSpecifier d) {
    return new IASTElaboratedTypeSpecifier(d.getRawSignature(), convert(d.getFileLocation()), d.getStorageClass(), d.isConst(), d.isInline(), d.isVolatile(), d.getKind(), convert(d.getName()));
  }
  
  private static IASTEnumerationSpecifier convert(org.eclipse.cdt.core.dom.ast.IASTEnumerationSpecifier d) {
    List<IASTEnumerator> list = new ArrayList<IASTEnumerator>(d.getEnumerators().length);
    for (org.eclipse.cdt.core.dom.ast.IASTEnumerationSpecifier.IASTEnumerator c : d.getEnumerators()) {
      list.add(convert(c));
    }
    return new IASTEnumerationSpecifier(d.getRawSignature(), convert(d.getFileLocation()), d.getStorageClass(), d.isConst(), d.isInline(), d.isVolatile(), list, convert(d.getName()));
  }
  
  private static IASTNamedTypeSpecifier convert(org.eclipse.cdt.core.dom.ast.IASTNamedTypeSpecifier d) {
    return new IASTNamedTypeSpecifier(d.getRawSignature(), convert(d.getFileLocation()), d.getStorageClass(), d.isConst(), d.isInline(), d.isVolatile(), convert(d.getName()));
  }
  
  private static IASTSimpleDeclSpecifier convert(org.eclipse.cdt.core.dom.ast.IASTSimpleDeclSpecifier d) {
    return new IASTSimpleDeclSpecifier(d.getRawSignature(), convert(d.getFileLocation()), d.getStorageClass(), d.isConst(), d.isInline(), d.isVolatile(), d.getType(), d.isLong(), d.isShort(), d.isSigned(), d.isUnsigned());
  }
  
  
  private static IASTArrayModifier convert(org.eclipse.cdt.core.dom.ast.IASTArrayModifier m) {
    return new IASTArrayModifier(m.getRawSignature(), convert(m.getFileLocation()), convert(m.getConstantExpression()));
  }
  
  private static IASTEnumerator convert(org.eclipse.cdt.core.dom.ast.IASTEnumerationSpecifier.IASTEnumerator e) {
    return new IASTEnumerator(e.getRawSignature(), convert(e.getFileLocation()), convert(e.getName()), convert(e.getValue()));
  }

  private static IASTInitializer convert(org.eclipse.cdt.core.dom.ast.IASTInitializer i) {
    if (i == null) {
      return null;
    
    } else if (i instanceof org.eclipse.cdt.core.dom.ast.IASTInitializerExpression) {
      return convert((org.eclipse.cdt.core.dom.ast.IASTInitializerExpression)i);
    } else {
      throw new CFAGenerationRuntimeException("", i);
    }
  }
  
  private static IASTInitializerExpression convert(org.eclipse.cdt.core.dom.ast.IASTInitializerExpression i) {
    return new IASTInitializerExpression(i.getRawSignature(), convert(i.getFileLocation()), convert(i.getExpression()));
  }
  
  public static IASTParameterDeclaration convert(org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration p) {
    return new IASTParameterDeclaration(p.getRawSignature(), convert(p.getFileLocation()), convert(p.getDeclSpecifier()), convert(p.getDeclarator()));
  }
  
  private static IASTPointerOperator convert(org.eclipse.cdt.core.dom.ast.IASTPointerOperator o) {
    assert o instanceof org.eclipse.cdt.core.dom.ast.IASTPointer;
    
    org.eclipse.cdt.core.dom.ast.IASTPointer p = (org.eclipse.cdt.core.dom.ast.IASTPointer)o;
    return new IASTPointer(p.getRawSignature(), convert(p.getFileLocation()), p.isConst(), p.isVolatile());
  }
  
  private static IASTFileLocation convert(org.eclipse.cdt.core.dom.ast.IASTFileLocation l) {
    if (l == null) {
      return null;
    }
    return new IASTFileLocation(l.getEndingLineNumber(), l.getFileName(), l.getNodeLength(), l.getNodeOffset(), l.getStartingLineNumber());
  }
  
  private static IASTName convert(org.eclipse.cdt.core.dom.ast.IASTName n) {
    return new IASTName(n.getRawSignature(), convert(n.getFileLocation()));
  }
  
  private static IASTTypeId convert(org.eclipse.cdt.core.dom.ast.IASTTypeId t) {
    return new IASTTypeId(t.getRawSignature(), convert(t.getFileLocation()), convert(t.getAbstractDeclarator()), convert(t.getDeclSpecifier()));
  }
  
  
  private static IType convert(org.eclipse.cdt.core.dom.ast.IType t) {
    if (t instanceof org.eclipse.cdt.core.dom.ast.IBasicType) {
      return convert((org.eclipse.cdt.core.dom.ast.IBasicType)t);
 
    } else if (t instanceof org.eclipse.cdt.core.dom.ast.IPointerType) {
      return convert((org.eclipse.cdt.core.dom.ast.IPointerType)t);
      
    } else if (t instanceof org.eclipse.cdt.core.dom.ast.ITypedef) {
      return convert((org.eclipse.cdt.core.dom.ast.ITypedef)t);
      
    } else {
      return null;
      //throw new CFAGenerationRuntimeException("Unknown type " + t.toString());
    }
  }
  
  private static IBasicType convert(org.eclipse.cdt.core.dom.ast.IBasicType t) {
    assert t instanceof org.eclipse.cdt.core.dom.ast.c.ICBasicType;
    org.eclipse.cdt.core.dom.ast.c.ICBasicType c = (org.eclipse.cdt.core.dom.ast.c.ICBasicType)t;
    
    try {
      return new ICBasicType(t.getType(), t.isLong(), t.isShort(), t.isSigned(), t.isUnsigned(), c.isComplex(), c.isImaginary(), c.isLongLong());
    } catch (org.eclipse.cdt.core.dom.ast.DOMException e) {
      throw new CFAGenerationRuntimeException(e.getMessage());
    }
  }

  private static IPointerType convert(org.eclipse.cdt.core.dom.ast.IPointerType t) {
    try {
      return new IPointerType(convert(t.getType()), t.isConst(), t.isVolatile());
    } catch (org.eclipse.cdt.core.dom.ast.DOMException e) {
      throw new CFAGenerationRuntimeException(e.getMessage());
    }
  }
  
  private static ITypedef convert(org.eclipse.cdt.core.dom.ast.ITypedef t) {
    try {
      return new ITypedef(t.getName(), convert(t.getType()));
    } catch (org.eclipse.cdt.core.dom.ast.DOMException e) {
      throw new CFAGenerationRuntimeException(e.getMessage());
    }
  }
}
