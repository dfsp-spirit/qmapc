/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.ast;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;
import org.rcmd.qmapc.parsing.lexer.Token;

/**
 *
 * @author spirit
 */
public class AbstractSyntaxTreeTest {
    
    AbstractSyntaxTree astRoot;
    
    public AbstractSyntaxTreeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
        astRoot = null;
    }

    @org.junit.Test
    public void testItCanBeConstructed() {
        
        astRoot = new AbstractSyntaxTree(new Token(Quake2MapLexer.DECLARE_MAP, "map"));
        AbstractSyntaxTree declareEntity1 = new AbstractSyntaxTree(new Token(Quake2MapLexer.DECLARE_ENTITY, "e1"));
        AbstractSyntaxTree declareEntity2 = new AbstractSyntaxTree(new Token(Quake2MapLexer.DECLARE_ENTITY, "e2"));
        AbstractSyntaxTree declareEntity3 = new AbstractSyntaxTree(new Token(Quake2MapLexer.DECLARE_ENTITY, "e3"));
        astRoot.addChild(declareEntity1);
        astRoot.addChild(declareEntity2);
        astRoot.addChild(declareEntity3);
        
        AbstractSyntaxTree declareEntity1Brush1 = new AbstractSyntaxTree(new Token(Quake2MapLexer.DECLARE_BRUSH, "e1b1"));
        AbstractSyntaxTree declareEntity1Brush2 = new AbstractSyntaxTree(new Token(Quake2MapLexer.DECLARE_BRUSH, "e1b1"));
        declareEntity1.addChild(declareEntity1Brush1);
        declareEntity1.addChild(declareEntity1Brush2);
        
        AbstractSyntaxTree declareEntity2Prop1 = new AbstractSyntaxTree(new Token(Quake2MapLexer.DECLARE_ENTITY_PROPERTYLINE, "e2p1"));
        AbstractSyntaxTree declareEntity2Prop2 = new AbstractSyntaxTree(new Token(Quake2MapLexer.DECLARE_ENTITY_PROPERTYLINE, "e2p2"));
        AbstractSyntaxTree declareEntity2Prop3 = new AbstractSyntaxTree(new Token(Quake2MapLexer.DECLARE_ENTITY_PROPERTYLINE, "e2p3"));
        declareEntity2.addChild(declareEntity2Prop1);
        declareEntity2.addChild(declareEntity2Prop2);
        declareEntity2.addChild(declareEntity2Prop3);
        
        AbstractSyntaxTree declareEntity3Prop1 = new AbstractSyntaxTree(new Token(Quake2MapLexer.DECLARE_ENTITY_PROPERTYLINE, "e3p1"));
        declareEntity3.addChild(declareEntity3Prop1);
        
        String treeStringRep = astRoot.toStringTree();
        assertTrue("String representation must start with known pattern.", treeStringRep.startsWith("(<'map'"));
        assertTrue("String representation must end with known pattern.", treeStringRep.endsWith("DECLARE_ENTITY>(<'e3p1',DECLARE_ENTITY_PROPERTYLINE>))"));
    }
    
}
