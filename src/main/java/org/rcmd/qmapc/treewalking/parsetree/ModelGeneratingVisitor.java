/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import org.rcmd.qmapc.ir.model.quakemap.QuakeMapModel;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.ir.parsetree.TokenNode;

/**
 * A visitor that generates a model while visiting parse tree nodes.
 * @author spirit
 */
public class ModelGeneratingVisitor implements IMapModelGeneratingVisitor, IParseTreeVisitor {
    
    private final QuakeMapModel model;
    
    public ModelGeneratingVisitor() {
        this.model = new QuakeMapModel();
    }

    @Override
    public void visit(TokenNode t) {
        System.err.println("ModelGeneratingVisitor: implement me");
    }

    @Override
    public void visit(RuleNode r) {
        System.err.println("ModelGeneratingVisitor: implement me");
    }

    @Override
    public QuakeMapModel getMapModel() {
        return this.model;
    }
    
}
