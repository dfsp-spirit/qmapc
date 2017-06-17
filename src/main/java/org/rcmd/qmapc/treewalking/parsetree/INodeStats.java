/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.treewalking.parsetree;

/**
 *
 * @author spirit
 */
public interface INodeStats {
    
    public int getNumTokenNodes();
    
    public int getNumRuleNodes();
    
    public int getNumBrushNodes();
    
    public int getNumEntityNodes();
    
    public int getNumFaceNodes();
    
    public int getNumPatchMeshNodes();
    
}
