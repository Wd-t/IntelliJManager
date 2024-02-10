package org.wdt.intellijmanager.command

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option
import org.wdt.intellijmanager.objects.ConfigObject
import org.wdt.intellijmanager.objects.getCofnig
import org.wdt.intellijmanager.utils.getOption
import org.wdt.intellijmanager.utils.openFile
import org.wdt.utils.gson.writeObjectToFile
import org.wdt.utils.io.isFileNotExists
import java.io.File

fun startTask(commandLine: CommandLine) {
    configFile.run {
        if (isFileNotExists()) {
            writeObjectToFile(ConfigObject())
        }
        if (commandLine.hasOption(openConfigFileOption)) {
            println("The configuration file needs to be manually edited")
            openFile(this)
        } else {
            println(getCofnig())
        }
    }
}


val configOption: Option = getOption("c", "config", false)

val openConfigFileOption: Option = getOption("o", "open", false)

val configFile: File = getSavdFilePath("intellijmanger.json")
