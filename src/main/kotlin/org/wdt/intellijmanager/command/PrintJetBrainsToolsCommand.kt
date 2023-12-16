package org.wdt.intellijmanager.command

import org.apache.commons.cli.Option
import org.wdt.intellijmanager.objects.StateObject
import org.wdt.intellijmanager.utils.getOption
import org.wdt.intellijmanager.utils.toobloxStateJsonFile
import org.wdt.utils.gson.readFileToClass
import org.wdt.utils.io.FileUtils
import java.io.File
import java.util.*

fun printJetBrainsTools() {
    val stateObject: StateObject = toobloxStateJsonFile.readFileToClass()
    val toolsObjectList = stateObject.tools
    if (toolsObjectList.isNullOrEmpty()) {
        println("warning:No IDE version available")
    } else {
        for (toolsObject in Objects.requireNonNull(stateObject.tools)) {
            println("IDE Name :${toolsObject.displayName}  ,Version :${toolsObject.displayVersion},BuildNumber :${toolsObject.buildNumber}")
        }
    }
}

fun getJetBrainsToolsDirectorys(): List<File>? {
    val stateObject: StateObject = toobloxStateJsonFile.readFileToClass()
    val toolsObjectList = stateObject.tools
    if (toolsObjectList.isNullOrEmpty()) {
        println("warning:No IDE version available")
    } else {
        val directoryList: MutableList<File> = ArrayList()
        for (toolsObject in toolsObjectList) {
            directoryList.add(FileUtils.toFile(toolsObject.installLocation))
        }
        return directoryList
    }
    return null
}

val printToolsListOption: Option = getOption("l", "list")
