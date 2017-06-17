/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rcmd.qmapc.ioutil.IOUtil;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;
import org.rcmd.qmapc.parsing.parser.Quake2MapParser;


/**
 * Main class, its main method should be run when the program is executed.
 * @author spirit
 */
public class Main {
    
    private static final Logger LOGGER = Logger.getLogger(QmapcCLI.class.getName());

    /**
     * Main method of the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
               
        
        new QmapcCLI(args).parse();
        
        String inputMapContent = "";
        try {
            inputMapContent = IOUtil.readTextFileToString(Settings.getInstance().getAppSettingString("inputFile"));
        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load input map '" + Settings.getInstance().getAppSettingString("inputFile") + ": '" + e.getMessage() + "'.");
            System.exit(1);
        }
        
        File outputFile = new File(Settings.getInstance().getAppSettingString("outputFile"));
        if(outputFile.exists() && !(IOUtil.getBoolean(Settings.getInstance().getAppSettingString("allowOverwrite")) && outputFile.isFile()&& outputFile.canWrite())) {
            LOGGER.log(Level.SEVERE, "Output file '" + Settings.getInstance().getAppSettingString("outputFile") + "' exists. Add command line option to allow overwriting existing files and make sure it is writeable if you know what you are doing.");
            System.exit(1);
        }
        
        Quake2MapLexer q2ml = new Quake2MapLexer(inputMapContent);
        Quake2MapParser q2mp = new Quake2MapParser(q2ml);
        
        System.out.println("Parsing input map '" + Settings.getInstance().getAppSettingString("inputFile") + "' of length " + inputMapContent.length() + " characters.");
        
        q2mp.map();
        
        System.out.println("Done. Exiting.");
        System.exit(0);
    }

}
