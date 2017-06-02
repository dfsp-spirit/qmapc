/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.rcmd.qmapc.ir.parsetree.ParseTree;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.parsing.lexer.Lexer;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;

/**
 *
 * @author spirit
 */
public class Quake2MapParser extends Parser {
    

    public Quake2MapParser(Lexer input) {
        super(input, 10);
    }

    public void map() {

        
        if (this.lookaheadTokenType(1) == Quake2MapLexer.ENTITY_ID) {
            q2EntityWithEntityIDComment();
        } else {
            q2EntityWithoutEntityIDComment();
        }
        
    }

    void point3DInteger() {
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
    }

    void point3DFloat() {
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
    }

    void q2BrushFaceLine() {
        bracketedPoint3DInteger();
        bracketedPoint3DInteger();
        bracketedPoint3DInteger();
        match(Quake2MapLexer.PATH_OR_NAME);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
    }

    void q2BrushWithBrushIDComment() {
        match(Quake2MapLexer.BRUSH_ID);
        q2BrushWithoutBrushIDComment();
    }

    void q2BrushWithoutBrushIDComment() {
        match(Quake2MapLexer.CURLYBRACKET_L);
        q2BrushFaceLine();
        while (this.lookaheadTokenType(1) == Quake2MapLexer.ROUNDBRACKET_L) {
            q2BrushFaceLine();
        }
        match(Quake2MapLexer.CURLYBRACKET_R);
    }

    void bracketedPoint3DInteger() {
        match(Quake2MapLexer.ROUNDBRACKET_L);
        point3DInteger();
        match(Quake2MapLexer.ROUNDBRACKET_R);
    }

    void q2EntityWithEntityIDComment() {
        match(Quake2MapLexer.ENTITY_ID);
        q2EntityWithoutEntityIDComment();
    }

    void q2EntityWithoutEntityIDComment() {
        match(Quake2MapLexer.CURLYBRACKET_L);
        while (this.lookaheadTokenType(1) == Quake2MapLexer.QUOTED_STRING || this.lookaheadTokenType(1) == Quake2MapLexer.BRUSH_ID || this.lookaheadTokenType(1) == Quake2MapLexer.CURLYBRACKET_L) {
            if (this.lookaheadTokenType(1) == Quake2MapLexer.QUOTED_STRING) {
                anyEntityKeyValueLine();
            } else if (this.lookaheadTokenType(1) == Quake2MapLexer.BRUSH_ID) {
                q2BrushWithBrushIDComment();
            } else if (this.lookaheadTokenType(1) == Quake2MapLexer.CURLYBRACKET_L) {
                q2BrushWithoutBrushIDComment();
            } else {
                throw new IllegalArgumentException("Hit invalid token '" + this.lookaheadTokenType(1) + "' while parsing an entity. Expected '\"', '(', or '/'.");
            }
        }

        match(Quake2MapLexer.CURLYBRACKET_R);
    }
    
    void anyEntityKeyValueLine() {
        match(Quake2MapLexer.QUOTED_STRING);
        match(Quake2MapLexer.QUOTED_STRING);
    }    
}
