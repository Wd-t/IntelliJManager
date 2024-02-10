package org.wdt.intellijmanager.objects

import com.google.gson.annotations.SerializedName
import org.wdt.intellijmanager.utils.toolboxSettingJsonFile
import org.wdt.utils.gson.readFileToClass
import java.io.File

class ToolboxSettingObject {
    @SerializedName("autostart")
    val autostart: Boolean = false

    @SerializedName("install_location")
    val installLocation: File? = null

    @SerializedName("channel_rollback_max_history")
    val channel: Int = 0
}

val toolboxSetting: ToolboxSettingObject
    get() = toolboxSettingJsonFile.readFileToClass()