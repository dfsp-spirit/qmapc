/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.lexer;

/**
 *
 * @author spirit
 */
public class Quake2MapLexer extends Lexer {

    public static int QUOTED_STRING = 2;
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
    public static int PATH_OR_NAME = 15;
    public static int ENTITY_ID = 16;
    public static int CURLYBRACKET_L = 17;
    public static int CURLYBRACKET_R = 18;

    public static String[] tokenNames = {"n/a", "EOF", "QUOTED_STRING", "COMMA", "SQUAREBRACKET_L", "SQUAREBRACKET_R", "ROUNDBRACKET_L", "ROUNDBRACKET_R", "SLASH", "DOT", "INTEGER", "FLOAT", "BRUSH_ID", "COMMENT", "DOUBLEQUOTATIONMARKS", "PATH_OR_NAME", "ENTITY_ID", "CURLYBRACKET_L", "CURLYBRACKET_R"};

    public Quake2MapLexer(String input) {
        super(input);
    }

    @Override
    public String getTokenName(int tokenID) {
        return Quake2MapLexer.tokenNames[tokenID];
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
    
    
    public Boolean isPathCompatibleFollowupChar() {
        return (this.c == '/' || this.c == '_' || this.isDigit());
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
    
    Token handleComment() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(this.c);
            this.consume();
        } while (this.c != '\n' && this.c != Lexer.EOF);
        String completeTokenString = buf.toString();
        if(completeTokenString.startsWith("//")) {
            if(completeTokenString.startsWith("// brush ")) {       // Some quake editors like GtkRadiant add the brush ID in a comment before each brush.
                try {
                    int brushID = Integer.parseInt(completeTokenString.substring("// brush ".length()));
                    return new Token(BRUSH_ID, "" + brushID);
                }
                catch(NumberFormatException e) {
                    return new Token(COMMENT, completeTokenString);
                }                
            }
            else if(completeTokenString.startsWith("// entity ")) {       // Some quake editors like GtkRadiant add the entity ID in a comment before each entity.
                System.out.println("starts with entitiy");
                try {
                    int entityID = Integer.parseInt(completeTokenString.substring("// entity ".length()));
                    return new Token(ENTITY_ID, "" + entityID);
                }
                catch(NumberFormatException e) {
                    return new Token(COMMENT, completeTokenString);
                }                
            }
            else {
                return new Token(COMMENT, completeTokenString);
            }
        } else {
            throw new Error("Hit invalid character sequence '" + completeTokenString + "', expected comment but missing second slash at start.");
        }
        
    }

    Token handlePathOrName() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(this.c);
            this.consume();
        } while (this.isLetter() || this.isPathCompatibleFollowupChar());
        return new Token(PATH_OR_NAME, buf.toString());
    }
    
    Token handleQuotedString() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(this.c);
            this.consume();
        } while (this.c != '"' && this.c != Lexer.EOF);
        if(this.c == '"') {
            buf.append(this.c);
            this.consume();
            return new Token(QUOTED_STRING, buf.toString());
        }
        else {
            throw new Error("Hit EOF while in a quoted string. Expected closing '\"' before EOF.");
        }
                
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
                case '{':
                    this.consume();
                    return new Token(CURLYBRACKET_L, "{");
                case '}':
                    this.consume();
                    return new Token(CURLYBRACKET_R, "}");
                case '"':
                    this.consume();
                    return new Token(DOUBLEQUOTATIONMARKS, "\"");
                case '/':
                    return this.handleComment();
                default:
                    if (this.isLetter()) {
                        return this.handlePathOrName();
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
