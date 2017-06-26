/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.model.quakemap;

import java.util.ArrayList;
import java.util.List;

/**
 * A Quake map. A map consists of a list of entities. (Note that its special properties, like the map name,
 * are defined in the entityProperties of a special entity named "worldspawn" and are therefore not store in here.)
 * 
 * @author spirit
 */
public class MapModel extends QuakeObjectModel {
    
    private final List<EntityModel> entities;
    
    public MapModel() {
        this.entities = new ArrayList<>();
    }
    
    public List<EntityModel> getEntities() {
        return this.entities;
    }
    
    private EntityModel getCurrentEntity() {
        return this.entities.get(this.entities.size() - 1);
    }
    
    private BrushModel getCurrentBrush() {
        return this.getCurrentEntity().getCurrentBrush();
    }
    
    public int getNextFreeEntityIDInLevel() {
        int maxUsedID = 0;
        for(EntityModel e : this.entities) {
            if(e.entityID >= maxUsedID) {
                maxUsedID = e.entityID;
            }
        }
        return (maxUsedID + 1);
    }
    
    public Boolean addEntity(EntityModel e) {
        return this.entities.add(e);
    }
    
}
