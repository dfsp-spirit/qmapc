/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.model.quakemap;

/**
 * An entity property, defined by a key/value pair.
 * 
 * @author spirit
 */
public class EntityPropertyModel extends QuakeObjectModel {
    
    public String key;
    public String value;
    
    public EntityPropertyModel(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
}
