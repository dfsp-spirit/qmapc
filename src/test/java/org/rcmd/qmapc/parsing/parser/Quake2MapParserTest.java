/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
    
    public static final String inputWorldSpawnWith2Brushes = "// entity 0\n"
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
                + "// brush 1\n"
                + "{\n"
                + "( -16 -384 -128 ) ( -16 64 -128 ) ( 368 -384 -128 ) spirit2dm9/floor_3 0 96 0 1.000000 1.000000 0 0 0\n"
                + "( -24 -128 -88 ) ( 360 -128 -88 ) ( -24 -128 -216 ) spirit2dm9/floor_3 40 0 0 1.000000 1.000000 0 0 0\n"
                + "( 256 -192 -96 ) ( 256 -192 -224 ) ( 256 256 -96 ) spirit2dm9/bricks_1 0 0 0 1.000000 1.000000 0 0 0\n"
                + "( 344 48 -256 ) ( -40 48 -256 ) ( 344 -400 -256 ) spirit2dm9/floor_3 0 96 0 1.000000 1.000000 0 0 0\n"
                + "( 384 128 -152 ) ( 384 128 -24 ) ( 0 128 -152 ) spirit2dm9/floor_3 32 0 0 1.000000 1.000000 0 0 0\n"
                + "( 288 64 -208 ) ( 288 -384 -208 ) ( 288 64 -80 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 0 0\n"
                + "}\n"
                + "}"; 

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

        q2mp.entityProperty();
    }

    @org.junit.Test
    public void testItProperlyParsesAnEntityLineWithAPoint3DFloatValue() {
        String input = "\"_color\" \"1.0 0.8 0.8\"";

        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.entityProperty();
    }

    @org.junit.Test
    public void testItProperlyParsesAnEntityLineWithAStringIncludingSpecialChars() {
        String input = "\"message\" \"This is a string with ;(){} many special . chars and 234234 digits.s\"";

        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.entityProperty();
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
    public void testItProperlyParsesAWorldspawnEntityWithOneBrush() {
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

    @org.junit.Test
    public void testItProperlyParsesAWorldspawnEntityWithTwoBrushes() {
        
        q2ml = new Quake2MapLexer(Quake2MapParserTest.inputWorldSpawnWith2Brushes);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.q2EntityWithEntityIDComment();
        
        assertEquals(1, q2mp.numEntities);
        assertEquals(2, q2mp.numBrushes);
        assertEquals(12, q2mp.numFaces);
        assertEquals(0, q2mp.numPatchMeshes);

    }

    @org.junit.Test
    public void testItProperlyParsesAPatchMesh() {
        String input = "// entity 10\n"
                + "{\n"
                + "\"type\" \"patchCapped\"\n"
                + "\"classname\" \"func_group\"\n"
                + "// brush 0\n"
                + "{\n"
                + "patchDef2\n"
                + "{\n"
                + "base_floor/clangdark\n"
                + "( 3 3 0 0 0 )\n"
                + "(\n"
                + "( ( 2016 -1408 48 -27 -26 ) ( 2016 -1472 48 -26 -26 ) ( 2080 -1472 48 -26 -27 ) )\n"
                + "( ( 2016 -1472 48 -26 -26 ) ( 2016 -1472 48 -26 -26 ) ( 2016 -1472 48 -26 -26 ) )\n"
                + "( ( 2016 -1472 48 -26 -26 ) ( 2016 -1472 48 -26 -26 ) ( 2016 -1472 48 -26 -26 ) )\n"
                + ")\n"
                + "}\n"
                + "}\n"
                + "// brush 1\n"
                + "{\n"
                + "patchDef2\n"
                + "{\n"
                + "spirit3t2_sweltdm4/mtldrk0\n"
                + "( 3 3 0 0 0 )\n"
                + "(\n"
                + "( ( 2016 -1408 48.000011 0 0 ) ( 2016 -1408 -104 0 -1.187500 ) ( 2016 -1408 -256 0 -2.375000 ) )\n"
                + "( ( 2016 -1472 48.000011 0.500000 0 ) ( 2016 -1472 -104 0.500000 -1.187500 ) ( 2016 -1472 -256 0.500000 -2.375000 ) )\n"
                + "( ( 2080 -1472 48.000011 1 0 ) ( 2080 -1472 -104 1 -1.187500 ) ( 2080 -1472 -256 1 -2.375000 ) )\n"
                + ")\n"
                + "}\n"
                + "}\n"
                + "// brush 2\n"
                + "{\n"
                + "patchDef2\n"
                + "{\n"
                + "base_floor/clangdark\n"
                + "( 3 3 0 0 0 )\n"
                + "(\n"
                + "( ( 2080 -1472 48 -27 -26 ) ( 2144 -1472 48 -26 -26 ) ( 2144 -1408 48 -26 -27 ) )\n"
                + "( ( 2144 -1472 48 -26 -26 ) ( 2144 -1472 48 -26 -26 ) ( 2144 -1472 48 -26 -26 ) )\n"
                + "( ( 2144 -1472 48 -26 -26 ) ( 2144 -1472 48 -26 -26 ) ( 2144 -1472 48 -26 -26 ) )\n"
                + ")\n"
                + "}\n"
                + "}\n"
                + "// brush 3\n"
                + "{\n"
                + "patchDef2\n"
                + "{\n"
                + "spirit3t2_sweltdm4/mtldrk0\n"
                + "( 3 3 0 0 0 )\n"
                + "(\n"
                + "( ( 2080 -1472 48.000011 0 0 ) ( 2080 -1472 -104 0 -1.187500 ) ( 2080 -1472 -256 0 -2.375000 ) )\n"
                + "( ( 2144 -1472 48.000011 0.500000 0 ) ( 2144 -1472 -104 0.500000 -1.187500 ) ( 2144 -1472 -256 0.500000 -2.375000 ) )\n"
                + "( ( 2144 -1408 48.000011 1 0 ) ( 2144 -1408 -104 1 -1.187500 ) ( 2144 -1408 -256 1 -2.375000 ) )\n"
                + ")\n"
                + "}\n"
                + "}\n"
                + "// brush 4\n"
                + "{\n"
                + "patchDef2\n"
                + "{\n"
                + "spirit3t2_evil8_floor/e8clangfloor\n"
                + "( 3 3 0 0 0 )\n"
                + "(\n"
                + "( ( 1216 -96 -256 -27 -26 ) ( 1152 -96 -256 -26 -26 ) ( 1152 -160 -256 -26 -27 ) )\n"
                + "( ( 1152 -96 -256 -26 -26 ) ( 1152 -96 -256 -26 -26 ) ( 1152 -96 -256 -26 -26 ) )\n"
                + "( ( 1152 -96 -256 -26 -26 ) ( 1152 -96 -256 -26 -26 ) ( 1152 -96 -256 -26 -26 ) )\n"
                + ")\n"
                + "}\n"
                + "}\n"
                + "// brush 5\n"
                + "{\n"
                + "patchDef2\n"
                + "{\n"
                + "spirit3t2_sweltdm4/mtldrk0\n"
                + "( 3 3 0 0 0 )\n"
                + "(\n"
                + "( ( 1216 -96 -255.999985 0 0 ) ( 1216 -96 -352 0 -0.750000 ) ( 1216 -96 -448 0 -1.500000 ) )\n"
                + "( ( 1152 -96 -255.999985 0.500000 0 ) ( 1152 -96 -352 0.500000 -0.750000 ) ( 1152 -96 -448 0.500000 -1.500000 ) )\n"
                + "( ( 1152 -160 -255.999985 1 0 ) ( 1152 -160 -352 1 -0.750000 ) ( 1152 -160 -448 1 -1.500000 ) )\n"
                + ")\n"
                + "}\n"
                + "}\n"
                + "// brush 6\n"
                + "{\n"
                + "patchDef2\n"
                + "{\n"
                + "spirit3t2_evil8_floor/e8clangfloor\n"
                + "( 3 3 0 0 0 )\n"
                + "(\n"
                + "( ( 1152 -160 -256 -27 -26 ) ( 1152 -224 -256 -26 -26 ) ( 1216 -224 -256 -26 -27 ) )\n"
                + "( ( 1152 -224 -256 -26 -26 ) ( 1152 -224 -256 -26 -26 ) ( 1152 -224 -256 -26 -26 ) )\n"
                + "( ( 1152 -224 -256 -26 -26 ) ( 1152 -224 -256 -26 -26 ) ( 1152 -224 -256 -26 -26 ) )\n"
                + ")\n"
                + "}\n"
                + "}\n"
                + "// brush 7\n"
                + "{\n"
                + "patchDef2\n"
                + "{\n"
                + "spirit3t2_sweltdm4/mtldrk0\n"
                + "( 3 3 0 0 0 )\n"
                + "(\n"
                + "( ( 1152 -160 -255.999985 0 0 ) ( 1152 -160 -352 0 -0.750000 ) ( 1152 -160 -448 0 -1.500000 ) )\n"
                + "( ( 1152 -224 -255.999985 0.500000 0 ) ( 1152 -224 -352 0.500000 -0.750000 ) ( 1152 -224 -448 0.500000 -1.500000 ) )\n"
                + "( ( 1216 -224 -255.999985 1 0 ) ( 1216 -224 -352 1 -0.750000 ) ( 1216 -224 -448 1 -1.500000 ) )\n"
                + ")\n"
                + "}\n"
                + "}\n"
                + "}";
        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);
        
        assertEquals(0, q2mp.numEntities);
        assertEquals(0, q2mp.numBrushes);
        assertEquals(0, q2mp.numFaces);
        assertEquals(0, q2mp.numPatchMeshes);

        q2mp.q2EntityWithEntityIDComment();
    }
}
