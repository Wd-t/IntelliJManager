package org.wdt.intellijmanager;

import com.alibaba.fastjson2.JSONObject;
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

import static java.util.Objects.requireNonNull;

public class CopyVmoptions {

    public CopyVmoptions(Options options) {
        Option option = Option.builder("ip").longOpt("idepath").hasArgs().required(false).build();
        Option cp = Option.builder("cp").longOpt("cachepath").hasArgs().required(false).build();
        options.addOption(option);
        options.addOption(cp);
    }

    public static void CopyVmoptionsFile(String ip, String cp, File saveFile) throws IOException {
        File IdeBinPath = new File(ip);
        File CacheAddress = new File(cp);
        Files.createDirectories(CacheAddress.toPath());
        if (IdeBinPath.isDirectory() && IdeBinPath.exists()) {
            File ProductInfoFile = new File(IdeBinPath + "\\product-info.json");
            JSONObject ProductInfoFileJson = JSONObject.parseObject(FileUtils.readFileToString(ProductInfoFile, "UTF-8"));
            JSONObject LaunchFirstJson = ProductInfoFileJson.getJSONArray("launch").getJSONObject(0);
            File child = new File(FilenameUtils.separatorsToWindows(IdeBinPath.getCanonicalPath() + "\\" + LaunchFirstJson.getString("vmOptionsFilePath")));
            String Vmoptions = IOUtils.toString(requireNonNull(CopyVmoptions.class.getResourceAsStream("/idea.vmoptions")), StandardCharsets.UTF_8)
                    .replace(":CacheAddress", FilenameUtils.separatorsToSystem(cp));
            FileUtils.writeStringToFile(child, Vmoptions, "UTF-8");
            System.out.println("Copy File To: " + child);
            if (saveFile != null) {
                FileUtils.writeStringToFile(saveFile, ip + "\n" + cp, "UTF-8", false);
            }
        } else {
            throw new IOException("IDE path must is a directory");
        }
    }

    public void CopyVmoptionsFile(CommandLine commandLine) throws IOException {
        if (commandLine.hasOption("cp")) {
            if (commandLine.hasOption("sf")) {
                CopyVmoptionsFile(commandLine.getOptionValue("ip"), commandLine.getOptionValue("cp"), new File(commandLine.getOptionValue("sf")));
            } else {
                CopyVmoptionsFile(commandLine.getOptionValue("ip"), commandLine.getOptionValue("cp"), null);
            }
        } else {
            throw new IOException("Must have -cp parameter");
        }
    }
}

