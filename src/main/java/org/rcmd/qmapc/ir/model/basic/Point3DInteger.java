/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.model.basic;

/**
 * A point in 3D space. Used in origin of entities and for coordinates of plane points of brushes.
 * @author spirit
 */
public class Point3DInteger {
    
    public int x;
    public int y;
    public int z;
    
    public Point3DInteger(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
}
