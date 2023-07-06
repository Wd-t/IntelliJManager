package org.wdt;

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

import static java.util.Objects.requireNonNull;

public class CreateStartupScript {
    public CreateStartupScript(Options options) {
        Option option = Option.builder("s").longOpt("script").hasArgs().required(false).desc("choose ide").build();
        options.addOption(option);
    }

    public void CreateScript(CommandLine commandLine) {
        if (commandLine.getOptionValue("s").equals("all")) {
            try {
                for (File file : GetIDEDirList.IdeDirList()) {
                    if (file.isDirectory() && !file.getName().equals("scripts") && !file.getName().equals("download")) {
                        File ProductInfoFile = new File(file.getCanonicalPath() + "\\product-info.json");
                        JSONObject ProductInfoFileJson = JSONObject.parseObject(FileUtils.readFileToString(ProductInfoFile, "UTF-8"));
                        JSONObject LaunchFirstJson = ProductInfoFileJson.getJSONArray("launch").getJSONObject(0);
                        String exePath = FilenameUtils.separatorsToWindows(file.getCanonicalPath() + "\\"
                                + LaunchFirstJson.getString("launcherPath"));
                        File StarterPath = new File(GetToolboxSetting.GetShellScriptsPath()
                                + "\\" + new File(exePath).getName().replace("64.exe", "") + ".cmd");
                        String Scripts = IOUtils.toString(requireNonNull(getClass().getResourceAsStream("/idea.cmd")), StandardCharsets.UTF_8).replace(":idepath", exePath);
                        FileUtils.writeStringToFile(StarterPath, Scripts, "UTF-8");
                        System.out.println("File to " + StarterPath);
                        copyIcon(new File(exePath));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    private void copyIcon(File exePath) throws IOException {
        File BinPath = new File(exePath.getParent());
        for (File file : requireNonNull(BinPath.listFiles())) {
            if (FilenameUtils.getExtension(file.getAbsolutePath()).equals("ico")) {
                File icon = new File(GetToolboxSetting.GetShellScriptsPath()
                        + "\\icon\\" + exePath.getName().replace("64.exe", "") + ".ico");
                FileUtils.copyFile(file, icon);
                System.out.println("File to " + icon);
            }
        }
    }
}
