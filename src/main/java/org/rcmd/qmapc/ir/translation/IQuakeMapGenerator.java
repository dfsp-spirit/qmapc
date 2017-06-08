/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.translation;

import java.util.logging.Level;
import org.rcmd.qmapc.ir.model.quakemap.Brush;
import org.rcmd.qmapc.ir.model.quakemap.Entity;

/**
 * Interface to generate Quake maps from the internal representation.
 * @author spirit
 */
public interface IQuakeMapGenerator {
    
    public String genBrush(Brush b);
    public String genEntity(Entity e);
    public String genLevel(Level l);
    
}
