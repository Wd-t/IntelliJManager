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
import java.nio.file.Path;

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
            File IdeBinPath = new File(commandLine.getOptionValue("ip"));
            String CacheAddressPath = commandLine.getOptionValue("cp");
            File CacheAddress = new File(CacheAddressPath);
            Files.createDirectories(Path.of(CacheAddress.getCanonicalPath()));
            if (IdeBinPath.isDirectory() && IdeBinPath.exists()) {
                File ProductInfoFile = new File(IdeBinPath + "\\product-info.json");
                JSONObject ProductInfoFileJson = JSONObject.parseObject(FileUtils.readFileToString(ProductInfoFile, "UTF-8"));
                JSONObject LaunchFirstJson = ProductInfoFileJson.getJSONArray("launch").getJSONObject(0);
                File child = new File(FilenameUtils.separatorsToWindows(IdeBinPath.getCanonicalPath() + "\\" + LaunchFirstJson.getString("vmOptionsFilePath")));
                String Vmoptions = IOUtils.toString(requireNonNull(getClass().getResourceAsStream("/idea.vmoptions")), StandardCharsets.UTF_8)
                        .replace(":CacheAddress", FilenameUtils.separatorsToWindows(CacheAddressPath));
                FileUtils.writeStringToFile(child, Vmoptions, "UTF-8");
                System.out.println("Copy File To: " + child);
            } else {
                throw new IOException("IDE path must is a directory");
            }

        } else {
            throw new IOException("Must have -cp parameter");
        }
    }
}

