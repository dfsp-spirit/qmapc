/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.rcmd.qmapc.ir.parsetree.ParseTree;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.ir.parsetree.TokenNode;
import org.rcmd.qmapc.parsing.lexer.Lexer;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;

/**
 *
 * @author spirit
 */
public class Quake2MapParser extends ParseTreeTrackingParser {
    
    
    public static final String RULE_MAP = "RULE_MAP";
    public static final String RULE_ENTITY_ID = "RULE_ENTITY_ID";
    public static final String RULE_ENTITY = "RULE_ENTITY";
    public static final String RULE_ENTITY_PROPERTY = "RULE_ENTITY_PROPERTY";
    public static final String RULE_ENTITY_PROPERTY_KEY = "RULE_ENTITY_PROPERTY_KEY";
    public static final String RULE_ENTITY_PROPERTY_VALUE = "RULE_ENTITY_PROPERTY_VALUE";
    public static final String RULE_BRUSH = "RULE_BRUSH";
    public static final String RULE_BRUSH_FACE = "RULE_BRUSH_FACE";
    public static final String RULE_BRUSH_FACE_POINT = "RULE_BRUSH_FACE_POINT";
    public static final String RULE_BRUSH_FACE_TEXTURE = "RULE_BRUSH_FACE_TEXTURE";

    
    Boolean trackParseTree = true;

    public Quake2MapParser(Lexer input) {
        super(input, 10);
    }

    /**
     * Parses a map and builds the nodes for the parse tree while doing so.
     * This wrapper function only tracks the parse tree, it calls mapCore
     * to do the actual parsing of the map.
     */
    public void map() {

        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_MAP);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.mapCore();        

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    /**
     * Internal method that parses a map without parse tree tracking. 
     * This function does not track the path node,
     * this has to be done by a wrapping function (see map).
     */
    private void mapCore() {
        if (this.lookaheadTokenType(1) == Quake2MapLexer.ENTITY_ID) {
            q2EntityWithEntityIDComment();
        } else {
            q2EntityWithoutEntityIDComment();
        }
        
    }

    void point3DInteger() {
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
    }

    void point3DFloat() {
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
    }

    void q2BrushFaceLine() {
        bracketedPoint3DInteger();
        bracketedPoint3DInteger();
        bracketedPoint3DInteger();
        match(Quake2MapLexer.PATH_OR_NAME);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
    }

    void q2BrushWithBrushIDComment() {
        match(Quake2MapLexer.BRUSH_ID);
        q2BrushWithoutBrushIDComment();
    }

    void q2BrushWithoutBrushIDComment() {
        match(Quake2MapLexer.CURLYBRACKET_L);
        q2BrushFaceLine();
        while (this.lookaheadTokenType(1) == Quake2MapLexer.ROUNDBRACKET_L) {
            q2BrushFaceLine();
        }
        match(Quake2MapLexer.CURLYBRACKET_R);
    }

    void bracketedPoint3DInteger() {
        match(Quake2MapLexer.ROUNDBRACKET_L);
        point3DInteger();
        match(Quake2MapLexer.ROUNDBRACKET_R);
    }

    public void q2EntityWithEntityIDComment() {
        
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_ENTITY_ID);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.q2EntityWithEntityIDCommentCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
        
    }
    
    private void q2EntityWithEntityIDCommentCore() {
        match(Quake2MapLexer.ENTITY_ID);
        q2EntityWithoutEntityIDComment();
    }

    private void q2EntityWithoutEntityIDComment() {
        
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_ENTITY);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.q2EntityWithoutEntityIDCommentCore();
        
        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    private void q2EntityWithoutEntityIDCommentCore() {
        match(Quake2MapLexer.CURLYBRACKET_L);
        while (this.lookaheadTokenType(1) == Quake2MapLexer.QUOTED_STRING || this.lookaheadTokenType(1) == Quake2MapLexer.BRUSH_ID || this.lookaheadTokenType(1) == Quake2MapLexer.CURLYBRACKET_L) {
            switch (this.lookaheadTokenType(1)) {
                case Quake2MapLexer.QUOTED_STRING:
                    entityProperty();
                    break;
                case Quake2MapLexer.BRUSH_ID:
                    q2BrushWithBrushIDComment();
                    break;
                case Quake2MapLexer.CURLYBRACKET_L:
                    q2BrushWithoutBrushIDComment();
                    break;
                default:
                    throw new IllegalArgumentException("Hit invalid token '" + this.lookaheadTokenType(1) + "' while parsing an entity. Expected '\"', '(', or '/'.");
            }
        }

        match(Quake2MapLexer.CURLYBRACKET_R);
    }
    
    
    public void entityProperty() {
        
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_ENTITY_PROPERTY);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.entityPropertyCore();
        
        if(this.trackParseTree) {
            currentNode = _saved;
        }
        
    }
    
    public void entityPropertyKey() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_ENTITY_PROPERTY_KEY);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.entityPropertyKeyCore();
        
        if(this.trackParseTree) {
            currentNode = _saved;
        }
        
    }
    
    private void entityPropertyKeyCore() {
        match(Quake2MapLexer.QUOTED_STRING);
    }
    
    private void entityPropertyCore() {
        entityPropertyKey();
        entityPropertyValue();
    }

    public void entityPropertyValue() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_ENTITY_PROPERTY_VALUE);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.entityPropertyValueCore();
        
        if(this.trackParseTree) {
            currentNode = _saved;
        }
        
    }
    
    private void entityPropertyValueCore() {
        match(Quake2MapLexer.QUOTED_STRING);
    }
}
