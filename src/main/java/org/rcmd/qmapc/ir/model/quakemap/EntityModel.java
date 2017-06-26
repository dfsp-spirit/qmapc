/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.model.quakemap;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity. A map is a list of entities. An entity consists of 0 or more EntityProperties and
 * 0 or more Brushes.
 * 
 * @author spirit
 */
public class EntityModel extends QuakeObjectModel {
    
    public int entityID;
    private final List<EntityPropertyModel> entityProperties;
    private final List<BrushModel> brushes;
    
    public EntityModel() {
        this.entityID = -1;
        this.entityProperties = new ArrayList<>();
        this.brushes = new ArrayList<>();
    }
    
    public EntityModel(int entityID) {
        this.entityID = entityID;
        this.entityProperties = new ArrayList<>();
        this.brushes = new ArrayList<>();
    }
    
    public int getNextFreeBrushIDInEntity() {
        int maxUsedID = 0;
        for(BrushModel b : this.brushes) {
            if(b.brushID >= maxUsedID) {
                maxUsedID = b.brushID;
            }
        }
        return (maxUsedID + 1);
    }
    
    public List<BrushModel> getBrushes() {
        return this.brushes;
    }
    
    public Boolean addBrush(BrushModel b) {
        return this.brushes.add(b);
    }
    
    public List<EntityPropertyModel> getEntityProperties() {
        return this.entityProperties;
    }
    
    public Boolean addEntityProperty(EntityPropertyModel p) {
        return this.entityProperties.add(p);
    }
    
    public BrushModel getCurrentBrush() {
        return this.brushes.get(this.brushes.size() - 1);
    }
    
}
