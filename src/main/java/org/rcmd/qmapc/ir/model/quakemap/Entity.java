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
public class Entity {
    
    public int entityID;
    private final List<EntityProperty> entityProperties;
    private final List<Brush> brushes;
    
    public Entity(int entityID) {
        this.entityID = entityID;
        this.entityProperties = new ArrayList<>();
        this.brushes = new ArrayList<>();
    }
    
    public int getNextFreeBrushIDInEntity() {
        int maxUsedID = 0;
        for(Brush b : this.brushes) {
            if(b.brushID >= maxUsedID) {
                maxUsedID = b.brushID;
            }
        }
        return (maxUsedID + 1);
    }
    
    public List<Brush> getBrushes() {
        return this.brushes;
    }
    
    public Boolean addBrush(Brush b) {
        return this.brushes.add(b);
    }
    
    public List<EntityProperty> getEntityProperties() {
        return this.entityProperties;
    }
    
    public Boolean addEntityProperty(EntityProperty p) {
        return this.entityProperties.add(p);
    }
    
}
