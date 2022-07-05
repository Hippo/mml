import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("7.1.0")
    id("maven-publish")
}

group = "rip.hippo.mml"
version = "1.3.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.8-R0.1-SNAPSHOT")
    implementation(project(":core"))
    implementation(project(":spigot"))

    implementation("rip.hippo:BukkitVersion:1.0.0")
    implementation("rip.hippo:ChatTranslate:1.2.0")
}

tasks.getByName<ShadowJar>("shadowJar") {
    this.dependencies {
        this.exclude {
            !it.name.startsWith("rip.hippo")
        }
    }
    relocate("rip.hippo.mml",
        "rip.hippo.example.mml.spigot.libs.rip.hippo.mml")
    relocate("rip.hippo.translate",
    "rip.hippo.example.mml.spigot.libs.rip.hippo.translate")
    relocate("rip.hippo.version",
    "rip.hippo.example.mml.spigot.libs.rip.hippo.version")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            from(components["java"])
        }
    }
}