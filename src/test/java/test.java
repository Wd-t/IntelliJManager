import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.io.FileUtils;
import org.wdt.GetToolboxSetting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class test {
    public static void main(String[] args) throws IOException {
        List<String> idepath = new ArrayList<>();
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
                                idepath.add(channelid + "\\" + ItemJSONObject.getString("build"));
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
        List<String> l = new ArrayList<>();
        for (File s : new File("E:\\JetBrains").listFiles()) {
            l.add(s.getCanonicalPath());
        }
        for (int i = 0; i < 6; i++) {
            String command = "java -jar D:\\IntelliJManager\\IntelliJManager-1.0-all.jar" + " -ip \"" + idepath.get(i) + "\" -cp \"" + l.get(i) + "\"";
            FileUtils.writeStringToFile(new File(i + ".bat"), command);

        }
    }
}
