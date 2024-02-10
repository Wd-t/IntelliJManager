package org.wdt.intellijmanager.command

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option
import org.wdt.intellijmanager.objects.getCofnig
import org.wdt.intellijmanager.objects.toolboxSetting
import org.wdt.intellijmanager.utils.getOption
import org.wdt.utils.io.toFile
import org.wdt.utils.io.writeStringToFile
import java.io.File
import java.io.IOException

fun copyVmoptionsFile(commandLine: CommandLine) {
    if (commandLine.hasOption(toolsCachePathCommandOption)) {
        commandLine.run {
            CopyVmoptionsManger(
                getOptionValue(toolsPathCommandOption),
                getOptionValue(toolsCachePathCommandOption)
            ).apply {
                saveFile = if (hasOption(saveFileOption)) getSavdFilePath(getOptionValue(saveFileOption)) else null
                notConfig = hasOption(notCofnigOption)
                openFile = hasOption(openConfigFileOption)
            }
        }.let {
            CopyVmoptionsTask(it).run {
                copyVmoptionsFile()
            }
        }
    } else {
        throw IOException("Must have -cp parameter")
    }
}

class CopyVmoptionsManger(
    val idePath: String, val cachePath: String,
) {
    var notConfig: Boolean = false
    var openFile: Boolean = false
    var saveFile: File? = null
}


class CopyVmoptionsTask(private val manger: CopyVmoptionsManger) {
    fun copyVmoptionsFile() {
        val ideBinFile = manger.idePath.toFile()
        val cachePathFile = manger.cachePath.toFile()
        CopyVmoptionsConfig(cachePathFile.canonicalPath, manger.notConfig).run {
            buildString {
                append("-Didea.system.path=").append(systemPath).append("\n")
                append("-Didea.config.path=").append(configPath).append("\n")
                append("-Didea.plugins.path=").append(pluginsPath).append("\n")
                append("-Didea.log.path=").append(logPath).append("\n")
            }
        }.let {
            File(toolboxSetting.installLocation, "${ideBinFile.parent}.vmoptions").run {
                writeStringToFile(it)
            }
        }
    }
}

class CopyVmoptionsConfig(
    cachePath: String,
    notConfig: Boolean,
) {
    val pluginsPath: File
    val logPath: File
    val systemPath: File
    val configPath: File

    init {
        val config = getCofnig()!!
        cachePath.let {
            pluginsPath = if (notConfig) {
                File(it, "plugins")
            } else config.idePluginsSameDirectory!!
            configPath = if (notConfig) {
                File(it, "config")
            } else config.ideConfigSameDirectory!!
            systemPath = File(it, "system")
            logPath = File(systemPath, "log")
        }
    }
}

val toolsPathCommandOption: Option = getOption("ip", "idepath", true)


val toolsCachePathCommandOption: Option = getOption("cp", "cachepath", true)


val notCofnigOption: Option = getOption("nc", "notconfig", false)
