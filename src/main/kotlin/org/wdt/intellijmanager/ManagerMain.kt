package org.wdt.intellijmanager

import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import org.apache.commons.cli.ParseException
import org.wdt.intellijmanager.command.*
import org.wdt.intellijmanager.command.AddAllOptions.addAllOptions
import org.wdt.intellijmanager.command.ConfigCommand.Companion.configOption
import org.wdt.intellijmanager.command.CopyVmoptionsCommand.Companion.toolsPathCommandOption
import org.wdt.intellijmanager.command.PrintVersionCommand.Companion.getOption
import org.wdt.intellijmanager.command.SaveConfigFileCommand.Companion.saveFileOption
import org.wdt.intellijmanager.command.SetToolsInstallPathCommand.setInstallOption
import org.wdt.intellijmanager.utils.ToolboxUtils.toolboxInstallPath
import org.wdt.utils.io.isFileExists
import java.io.IOException

object ManagerMain {
    private val options = Options()

    @Throws(ParseException::class, IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        if (toolboxInstallPath.isFileExists()) {
            throw IOException("Toolbox is not installed!")
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
        if (commandLine.hasOption(setInstallOption)) {
            SetToolsInstallPathCommand.setInstallPath(commandLine)
        }
    }
}
