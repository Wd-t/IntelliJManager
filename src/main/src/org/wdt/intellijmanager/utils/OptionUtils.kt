package org.wdt.intellijmanager.utils

import org.apache.commons.cli.Option

object OptionUtils {
    @JvmStatic
    fun getOption(command: String?, longCommand: String?): Option {
        return getOption(command, longCommand, false)
    }

    fun getOption(command: String?, longCommand: String?, hasArgs: Boolean): Option {
        return Option.builder(command).longOpt(longCommand).hasArg(hasArgs).required(false).build()
    }
}
