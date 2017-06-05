/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import org.rcmd.qmapc.ir.parsetree.ParseTree;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.ir.parsetree.TokenNode;

/**
 *
 * @author spirit
 */
public class NodeBasedPrintVisitor implements IParseTreeVisitor {
    
    @Override
    public void visit(RuleNode r) {
        System.out.print(r.name);
        System.out.print("(");
        for(ParseTree p : r.children) {
            if(p instanceof TokenNode) {
                visit((TokenNode)p);
            }
            else if(p instanceof RuleNode) {
                visit((RuleNode)p);
            }            
        }
        System.out.print(")");
    }
    
    @Override
    public void visit(TokenNode t) {
        System.out.println(t.token);        
    }
    
}
