/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.translation;

import org.rcmd.qmapc.ir.model.quakemap.QuakeMapModel;
import org.rcmd.qmapc.ir.model.quakemap.Brush;
import org.rcmd.qmapc.ir.model.quakemap.Entity;
import org.rcmd.qmapc.ir.model.quakemap.PatchMesh;

/**
 * Interface to generate Quake maps from the internal representation.
 * @author spirit
 */
public interface IQuakeMapGenerator {
    
    public String genBrush(Brush b);
    public String genEntity(Entity e);
    public String genLevel(QuakeMapModel l);
    public String genPatchMesh(PatchMesh p);
    
}
