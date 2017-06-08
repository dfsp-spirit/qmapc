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
    
    Brush brush;
    
    @After
    public void tearDown() {
        brush = null;
    }
    
    @org.junit.Test
    public void testItHasTheExpectedBrushID() {
        int brushID = 5;
        Brush b = new Brush(brushID);
        assertEquals(5, b.brushID);
    }
    
    @org.junit.Test
    public void testItHasTheExpectedNumberOfFaces() {
        int brushID = 5;
        Brush b = new Brush(brushID);
        b.addFace(new Face());
        b.addFace(new Face());
        b.addFace(new Face());
        assertEquals(5, b.brushID);
        assertEquals(3, b.getFaces().size());
    }
    
}
