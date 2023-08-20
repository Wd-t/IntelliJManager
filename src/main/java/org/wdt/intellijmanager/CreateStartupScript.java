package org.wdt.intellijmanager;

import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class CreateStartupScript {
    public CreateStartupScript(Options options) {
        Option option = Option.builder("s").longOpt("script").hasArgs().required(false).desc("choose ide").build();
        options.addOption(option);
    }

    public void CreateScript(CommandLine commandLine) {
        if (commandLine.getOptionValue("s").equals("all")) {
            try {
                File IDEAppPath = new File(GetToolboxSetting.GetJetBrainsIDEInstallPath() + "\\apps");
                if (Objects.nonNull(IDEAppPath.listFiles())) {
                    for (File file : Objects.requireNonNull(IDEAppPath.listFiles())) {
                        if (file.isDirectory() && !file.getName().equals("Toolbox")) {
                            if (Objects.nonNull(file.listFiles())) {
                                for (File channelid : Objects.requireNonNull(file.listFiles())) {
                                    File HistroyFile = new File(channelid + "\\.history.json");
                                    JSONObject HistoryJSONObject = JSONObject.parseObject(FileUtils.readFileToString(HistroyFile, "UTF-8"));
                                    JSONObject FeedJSONObject = HistoryJSONObject.getJSONArray("history")
                                            .getJSONObject(HistoryJSONObject.getJSONArray("history").size() - 1);
                                    String buildNumber = FeedJSONObject.getJSONObject("item").getString("build");
                                    copyStartScript(channelid, buildNumber, FeedJSONObject);
                                    copyIcon(channelid, buildNumber, FeedJSONObject);
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            } catch (IOException | NullPointerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void copyStartScript(File channelid, String buildNumber, JSONObject FeedJSONObject) throws IOException {

        String exePath = FilenameUtils.separatorsToWindows(channelid + "\\" + buildNumber + "\\" + FeedJSONObject.getJSONObject("item")
                .getJSONObject("package").getString("command"));
        InputStream Starter = getClass().getResourceAsStream("/idea.cmd");
        File StarterPath = new File(GetToolboxSetting.GetShellScriptsPath()
                + "\\" + new File(exePath).getName().replace("64.exe", "") + ".cmd");
        FileOutputStream fileOutputStream = new FileOutputStream(StarterPath);
        IOUtils.copy(Objects.requireNonNull(Starter), fileOutputStream);
        FileUtils.writeStringToFile(StarterPath, FileUtils.readFileToString(StarterPath, "UTF-8")
                .replace(":idepath", exePath), "UTF-8");
        System.out.println("File to " + StarterPath);

    }

    private void copyIcon(File channelid, String buildNumber, JSONObject FeedJSONObject) throws IOException {
        File exePath = new File(FilenameUtils.separatorsToWindows(channelid + "\\" + buildNumber + "\\" + FeedJSONObject.getJSONObject("item")
                .getJSONObject("package").getString("command")));
        File BinPath = new File(exePath.getParent());
        for (File file : BinPath.listFiles()) {
            if (FilenameUtils.getExtension(file.getAbsolutePath()).equals("ico")) {
                File icon = new File(GetToolboxSetting.GetShellScriptsPath()
                        + "\\icon\\" + exePath.getName().replace("64.exe", "") + ".ico");
                FileUtils.copyFile(file, icon);
                System.out.println("File to " + icon);
            }
        }
    }
}
