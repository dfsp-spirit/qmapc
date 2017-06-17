/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rcmd.qmapc.ioutil.IOUtil;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;
import org.rcmd.qmapc.parsing.parser.Quake2MapParser;


/**
 *
 * @author spirit
 */
public class Main {
    
    private static final Logger LOGGER = Logger.getLogger(QmapcCLI.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
               
        Properties settings = getDefaultSettings();
        new QmapcCLI(args).parseInto(settings);
        
        String inputMapContent = "";
        try {
            inputMapContent = IOUtil.readTextFileToString(settings.getProperty("inputFile"));
        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load input map '" + settings.getProperty("inputFile") + ": '" + e.getMessage() + "'.");
            System.exit(1);
        }
        
        File outputFile = new File(settings.getProperty("outputFile"));
        if(outputFile.exists() && !(IOUtil.getBoolean(settings.getProperty("allowOverwrite")) && outputFile.isFile()&& outputFile.canWrite())) {
            LOGGER.log(Level.SEVERE, "Output file '" + settings.getProperty("outputFile") + "' exists. Add command line option to allow overwriting existing files and make sure it is writeable if you know what you are doing.");
            System.exit(1);
        }
        
        Quake2MapLexer q2ml = new Quake2MapLexer(inputMapContent);
        Quake2MapParser q2mp = new Quake2MapParser(q2ml);
        
        System.out.println("Parsing input map '" + settings.getProperty("inputFile") + "' of length " + inputMapContent.length() + " characters.");
        
        q2mp.map();
        
        System.out.println("Done. Exiting.");
        System.exit(0);
    }
    
    private static Properties getDefaultSettings() {
        Properties settings = new Properties();
        settings.setProperty("inputFile", "mymap.map");
        settings.setProperty("outputFile", "mymap_converted.map");
        settings.setProperty("inputFormat", "q2");
        settings.setProperty("outputFormat", "q1");
        settings.setProperty("allowOverwrite", "false");
        settings.setProperty("outputBrushScaleX", "1.0");
        settings.setProperty("outputBrushScaleY", "1.0");
        settings.setProperty("outputBrushScaleZ", "1.0");
        settings.setProperty("outputTextureScaleVertical", "1.0");
        settings.setProperty("outputTextureScaleHorizontal", "1.0");
        return settings;
    }

}
