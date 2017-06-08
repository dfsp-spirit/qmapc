/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.translation;

import java.util.logging.Level;
import org.rcmd.qmapc.ir.model.basic.Point3DInteger;
import org.rcmd.qmapc.ir.model.quakemap.Brush;
import org.rcmd.qmapc.ir.model.quakemap.Entity;
import org.rcmd.qmapc.ir.model.quakemap.EntityProperty;
import org.rcmd.qmapc.ir.model.quakemap.Face;

/**
 *
 * @author spirit
 */
public class Quake1MapGenerator extends CommonQuakeMapGenerator implements IQuakeMapGenerator {
    
    @Override
    public String genBrush(Brush b) {
        StringBuilder sb = new StringBuilder();
        sb.append("// brush ").append(b.brushID).append("\n");
        sb.append("{\n");
        for(Face f : b.getFaces()) {
            sb.append(genFace(f));
        }
        sb.append("}\n");
        return sb.toString();
    }
    
    @Override
    public String genEntity(Entity e) {
        StringBuilder sb = new StringBuilder();
        sb.append("// entity ").append(e.entityID).append("\n");
        sb.append("{\n");
        for(EntityProperty p : e.getEntityProperties()) {
            sb.append(genEntityProperty(p));
        }
        for(Brush b : e.getBrushes()) {
            sb.append(genBrush(b));
        }
        sb.append("}\n");        
        return sb.toString();        
    }
    
    @Override
    public String genLevel(Level l) {
        StringBuilder sb = new StringBuilder();
        
        return sb.toString();
    }
    
    private String genFace(Face f) {
        StringBuilder sb = new StringBuilder();
        sb.append(genPoint3DInteger(f.point1)).append(" ");
        sb.append(genPoint3DInteger(f.point2)).append(" ");
        sb.append(genPoint3DInteger(f.point3)).append(" ");
        sb.append(f.texturePath).append(" ");
        sb.append(f.textureHorizontalShift).append(" ");
        sb.append(f.textureVerticalShift).append(" ");
        sb.append(f.textureRotation).append(" ");
        sb.append(f.textureHorizontalScale).append(" ");
        sb.append(f.textureVerticalScale).append("\n");
        return sb.toString();
    }        
    
}
