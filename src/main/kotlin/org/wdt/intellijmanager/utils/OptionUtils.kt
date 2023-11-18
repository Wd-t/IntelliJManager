package org.wdt.intellijmanager.utils

import org.apache.commons.cli.Option
import java.io.File

object OptionUtils {
    fun getOption(command: String, longCommand: String): Option {
        return getOption(command, longCommand, false)
    }

    fun getOption(command: String, longCommand: String, hasArgs: Boolean): Option {
        return Option.builder(command).longOpt(longCommand).hasArg(hasArgs).required(false).build()
    }

    fun openFile(file: File) {
        Runtime.getRuntime().exec(arrayOf("cmd.exe", "/C", "start", file.canonicalPath))
    }
}
