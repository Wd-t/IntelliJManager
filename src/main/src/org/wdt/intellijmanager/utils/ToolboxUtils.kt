package org.wdt.intellijmanager.utils

import com.google.gson.JsonObject
import org.wdt.utils.gson.JsonUtils
import java.io.File

object ToolboxUtils {
    @JvmStatic
    fun getToolboxInstallPath(): File {
        return File(System.getProperty("user.home"), "AppData/Local/JetBrains/Toolbox")
    }

    @JvmStatic
    fun getToolboxSettingJsonFile(): File {
        return File(getToolboxInstallPath(), ".settings.json")
    }

    @JvmStatic
    fun getToobloxSettingJsonObject(): JsonObject {
        return JsonUtils.getJsonObject(getToolboxSettingJsonFile())
    }

    @JvmStatic
    fun getToobloxStateJsonFile(): File {
        return File(getToolboxInstallPath(), "state.json")
    }
}