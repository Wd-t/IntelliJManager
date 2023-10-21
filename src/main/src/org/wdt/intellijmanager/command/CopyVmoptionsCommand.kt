package org.wdt.intellijmanager.command

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import org.wdt.intellijmanager.utils.OptionUtils
import org.wdt.utils.gson.JsonUtils
import org.wdt.utils.gson.getString
import org.wdt.utils.io.*
import java.io.File
import java.io.IOException
import java.util.*

class CopyVmoptionsCommand(options: Options) {
    init {
        options.addOption(toolsPathCommandOption)
        options.addOption(toolsCachePathCommandOption)
    }

    @Throws(IOException::class)
    fun copyVmoptionsFile(commandLine: CommandLine) {

        if (commandLine.hasOption(toolsCachePathCommandOption)) {
            if (commandLine.hasOption(SaveConfigFileCommand.getSaveFileOption())) {
                copyVmoptionsFile(
                    commandLine.getOptionValue(toolsPathCommandOption),
                    commandLine.getOptionValue(toolsCachePathCommandOption),
                    File(commandLine.getOptionValue(SaveConfigFileCommand.getSaveFileOption()))
                )
            } else {
                copyVmoptionsFile(
                    commandLine.getOptionValue(toolsPathCommandOption), commandLine.getOptionValue(
                        toolsCachePathCommandOption
                    ), null
                )
            }
        } else {
            throw IOException("Must have -cp parameter")
        }
    }

    companion object {
        @JvmStatic
        val toolsPathCommandOption: Option = OptionUtils.getOption("ip", "idepath", true)

        @JvmStatic
        val toolsCachePathCommandOption: Option = OptionUtils.getOption("cp", "cachepath", true)

        @JvmStatic
        @Throws(IOException::class)
        fun copyVmoptionsFile(ip: String, cp: String, saveFile: File?) {
            val ideBinPath = File(ip)
            val cacheAddress = File(cp)
            cacheAddress.createDirectories()
            if (ideBinPath.isDirectory() && ideBinPath.exists()) {
                val productInfoFile = File(ideBinPath, "product-info.json")
                val productInfoFileJson = JsonUtils.getJSONObject(productInfoFile)
                val launchFirstJson = productInfoFileJson.getAsJsonArray("launch").get(0).asJsonObject
                val child = File(ideBinPath.getCanonicalFile(), launchFirstJson.getString("vmOptionsFilePath"))
                val vmoptions = IOUtils.toString(
                    Objects.requireNonNull(
                        CopyVmoptionsCommand::class.java.getResourceAsStream("/idea.vmoptions")
                    )
                ).replace(":CacheAddress", FilenameUtils.separatorsToWindows(cp))
                child.writeStringToFile(vmoptions)
                println("Copy File To: $child")
                saveFile?.writeStringToFile("$ip\n$cp")
            } else {
                throw IOException("IDE path must is a directory")
            }
        }

    }
}
