/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rcmd.qmapc.ioutil.IOUtil;
import org.rcmd.qmapc.ir.model.quakemap.QuakeMapModel;
import org.rcmd.qmapc.ir.translation.Quake2MapGenerator;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;
import org.rcmd.qmapc.parsing.parser.Quake2MapParser;
import org.rcmd.qmapc.treewalking.parsetree.IMapModelGeneratingVisitor;
import org.rcmd.qmapc.treewalking.parsetree.ModelGeneratingVisitor;


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
        
        System.out.println(Settings.getInstance().getAppTag() + "Parsing input map '" + Settings.getInstance().getAppSettingString("inputFile") + "' of length " + inputMapContent.length() + " characters.");        
        q2mp.map();
        
        System.out.println(Settings.getInstance().getAppTag() + "Visiting parse tree to generate internal model.");
        
        IMapModelGeneratingVisitor visitor = new ModelGeneratingVisitor();
        q2mp.root.visit(visitor);
        
        QuakeMapModel model = visitor.getMapModel();
        
        System.out.println(Settings.getInstance().getAppTag() + "Generating output in format Quake 2 for IR.");
        
        Quake2MapGenerator q2gen = new Quake2MapGenerator();
        String mapString = q2gen.genLevel(model);
        
        String outputFileName = Settings.getInstance().getAppSettingString("outputFile");
        System.out.println(Settings.getInstance().getAppTag() + "Writing output map to '" + outputFileName + "'.");
        try {
            IOUtil.stringToTextFile(outputFileName, mapString);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Failed to write output to file: " + ex.getMessage());
            System.exit(1);
        }
        
        System.out.println(Settings.getInstance().getAppTag() + "Done. Exiting.");
        System.exit(0);
    }

}
