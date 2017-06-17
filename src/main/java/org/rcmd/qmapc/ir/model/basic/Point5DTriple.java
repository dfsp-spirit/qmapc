/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rcmd.qmapc.ir.model.basic;

/**
 *
 * @author spirit
 */
public class Point5DTriple {
    
    Point5DFloat point1;
    Point5DFloat point2;
    Point5DFloat point3;
    
    public Point5DTriple(Point5DFloat point1, Point5DFloat point2, Point5DFloat point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }
    
}
