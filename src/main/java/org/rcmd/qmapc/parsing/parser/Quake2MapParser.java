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
        super(input, 10);
    }

    public void map() {
        // TODO: implement me
        if (this.lookaheadTokenType(1) == Quake2MapLexer.DOUBLEQUOTATIONMARKS
                && this.lookaheadTokenType(2) == Quake2MapLexer.PATH_OR_NAME) {

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
    
    void entityKey() {
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
        match(Quake2MapLexer.PATH_OR_NAME);
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
    }
    
    void entityValueString() {
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
        match(Quake2MapLexer.PATH_OR_NAME);
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
    }
    
    void entityLineWithValueString() {
        entityKey();
        entityValueString();
    }
    
    void entityValueFloat() {
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
    }
    
    void entityLineWithValueFloat() {
        entityKey();
        entityValueFloat();
    }
    
    void entityValueInteger() {
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
    }
    
    void entityLineWithValueInteger() {
        entityKey();
        entityValueInteger();
    }
    
    void entityValuePoint3DInteger() {
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
        point3DInteger();
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
    }
    
    void entityLineWithValuePoint3DInteger() {
        entityKey();
        entityValuePoint3DInteger();
    }
    
    void entityValuePoint3DFloat() {
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
        point3DFloat();
        match(Quake2MapLexer.DOUBLEQUOTATIONMARKS);
    }
    
    void entityLineWithValuePoint3DFloat() {
        entityKey();
        entityValuePoint3DFloat();
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
    
    void bracketedPoint3DInteger() {
        match(Quake2MapLexer.ROUNDBRACKET_L);
        point3DInteger();
        match(Quake2MapLexer.ROUNDBRACKET_R);
    }

    

}
