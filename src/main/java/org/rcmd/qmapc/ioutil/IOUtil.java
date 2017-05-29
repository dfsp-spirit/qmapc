/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.ioutil;

import java.io.BufferedReader;
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
    
    public static String getTestMap() {
        return IOUtil.class.getResource("/org/rcmd/qmapc/parsing/resources/maps/spirit2dm9.map").getFile();
    }

}
