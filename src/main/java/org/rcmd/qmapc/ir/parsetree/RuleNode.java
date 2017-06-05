/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.parsetree;

import org.rcmd.qmapc.treewalking.parsetree.IParseTreeVisitor;

/**
 *
 * @author spirit
 */
public class RuleNode extends ParseTree {
    
    public String name;
    
    public RuleNode(String name) {
        this.name = name;
    }
    
    @Override
    public void visit(IParseTreeVisitor visitor) {
        visitor.visit(this);
    }
    
}
