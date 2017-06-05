/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.ast.visitor;

import org.rcmd.qmapc.ir.ast.QuakeMapNode;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;

/**
 *
 * @author spirit
 */
public class TokenBasedPrintVisitor {
    
    public void print(QuakeMapNode node) {
        switch(node.token.type) {
            case Quake2MapLexer.INTEGER:
                printIntNode(node);
                break;
            default:
                System.out.println("Token type " + node.token.type + " not handled yet.");
        }        
    }
    
    void printIntNode(QuakeMapNode node) {
        System.out.println("INT");
    }
    
}
