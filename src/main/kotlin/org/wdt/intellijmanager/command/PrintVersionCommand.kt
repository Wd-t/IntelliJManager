package org.wdt.intellijmanager.command

import org.wdt.intellijmanager.objects.stateObject
import org.wdt.intellijmanager.utils.getOption


fun printVersionNumber() {
    println("Application Version:${System.getProperty("intellijmanager.version.number")}")
    println("Toolbox Version:${stateObject.toobloxVersion}")
}

val versionOption = getOption("v", "version")