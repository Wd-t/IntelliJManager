plugins {
	application
	kotlin("jvm") version "1.9.20"
}

group = "org.wdt.intellijmanager"
version = "2.6.1"


repositories {
	maven { url = uri("https://jitpack.io") }
	mavenLocal()
	mavenCentral()
}


dependencies {
	implementation("com.github.wd-t.utils:utils-gson:1.2.3")
	implementation("com.github.wd-t.utils:utils-io:1.2.3")
	implementation("commons-cli:commons-cli:1.5.0")
	implementation("com.google.code.gson:gson:2.10.1")
	implementation(kotlin("stdlib"))
	testImplementation(kotlin("test"))
	testImplementation(platform("org.junit:junit-bom:5.9.3"))
	testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
}

application {
	mainClass.set("org.wdt.intellijmanager.ManagerMain")
	applicationDefaultJvmArgs = listOf("-Dintellijmanager.version.number=${project.version}")
}



tasks.test {
	useJUnitPlatform()
}