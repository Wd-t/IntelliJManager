package org.wdt.intellijmanager.command

import org.apache.commons.cli.Options

object AddAllOptions {
    @JvmStatic
    fun addAllOptions(options: Options) {
        options.addOption(CopyVmoptionsCommand.notCofnigOption)
        options.addOption(CopyVmoptionsCommand.toolsPathCommandOption)
        options.addOption(CopyVmoptionsCommand.toolsCachePathCommandOption)
        options.addOption(ConfigCommand.configOption)
        options.addOption(ConfigCommand.openConfigFileOption)
        options.addOption(PrintVersionCommand.getOption())
        options.addOption(PrintJetBrainsToolsCommand.option)
        options.addOption(SaveConfigFileCommand.saveFileOption)
    }
}