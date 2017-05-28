/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.lexer;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author spirit
 */
public class LexerTest {

    Token token;
    Lexer lexer;
    List<Token> tokenList;

    public LexerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tokenList = new ArrayList<>();
    }

    @After
    public void tearDown() {
        token = null;
        tokenList = null;
    }

    @org.junit.Test
    public void testABasicBinaryLexerImplementationProperlyLexesValidInput() {
        String input = "01101";
        lexer = new BinaryTestLexer(input);

        token = lexer.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = lexer.nextToken();
        }
        assertEquals(5, tokenList.size());
        assertEquals(BinaryTestLexer.TOKEN_ZERO, tokenList.get(0).type);
        assertEquals(BinaryTestLexer.TOKEN_ONE, tokenList.get(1).type);
        assertEquals(BinaryTestLexer.TOKEN_ONE, tokenList.get(2).type);
        assertEquals(BinaryTestLexer.TOKEN_ZERO, tokenList.get(3).type);
        assertEquals(BinaryTestLexer.TOKEN_ONE, tokenList.get(4).type);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testABasicBinaryLexerImplementationThrowsErrorOnInvalidInput() {
        String input = "2";
        lexer = new BinaryTestLexer(input);

        token = lexer.nextToken();
        while (token.type != Lexer.EOF_TYPE) {
            tokenList.add(token);
            token = lexer.nextToken();
        }

    }

}

class BinaryTestLexer extends Lexer {

    public BinaryTestLexer(String input) {
        super(input);
    }

    static int TOKEN_ZERO = 2;
    static int TOKEN_ONE = 3;
    static String[] tokenNames = {"n/a", "EOF", "ZERO", "ONE"};

    @Override
    public Token nextToken() {
        while (this.c != Lexer.EOF) {
            switch (this.c) {
                case '0':
                    this.consume();
                    return new Token(TOKEN_ZERO, "ZERO");
                case '1':
                    this.consume();
                    return new Token(TOKEN_ONE, "ONE");
                default:
                    throw new IllegalArgumentException("Hit invalid character '" + this.c + "'.");
            }
        }
        return new Token(EOF_TYPE, "<EOF>");
    }

    @Override
    public String getTokenName(int tokenID) {
        return BinaryTestLexer.tokenNames[tokenID];
    }

}
