package org.wdt;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;

public class ManagerMain {
    private static final Options options = new Options();

    public static void main(String[] args) throws ParseException, IOException {
        File Toolbox = new File(System.getProperty("user.home") + "\\AppData\\Local\\JetBrains\\Toolbox");
        if (!Toolbox.exists()) {
            throw new IOException("Toolbox is not installed!");
        }
        CommandLineParser commandLineParser = new BasicParser();
        CreateStartupScript script = new CreateStartupScript(options);
        CopyVmoptions copyVmoptions = new CopyVmoptions(options);
        JetBrainsIDEList GetList = new JetBrainsIDEList(options);
        CommandLine commandLine = commandLineParser.parse(options, args);
        if (commandLine.hasOption("ip")) {
            copyVmoptions.CopyVmoptionsFile(commandLine);
        }
        if (commandLine.hasOption("l")) {
            GetList.GetJetBrainsIDEList();
        }
        if (commandLine.hasOption("s")) {
            script.CreateScript(commandLine);
        }
    }

}
