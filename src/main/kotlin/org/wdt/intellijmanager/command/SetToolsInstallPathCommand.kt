package org.wdt.intellijmanager.command

import org.apache.commons.cli.CommandLine
import org.wdt.intellijmanager.utils.getOption
import org.wdt.intellijmanager.utils.toolboxSettingJsonFile
import org.wdt.utils.gson.Json
import org.wdt.utils.gson.readFileToJsonObject
import org.wdt.utils.gson.writeObjectToFile
import org.wdt.utils.io.createDirectories
import java.io.File

fun setInstallPath(command: CommandLine) {
    File(command.getOptionValue(setInstallOption)).run {
        createDirectories()
        toolboxSettingJsonFile.readFileToJsonObject().let {
            it.addProperty("install_location", canonicalPath)
            writeObjectToFile(it, Json.GSON_BUILDER.setPrettyPrinting())
        }
    }
}

val setInstallOption = getOption("i", "install", true)
