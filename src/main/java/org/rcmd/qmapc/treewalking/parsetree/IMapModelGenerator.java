/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

import org.rcmd.qmapc.ir.model.quakemap.QuakeMapModel;

/**
 * Interface for generating the model of a Quake map. Used by tree walkers to
 * create the model IR from the parse tree while walking the latter. 
 * @author spirit
 */
public interface IMapModelGenerator {
    
    public QuakeMapModel getMapModel();
    
}
