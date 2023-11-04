package org.wdt.intellijmanager.command

import org.apache.commons.cli.Option
import org.wdt.intellijmanager.utils.OptionUtils
import org.wdt.intellijmanager.utils.ToolboxUtils
import org.wdt.utils.gson.JsonUtils
import org.wdt.utils.gson.getString

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
            "Toolbox Version: " + JsonUtils.getJsonObject(ToolboxUtils.getToobloxStateJsonFile())
                .getString("appVersion")
        )
    }
}