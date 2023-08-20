package org.wdt.intellijmanager;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public class CopyVmoptions {

    public CopyVmoptions(Options options) {
        Option option = Option.builder("ip").longOpt("idepath").hasArgs().required(false).desc("choose ide").build();
        Option cp = Option.builder("cp").longOpt("cachepath").hasArgs().required(false).desc("choose ide").build();
        options.addOption(option);
        options.addOption(cp);
    }

    public void CopyVmoptionsFile(CommandLine commandLine) throws IOException {
        if (commandLine.hasOption("cp")) {
            File IdeBinPath = new File(commandLine.getOptionValue("ip") + "\\bin");
            File CacheAddress = new File(commandLine.getOptionValue("cp"));
            Files.createDirectories(Path.of(CacheAddress.getCanonicalPath()));
            if (IdeBinPath.isDirectory() && IdeBinPath.exists()) {
                for (File child : requireNonNull(IdeBinPath.listFiles())) {
                    if (Pattern.compile("\\.exe\\.vmoptions").matcher(child.getName()).find()) {
                        String Vmoptions = IOUtils.toString(requireNonNull(getClass().getResourceAsStream("/idea.vmoptions")), StandardCharsets.UTF_8)
                                .replace(":CacheAddress", FilenameUtils.separatorsToWindows(CacheAddress.getCanonicalPath()));
                        FileUtils.writeStringToFile(child, Vmoptions, "UTF-8");
                        System.out.println("Copy File To: " + child);
                    } else {
                        System.out.println("The " + child.getName() + " is not a vmoptions file");
                    }
                }
            } else {
                throw new IOException("The ide bin address does not exist");
            }
        } else {
            throw new IOException("Must have -cp parameter");
        }
    }
}

