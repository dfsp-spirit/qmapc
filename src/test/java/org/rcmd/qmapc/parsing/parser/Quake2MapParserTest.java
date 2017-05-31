/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

        q2mp.anyEntityKeyValueLine();
    }

    @org.junit.Test
    public void testItProperlyParsesAnEntityLineWithAPoint3DFloatValue() {
        String input = "\"_color\" \"1.0 0.8 0.8\"";

        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.anyEntityKeyValueLine();
    }

    @org.junit.Test
    public void testItProperlyParsesAnEntityLineWithAStringIncludingSpecialChars() {
        String input = "\"message\" \"This is a string with ;(){} many special . chars and 234234 digits.s\"";

        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.anyEntityKeyValueLine();
    }

    @org.junit.Test
    public void testItProperlyParsesAQuake2BrushWithBrushIDComment() {
        String input = "// brush 0\n"
                + "{\n"
                + "( 248 320 192 ) ( 248 -128 192 ) ( -136 320 192 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 312 192 ) ( -128 312 64 ) ( -128 -136 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 -128 160 ) ( 256 -128 160 ) ( -128 320 160 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -136 -128 64 ) ( -136 -128 192 ) ( 248 -128 64 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 -120 64 ) ( -192 328 64 ) ( -192 -120 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 0 -96 ) ( -128 -64 -96 ) ( -192 0 32 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "}";

        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.q2BrushWithBrushIDComment();
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testItThrowsAnExceptionWhenParsingAnInvalidBrush() {
        String input = "// brush 0\n"
                + "{\n"
                + "( 248 ) ( 248 -128 192 ) ( -136 320 192 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 1 50\n"
                + "}";

        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.q2BrushWithBrushIDComment();
    }

    @org.junit.Test
    public void testItPoperlyParsesAWorldspawnEntity() {
        String input = "// entity 0\n"
                + "{\n"
                + "\"classname\" \"worldspawn\"\n"
                + "\"message\" \"spirit2dm9 - The cake is a lie\"\n"
                + "\"_ambient\" \"5\"\n"
                + "\"_sun\" \"star\"\n"
                + "\"_sun_ambient\" \"5 5 5\"\n"
                + "\"_sun_color\" \"1.0 0.8 0.8\"\n"
                + "\"_sun_light\" \"240\"\n"
                + "\"sky\" \"spirit2dm9/phobos\"\n"
                + "\"_color\" \"1.0 0.8 0.8\"\n"
                + "\"_sun_diffuse\" \"120\"\n"
                + "\"_sun_diffwait\" \"1\"\n"
                + "// brush 0\n"
                + "{\n"
                + "( 248 320 192 ) ( 248 -128 192 ) ( -136 320 192 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 312 192 ) ( -128 312 64 ) ( -128 -136 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 -128 160 ) ( 256 -128 160 ) ( -128 320 160 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -136 -128 64 ) ( -136 -128 192 ) ( 248 -128 64 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 -120 64 ) ( -192 328 64 ) ( -192 -120 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 0 -96 ) ( -128 -64 -96 ) ( -192 0 32 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "}\n"
                + "}";
        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.q2EntityWithEntityIDComment();

    }
}
