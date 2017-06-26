/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.translation;

import org.rcmd.qmapc.ir.model.quakemap.MapModel;
import org.rcmd.qmapc.ir.model.quakemap.BrushModel;
import org.rcmd.qmapc.ir.model.quakemap.EntityModel;
import org.rcmd.qmapc.ir.model.quakemap.PatchMeshModel;

/**
 * Interface to generate Quake maps from the internal representation.
 * @author spirit
 */
public interface IQuakeMapGenerator {
    
    public String genBrush(BrushModel b);
    public String genEntity(EntityModel e);
    public String genLevel(MapModel l);
    public String genPatchMesh(PatchMeshModel p);
    
}
