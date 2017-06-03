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
 * @author spirit
 */
public class ParseTreeTrackingParser extends Parser {
    
    ParseTree root;
    ParseTree currentNode;

    public ParseTreeTrackingParser(Lexer input, int lookaheadBufferSize) {
        super(input, lookaheadBufferSize);
        currentNode = new RuleNode("start");
    }
    
    @Override
    public void match(int tokenType) {
        currentNode.addChild(this.lookaheadToken(1));
        super.match(tokenType);
        
    }
    
}