package org.wdt.intellijmanager;

import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class JetBrainsIDEList {

    public JetBrainsIDEList(Options options) {
        Option option = Option.builder("l").longOpt("list").hasArg(false).required(false).desc("choose ide").build();
        options.addOption(option);
    }

    public void GetJetBrainsIDEList() {
        try {
            File IDEAppPath = new File(GetToolboxSetting.GetJetBrainsIDEInstallPath() + "\\apps");
            if (Objects.nonNull(IDEAppPath.listFiles())) {
                for (File file : Objects.requireNonNull(IDEAppPath.listFiles())) {
                    if (file.isDirectory() && !file.getName().equals("Toolbox")) {
                        if (Objects.nonNull(file.listFiles())) {
                            for (File channelid : Objects.requireNonNull(file.listFiles())) {
                                File HistroyFile = new File(channelid + "\\.history.json");
                                JSONObject HistoryJSONObject = JSONObject.parseObject(FileUtils.readFileToString(HistroyFile, "UTF-8"));
                                JSONObject ItemJSONObject = HistoryJSONObject.getJSONArray("history")
                                        .getJSONObject(HistoryJSONObject.getJSONArray("history").size() - 1).getJSONObject("item");
                                System.out.println("IDE Name:" + ItemJSONObject.getString("name") + "," +
                                        "IDE Version:" + ItemJSONObject.getString("version") + "," +
                                        "IDE Build:" + ItemJSONObject.getString("build"));
                            }
                        } else {
                            break;
                        }
                    }
                }
            } else {
                System.out.println("warning:No IDE version available");
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}


