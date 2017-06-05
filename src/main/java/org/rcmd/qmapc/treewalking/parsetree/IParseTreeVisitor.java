/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.ir.parsetree.TokenNode;

/**
 *
 * @author spirit
 */
public interface IParseTreeVisitor {
    
    public void visit(TokenNode t);
    
    public void visit(RuleNode r);
    
}
