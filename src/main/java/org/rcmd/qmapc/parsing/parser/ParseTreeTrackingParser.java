/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.rcmd.qmapc.ir.parsetree.ParseTree;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.parsing.lexer.Lexer;

/**
 * A parser that tracks the parse tree while parsing. To use this, you need to
 * add an interior rule node every time you enter a parsing rule.
 *
 * @author spirit
 */
public class ParseTreeTrackingParser extends Parser {

    /**
     * The root of the parse tree build by this parser.
     */
    protected ParseTree root;

    /**
     * The root under construction which is to be added to the parse tree when
     * it is done.
     */
    protected ParseTree currentNode;

    public static final String RULE_ROOT = "RULE_ROOT";

    public ParseTreeTrackingParser(Lexer input, int lookaheadBufferSize) {
        super(input, lookaheadBufferSize);
        currentNode = new RuleNode(RULE_ROOT);
    }

    @Override
    public void match(int tokenType) {
        getCurrentNode().addChild(this.lookaheadToken(1));
        super.match(tokenType);
    }

    /**
     * @return the root
     */
    public ParseTree getRoot() {
        return root;
    }

    /**
     * @return the currentNode
     */
    public ParseTree getCurrentNode() {
        return currentNode;
    }

}
