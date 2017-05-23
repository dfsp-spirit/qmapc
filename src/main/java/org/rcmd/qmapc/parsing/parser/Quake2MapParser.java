/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.rcmd.qmapc.parsing.lexer.Lexer;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;

/**
 *
 * @author spirit
 */
public class Quake2MapParser extends Parser {
    
    public Quake2MapParser(Lexer input) {
        super(input);
    }
    
    public void map() {
        // TODO: implement me
    }
    
    void point3D() {
        match(Quake2MapLexer.ROUNDBRACKET_L);
        numberInt();
        numberInt();
        numberInt();
        match(Quake2MapLexer.ROUNDBRACKET_R);
    }
    
    void numberInt() {
        if(lookahead.type == Quake2MapLexer.INTEGER) {
            match(Quake2MapLexer.INTEGER);
        }
    }
    
}
