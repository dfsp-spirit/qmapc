/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import org.rcmd.qmapc.ir.parsetree.ParseTree;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.ir.parsetree.TokenNode;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;

/**
 * A visitor that does nothing but to keep track of the number
 * of nodes of certain types that it visits. Useful for testing
 * and getting data for statistics.
 * @author spirit
 */
public class NodeCountVisitor implements INodeStatsVisitor, IParseTreeVisitor, INodeStats {
    
    public int ruleNodeCount = 0;
    public int tokenNodeCount = 0;
    
    public int tokenTypeBrushCount = 0;
    public int tokenTypeBrushFaceCount = 0;
    public int tokenTypeEntityCount = 0;
    public int tokenTypePatchMeshCount = 0;
    public int tokenTypeAnyOtherCount = 0;
    
    @Override
    public void visit(TokenNode t) {
        tokenNodeCount++;
        
        switch (t.token.type) {
            case Quake2MapLexer.DECLARE_BRUSH:
                this.tokenTypeBrushCount++;
                break;
            case Quake2MapLexer.DECLARE_BRUSH_FACE:
                this.tokenTypeBrushFaceCount++;
                break;
            case Quake2MapLexer.DECLARE_ENTITY:
                this.tokenTypeEntityCount++;
                break;
            case Quake2MapLexer.DECLARE_PATCH_MESH:
                this.tokenTypePatchMeshCount++;
                break;
            default:
                this.tokenTypeAnyOtherCount++;
                break;
        }
    }

    @Override
    public void visit(RuleNode r) {
        
        ruleNodeCount++;
        
        for(ParseTree p : r.children) {
            if(p instanceof TokenNode) {
                visit((TokenNode)p);
            }
            else if(p instanceof RuleNode) {
                visit((RuleNode)p);
            }
        }
    }

    @Override
    public int getNumTokenNodes() {
        return this.tokenNodeCount;
    }

    @Override
    public int getNumRuleNodes() {
        return this.ruleNodeCount;
    }
    
}
