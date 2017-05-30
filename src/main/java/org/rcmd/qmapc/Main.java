/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc;

import java.util.Properties;


/**
 *
 * @author spirit
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        
        Properties settings = getDefaultSettings();
        new QmapcCLI(args).parseInto(settings);
        
        System.out.println("Main: file is " + settings.getProperty("inputFile"));
    }
    
    private static Properties getDefaultSettings() {
        Properties settings = new Properties();
        settings.setProperty("inputFile", "mymap.map");
        settings.setProperty("outputFile", "mymap_converted.map");
        settings.setProperty("inputFormat", "q2");
        settings.setProperty("outputFormat", "q1");
        settings.setProperty("allowOverwrite", "false");
        return settings;
    }

}
