plugins {
    java
    application
    kotlin("jvm") version "1.9.10"
}

group = "org.wdt"
version = "2.4.0"

repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public/") }
    mavenLocal()
    mavenCentral()
}

application {
    mainClass.set("org.wdt.intellijmanager.ManagerMain")
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
    implementation("commons-cli:commons-cli:1.5.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(files("./libs/utils-gson-1.1.0.jar"))
    implementation(files("./libs/utils-io-1.1.0.jar"))
    testImplementation(platform("org.junit:junit-bom:5.9.3"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    implementation(kotlin("stdlib"))
}

tasks.test {
    useJUnitPlatform()
}