/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.translation;

import org.rcmd.qmapc.ir.model.basic.Point3DInteger;
import org.rcmd.qmapc.ir.model.basic.Point5DFloat;
import org.rcmd.qmapc.ir.model.basic.Point5DTriple;
import org.rcmd.qmapc.ir.model.quakemap.Brush;
import org.rcmd.qmapc.ir.model.quakemap.Entity;
import org.rcmd.qmapc.ir.model.quakemap.EntityProperty;
import org.rcmd.qmapc.ir.model.quakemap.Face;
import org.rcmd.qmapc.ir.model.quakemap.Level;
import org.rcmd.qmapc.ir.model.quakemap.PatchMesh;

/**
 *
 * @author spirit
 */
public class CommonQuakeMapGenerator implements IQuakeMapGenerator {
    
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
        for(Entity e : l.getEntities()) {
            sb.append(genEntity(e));
        }
        return sb.toString();
    }
    
    protected String genFace(Face f) {
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

    @Override
    public String genPatchMesh(PatchMesh p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.type).append("\n");
        sb.append("{").append("\n");
        sb.append(p.texturePath).append("\n");
        sb.append("(").append("\n");
        for(Point5DTriple pt : p.coordLines) {
            sb.append(genPatchCoordLine(pt));
        }        
        sb.append(")").append("\n");
        sb.append("}").append("\n");
        return sb.toString();
        
    }
    
    protected String genPatchCoordLine(Point5DTriple pt) {
        StringBuilder sb = new StringBuilder();
        sb.append("( ");
        sb.append(genBracketedPoint5D(pt.point1));
        sb.append(genBracketedPoint5D(pt.point2));
        sb.append(genBracketedPoint5D(pt.point3));
        sb.append(" )\n");
        return sb.toString();
    }
    
    protected String genBracketedPoint5D(Point5DFloat p) {
        StringBuilder sb = new StringBuilder();
        sb.append("( ");
        sb.append(genPoint5D(p));
        sb.append(" )");
        return sb.toString();        
    }
    
    protected String genPoint5D(Point5DFloat p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.c1).append(" ");
        sb.append(p.c2).append(" ");
        sb.append(p.c3).append(" ");
        sb.append(p.c4).append(" ");
        sb.append(p.c5);
        return sb.toString();        
    }
    
}
