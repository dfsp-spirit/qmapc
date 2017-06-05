/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ioutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Some basic utility function for input/output.
 * @author spirit
 */
public class IOUtil {

    public static String readTextFileToString(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }
    }
    
    public static String getTestMap(String mapNameIncludingFileExtension) {
        return IOUtil.class.getResource("/org/rcmd/qmapc/maps/" + mapNameIncludingFileExtension).getFile();
    }
    
    
    public static Boolean getBoolean(String s) {
       
        if(s.toLowerCase().equals("true")) {
            return(true);
        }
        else if(s.toLowerCase().equals("false")) {
            return(false);
        }
        else {
            System.err.println("ERROR: Settings: Could not load setting '" + s + "' from settings as Boolean, invalid format.");
            System.exit(1);
            return(false);      // never reached
        }
    }
    
    public static String getSpacesOfDepth(int depth) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

}
