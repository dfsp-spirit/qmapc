/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.transformation;

import org.rcmd.qmapc.ir.model.basic.Point3DInteger;

/**
 * A transformer for 3D points of type Integer. Used when scaling a map
 * up or down during conversion.
 * @author spirit
 */
public class Point3DIntegerTransformer {
    
    private final Point3DInteger point;
    private double fx = 1.0;
    private double fy = 1.0;
    private double fz = 1.0;
    
    public Point3DIntegerTransformer(Point3DInteger point) {
        this.point = point;
    }
    
    public Point3DIntegerTransformer(Point3DInteger point, double fx, double fy, double fz) {
        this.point = point;
        this.fx = fx;
        this.fy = fy;
        this.fz = fz;
    }
    
    public Point3DIntegerTransformer(Point3DInteger point, double f) {
        this.point = point;
        this.fx = f;
        this.fy = f;
        this.fz = f;
    }
    
    public Point3DInteger transform() {
        return new Point3DInteger(transformCoord(point.x, fx), transformCoord(point.y, fy), transformCoord(point.z, fz));
    }
    
    private int transformCoord(int xCoord, double factor) {
        return (int) Math.round(Math.floor(xCoord * factor));
    }
    
}
