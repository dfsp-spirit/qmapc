/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.parsetree;

import java.util.ArrayList;
import java.util.List;
import org.rcmd.qmapc.parsing.lexer.Token;

/**
 *
 * @author spirit
 */
public abstract class ParseTree {
    
    public List<ParseTree> children;
    
    public RuleNode addChild(String value) {
        RuleNode r = new RuleNode(value);
        addChild(r);
        return r;
    }
    
    public TokenNode addChild(Token value) {
        TokenNode t = new TokenNode(value);
        addChild(t);
        return t;
    }
    
    public void addChild(ParseTree t) {
        if(this.children == null) {
            children = new ArrayList<>();
        }
        children.add(t);
    }
    
    
}
