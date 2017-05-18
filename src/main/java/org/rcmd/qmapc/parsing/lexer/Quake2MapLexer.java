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
    public static int SQUAREBRACKET_L = 4;
    public static int SQUAREBRACKET_R = 5;
    public static int ROUNDBRACKET_L = 6;
    public static int ROUNDBRACKET_R = 7;
    public static int SLASH = 8;
    public static int DOT = 9;
    public static int INTEGER = 10;

    public static String[] tokenNames = {"n/a", "EOF", "NAME", "COMMA", "SQUAREBRACKET_L", "SQUAREBRACKET_R", "ROUNDBRACKET_L", "ROUNDBRACKET_R", "SLASH", "DOT", "INTEGER"};

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

    public Boolean isDigit() {
        return (this.c >= '0' && this.c <= '9');
    }

    void handleWhiteSpace() {
        while (this.c == ' ' || this.c == '\n' || this.c == '\r' || this.c == '\t') {
            this.consume();
        }
    }
    
    Token handleInteger() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(this.c);
            this.consume();
        } while (this.isDigit());
        return new Token(NAME, buf.toString());
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
                    return new Token(SQUAREBRACKET_L, "[");
                case ']':
                    this.consume();
                    return new Token(SQUAREBRACKET_R, "]");
                default:
                    if (this.isLetter()) {
                        return this.handleName();
                    } else {
                        throw new Error("Hit invalid character '" + this.c + "'.");
                    }

            }

        }
        return new Token(EOF_TYPE, "<EOF>");
    }

}
