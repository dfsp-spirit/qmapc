/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.parsetree;

import org.rcmd.qmapc.parsing.lexer.Token;

/**
 *
 * @author spirit
 */
public class TokenNode extends ParseTree {
    public Token token;
    
    public TokenNode(Token token) {
        this.token = token;
    }
    
}
