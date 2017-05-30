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

public class QmapcCLI {

    private static final Logger LOGGER = Logger.getLogger(QmapcCLI.class.getName());
    private String[] args = null;
    private final Options options = new Options();

    public QmapcCLI(String[] args) {

        this.args = args;

        options.addOption("h", "help", false, "show help and exit.");
        options.addOption("v", "version", false, "show version info and exit.");
        options.addOption("a", "allow-overwrite", false, "overwrite existing output files.");
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

    }

    public void parseInto(Properties settings) {
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                help(0);
            }

            if (cmd.hasOption("v")) {
                System.out.println("0.01");
                System.exit(0);
            }

            if (cmd.hasOption("i")) {
                String inputMapFile = cmd.getOptionValue("i");
                LOGGER.log(Level.INFO, "Using input map file '" + inputMapFile + "'.");
                // TODO: do something with this info: add to our app settings.
                settings.put("inputFile", inputMapFile);
            } else {
                LOGGER.log(Level.SEVERE, "Missing required i option");
                help(1);
            }

        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "Failed to parse command line arguments.", e);
            help(1);
        }
    }

    private void help(int retVal) {
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("qmapc", options);
        System.exit(retVal);
    }
}
