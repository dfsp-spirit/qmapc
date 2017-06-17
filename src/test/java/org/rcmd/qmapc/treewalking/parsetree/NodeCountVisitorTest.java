/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import org.junit.After;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;
import org.rcmd.qmapc.parsing.lexer.Token;
import org.rcmd.qmapc.parsing.parser.Quake2MapParser;
import org.rcmd.qmapc.parsing.parser.Quake2MapParserTest;

/**
 *
 * @author spirit
 */
public class NodeCountVisitorTest {
    
    Quake2MapLexer q2ml;
    Quake2MapParser q2mp;
    Token token;
    
    @After
    public void tearDown() {
        q2ml = null;
        q2mp = null;
    }
    
    @org.junit.Test
    public void testItWalksAParseTreeWithoutErrors() {
        
        q2ml = new Quake2MapLexer(Quake2MapParserTest.inputWorldSpawnWith2Brushes);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.q2EntityWithEntityIDComment();
        
        INodeStatsVisitor visitor = new NodeCountVisitor();
        q2mp.root.visit(visitor);
        
        System.out.println("rule nodes: " + visitor.getNumRuleNodes());
        System.out.println("token nodes: " + visitor.getNumTokenNodes());
    }
    
}
