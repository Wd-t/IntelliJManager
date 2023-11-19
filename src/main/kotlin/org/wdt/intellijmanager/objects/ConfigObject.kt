package org.wdt.intellijmanager.objects

import com.google.gson.annotations.SerializedName
import org.wdt.intellijmanager.command.ConfigCommand.Companion.configFile
import org.wdt.utils.gson.readFileToClass
import org.wdt.utils.io.isFileExists
import java.io.File

class ConfigObject {


    @SerializedName("ideConfigDirectroy")
    var ideConfigSameDirectory: File? = null

    @SerializedName("idePluginsDirectroy")
    var idePluginsSameDirectory: File? = null


    companion object {
        @JvmStatic
        fun getCofnig(): ConfigObject? {
            return if (configFile.isFileExists())
                configFile.readFileToClass()
            else null
        }
    }

    override fun toString(): String {
        return "ConfigObject(ideConfigSameDirectory=$ideConfigSameDirectory, idePluginsSameDirectory=$idePluginsSameDirectory)"
    }

}
