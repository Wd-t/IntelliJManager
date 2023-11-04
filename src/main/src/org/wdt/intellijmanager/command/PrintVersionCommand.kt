package org.wdt.intellijmanager.command

import org.apache.commons.cli.Option
import org.wdt.intellijmanager.utils.OptionUtils
import org.wdt.intellijmanager.utils.ToolboxUtils
import org.wdt.utils.gson.getString
import org.wdt.utils.gson.readFileToJsonObject

class PrintVersionCommand {
    companion object {
        @JvmStatic
        fun getOption(): Option {
            return OptionUtils.getOption("v", "version")
        }
    }

    fun printVersionNumber() {
        println("Application Version: ${System.getProperty("intellijmanager.version.number")}")
        println(
            "Toolbox Version: ${ToolboxUtils.getToobloxStateJsonFile().readFileToJsonObject().getString("appVersion")}"
        )
    }
}