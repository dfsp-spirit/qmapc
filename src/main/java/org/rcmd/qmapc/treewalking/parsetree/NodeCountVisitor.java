/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import org.rcmd.qmapc.ir.parsetree.ParseTree;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.ir.parsetree.TokenNode;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;
import org.rcmd.qmapc.parsing.parser.Quake2MapParser;

/**
 * A visitor that does nothing but to keep track of the number
 * of nodes of certain types that it visits. Useful for testing
 * and getting data for statistics.
 * @author spirit
 */
public class NodeCountVisitor implements INodeStatsVisitor, IParseTreeVisitor, INodeStats {
    
    public int ruleNodeCount = 0;
    public int tokenNodeCount = 0;
    
    public int ruleTypeBrushCount = 0;
    public int ruleTypeBrushFaceCount = 0;
    public int ruleTypeEntityCount = 0;
    public int ruleTypePatchMeshCount = 0;
    public int ruleTypeAnyOtherCount = 0;
    
    @Override
    public String visit(TokenNode t) {
        tokenNodeCount++;
        
        switch (t.token.type) {
            case Quake2MapLexer.DECLARE_BRUSH:
                this.ruleTypeBrushCount++;
                break;
            case Quake2MapLexer.DECLARE_BRUSH_FACE:
                this.ruleTypeBrushFaceCount++;
                break;
            case Quake2MapLexer.DECLARE_ENTITY:
                this.ruleTypeEntityCount++;
                break;
            case Quake2MapLexer.DECLARE_PATCH_MESH:
                this.ruleTypePatchMeshCount++;
                break;
            default:
                this.ruleTypeAnyOtherCount++;
                break;
        }
        return t.token.text;
    }

    @Override
    public String visit(RuleNode r) {
        
        ruleNodeCount++;                
        
        switch (r.name) {
            case Quake2MapParser.RULE_BRUSH:
                this.ruleTypeBrushCount++;
                break;
            case Quake2MapParser.RULE_BRUSH_FACE:
                this.ruleTypeBrushFaceCount++;
                break;
            case Quake2MapParser.RULE_ENTITY:
                this.ruleTypeEntityCount++;
                break;
            case Quake2MapParser.RULE_BRUSH_PATCHDEF:
                this.ruleTypePatchMeshCount++;
                break;
            default:
                this.ruleTypeAnyOtherCount++;
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
        
        return r.name;
    }

    @Override
    public int getNumTokenNodes() {
        return this.tokenNodeCount;
    }

    @Override
    public int getNumRuleNodes() {
        return this.ruleNodeCount;
    }

    @Override
    public int getNumBrushNodes() {
        return this.ruleTypeBrushCount;
    }

    @Override
    public int getNumEntityNodes() {
        return this.ruleTypeEntityCount;
    }

    @Override
    public int getNumFaceNodes() {
        return this.ruleTypeBrushFaceCount;
    }

    @Override
    public int getNumPatchMeshNodes() {
        return this.ruleTypePatchMeshCount;
    }
    
}
