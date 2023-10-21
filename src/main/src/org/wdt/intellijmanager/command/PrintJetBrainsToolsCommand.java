package org.wdt.intellijmanager.command;


import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.wdt.intellijmanager.objects.StateObject;
import org.wdt.intellijmanager.objects.ToolsObject;
import org.wdt.intellijmanager.utils.OptionUtils;
import org.wdt.intellijmanager.utils.ToolboxUtils;
import org.wdt.utils.gson.JsonUtils;
import org.wdt.utils.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PrintJetBrainsToolsCommand {

    public PrintJetBrainsToolsCommand(Options options) {
        options.addOption(getOption());
    }

    public static List<File> getJetBrainsToolsDirectorys() throws IOException {
        StateObject stateObject = JsonUtils.readFileToClass(ToolboxUtils.getToobloxStateJsonFile(), StateObject.class);
        List<ToolsObject> toolsObjectList = stateObject.getTools();
        if (toolsObjectList == null || toolsObjectList.isEmpty()) {
            System.out.println("warning:No IDE version available");
        } else {
            List<File> directoryList = new ArrayList<>();
            for (ToolsObject toolsObject : toolsObjectList) {
                directoryList.add(FileUtils.toFile(toolsObject.getInstallLocation()));
            }
            return directoryList;
        }
        return null;
    }

    public static Option getOption() {
        return OptionUtils.getOption("l", "list");
    }

    public void printJetBrainsTools() {
        try {
            StateObject stateObject = JsonUtils.readFileToClass(ToolboxUtils.getToobloxStateJsonFile(), StateObject.class);
            List<ToolsObject> toolsObjectList = stateObject.getTools();
            if (toolsObjectList == null || toolsObjectList.isEmpty()) {
                System.out.println("warning:No IDE version available");
            } else {
                for (ToolsObject toolsObject : Objects.requireNonNull(stateObject.getTools())) {
                    System.out.println("IDE Name : " + toolsObject.getDisplayName() + ",Version : " + toolsObject.getDisplayVersion() + ",BuildNumber : " + toolsObject.getBuildNumber());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


