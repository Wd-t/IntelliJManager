package org.wdt.intellijmanager.command;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.wdt.intellijmanager.utils.OptionUtils;
import org.wdt.utils.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaveConfigFileCommand {
    public SaveConfigFileCommand(Options options) {
        options.addOption(getSaveFileOption());
    }

    public static Option getSaveFileOption() {
        return OptionUtils.getOption("sf", "savefile", true);
    }

    public void copyFile(CommandLine commandLine) throws IOException {
        File file = new File(commandLine.getOptionValue("sf"));
        List<String> line = FileUtils.readFileToLines(file);
        String ip = line.get(0);
        String cp = line.get(1);
        CopyVmoptionsCommand.copyVmoptionsFile(ip, cp, file);
    }
}
