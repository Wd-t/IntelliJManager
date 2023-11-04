plugins {
    java
    application
    kotlin("jvm") version "1.9.0"
}

group = "org.wdt.intellijmanager"
version = "2.5.1"

repositories {
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://maven.aliyun.com/repository/public/") }
    mavenLocal()
    mavenCentral()
}

application {
    mainClass.set("org.wdt.intellijmanager.ManagerMain")
    applicationDefaultJvmArgs = listOf("-Dintellijmanager.version.number=${project.version}")
}

sourceSets {
    main {
        java.srcDir("src/main/src")
        kotlin.srcDir("src/main/src")
    }
}


tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


dependencies {
    implementation("com.github.wd-t.utils:utils-gson:1.2.0")
    implementation("com.github.wd-t.utils:utils-io:1.2.0")
    implementation("commons-cli:commons-cli:1.5.0")
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(platform("org.junit:junit-bom:5.9.3"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    implementation(kotlin("stdlib"))
}

tasks.test {
    useJUnitPlatform()
}