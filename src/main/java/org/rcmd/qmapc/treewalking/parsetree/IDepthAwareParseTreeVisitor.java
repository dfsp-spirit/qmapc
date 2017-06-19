/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.ir.parsetree.TokenNode;

/**
 *
 * @author spirit
 */
public interface IDepthAwareParseTreeVisitor extends IParseTreeVisitor {
    
    public String visit(TokenNode t, int depth);
    
    public String visit(RuleNode r, int depth);
    
}
