@file:JvmName("ManagerMain")

package org.wdt.intellijmanager

import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import org.wdt.intellijmanager.command.*
import org.wdt.intellijmanager.utils.toolboxInstallPath
import org.wdt.utils.io.isFileNotExists
import java.io.IOException

fun main(args: Array<String>) {
    if (toolboxInstallPath.isFileNotExists()) {
        throw IOException("Toolbox is not installed!")
    }
    Options().apply { addAllOptions() }.let {
        DefaultParser().parse(it, args)
    }.let {
        if (it.hasOption(toolsPathCommandOption)) {
            copyVmoptionsFile(it)
        } else if (it.hasOption(saveFileOption)) {
            copyFile(it)
        }
        if (it.hasOption(printToolsListOption)) {
            printJetBrainsTools()
        }
        if (it.hasOption(versionOption)) {
            printVersionNumber()
        }
        if (it.hasOption(configOption)) {
            startTask(it)
        }
        if (it.hasOption(setInstallOption)) {
            setInstallPath(it)
        }
    }
}

fun Options.addAllOptions() {
    addOption(notCofnigOption)
    addOption(toolsPathCommandOption)
    addOption(toolsCachePathCommandOption)
    addOption(configOption)
    addOption(openConfigFileOption)
    addOption(versionOption)
    addOption(printToolsListOption)
    addOption(saveFileOption)
    addOption(setInstallOption)
}
