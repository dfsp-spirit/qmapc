/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.lexer;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author spirit
 */
public class Quake2MapLexerTest {

    Quake2MapLexer q2ml;
    String inputIntegerUnsigned, inputIntegerNegative, inputFloatUnsigned, inputFloatNegative, inputPoint, inputQ2Brush, inputComment, inputCommentBrushID, inputCommentEntityID, inputQ2EntityKeyValueLine, inputQ2SimpleEntityWithoutBrush, inputQuotedString, inputQ2ComplexEntityWithBrush;
    Token token;
    List<Token> tokenList;

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
        tokenList = new ArrayList<>();
        inputIntegerUnsigned = "134";
        inputIntegerNegative = "-134234234";
        inputFloatUnsigned = "1.0034";
        inputFloatNegative = "-21.003400";
        inputPoint = "( 123 34 211 )";
        inputComment = "// this is just a comment";
        inputCommentBrushID = "// brush 23";
        inputCommentEntityID = "// entity 3";
        inputQuotedString = "\"I am a string with (){}; many special characters 1.0 and even an int 5 .\"";
        inputQ2Brush = "// brush 0\n"
                + "{\n"
                + "( 248 320 192 ) ( 248 -128 192 ) ( -136 320 192 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 312 192 ) ( -128 312 64 ) ( -128 -136 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 -128 160 ) ( 256 -128 160 ) ( -128 320 160 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -136 -128 64 ) ( -136 -128 192 ) ( 248 -128 64 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 -120 64 ) ( -192 328 64 ) ( -192 -120 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 0 -96 ) ( -128 -64 -96 ) ( -192 0 32 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "}";
        inputQ2EntityKeyValueLine = "\"origin\" \"24 -344 40\"\n";
        inputQ2SimpleEntityWithoutBrush = "// entity 81\n"
                + "{\n"
                + "\"angle\" \"90\"\n"
                + "\"origin\" \"24 -344 40\"\n"
                + "\"classname\" \"ammo_shells\"\n"
                + "}\n";
        inputQ2ComplexEntityWithBrush = "// entity 19\n"
                + "{\n"
                + "\"classname\" \"func_plat\"\n"
                + "\"speed\" \"150\"\n"
                + "\"sounds\" \"base\"\n"
                + "\"angle\" \"-1\"\n"
                + "\"accel\" \"500\"\n"
                + "\"height\" \"154\"\n"
                + "// brush 0\n"
                + "{\n"
                + "( 832 -160 672 ) ( 832 -288 672 ) ( 704 -160 672 ) spirit2dm9/concrete_tile -64 -32 0 1.000000 1.000000 0 0 0\n"
                + "( 832 -160 672 ) ( 704 -160 672 ) ( 832 -160 656 ) spirit2dm9/concrete_tile -64 -32 0 1.000000 1.000000 0 0 0\n"
                + "( 832 -160 672 ) ( 832 -160 656 ) ( 832 -288 672 ) spirit2dm9/concrete_tile -64 -32 0 1.000000 1.000000 0 0 0\n"
                + "( 704 -288 656 ) ( 832 -288 656 ) ( 704 -160 656 ) spirit2dm9/threadplate3_hq 0 0 0 1.000000 1.000000 0 0 0\n"
                + "( 704 -288 656 ) ( 704 -288 672 ) ( 832 -288 656 ) spirit2dm9/concrete_tile -64 -32 0 1.000000 1.000000 0 0 0\n"
                + "( 704 -288 656 ) ( 704 -160 656 ) ( 704 -288 672 ) spirit2dm9/threadplate3_hq 0 0 0 1.000000 1.000000 0 0 0\n"
                + "}\n"
                + "}";
    }

    @After
    public void tearDown() {
        q2ml = null;
    }

    @org.junit.Test
    public void testItLexesValidIntegerUnsigned() {
        q2ml = new Quake2MapLexer(inputIntegerUnsigned);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(1, tokenList.size());
        assertEquals(Quake2MapLexer.INTEGER, tokenList.get(0).type);
    }

    @org.junit.Test
    public void testItLexesValidIntegerNegative() {
        q2ml = new Quake2MapLexer(inputIntegerNegative);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(1, tokenList.size());
        assertEquals(Quake2MapLexer.INTEGER, tokenList.get(0).type);
    }

    @org.junit.Test
    public void testItLexesValidFloatUnsigned() {
        q2ml = new Quake2MapLexer(inputFloatUnsigned);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(1, tokenList.size());
        assertEquals(Quake2MapLexer.FLOAT, tokenList.get(0).type);
    }

    @org.junit.Test
    public void testItLexesValidFloatNegative() {
        q2ml = new Quake2MapLexer(inputFloatNegative);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(1, tokenList.size());
        assertEquals(Quake2MapLexer.FLOAT, tokenList.get(0).type);
    }

    @org.junit.Test
    public void testItLexesValidComment() {
        q2ml = new Quake2MapLexer(inputComment);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(1, tokenList.size());
        assertEquals(Quake2MapLexer.COMMENT, tokenList.get(0).type);
    }

    @org.junit.Test
    public void testItLexesValidCommentWithBrushID() {
        q2ml = new Quake2MapLexer(inputCommentBrushID);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(1, tokenList.size());
        assertEquals(Quake2MapLexer.BRUSH_ID, tokenList.get(0).type);
        assertEquals("23", tokenList.get(0).text);
    }

    @org.junit.Test
    public void testItLexesValidCommentWithEntityID() {
        q2ml = new Quake2MapLexer(inputCommentEntityID);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(1, tokenList.size());
        assertEquals(Quake2MapLexer.ENTITY_ID, tokenList.get(0).type);
        assertEquals("3", tokenList.get(0).text);
    }

    @org.junit.Test
    public void testItLexesValidPoint() {
        q2ml = new Quake2MapLexer(inputPoint);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(5, tokenList.size());
        assertEquals(Quake2MapLexer.ROUNDBRACKET_L, tokenList.get(0).type);
        assertEquals(Quake2MapLexer.INTEGER, tokenList.get(1).type);
        assertEquals(Quake2MapLexer.INTEGER, tokenList.get(2).type);
        assertEquals(Quake2MapLexer.INTEGER, tokenList.get(3).type);
        assertEquals(Quake2MapLexer.ROUNDBRACKET_R, tokenList.get(4).type);
    }

    @org.junit.Test
    public void testItLexesValidQuake2Brush() {
        q2ml = new Quake2MapLexer(inputQ2Brush);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(147, tokenList.size());
    }

    @org.junit.Test
    public void testItLexesValidQuake2EntityLine() {
        q2ml = new Quake2MapLexer(inputQ2EntityKeyValueLine);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(2, tokenList.size());
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(0).type);
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(1).type);
    }

    @org.junit.Test
    public void testItLexesValidQuake2SimpleEntityWithoutIncludedBrush() {
        q2ml = new Quake2MapLexer(inputQ2SimpleEntityWithoutBrush);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(9, tokenList.size());
        assertEquals(Quake2MapLexer.ENTITY_ID, tokenList.get(0).type);
        assertEquals(Quake2MapLexer.CURLYBRACKET_L, tokenList.get(1).type);

        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(2).type);
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(3).type);

        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(4).type);
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(5).type);

        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(6).type);
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(7).type);

        assertEquals(Quake2MapLexer.CURLYBRACKET_R, tokenList.get(8).type);
    }

    @org.junit.Test
    public void testItLexesValidQuotedString() {
        q2ml = new Quake2MapLexer(inputQuotedString);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(1, tokenList.size());
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(0).type);
    }
    
    @org.junit.Test
    public void testItLexesValidQuake2ComplexEntityWithIncludedBrush() {
        q2ml = new Quake2MapLexer(inputQ2ComplexEntityWithBrush);
        token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = q2ml.nextToken();
        }
        assertEquals(162, tokenList.size());
        assertEquals(Quake2MapLexer.ENTITY_ID, tokenList.get(0).type);
        assertEquals(Quake2MapLexer.CURLYBRACKET_L, tokenList.get(1).type);

        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(2).type);
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(3).type);

        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(4).type);
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(5).type);

        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(6).type);
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(7).type);
        
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(8).type);
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(9).type);

        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(10).type);
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(11).type);

        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(12).type);
        assertEquals(Quake2MapLexer.QUOTED_STRING, tokenList.get(13).type);
        
        assertEquals(Quake2MapLexer.BRUSH_ID, tokenList.get(14).type);
        assertEquals(Quake2MapLexer.CURLYBRACKET_L, tokenList.get(15).type);
        
        assertEquals(Quake2MapLexer.ROUNDBRACKET_L, tokenList.get(16).type);
        assertEquals(Quake2MapLexer.INTEGER, tokenList.get(17).type);
        assertEquals(Quake2MapLexer.INTEGER, tokenList.get(18).type);
        assertEquals(Quake2MapLexer.INTEGER, tokenList.get(19).type);
        assertEquals(Quake2MapLexer.ROUNDBRACKET_R, tokenList.get(20).type);
        
        // Skip checks for rest of the face line (and for 5 more brush face lines) here.
        
        assertEquals(Quake2MapLexer.CURLYBRACKET_R, tokenList.get(tokenList.size() - 2).type);                
        assertEquals(Quake2MapLexer.CURLYBRACKET_R, tokenList.get(tokenList.size() - 1).type);
    }
}
