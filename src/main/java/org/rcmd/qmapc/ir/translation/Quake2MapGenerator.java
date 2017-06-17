/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.translation;

import org.rcmd.qmapc.ir.model.quakemap.PatchMesh;

/**
 * A generator based for generating maps in Quake 2 map format. Based on the
 * CommonQuakeMapGenerator.
 * @author spirit
 */
public class Quake2MapGenerator extends CommonQuakeMapGenerator implements IQuakeMapGenerator {
    
    @Override
    public String genPatchMesh(PatchMesh p) {
        return "";      // There is no patch mesh support in Quake 2, so just skip this.
    }
    
}
