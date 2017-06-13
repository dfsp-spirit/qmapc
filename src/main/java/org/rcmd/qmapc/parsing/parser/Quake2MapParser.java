/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.rcmd.qmapc.ir.parsetree.ParseTree;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
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
    public static final String RULE_BRUSH_ID = "RULE_BRUSH_ID";
    public static final String RULE_BRUSH = "RULE_BRUSH";
    public static final String RULE_BRUSH_FACE = "RULE_BRUSH_FACE";
    public static final String RULE_BRUSH_FACE_POINT = "RULE_BRUSH_FACE_POINT";
    public static final String RULE_BRUSH_FACE_TEXTURE = "RULE_BRUSH_FACE_TEXTURE";
    public static final String RULE_TEXTURE_PATH = "RULE_TEXTURE_PATH";
    public static final String RULE_TEXTURE_HORIZONTAL_SHIFT = "RULE_TEXTURE_HORIZONTAL_SHIFT";
    public static final String RULE_TEXTURE_VERTICAL_SHIFT = "RULE_TEXTURE_VERTICAL_SHIFT";
    public static final String RULE_TEXTURE_ROTATION = "RULE_TEXTURE_ROTATION";
    public static final String RULE_TEXTURE_HORIZONTAL_SCALE = "RULE_TEXTURE_HORIZONTAL_SCALE";
    public static final String RULE_TEXTURE_VERTICAL_SCALE = "RULE_TEXTURE_VERTICAL_SCALE";
    public static final String RULE_FACE_CONTENT_FLAGS = "RULE_FACE_CONTENT_FLAGS";
    public static final String RULE_FACE_SURFACE_FLAGS = "RULE_FACE_SURFACE_FLAGS";
    public static final String RULE_FACE_SURFACE_VALUE = "RULE_FACE_SURFACE_VALUE";
    public static final String RULE_BRUSH_PATCHDEF = "RULE_BRUSH_PATCHDEF";
    public static final String RULE_PATCHMESH_POINT = "RULE_PATCHMESH_POINT";
    public static final String RULE_BRUSH_PATCHMESH_COORDS = "RULE_BRUSH_PATCHMESH_COORDS";
    

    
    Boolean trackParseTree = true;
    public int numEntities;
    public int numBrushes;
    public int numFaces;
    public int numPatchMeshes;

    public Quake2MapParser(Lexer input) {
        super(input, 10);
        numEntities = 0;
        numBrushes = 0;
        numFaces = 0;
        numPatchMeshes = 0;
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
    
    public void point3DInteger() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_BRUSH_FACE_POINT);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.point3DIntegerCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
        
    }
    
    public void point5DFloat() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_PATCHMESH_POINT);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.point5DFloatCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
        
    }
    
    private void point5DFloatCore() {
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
    }

    private void point3DIntegerCore() {
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
        match(Quake2MapLexer.INTEGER);
    }

    void point3DFloat() {
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
        match(Quake2MapLexer.FLOAT);
    }
    
    public void q3PatchDef() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_BRUSH_PATCHDEF);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.q3PatchDefCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
        
    }

    public void q2BrushFace() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_BRUSH_FACE);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.q2BrushFaceCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }        
    }
    
    private void q3PatchDefCore() {
        
        this.numPatchMeshes++;
        match(Quake2MapLexer.QUOTED_STRING);
        match(Quake2MapLexer.CURLYBRACKET_L);
        texturePath();
        bracketedPoint5DInteger();
        match(Quake2MapLexer.ROUNDBRACKET_L);
        q3PatchMeshCoords();
        q3PatchMeshCoords();
        q3PatchMeshCoords();
        match(Quake2MapLexer.ROUNDBRACKET_R);                
    }
    
    public void q3PatchMeshCoords() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_BRUSH_PATCHMESH_COORDS);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.q3PatchMeshCoordsCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
        
    }
    
    private void q3PatchMeshCoordsCore() {
        match(Quake2MapLexer.ROUNDBRACKET_L);
        bracketedPoint5DInteger();
        bracketedPoint5DInteger();
        bracketedPoint5DInteger();
        match(Quake2MapLexer.ROUNDBRACKET_R);
    }
    
    private void q2BrushFaceCore() {
        
        this.numFaces++;
        
        bracketedPoint3DInteger();              // face point 1
        bracketedPoint3DInteger();              // face point 2
        bracketedPoint3DInteger();              // face point 3
        texturePath();                          // texture path
        textureHorizontalShift();               // texture horizontal shift        
        textureVerticalShift();                 // texture vertical shift
        textureRotation();                      // texture rotation (degrees)
        textureHorizontalScale();            // texture horizontal scale
        textureVerticalScale();            // texture vertical scale
        

        if (this.lookaheadTokenType(1) == Quake2MapLexer.INTEGER &&
                this.lookaheadTokenType(2) == Quake2MapLexer.INTEGER &&
                this.lookaheadTokenType(3) == Quake2MapLexer.INTEGER) {
            faceContentFlags();          // contents flags, optional (this applies to the whole brush and must be identical for all of its faces)
            faceSurfaceFlags();          // surface flags, optional (this applies to the whole brush and must be identical for all of its faces)
            faceSurfaceValue();          // surface value, optional (this applies to the whole brush and must be identical for all of its faces)
        }                
    }

    public void q2BrushWithBrushIDComment() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_BRUSH_ID);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.q2BrushWithBrushIDCommentCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    public void texturePath() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_TEXTURE_PATH);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.texturePathCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    public void textureHorizontalShift() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_TEXTURE_HORIZONTAL_SHIFT);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.textureHorizontalShiftCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    private void textureHorizontalShiftCore() {
        match(Quake2MapLexer.INTEGER);
    }
    
    public void textureVerticalShift() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_TEXTURE_VERTICAL_SHIFT);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.textureVerticalShiftCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    private void textureVerticalShiftCore() {
        match(Quake2MapLexer.INTEGER);
    }
    
    public void textureRotation() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_TEXTURE_ROTATION);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.textureRotationCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    private void textureRotationCore() {
        match(Quake2MapLexer.INTEGER);
    }
    
    private void texturePathCore() {
        match(Quake2MapLexer.PATH_OR_NAME);
    }
    
    private void q2BrushWithBrushIDCommentCore() {
        match(Quake2MapLexer.BRUSH_ID);
        q2BrushWithoutBrushIDComment();
    }

    
    public void q2BrushWithoutBrushIDComment() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_BRUSH);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.q2BrushWithoutBrushIDCommentCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    private void q2BrushWithoutBrushIDCommentCore() {
        
        this.numBrushes++;
        
        match(Quake2MapLexer.CURLYBRACKET_L);
        
        if(this.lookaheadTokenType(1) == Quake2MapLexer.QUOTED_STRING) {
            q3PatchDef();
        }
        
        while (this.lookaheadTokenType(1) == Quake2MapLexer.ROUNDBRACKET_L) {
            q2BrushFace();
        }
        match(Quake2MapLexer.CURLYBRACKET_R);
    }

    
    public void bracketedPoint3DInteger() {
        // No need for another rule here
        bracketedPoint3DIntegerCore();
    }
    
    private void bracketedPoint3DIntegerCore() {
        match(Quake2MapLexer.ROUNDBRACKET_L);
        point3DInteger();
        match(Quake2MapLexer.ROUNDBRACKET_R);
    }
    
    public void bracketedPoint5DInteger() {
        // No need for another rule here
        bracketedPoint5DIntegerCore();
    }
    
    private void bracketedPoint5DIntegerCore() {
        match(Quake2MapLexer.ROUNDBRACKET_L);
        point5DFloat();
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
        
        this.numEntities++;
        
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
    
    public void textureHorizontalScale() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_TEXTURE_HORIZONTAL_SCALE);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.textureHorizontalScaleCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    private void textureHorizontalScaleCore() {
        match(Quake2MapLexer.FLOAT);
    }
    
    public void textureVerticalScale() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_TEXTURE_VERTICAL_SCALE);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.textureVerticalScaleCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    private void textureVerticalScaleCore() {
        match(Quake2MapLexer.FLOAT);
    }
    
    public void faceContentFlags() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_FACE_CONTENT_FLAGS);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.faceContentFlagsCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    private void faceContentFlagsCore() {
        match(Quake2MapLexer.INTEGER);
    }
    
    public void faceSurfaceFlags() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_FACE_SURFACE_FLAGS);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.faceSurfaceFlagsCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    private void faceSurfaceFlagsCore() {
        match(Quake2MapLexer.INTEGER);
    }
    
    public void faceSurfaceValue() {
        ParseTree _saved = null;
        if(this.trackParseTree) {
            RuleNode r = new RuleNode(RULE_FACE_SURFACE_VALUE);
            if(this.root == null) {
                this.root = r;
                currentNode = root;
            } else {
                this.currentNode.addChild(r);
            }
            _saved = currentNode;
            currentNode = r;
        }
        
        this.faceSurfaceValueCore();

        if(this.trackParseTree) {
            currentNode = _saved;
        }
    }
    
    private void faceSurfaceValueCore() {
        match(Quake2MapLexer.INTEGER);
    }
}
