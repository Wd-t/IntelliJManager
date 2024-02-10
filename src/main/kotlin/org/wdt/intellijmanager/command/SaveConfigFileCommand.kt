package org.wdt.intellijmanager.command

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option
import org.wdt.intellijmanager.utils.getOption
import org.wdt.utils.io.readFileToLine
import java.io.File

fun copyFile(commandLine: CommandLine) {
    val file = getSavdFilePath(commandLine.getOptionValue(saveFileOption))
    val line = file.readFileToLine()
    CopyVmoptionsManger(line[1], line[0]).apply {
        saveFile = file
        notConfig = commandLine.hasOption(notCofnigOption)
    }.let {
        CopyVmoptionsTask(it).run {
            copyVmoptionsFile()
        }
    }
}

val saveFileOption: Option = getOption("sf", "savefile", true)

fun getSavdFilePath(name: String): File = File(System.getProperty("user.home"), ".config/intellijmanager/$name")
