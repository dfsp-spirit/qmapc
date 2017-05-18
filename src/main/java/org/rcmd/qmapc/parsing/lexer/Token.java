/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public String toString() {
        String tokenName = Quake2MapLexer.tokenNames[this.type];
        return "<'" + this.text + "'," + tokenName + ">";
    }
    
}
