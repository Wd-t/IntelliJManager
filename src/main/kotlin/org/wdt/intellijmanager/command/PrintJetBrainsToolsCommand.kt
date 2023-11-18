package org.wdt.intellijmanager.command

import org.apache.commons.cli.Option
import org.wdt.intellijmanager.objects.StateObject
import org.wdt.intellijmanager.utils.OptionUtils.getOption
import org.wdt.intellijmanager.utils.ToolboxUtils.toobloxStateJsonFile
import org.wdt.utils.gson.readFileToClass
import org.wdt.utils.io.FileUtils
import java.io.File
import java.util.*

class PrintJetBrainsToolsCommand {
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

    companion object {
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

        val option: Option = getOption("l", "list")
    }
}
