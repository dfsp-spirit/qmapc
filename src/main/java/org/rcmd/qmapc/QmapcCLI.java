/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */

package org.rcmd.qmapc;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Command line interface handling for qmapc.
 * @author spirit
 */
public class QmapcCLI {

    private static final Logger LOGGER = Logger.getLogger(QmapcCLI.class.getName());
    private String[] args = null;
    private final Options options = new Options();

    public QmapcCLI(String[] args) {

        this.args = args;

        options.addOption("h", "help", false, "show help and exit.");
        options.addOption("v", "version", false, "show version info and exit.");
        options.addOption("a", "allow-overwrite", false, "overwrite existing output files.");
        options.addOption("l", "license", false, "show license and copyright info and exit.");
        options.addOption(Option.builder("i")
                .longOpt("input-map")
                .desc("use input map file INMAP")
                .hasArg()
                .argName("INMAP")
                .build());
        options.addOption(Option.builder("o")
                .longOpt("output-map")
                .desc("use output map file OUTMAP")
                .hasArg()
                .argName("OUTMAP")
                .build());
        options.addOption(Option.builder("f")
                .longOpt("infile-format")
                .desc("use input map format FORMAT (one of: q1, q2, q3)")
                .hasArg()
                .argName("FORMAT")
                .build());
        options.addOption(Option.builder("F")
                .longOpt("outfile-format")
                .desc("use output map format FORMAT (one of: q1, q2, q3)")
                .hasArg()
                .argName("FORMAT")
                .build());
        options.addOption(Option.builder("b")
                .longOpt("brush-scale")
                .desc("scale brushes by FACTOR (float, e.g., 1.5)")
                .hasArg()
                .argName("FACTOR")
                .build());
        options.addOption(Option.builder("t")
                .longOpt("texture-scale")
                .desc("scale textures by FACTOR (float, e.g., 1.5)")
                .hasArg()
                .argName("FACTOR")
                .build());

    }

    public void parse() {
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "Failed to parse command line arguments.", e);
            help(1);
        }

        if (cmd.hasOption("h")) {
            help(0);
        }
        
        if (cmd.hasOption("a")) {
            Settings.getInstance().setAppSetting("allowOverwrite", "true");
        } else {
            Settings.getInstance().setAppSetting("allowOverwrite", "false");
        }

        if (cmd.hasOption("v")) {
            System.out.println("0.01");
            System.exit(0);
        }

        if (cmd.hasOption("l")) {
            System.out.println("");
            System.out.println("    qmapc -- parser and translator for map files of the Quake series games");
            System.out.println("");
            System.out.println("    Copyright 2017 Tim Schaefer, http://rcmd.org/ts/");
            System.out.println("");
            System.out.println("    This program is free software: you can redistribute it and/or modify\n"
                    + "    it under the terms of the GNU General Public License as published by\n"
                    + "    the Free Software Foundation, either version 3 of the License, or\n"
                    + "    (at your option) any later version.\n"
                    + "\n"
                    + "    This program is distributed in the hope that it will be useful,\n"
                    + "    but WITHOUT ANY WARRANTY; without even the implied warranty of\n"
                    + "    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"
                    + "    GNU General Public License for more details.\n"
                    + "\n"
                    + "    You should have received a copy of the GNU General Public License\n"
                    + "    along with this program.  If not, see <http://www.gnu.org/licenses/>.");
            System.exit(0);
        }

        if (cmd.hasOption("i")) {
            String inputMapFile = cmd.getOptionValue("i");
            LOGGER.log(Level.INFO, "Using input map file '" + inputMapFile + "'.");
            Settings.getInstance().setAppSetting("inputFile", inputMapFile);
        } else {
            LOGGER.log(Level.SEVERE, "Missing required '-i' option: please specify an input file.");
            help(1);
        }
        
        if (cmd.hasOption("o")) {
            String outputMapFile = cmd.getOptionValue("o");
            LOGGER.log(Level.INFO, "Using output map file '" + outputMapFile + "'.");
            Settings.getInstance().setAppSetting("outputFile", outputMapFile);
        } else {
            LOGGER.log(Level.SEVERE, "Missing required '-o' option: please specify an output file.");
            help(1);
        }
        
        if (cmd.hasOption("f")) {
            String inputFormat = cmd.getOptionValue("f");
            if(!(inputFormat.equals("q1") || inputFormat.equals("q2") || inputFormat.equals("q3"))) {
                LOGGER.log(Level.SEVERE, "Invalid input format.");
                help(1);
            }
            LOGGER.log(Level.INFO, "Using input map format '" + inputFormat + "'.");
            Settings.getInstance().setAppSetting("inputFormat", inputFormat);
        } else {
            LOGGER.log(Level.INFO, "Input map format not specified via '-f' option, assuming '" + Settings.getInstance().getAppSettingString("inputFormat") + "'.");
        }
        
        if (cmd.hasOption("F")) {
            String outputFormat = cmd.getOptionValue("F");
            if(!(outputFormat.equals("q1") || outputFormat.equals("q2") || outputFormat.equals("q3"))) {
                LOGGER.log(Level.SEVERE, "Invalid output format.");
                help(1);
            }
            LOGGER.log(Level.INFO, "Using output map format '" + outputFormat + "'.");
            Settings.getInstance().setAppSetting("outputFormat", outputFormat);
        } else {
            LOGGER.log(Level.INFO, "Output map format not specified via '-F' option, assuming '" + Settings.getInstance().getAppSettingString("outputFormat") + "'.");
        }
        
        if (cmd.hasOption("b")) {
            Float brushScale = Float.parseFloat(cmd.getOptionValue("b"));
            if(!(brushScale > 0f)) {
                LOGGER.log(Level.SEVERE, "Invalid brush scale, must be > 0.");
                help(1);
            }
            LOGGER.log(Level.INFO, "Using brush scale '" + brushScale + "'.");
            Settings.getInstance().setAppSetting("outputBrushScaleX", brushScale.toString());
            Settings.getInstance().setAppSetting("outputBrushScaleY", brushScale.toString());
            Settings.getInstance().setAppSetting("outputBrushScaleZ", brushScale.toString());
        } else {
            LOGGER.log(Level.INFO, "Brush scale not specified via '-b' option, assuming '" +Settings.getInstance().getAppSettingString("outputBrushScaleX") + "'.");
        }
        
        if (cmd.hasOption("t")) {
            Float textureScale = Float.parseFloat(cmd.getOptionValue("t"));
            if(!(textureScale > 0f)) {
                LOGGER.log(Level.SEVERE, "Invalid texture scale, must be > 0.");
                help(1);
            }
            LOGGER.log(Level.INFO, "Using texture scale '" + textureScale + "'.");
            Settings.getInstance().setAppSetting("outputTextureScaleVertical", textureScale.toString());
            Settings.getInstance().setAppSetting("outputTextureScaleHorizontal", textureScale.toString());
        } else {
            LOGGER.log(Level.INFO, "Texture scale not specified via '-b' option, assuming '" + Settings.getInstance().getAppSettingString("outputTextureScaleX") + "'.");
        }

    }

    private void help(int retVal) {
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("qmapc", options);
        System.exit(retVal);
    }
}
