/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.lexer;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.rcmd.qmapc.Main;

/**
 *
 * @author spirit
 */
public class TokenTest {

    

    Token token;
    
    public TokenTest() {
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
        token = null;
    }
    
    @org.junit.Test
    public void testItHasTheConstrcutorValues() {
        token = new Token(Quake2MapLexer.COMMENT, "// Some comment.");
        assertEquals(Quake2MapLexer.COMMENT, token.type);
        assertEquals("// Some comment.", token.text);
    }
    
    @org.junit.Test
    public void testItHasExpectedToStringBehaviour() {
        token = new Token(Quake2MapLexer.COMMENT, "// Some comment.");
        assertEquals("<'// Some comment.',COMMENT>", token.toString());
    }
}