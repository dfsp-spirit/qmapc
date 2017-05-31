/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 *
 * @author spirit
 */
public class MainTest {
    
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    
    public MainTest() {
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

    
    @Test
    public void testItExitsWithExitCode1WhenCalledWithoutArguments() {
        exit.expectSystemExitWithStatus(1);
        String[] args = new String[]{};
        Main.main(args);
    }
    
    @Test
    public void testItExitsWithExitCode0WhenCalledWithHelpArgument() {
        exit.expectSystemExitWithStatus(0);
        String[] args = new String[]{"-h"};
        Main.main(args);
    }
    
    @Test
    public void testItExitsWithExitCode0WhenCalledWithVersionArgument() {
        exit.expectSystemExitWithStatus(0);
        String[] args = new String[]{"-v"};
        Main.main(args);
    }
    
    @Test
    public void testItExitsWithExitCode0WhenCalledWithLicenseArgument() {
        exit.expectSystemExitWithStatus(0);
        String[] args = new String[]{"-l"};
        Main.main(args);
    }
    
}
