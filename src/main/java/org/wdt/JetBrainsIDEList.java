package org.wdt;

import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class JetBrainsIDEList {

    public JetBrainsIDEList(Options options) {
        Option option = Option.builder("l").longOpt("list").hasArgs().required(false).desc("choose ide").build();
        options.addOption(option);
    }

    public void GetJetBrainsIDEList() {
        try {

            for (File file : GetIDEDirList.IdeDirList()) {
                if (file.isDirectory()) {
                    File ProductInfoFile = new File(file.getCanonicalPath() + "\\product-info.json");
                    if (ProductInfoFile.exists()) {
                        JSONObject ProductInfoFileJson = JSONObject.parseObject(FileUtils.readFileToString(ProductInfoFile, "UTF-8"));
                        System.out.println("IDE Name : " + ProductInfoFileJson.getString("name") + ",Version : " + ProductInfoFileJson.getString("version")
                                + ",BuildNumber : " + ProductInfoFileJson.getString("buildNumber"));
                    }
                }
            }

        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}


