package org.wdt.intellijmanager.utils

import java.io.File

object ToolboxUtils {
	val toolboxInstallPath
		get() = File(System.getProperty("user.home"), "AppData/Local/JetBrains/Toolbox")


	val toolboxSettingJsonFile
		get() = File(toolboxInstallPath, ".settings.json")


	val toobloxStateJsonFile
		get() = File(toolboxInstallPath, "state.json")

}