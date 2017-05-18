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
public class Quake2MapLexer extends Lexer {

    public static int NAME = 2;
    public static int COMMA = 3;
    public static int LBRACKET = 4;
    public static int RBRACKET = 5;

    public static String[] tokenNames = {"n/a", "EOF", "NAME", "COMMA", "LBRACK", "RBRACK"};

    public Quake2MapLexer(String input) {
        super(input);
    }

    @Override
    public String getTokenName(int x) {
        return Quake2MapLexer.tokenNames[x];
    }

    public Boolean isLetter() {
        return (this.c >= 'a' && this.c <= 'z' || this.c >= 'A' && this.c <= 'Z');
    }

    void handleWhiteSpace() {
        while (this.c == ' ' || this.c == '\n' || this.c == '\r' || this.c == '\t') {
            this.consume();
        }
    }
    
    Token handleName() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(this.c);
            this.consume();
        } while (this.isLetter());
        return new Token(NAME, buf.toString());
    }

    @Override
    public Token nextToken() {
        while (this.c != Lexer.EOF) {
            switch (this.c) {
                case ' ':
                case '\n':
                case '\r':
                case '\t':
                    this.handleWhiteSpace();
                    continue;
                case ',':
                    this.consume();
                    return new Token(COMMA, ",");
                case '[':
                    this.consume();
                    return new Token(LBRACKET, "[");
                case ']':
                    this.consume();
                    return new Token(RBRACKET, "]");
                default:
                    if(this.isLetter()) {
                        return this.handleName();
                    }
                    else {
                        throw new Error("Hit invalid character '" + this.c + "'.");
                    }
                    
            }

        }
        return new Token(EOF_TYPE, "<EOF>");
    }

}
