/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import org.rcmd.qmapc.ioutil.IOUtil;
import org.rcmd.qmapc.ir.parsetree.ParseTree;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.ir.parsetree.TokenNode;

/**
 *
 * @author spirit
 */
public class NodeBasedPrintVisitor implements IParseTreeVisitor, IDepthAwareParseTreeVisitor {
    
    @Override
    public void visit(RuleNode r, int depth) {
        System.out.println(IOUtil.getSpacesOfDepth(depth) + r.name);
        System.out.println(IOUtil.getSpacesOfDepth(depth) + "(");
        for(ParseTree p : r.children) {
            if(p instanceof TokenNode) {
                visit((TokenNode)p, depth + 1);
            }
            else if(p instanceof RuleNode) {
                visit((RuleNode)p, depth + 1);
            }            
        }
        System.out.println(IOUtil.getSpacesOfDepth(depth) + ")");
    }
    
    @Override
    public void visit(RuleNode r) {
        visit(r, 0);
    }
    
    @Override
    public void visit(TokenNode t) {
        System.out.println(t.token);        
    }
        
    
    @Override
    public void visit(TokenNode t, int depth) {
        System.out.print(IOUtil.getSpacesOfDepth(depth));
        visit(t);
    }
    
}
