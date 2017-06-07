/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.model.quakemap;

/**
 * An entity property, defined by a key/value pair.
 * 
 * @author spirit
 */
public class EntityProperty {
    
    public String key;
    public String value;
    
    public EntityProperty(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
}
