plugins {
    application
    kotlin("jvm") version "1.9.22"
}

group = "org.wdt.intellijmanager"
version = "2.7.0"

repositories {
    maven { url = uri("https://jitpack.io") }
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("com.github.wd-t.utils:utils-gson:1.3.3")
    implementation("com.github.wd-t.utils:utils-io:1.3.3")
    implementation("commons-cli:commons-cli:1.5.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("org.wdt.intellijmanager.ManagerMain")
    applicationDefaultJvmArgs = listOf("-Dintellijmanager.version.number=${project.version}")
}

tasks.test {
    useJUnitPlatform()
}