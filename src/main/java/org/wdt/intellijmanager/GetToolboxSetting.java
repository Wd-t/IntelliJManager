package org.wdt.intellijmanager;

import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class GetToolboxSetting {

    public static String GetJetBrainsIDEInstallPath() throws IOException {

        return ToolboxSettingJSONObect().getString("install_location");
    }

    public static String GetShellScriptsPath() throws IOException {
        return ToolboxSettingJSONObect().getJSONObject("shell_scripts").getString("location");
    }

    public static JSONObject ToolboxSettingJSONObect() throws IOException {
        File ToolboxSettingFile = new File(System.getProperty("user.home") + "\\AppData\\Local\\JetBrains\\Toolbox\\.settings.json");
        return JSONObject.parseObject(FileUtils.readFileToString(ToolboxSettingFile, "UTF-8"));
    }
}

