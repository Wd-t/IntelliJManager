package org.wdt.intellijmanager.command

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option
import org.wdt.intellijmanager.objects.ConfigObject
import org.wdt.intellijmanager.utils.OptionUtils
import org.wdt.utils.gson.Json
import org.wdt.utils.io.isFileNotExists
import org.wdt.utils.io.writeStringToFile
import java.io.File

class ConfigCommand {
    fun startTask(commandLine: CommandLine) {
        if (configFile.isFileNotExists()) {
            configFile.writeStringToFile(Json.gsonBuilder.serializeNulls().create().toJson(ConfigObject()))
        }
        if (commandLine.hasOption(openConfigFileOption)) {
            Runtime.getRuntime().exec(arrayOf("cmd.exe", "/C", "start", configFile.canonicalPath))
        } else {
            println(ConfigObject.getCofnig())
        }
    }

    companion object {
        @JvmStatic
        val configOption: Option = OptionUtils.getOption("c", "config", false)

        @JvmStatic
        val openConfigFileOption: Option = OptionUtils.getOption("o", "open", false)

        @JvmStatic
        val configFile: File = SaveConfigFileCommand.getSavdFilePath("intellijmanger.json")
    }
}