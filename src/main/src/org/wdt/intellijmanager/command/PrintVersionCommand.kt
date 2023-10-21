package org.wdt.intellijmanager.command

import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import org.wdt.intellijmanager.utils.OptionUtils
import org.wdt.intellijmanager.utils.ToolboxUtils
import org.wdt.utils.gson.JsonUtils
import org.wdt.utils.gson.getString

class PrintVersionCommand(options: Options) {
    init {
        options.addOption(getOption())
    }

    companion object {
        @JvmStatic
        fun getOption(): Option {
            return OptionUtils.getOption("v", "version")
        }
    }

    fun printVersionNumber() {
        println("Application Version: 2.4.0")
        println(
            "Toolbox Version: " + JsonUtils.getJsonObject(ToolboxUtils.getToobloxStateJsonFile())
                .getString("appVersion")
        )
    }
}