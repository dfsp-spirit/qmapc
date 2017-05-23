/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.rcmd.qmapc.parsing.lexer.Lexer;
import org.rcmd.qmapc.parsing.lexer.Token;

/**
 *
 * @author spirit
 */
public class Parser {
    
    Lexer input;
    Token lookahead;
    
    public Parser(Lexer input) {
        this.input = input;
    }
    
    public void match(int x) {
        if(this.lookahead.type == x) {
            consume();
        }
        else {
            throw new Error("[Parser] Expected token '" + this.input.getTokenName(x) + "', but found '" + this.lookahead + "'.");
        }
    }
    
    public void consume() {
        this.lookahead = input.nextToken();
    }
    
}
