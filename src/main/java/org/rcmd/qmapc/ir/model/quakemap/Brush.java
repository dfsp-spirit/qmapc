/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.model.quakemap;

import java.util.ArrayList;
import java.util.List;

/**
 * A brush, i.e., a building block of a map. Quake brushes are convex and consist of
 * at least 4 faces.
 * 
 * @author spirit
 */
public class Brush {
    
    public int brushID;
    private final List<Face> faces;
    
    public Brush() {
        this.brushID = -1;
        faces = new ArrayList<>();
    }
    
    public Brush(int brushID) {
        this.brushID = brushID;
        faces = new ArrayList<>();
    }
    
    public Boolean addFace(Face f) {
        return this.faces.add(f);
    }
    
    public List<Face> getFaces() {
        return this.faces;
    }
    
}
