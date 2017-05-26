/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.lexer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.rcmd.qmapc.Main;

/**
 *
 * @author spirit
 */
public class Quake2MapLexerTest {
    
    Quake2MapLexer q2ml;
    String q2Brush;
        
    public Quake2MapLexerTest() {        
    }
    
    @BeforeClass
    public static void setUpClass() {        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        q2Brush = "// brush 0\n" +
"{\n" +
"( 248 320 192 ) ( 248 -128 192 ) ( -136 320 192 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 1 50\n" +
"( -128 312 192 ) ( -128 312 64 ) ( -128 -136 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n" +
"( -128 -128 160 ) ( 256 -128 160 ) ( -128 320 160 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n" +
"( -136 -128 64 ) ( -136 -128 192 ) ( 248 -128 64 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n" +
"( -192 -120 64 ) ( -192 328 64 ) ( -192 -120 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n" +
"( -192 0 -96 ) ( -128 -64 -96 ) ( -192 0 32 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n" +
"}";        
    }
    
    @After
    public void tearDown() {
        q2ml = null;
    }

    /**
     * Test of main method, of class Main.
     */
    @org.junit.Test
    public void testQuake2MapLexer() {
        q2ml = new Quake2MapLexer(q2Brush);
        Token t;
        while (q2ml.nextToken().type != Lexer.EOF_TYPE) {
            t = q2ml.nextToken();
            System.out.println("Received token " + t.type + " '" + q2ml.getTokenName(t.type) + "' from text '" + t.text + "'.");
        }
        // Consume EOF token
        t = q2ml.nextToken();
        System.out.println("Received token " + t.type + " '" + q2ml.getTokenName(t.type) + "' from text '" + t.text + "'.");
    }
    
}

    
