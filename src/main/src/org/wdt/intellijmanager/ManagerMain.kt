package org.wdt.intellijmanager

import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import org.apache.commons.cli.ParseException
import org.wdt.intellijmanager.command.*
import org.wdt.intellijmanager.command.AddAllOptions.addAllOptions
import org.wdt.intellijmanager.command.ConfigCommand.Companion.configFile
import org.wdt.intellijmanager.command.ConfigCommand.Companion.configOption
import org.wdt.intellijmanager.command.CopyVmoptionsCommand.Companion.toolsPathCommandOption
import org.wdt.intellijmanager.command.PrintVersionCommand.Companion.getOption
import org.wdt.intellijmanager.command.SaveConfigFileCommand.Companion.saveFileOption
import org.wdt.intellijmanager.objects.ConfigObject
import org.wdt.intellijmanager.utils.ToolboxUtils.getToolboxInstallPath
import org.wdt.utils.gson.Json.getBuilder
import org.wdt.utils.io.isFileNotExists
import org.wdt.utils.io.writeStringToFile
import java.io.IOException

object ManagerMain {
    private val options = Options()

    @Throws(ParseException::class, IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        if (getToolboxInstallPath().isFileNotExists()) {
            throw IOException("Toolbox is not installed!")
        }
        if (configFile.isFileNotExists()) {
            configFile.writeStringToFile(getBuilder().serializeNulls().create().toJson(ConfigObject()))
        }
        val commandLineParser: CommandLineParser = DefaultParser()
        addAllOptions(options)
        val configCommand = ConfigCommand()
        val configFile = SaveConfigFileCommand()
        val copyVmoptionsCommand = CopyVmoptionsCommand()
        val printList = PrintJetBrainsToolsCommand()
        val info = PrintVersionCommand()
        val commandLine = commandLineParser.parse(options, args)
        if (commandLine.hasOption(toolsPathCommandOption)) {
            copyVmoptionsCommand.copyVmoptionsFile(commandLine)
        } else if (commandLine.hasOption(saveFileOption)) {
            configFile.copyFile(commandLine)
        }
        if (commandLine.hasOption(PrintJetBrainsToolsCommand.option)) {
            printList.printJetBrainsTools()
        }
        if (commandLine.hasOption(getOption())) {
            info.printVersionNumber()
        }
        if (commandLine.hasOption(configOption)) {
            configCommand.startTask(commandLine)
        }
    }
}
