/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import java.util.Stack;
import org.rcmd.qmapc.ir.model.quakemap.Entity;
import org.rcmd.qmapc.ir.model.quakemap.Brush;
import org.rcmd.qmapc.ir.model.quakemap.QuakeMapModel;
import org.rcmd.qmapc.ir.parsetree.ParseTree;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.ir.parsetree.TokenNode;
import org.rcmd.qmapc.parsing.parser.Quake2MapParser;

/**
 * A visitor that generates a model while visiting parse tree nodes.
 * @author spirit
 */
public class ModelGeneratingVisitor implements IMapModelGeneratingVisitor, IParseTreeVisitor {
    
    private final QuakeMapModel model;
    private final Stack<RuleNode> ruleNodeStack;
    
    private final Entity currentEntity = null;
    private final Brush currentBrush = null;
    
    public ModelGeneratingVisitor() {
        this.model = new QuakeMapModel();
        this.ruleNodeStack = new Stack<>();
    }

    @Override
    public String visit(TokenNode t) {
        System.err.println("ModelGeneratingVisitor: implement me");
        return t.token.text;
    }

    @Override
    public String visit(RuleNode r) {
        ruleNodeStack.add(r);
        
        switch (r.name) {
            case Quake2MapParser.RULE_BRUSH:
                break;
            case Quake2MapParser.RULE_BRUSH_FACE:
                break;
            case Quake2MapParser.RULE_ENTITY:
                break;
            case Quake2MapParser.RULE_BRUSH_PATCHDEF:
                break;
            default:
                break;
        }
        
        for(ParseTree p : r.children) {
            if(p instanceof TokenNode) {
                visit((TokenNode)p);
            }
            else if(p instanceof RuleNode) {
                visit((RuleNode)p);
            }
        }
        System.err.println("ModelGeneratingVisitor: implement me");
        return r.name;
    }

    @Override
    public QuakeMapModel getMapModel() {
        return this.model;
    }
    
}
