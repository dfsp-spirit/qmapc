/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.ast;

import java.util.ArrayList;
import java.util.List;
import org.rcmd.qmapc.parsing.lexer.Token;

/**
 *
 * @author spirit
 */
public class AbstractSyntaxTree {
    
    public Token token;
    List<AbstractSyntaxTree> children;
    
    public AbstractSyntaxTree(Token token) {
        this.token = token;
    }
    
    public AbstractSyntaxTree() {
    }
    
    public AbstractSyntaxTree(int tokenType) {
        this.token = new Token(tokenType);
    }
    
    public int getNodeType() {
        return token.type;
    }
    
    public Boolean isNil() {
        return (token == null);
    }
    
    public void addChild(AbstractSyntaxTree ast) {
        if(this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(ast);
    }
    
    @Override
    public String toString() {
        return(this.token != null ? token.toString() : "nil");
    }
    
    public String toStringTree() {
        if(this.children == null || this.children.isEmpty()) {
            return this.toString();
        }
        
        StringBuilder sb = new StringBuilder();
        if(!this.isNil()) {
           sb.append("(");
           sb.append(this.toString());
           sb.append("(");
        }
        for(int i = 0; i < children.size(); i++) {
            AbstractSyntaxTree ast = (AbstractSyntaxTree)children.get(i);
            if(i > 0) {
                sb.append(' ');                
            }
            sb.append(ast.toStringTree());
        }
        if(!this.isNil()) {
            sb.append(")");
        }
        return sb.toString();
    }
    
}
