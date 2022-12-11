package org.autoquest.service;

import org.apache.commons.cli.*;

import java.nio.file.Path;

public class CommandLineP {

    private static Options options = new Options();
  //  private static Option input = new Option("x", "xml", true, "create io points xml file");

    public CommandLineP() {
    }

    public static void parseCL(String[] args) {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CreateXMLForRapidScada createXMLForRapidScada  = new CreateXMLForRapidScada();
        String path = "";
        String filename = "";
//        input = new Option("x", "xml", true, "create io points xml file");
          options.addOption(Option.builder("x").longOpt("xml")
                .desc("create io points xml file")
                .hasArg(false)
                .build());
        options.addOption(Option.builder("p").longOpt("path")
                .desc("xml file path")
                .hasArg(true)
                .build());
        options.addOption(Option.builder("n").longOpt("name")
                .desc("xml file name")
                .hasArg(true)
                .build());

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("xml")) {
                if (cmd.hasOption("path")) {
                    path =  cmd.getOptionValue("path");
                }
                if (cmd.hasOption("name")) {
                    filename =  cmd.getOptionValue("name");
                }
                createXMLForRapidScada.createXMLFile(path, filename);
                System.exit(0);
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("help", options);
            System.exit(0);
//            throw new RuntimeException(e);
        }

    }
}
