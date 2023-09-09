import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("idea")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.wdt"
version = "2.3.0"

repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public/") }
    mavenLocal()
    mavenCentral()
}

tasks.jar {
    enabled = false
    dependsOn(tasks["shadowJar"])
}
tasks.withType<ShadowJar> {
    manifest.attributes.apply {
        put("Main-Class", "org.wdt.intellijmanager.ManagerMain")
    }
}
tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.create<JavaExec>("run") {
    dependsOn(tasks.shadowJar)
    group = "application"
    classpath = files(tasks.shadowJar.get().archiveFile.get().asFile)
    args = listOf("-v")
}

dependencies {
    // https://mvnrepository.com/artifact/commons-cli/commons-cli
    implementation("commons-cli:commons-cli:1.5.0")
    implementation("com.alibaba.fastjson2:fastjson2:2.0.34")
    implementation("commons-io:commons-io:2.13.0")
    testImplementation(platform("org.junit:junit-bom:5.9.3"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
}

tasks.test {
    useJUnitPlatform()
}