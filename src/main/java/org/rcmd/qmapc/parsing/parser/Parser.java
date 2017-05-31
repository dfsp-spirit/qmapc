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
    Token[] lookahead;
    int k;  // Size of lookahead buffer
    int p = 0;  // Current position in buffer
    
    public Parser(Lexer input, int k) {
        this.input = input;
        this.k = k;
        lookahead = new Token[k];
        
        // prime buffer
        for(int i = 1; i <= k; i++) {
           consume();
        }
    }
    
    public Token lookaheadToken(int i) {
        return lookahead[(p + i - 1) % k];
    }
    
    public int lookaheadTokenType(int i) {
        return this.lookaheadToken(i).type;
    }
    
    public void match(int tokenType) {
        if(this.lookaheadTokenType(1) == tokenType) {
            System.out.println("Matched token of type '" + this.input.getTokenName(tokenType) + "'.");
            consume();
        }
        else {
            throw new IllegalArgumentException("[Parser] Expected token '" + this.input.getTokenName(tokenType) + "', but found '" + this.input.getTokenName(this.lookaheadTokenType(1)) + "'. Position was " + this.input.getCurrentPositionInInput() + ", last 100 chars: '" + this.input.getLastCharsUpTo(100) + "'.");
        }
    }
    
    public final void consume() {
        this.lookahead[p] = input.nextToken();
        p = (p+1) % k;
    }
    
}
