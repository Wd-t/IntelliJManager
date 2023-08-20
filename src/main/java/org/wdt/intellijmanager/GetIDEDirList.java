package org.wdt.intellijmanager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetIDEDirList {
    public static List<File> IdeDirList() throws IOException {
        List<File> list = new ArrayList<>();
        File IDEAppPath = new File(GetToolboxSetting.GetJetBrainsIDEInstallPath());
        if (Objects.nonNull(IDEAppPath.listFiles())) {
            for (File file : Objects.requireNonNull(IDEAppPath.listFiles())) {
                if (file.isDirectory() && !file.getName().equals("scripts") && !file.getName().equals("download")) {
                    list.add(file);
                }
            }
        } else {
            System.out.println("warning:No IDE version available");
        }
        return list;
    }
}
