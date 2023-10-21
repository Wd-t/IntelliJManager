package org.wdt.intellijmanager;

import org.apache.commons.cli.*;
import org.wdt.intellijmanager.command.CopyVmoptionsCommand;
import org.wdt.intellijmanager.command.PrintJetBrainsToolsCommand;
import org.wdt.intellijmanager.command.PrintVersionCommand;
import org.wdt.intellijmanager.command.SaveConfigFileCommand;
import org.wdt.intellijmanager.utils.ToolboxUtils;
import org.wdt.utils.io.FileUtils;

import java.io.IOException;

public class ManagerMain {
    private static final Options options = new Options();

    public static void main(String[] args) throws ParseException, IOException {
        if (FileUtils.isFileNotExists(ToolboxUtils.getToolboxInstallPath())) {
            throw new IOException("Toolbox is not installed!");
        }
        CommandLineParser commandLineParser = new DefaultParser();
        SaveConfigFileCommand configFile = new SaveConfigFileCommand(options);
        CopyVmoptionsCommand copyVmoptionsCommand = new CopyVmoptionsCommand(options);
        PrintJetBrainsToolsCommand printList = new PrintJetBrainsToolsCommand(options);
        PrintVersionCommand info = new PrintVersionCommand(options);
        CommandLine commandLine = commandLineParser.parse(options, args);
        if (commandLine.hasOption(CopyVmoptionsCommand.getToolsPathCommandOption())) {
            copyVmoptionsCommand.copyVmoptionsFile(commandLine);
        } else if (commandLine.hasOption(SaveConfigFileCommand.getSaveFileOption())) {
            configFile.copyFile(commandLine);
        }
        if (commandLine.hasOption(PrintJetBrainsToolsCommand.getOption())) {
            printList.printJetBrainsTools();
        }
        if (commandLine.hasOption(PrintVersionCommand.getOption())) {
            info.printVersionNumber();
        }
    }

}
