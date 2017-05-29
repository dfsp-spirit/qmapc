/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ioutil;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author spirit
 */
public class IOUtilTest {

    public IOUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @org.junit.Test
    public void testItProperlyParsesAnEntityLineWithAStringValue() {
        String testMapResourcePath = IOUtil.getTestMap();
        String testMapContent = "";
        try {
            testMapContent = IOUtil.readTextFileToString(testMapResourcePath);
        } catch (IOException ex) {
            Logger.getLogger(IOUtilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue("The loaded test map should have a non-zero size.", testMapContent.length() > 0);
    }

}
