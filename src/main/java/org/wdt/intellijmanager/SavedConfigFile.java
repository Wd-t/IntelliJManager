package org.wdt.intellijmanager;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SavedConfigFile {
    public SavedConfigFile(Options options) {
        options.addOption(SavedConfigFile.getSaveFileOption());
    }

    public static Option getSaveFileOption() {
        return Option.builder("sf").longOpt("savefile").required(false).hasArg().build();
    }

    public void CopyFile(CommandLine commandLine) throws IOException {
        File file = new File(commandLine.getOptionValue("sf"));
        List<String> line = FileUtils.readLines(file, "UTF-8");
        String ip = line.get(0);
        String cp = line.get(1);
        CopyVmoptions.CopyVmoptionsFile(ip, cp, file);
    }
}
