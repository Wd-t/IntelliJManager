package org.wdt.intellijmanager

import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import org.wdt.intellijmanager.command.*
import org.wdt.intellijmanager.utils.toolboxInstallPath
import org.wdt.utils.io.isFileNotExists
import java.io.IOException

private val options = Options()

fun main(args: Array<String>) {
    if (toolboxInstallPath.isFileNotExists()) {
        throw IOException("Toolbox is not installed!")
    }
    val commandLineParser: CommandLineParser = DefaultParser()
    addAllOptions(options)
    val commandLine = commandLineParser.parse(options, args)
    if (commandLine.hasOption(toolsPathCommandOption)) {
        copyVmoptionsFile(commandLine)
    } else if (commandLine.hasOption(saveFileOption)) {
        copyFile(commandLine)
    }
    if (commandLine.hasOption(printToolsListOption)) {
        printJetBrainsTools()
    }
    if (commandLine.hasOption(versionOption)) {
        printVersionNumber()
    }
    if (commandLine.hasOption(configOption)) {
        startTask(commandLine)
    }
    if (commandLine.hasOption(setInstallOption)) {
        setInstallPath(commandLine)
    }
}

fun addAllOptions(options: Options) {
    options.addOption(notCofnigOption)
    options.addOption(toolsPathCommandOption)
    options.addOption(toolsCachePathCommandOption)
    options.addOption(configOption)
    options.addOption(openConfigFileOption)
    options.addOption(versionOption)
    options.addOption(printToolsListOption)
    options.addOption(saveFileOption)
    options.addOption(setInstallOption)
}
