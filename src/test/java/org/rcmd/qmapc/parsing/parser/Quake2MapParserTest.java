/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.rcmd.qmapc.Main;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;
import org.rcmd.qmapc.parsing.lexer.Token;

/**
 *
 * @author spirit
 */
public class Quake2MapParserTest {
    
    Quake2MapLexer q2ml;
    Quake2MapParser q2mp;
    Token token;
    
    public Quake2MapParserTest() {
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
        q2ml = null;
        q2mp = null;
    }

    @org.junit.Test
    public void testItProperlyParsesAnEntityLineWithAStringValue() {        
        String input = "\"classname\" \"worldspawn\"";
        
        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);
        
        q2mp.entityLineWithValueString();
    }
    
    @org.junit.Test
    public void testItProperlyParsesAnEntityLineWithAPoint3DFloatValue() {        
        String input = "\"_color\" \"1.0 0.8 0.8\"";
        
        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);
        
        q2mp.entityLineWithValuePoint3DFloat();
    }
    
    
    
    
}

    
