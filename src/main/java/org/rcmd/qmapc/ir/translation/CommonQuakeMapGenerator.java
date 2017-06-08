/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.translation;

import org.rcmd.qmapc.ir.model.basic.Point3DInteger;
import org.rcmd.qmapc.ir.model.quakemap.EntityProperty;

/**
 *
 * @author spirit
 */
public class CommonQuakeMapGenerator {
    
    protected String genPoint3DInteger(Point3DInteger p) {
        StringBuilder sb = new StringBuilder();
        sb.append("( ");
        sb.append(p.x).append(" ");
        sb.append(p.y).append(" ");
        sb.append(p.z).append(" ");
        sb.append(")");
        return sb.toString();
    }
    
    protected String genEntityProperty(EntityProperty p) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"").append(p.key).append("\" ").append("\"").append(p.value).append("\"\n");
        return sb.toString();
    }
    
}
