/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc;

import org.rcmd.qmapc.parsing.lexer.Lexer;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;
import org.rcmd.qmapc.parsing.lexer.Token;

/**
 *
 * @author spirit
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Quake2MapLexer q2ml;
        String q2Brush;

        q2Brush = "// brush 0\n"
                + "{\n"
                + "( 248 320 192 ) ( 248 -128 192 ) ( -136 320 192 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 312 192 ) ( -128 312 64 ) ( -128 -136 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 -128 160 ) ( 256 -128 160 ) ( -128 320 160 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -136 -128 64 ) ( -136 -128 192 ) ( 248 -128 64 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 -120 64 ) ( -192 328 64 ) ( -192 -120 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 0 -96 ) ( -128 -64 -96 ) ( -192 0 32 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "}";

        q2ml = new Quake2MapLexer(q2Brush);
        Token token = q2ml.nextToken();
        while (token.type != Lexer.EOF_TYPE) {            
            System.out.println("Received token '" + token + "'.");
            token = q2ml.nextToken();
        }
    }

}
