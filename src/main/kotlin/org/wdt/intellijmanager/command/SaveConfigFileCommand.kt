package org.wdt.intellijmanager.command

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option
import org.wdt.intellijmanager.utils.getOption
import org.wdt.utils.io.readFileToLine
import java.io.File

fun copyFile(commandLine: CommandLine) {
    val file = getSavdFilePath(commandLine.getOptionValue(saveFileOption))
    val line = file.readFileToLine()
    val ip = line[0]
    val cp = line[1]
    val copyVmoptionsTask = CopyVmoptionsTask(ip, cp, file)
    copyVmoptionsTask.notConfig = commandLine.hasOption(notCofnigOption)
    copyVmoptionsTask.copyVmoptionsFile()
}

val saveFileOption: Option = getOption("sf", "savefile", true)

fun getSavdFilePath(name: String): File {
    return File(System.getProperty("user.home"), ".config/intellijmanager/$name")
}
