/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.ir.parsetree.TokenNode;

/**
 * Interface for parse tree visitors.
 * @author spirit
 */
public interface IParseTreeVisitor {
    
    public String visit(TokenNode t);
    
    public String visit(RuleNode r);
    
}
