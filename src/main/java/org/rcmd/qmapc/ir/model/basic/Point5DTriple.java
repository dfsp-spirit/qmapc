/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.model.basic;

/**
 * A point5D triple, used in patch meshes.
 * @author spirit
 */
public class Point5DTriple {
    
    public Point5DFloat point1;
    public Point5DFloat point2;
    public Point5DFloat point3;
    
    public Point5DTriple(Point5DFloat point1, Point5DFloat point2, Point5DFloat point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }
    
}
