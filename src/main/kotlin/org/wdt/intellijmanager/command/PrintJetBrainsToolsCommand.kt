package org.wdt.intellijmanager.command

import org.apache.commons.cli.Option
import org.wdt.intellijmanager.objects.stateObject
import org.wdt.intellijmanager.utils.getOption
import org.wdt.utils.io.toFile
import java.io.File
import java.util.*

fun printJetBrainsTools() {
    stateObject.tools.run {
        if (isNullOrEmpty()) {
            println("warning:No IDE version available")
        } else {
            forEach {
                println("IDE Name:${it.displayName},Version:${it.displayVersion},BuildNumber:${it.buildNumber}")
            }
        }
    }
}

fun getJetBrainsToolsDirectorys(): LinkedList<File> {
    return stateObject.tools.run {
        if (isNullOrEmpty()) {
            println("warning:No IDE version available")
            LinkedList<File>()
        } else {
            return LinkedList<File>().apply {
                this@run.forEach {
                    add(it.installLocation?.toFile()!!)
                }
            }
        }
    }
}

val printToolsListOption: Option = getOption("l", "list")
