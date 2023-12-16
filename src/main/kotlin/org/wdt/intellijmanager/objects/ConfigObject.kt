package org.wdt.intellijmanager.objects

import com.google.gson.annotations.SerializedName
import org.wdt.intellijmanager.command.configFile
import org.wdt.utils.gson.readFileToClass
import org.wdt.utils.io.isFileExists
import java.io.File

class ConfigObject {


    @SerializedName("ideConfigDirectroy")
    var ideConfigSameDirectory: File? = null

    @SerializedName("idePluginsDirectroy")
    var idePluginsSameDirectory: File? = null


    override fun toString(): String {
        return "ConfigObject(ideConfigSameDirectory=$ideConfigSameDirectory, idePluginsSameDirectory=$idePluginsSameDirectory)"
    }

}

fun getCofnig(): ConfigObject? {
    return if (configFile.isFileExists())
        configFile.readFileToClass()
    else null
}
