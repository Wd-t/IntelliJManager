package org.wdt.intellijmanager;

import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class VersionInfo {
    public VersionInfo(Options options) {
        Option option = Option.builder("v").longOpt("version").hasArg(false).required(false).desc("choose ide").build();
        options.addOption(option);
    }

    public void getVersionInfo() throws IOException {
        System.out.println("Application Version: 2.3.0");
        File file = new File(System.getProperty("user.home") + "\\AppData\\Local\\JetBrains\\Toolbox\\state.json");
        System.out.println("Toolbox Version: " + JSONObject.parseObject(FileUtils.readFileToString(file, "UTF-8")).getString("appVersion"));
    }
}