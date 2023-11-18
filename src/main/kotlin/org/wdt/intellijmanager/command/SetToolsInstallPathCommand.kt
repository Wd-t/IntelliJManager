package org.wdt.intellijmanager.command

import org.apache.commons.cli.CommandLine
import org.wdt.intellijmanager.utils.OptionUtils
import org.wdt.intellijmanager.utils.ToolboxUtils.toolboxSettingJsonFile
import org.wdt.utils.gson.Json
import org.wdt.utils.gson.readFileToJsonObject
import org.wdt.utils.gson.writeObjectToFile
import org.wdt.utils.io.createDirectories
import java.io.File

object SetToolsInstallPathCommand {
	val setInstallOption = OptionUtils.getOption("i", "install", true)
	fun setInstallPath(command: CommandLine) {
		val installPath = File(command.getOptionValue(setInstallOption))
		installPath.createDirectories()
		val settingObject = toolboxSettingJsonFile.readFileToJsonObject()
		settingObject.addProperty("install_location", installPath.canonicalPath)
		installPath.writeObjectToFile(settingObject, Json.GSON_BUILDER.setPrettyPrinting())
	}
}