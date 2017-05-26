/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.lexer;

/**
 *
 * @author spirit
 */
public class Token {
    public int type;
    public String text;
    
    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }
    
    @Override
    public String toString() {
        String tokenName = Quake2MapLexer.tokenNames[this.type];
        return "<'" + this.text + "'," + tokenName + ">";
    }
    
    public void report() {
        System.out.println(this.toString());
    }
    
}
