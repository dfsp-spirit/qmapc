/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ir.model.quakemap;

import org.junit.After;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author spirit
 */
public class BrushTest {
    
    BrushModel brush;
    
    @After
    public void tearDown() {
        brush = null;
    }
    
    @org.junit.Test
    public void testItHasTheExpectedBrushID() {
        int brushID = 5;
        BrushModel b = new BrushModel(brushID);
        assertEquals(5, b.brushID);
    }
    
    @org.junit.Test
    public void testItHasTheExpectedNumberOfFaces() {
        int brushID = 5;
        BrushModel b = new BrushModel(brushID);
        b.addFace(new FaceModel());
        b.addFace(new FaceModel());
        b.addFace(new FaceModel());
        assertEquals(5, b.brushID);
        assertEquals(3, b.getFaces().size());
    }
    
}
