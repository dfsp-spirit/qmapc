/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.ast;

import org.rcmd.qmapc.parsing.lexer.Token;
import org.rcmd.qmapc.treewalking.ast.visitor.TokenBasedPrintVisitor;

/**
 *
 * @author spirit
 */
public abstract class QuakeMapNode extends AbstractSyntaxTree {
    
    public QuakeMapNode() {        
    }
    
    public QuakeMapNode(Token token) {
        this.token = token;
    }
    
    // implement in your non-abstract QuakeMapNode (e.g., BrushNode)
    public abstract void visit(TokenBasedPrintVisitor visitor);
    
}
