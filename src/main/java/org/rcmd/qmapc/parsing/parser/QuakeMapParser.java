/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.rcmd.qmapc.ir.parsetree.ParseTree;
import org.rcmd.qmapc.ir.parsetree.RuleNode;
import org.rcmd.qmapc.parsing.lexer.Lexer;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;

/**
 * A Quake map parser that generates a parse tree while parsing a map file.
 *
 * @author spirit
 */
public class QuakeMapParser extends ParseTreeTrackingParser implements IQuakeMapParser {

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

    private Boolean trackParseTree = true;
    private int numEntities;
    private int numBrushes;
    private int numFaces;
    private int numPatchMeshes;

    public QuakeMapParser(Lexer input) {
        super(input, 10);
        this.numEntities = 0;
        this.numBrushes = 0;
        this.numFaces = 0;
        this.numPatchMeshes = 0;
    }

    /**
     * Parses a map and builds the nodes for the parse tree while doing so. This
     * wrapper function only tracks the parse tree, it calls mapCore to do the
     * actual parsing of the map.
     */
    @Override
    public void map() {

        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_MAP);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.mapCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    /**
     * Internal method that parses a map without parse tree tracking. This
     * function does not track the path node, this has to be done by a wrapping
     * function (see map).
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
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_BRUSH_FACE_POINT);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.point3DCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }

    }

    public void point5D() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_PATCHMESH_POINT);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.point5DCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }

    }

    private void point5DCore() {
        match(Quake2MapLexer.NUMBER);
        match(Quake2MapLexer.NUMBER);
        match(Quake2MapLexer.NUMBER);
        match(Quake2MapLexer.NUMBER);
        match(Quake2MapLexer.NUMBER);
    }

    private void point3DCore() {
        match(Quake2MapLexer.NUMBER);
        match(Quake2MapLexer.NUMBER);
        match(Quake2MapLexer.NUMBER);
    }

    public void q3PatchDef() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_BRUSH_PATCHDEF);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.q3PatchDefCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }

    }

    public void q2BrushFace() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_BRUSH_FACE);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.q2BrushFaceCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    private void q3PatchDefCore() {

        this.numPatchMeshes++;

        texturePath();
        match(Quake2MapLexer.CURLYBRACKET_L);
        texturePath();
        bracketedPoint5DInteger();
        match(Quake2MapLexer.ROUNDBRACKET_L);
        q3PatchMeshCoords();
        q3PatchMeshCoords();
        q3PatchMeshCoords();
        match(Quake2MapLexer.ROUNDBRACKET_R);
        match(Quake2MapLexer.CURLYBRACKET_R);
    }

    public void q3PatchMeshCoords() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_BRUSH_PATCHMESH_COORDS);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.q3PatchMeshCoordsCore();

        if (this.getTrackParseTree()) {
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

        if (this.lookaheadTokenType(1) == Quake2MapLexer.NUMBER
                && this.lookaheadTokenType(2) == Quake2MapLexer.NUMBER
                && this.lookaheadTokenType(3) == Quake2MapLexer.NUMBER) {
            faceContentFlags();          // contents flags, optional (this applies to the whole brush and must be identical for all of its faces)
            faceSurfaceFlags();          // surface flags, optional (this applies to the whole brush and must be identical for all of its faces)
            faceSurfaceValue();          // surface value, optional (this applies to the whole brush and must be identical for all of its faces)
        }
    }

    public void q2BrushWithBrushIDComment() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_BRUSH_ID);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.q2BrushWithBrushIDCommentCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    public void texturePath() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_TEXTURE_PATH);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.texturePathCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    public void textureHorizontalShift() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_TEXTURE_HORIZONTAL_SHIFT);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.textureHorizontalShiftCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    private void textureHorizontalShiftCore() {
        match(Quake2MapLexer.NUMBER);
    }

    public void textureVerticalShift() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_TEXTURE_VERTICAL_SHIFT);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.textureVerticalShiftCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    private void textureVerticalShiftCore() {
        match(Quake2MapLexer.NUMBER);
    }

    public void textureRotation() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_TEXTURE_ROTATION);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.textureRotationCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    private void textureRotationCore() {
        match(Quake2MapLexer.NUMBER);
    }

    private void texturePathCore() {
        match(Quake2MapLexer.PATH_OR_NAME);
    }

    private void q2BrushWithBrushIDCommentCore() {
        match(Quake2MapLexer.BRUSH_ID);
        q2BrushWithoutBrushIDComment();
    }

    @Override
    public void brush() {
        this.q2BrushWithBrushIDCommentCore();
    }

    public void q2BrushWithoutBrushIDComment() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_BRUSH);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.q2BrushWithoutBrushIDCommentCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    private void q2BrushWithoutBrushIDCommentCore() {

        this.numBrushes++;

        match(Quake2MapLexer.CURLYBRACKET_L);

        if (this.lookaheadTokenType(1) == Quake2MapLexer.PATH_OR_NAME) {
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
        point5D();
        match(Quake2MapLexer.ROUNDBRACKET_R);
    }

    public void q2EntityWithEntityIDComment() {

        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_ENTITY_ID);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.q2EntityWithEntityIDCommentCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }

    }

    private void q2EntityWithEntityIDCommentCore() {
        match(Quake2MapLexer.ENTITY_ID);
        q2EntityWithoutEntityIDComment();
    }

    private void q2EntityWithoutEntityIDComment() {

        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_ENTITY);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.q2EntityWithoutEntityIDCommentCore();

        if (this.getTrackParseTree()) {
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
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_ENTITY_PROPERTY);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.entityPropertyCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }

    }

    public void entityPropertyKey() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_ENTITY_PROPERTY_KEY);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.entityPropertyKeyCore();

        if (this.getTrackParseTree()) {
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
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_ENTITY_PROPERTY_VALUE);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.entityPropertyValueCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }

    }

    private void entityPropertyValueCore() {
        match(Quake2MapLexer.QUOTED_STRING);
    }

    public void textureHorizontalScale() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_TEXTURE_HORIZONTAL_SCALE);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.textureHorizontalScaleCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    private void textureHorizontalScaleCore() {
        match(Quake2MapLexer.NUMBER);
    }

    public void textureVerticalScale() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_TEXTURE_VERTICAL_SCALE);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.textureVerticalScaleCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    private void textureVerticalScaleCore() {
        match(Quake2MapLexer.NUMBER);
    }

    public void faceContentFlags() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_FACE_CONTENT_FLAGS);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.faceContentFlagsCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    private void faceContentFlagsCore() {
        match(Quake2MapLexer.NUMBER);
    }

    public void faceSurfaceFlags() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_FACE_SURFACE_FLAGS);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.faceSurfaceFlagsCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    private void faceSurfaceFlagsCore() {
        match(Quake2MapLexer.NUMBER);
    }

    public void faceSurfaceValue() {
        ParseTree _saved = null;
        if (this.getTrackParseTree()) {
            RuleNode r = new RuleNode(RULE_FACE_SURFACE_VALUE);
            if (this.getRoot() == null) {
                this.root = r;
                currentNode = getRoot();
            } else {
                this.getCurrentNode().addChild(r);
            }
            _saved = getCurrentNode();
            currentNode = r;
        }

        this.faceSurfaceValueCore();

        if (this.getTrackParseTree()) {
            currentNode = _saved;
        }
    }

    private void faceSurfaceValueCore() {
        match(Quake2MapLexer.NUMBER);
    }

    /**
     * @return the numEntities
     */
    public int getNumEntities() {
        return numEntities;
    }

    /**
     * @return the numBrushes
     */
    public int getNumBrushes() {
        return numBrushes;
    }

    /**
     * @return the numFaces
     */
    public int getNumFaces() {
        return numFaces;
    }

    /**
     * @return the numPatchMeshes
     */
    public int getNumPatchMeshes() {
        return numPatchMeshes;
    }

    @Override
    public void entity() {
        this.q2EntityWithEntityIDComment();
    }

    /**
     * @return the trackParseTree
     */
    public Boolean getTrackParseTree() {
        return trackParseTree;
    }

    /**
     * @param trackParseTree the trackParseTree to set
     */
    public void setTrackParseTree(Boolean trackParseTree) {
        this.trackParseTree = trackParseTree;
    }
}
