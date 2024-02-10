package org.wdt.intellijmanager.objects

import com.google.gson.annotations.SerializedName
import org.wdt.intellijmanager.utils.toobloxStateJsonFile
import org.wdt.utils.gson.readFileToClass

class StateObject {
    @SerializedName("appVersion")
    val toobloxVersion: String? = null

    @SerializedName("tools")
    val tools: List<ToolsObject>? = null
}

val stateObject: StateObject = toobloxStateJsonFile.readFileToClass()

class ToolsObject {
    @SerializedName("channelId")
    val channelId: String? = null

    @SerializedName("toolId")
    val toolId: String? = null

    @SerializedName("productCode")
    val productCode: String? = null

    @SerializedName("tag")
    val tag: String? = null

    @SerializedName("displayName")
    val displayName: String? = null

    @SerializedName("displayVersion")
    val displayVersion: String? = null

    @SerializedName("buildNumber")
    val buildNumber: String? = null

    @SerializedName("installLocation")
    val installLocation: String? = null

    @SerializedName("launchCommand")
    val launchCommand: String? = null
}