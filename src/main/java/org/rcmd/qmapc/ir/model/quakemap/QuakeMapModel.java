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
public class QuakeMapModel {
    
    private final List<Entity> entities;
    
    public QuakeMapModel() {
        this.entities = new ArrayList<>();
    }
    
    public List<Entity> getEntities() {
        return this.entities;
    }
    
    public int getNextFreeEntityIDInLevel() {
        int maxUsedID = 0;
        for(Entity e : this.entities) {
            if(e.entityID >= maxUsedID) {
                maxUsedID = e.entityID;
            }
        }
        return (maxUsedID + 1);
    }
    
}
