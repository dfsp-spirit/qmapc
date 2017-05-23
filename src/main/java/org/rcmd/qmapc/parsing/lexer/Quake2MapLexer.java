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
    public static int FLOAT = 11;
    public static int BRUSH_ID = 12;
    public static int COMMENT = 13;
    public static int DOUBLEQUOTATIONMARKS = 14;
    public static int PATH = 15;

    public static String[] tokenNames = {"n/a", "EOF", "NAME", "COMMA", "SQUAREBRACKET_L", "SQUAREBRACKET_R", "ROUNDBRACKET_L", "ROUNDBRACKET_R", "SLASH", "DOT", "INTEGER", "FLOAT", "BRUSH_ID", "COMMENT", "DOUBLEQUOTATIONMARKS", "PATH"};

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
    
    public Boolean isDigitCompatibleStartChar() {
        return (this.c == '-');
    }
    
    public Boolean isDigitCompatibleFollowupChar() {
        return (this.c == '.');
    }
    
    public Boolean isPathOrCommentCompatibleStartChar() {
        return (this.c == '/');
    }
    
    public Boolean isNamePathOrCommentCompatibleFollowupChar() {
        return (this.c == '/' || this.c == '_' || this.c == ' ' || this.c == '\t' || this.isDigit());
    }

    void handleWhiteSpace() {
        while (this.c == ' ' || this.c == '\n' || this.c == '\r' || this.c == '\t') {
            this.consume();
        }
    }
    
    Token handleDigit() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(this.c);
            this.consume();
        } while (this.isDigit() || this.isDigitCompatibleFollowupChar());
        if(buf.toString().contains(".")) {
            return new Token(FLOAT, buf.toString());
        }
        else {
            return new Token(INTEGER, buf.toString());
        }        
    }

    Token handlePathOrComment() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(this.c);
            this.consume();
        } while (this.isLetter() || this.isNamePathOrCommentCompatibleFollowupChar());
        String completeTokenString = buf.toString();
        if(completeTokenString.startsWith("//")) {
            if(completeTokenString.startsWith("// brush ")) {       // Some quake editors like GtkRadiant add the brush ID in a commit before each brush.
                try {
                    int brushID = Integer.parseInt(completeTokenString.substring("// brush ".length() + 1));
                    return new Token(BRUSH_ID, "" + brushID);
                }
                catch(Exception e) {
                    return new Token(COMMENT, completeTokenString);
                }                
            }
            else {
                return new Token(COMMENT, completeTokenString);
            }
        }
        return new Token(PATH, buf.toString());
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
                case '(':
                    this.consume();
                    return new Token(ROUNDBRACKET_L, "(");
                case ')':
                    this.consume();
                    return new Token(ROUNDBRACKET_R, ")");
                case '"':
                    this.consume();
                    return new Token(DOUBLEQUOTATIONMARKS, "\"");
                default:
                    if (this.isLetter() || this.isPathOrCommentCompatibleStartChar()) {
                        return this.handlePathOrComment();
                    } else if(this.isDigit() || this.isDigitCompatibleStartChar()) {
                        return this.handleDigit();
                    } else {
                        throw new Error("Hit invalid character '" + this.c + "'.");
                    }

            }

        }
        return new Token(EOF_TYPE, "<EOF>");
    }

}
