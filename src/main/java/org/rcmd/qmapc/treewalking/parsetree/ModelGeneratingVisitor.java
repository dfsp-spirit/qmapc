/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rcmd.qmapc.Settings;
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
    
    private static final Logger LOGGER = Logger.getLogger(ModelGeneratingVisitor.class.getName());
    
    private final QuakeMapModel model;
    private final Stack<RuleNode> ruleNodeStack;
       
    
    public ModelGeneratingVisitor() {
        this.model = new QuakeMapModel();
        this.ruleNodeStack = new Stack<>();
    }

    @Override
    public String visit(TokenNode visitingTokenNode) {
        return visitingTokenNode.token.text;
    }

    @Override
    public String visit(RuleNode visitingRuleNode) {
        ruleNodeStack.add(visitingRuleNode);
        
        switch (visitingRuleNode.name) {
            case Quake2MapParser.RULE_MAP:
                return this.visitMapRuleNode(visitingRuleNode);
            case Quake2MapParser.RULE_ENTITY:
                return this.visitEntityRuleNode(visitingRuleNode);
            default:
                LOGGER.log(Level.SEVERE, "Hit unexpected RuleNode of type '" + visitingRuleNode.name + "' while walking parse tree.");
                return visitingRuleNode.name;
                //System.exit(1);       // TODO: fail here later.
        }                
    }

    @Override
    public QuakeMapModel getMapModel() {
        return this.model;
    }
    
    private String visitMapRuleNode(RuleNode visitingRuleNode) {
        TokenNode t;
        RuleNode r;
        for(ParseTree p : visitingRuleNode.children) {
            if(p instanceof TokenNode) {
                t = ((TokenNode) p);
                System.out.println("visitMapRuleNode: About to visit token node child '" + t.toString() + "' with token '" + t.token + "'.");
                visit((TokenNode)p);
            }
            else if(p instanceof RuleNode) {
                r = ((RuleNode) p);
                System.out.println("visitMapRuleNode: About to visit rule node child: '" + r.toString() + "' with rule '" + r.name + "'.");
                visit((RuleNode)p);
            }
        }
        return null;
    }
    
    private String visitEntityRuleNode(RuleNode visitingRuleNode) {
        
        Entity e = new Entity();
        this.model.addEntity(e);
        
        e.entityID = this.model.getNextFreeEntityIDInLevel();
        // TODO: configure entity with data from children here
        
        TokenNode t;
        RuleNode r;
        for(ParseTree p : visitingRuleNode.children) {
            if(p instanceof TokenNode) {
                t = ((TokenNode) p);
                System.out.println("visitEntityRuleNode: About to visit token node child '" + t.toString() + "' with token '" + t.token + "'.");
                visit((TokenNode)p);
            }
            else if(p instanceof RuleNode) {
                r = ((RuleNode) p);
                System.out.println("visitEntityRuleNode: About to visit rule node child: '" + r.toString() + "' with rule '" + r.name + "'.");
                visit((RuleNode)p);
            }
        }
        
        
        return visitingRuleNode.name;
    }
    
}
