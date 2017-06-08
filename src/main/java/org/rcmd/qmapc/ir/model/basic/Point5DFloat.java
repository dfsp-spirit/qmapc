/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.model.basic;

/**
 * A point consisting of 5 float values. Used in patch mesh definition.
 * @author spirit
 */
public class Point5DFloat {
    
    public float c1;
    public float c2;
    public float c3;
    public float c4;
    public float c5;
    
    public Point5DFloat(float c1, float c2, float c3, float c4, float c5) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
    }
    
}
