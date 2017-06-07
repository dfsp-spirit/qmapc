/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.model.quakemap;

import org.rcmd.qmapc.ir.model.basic.Point3DInteger;

/**
 * Model for a face. At least 4 faces are required to define a brush.
 * @author spirit
 */
public class Face {
    
    public Point3DInteger point1;
    public Point3DInteger point2;
    public Point3DInteger point3;
    public String texturePath;
    public int textureVerticalShift;
    public int textureHorizontalShift;
    public int textureRotation;
    public float textureHorizontalScale;
    public float textureVerticalScale;
    public int contentFlags;
    public int surfaceFlags;
    public int surfaceValue;
    
    public Face() {
        this.point1 = new Point3DInteger(0, 0, 0);
        this.point2 = new Point3DInteger(0, 0, 0);
        this.point3 = new Point3DInteger(0, 0, 0);
        this.texturePath = "mymap/mytex1";
        this.textureVerticalShift = 0;
        this.textureHorizontalShift = 0;
        this.textureRotation = 0;
        this.textureHorizontalScale = 1.0f;
        this.textureVerticalScale = 1.0f;
        this.contentFlags = 0;
        this.surfaceFlags = 0;
        this.surfaceValue = 0;
    }
    
    public Face(Point3DInteger point1, Point3DInteger point2, Point3DInteger point3, String texturePath, int textureVerticalShift, int textureHorizontalShift, int textureRotation, float textureHorizontalScale, float textureVerticalScale, int contentFlags, int surfaceFlags, int surfaceValue) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.texturePath = texturePath;
        this.textureVerticalShift = textureVerticalShift;
        this.textureHorizontalShift = textureHorizontalShift;
        this.textureRotation = textureRotation;
        this.textureHorizontalScale = textureHorizontalScale;
        this.textureVerticalScale = textureVerticalScale;
        this.contentFlags = contentFlags;
        this.surfaceFlags = surfaceFlags;
        this.surfaceValue = surfaceValue;
    }
    
    /**
     * Constructor without surface flags.
     * @param point1 first point that defines the plane of this face
     * @param point2 second point that defines the plane of this face
     * @param point3 third point that defines the plane of this face
     * @param texturePath relative path to the texture of the face, e.g., "mymap/texture_1"
     * @param textureVerticalShift texture vertical shift, in game units
     * @param textureHorizontalShift texture horizontal shift, in game units
     * @param textureRotation texture rotation, in degrees (0..360)
     * @param textureHorizontalScale texture horizontal scale
     * @param textureVerticalScale texture vertical scale
     */
    public Face(Point3DInteger point1, Point3DInteger point2, Point3DInteger point3, String texturePath, int textureVerticalShift, int textureHorizontalShift, int textureRotation, float textureHorizontalScale, float textureVerticalScale) {
        this(point1, point2, point3, texturePath, textureVerticalShift, textureHorizontalShift, textureRotation, textureHorizontalScale, textureVerticalScale, 0, 0, 0);
    }
    
    
}
