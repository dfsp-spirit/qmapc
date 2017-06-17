/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.model.quakemap;

import java.util.ArrayList;
import java.util.List;
import org.rcmd.qmapc.ir.model.basic.Point5DFloat;
import org.rcmd.qmapc.ir.model.basic.Point5DTriple;

/**
 * A quake map patch mesh. Usually used for rounded surfaces. This is the 
 * part that is contained inside a single surrounding brush.
 * @author spirit
 */
public class PatchMesh {
    
    public String type;             // usually "PatchDef2"
    public Point5DFloat point5D;
    public String texturePath;
    public List<Point5DTriple> coordLines;
    
    public PatchMesh() {
        this.coordLines = new ArrayList<>();
    }
    
    
}
