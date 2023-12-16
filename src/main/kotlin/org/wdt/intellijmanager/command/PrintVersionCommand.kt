package org.wdt.intellijmanager.command

import org.wdt.intellijmanager.utils.getOption
import org.wdt.intellijmanager.utils.toobloxStateJsonFile
import org.wdt.utils.gson.getString
import org.wdt.utils.gson.readFileToJsonObject


fun printVersionNumber() {
    println("Application Version: ${System.getProperty("intellijmanager.version.number")}")
    println(
        "Toolbox Version: ${toobloxStateJsonFile.readFileToJsonObject().getString("appVersion")}"
    )
}

val versionOption = getOption("v", "version")