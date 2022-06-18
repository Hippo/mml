import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("7.1.0")
    id("maven-publish")
}

group = "rip.hippo.mml"
version = "1.1.1"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.8-R0.1-SNAPSHOT")
    implementation(project(":core"))
    implementation(project(":spigot"))
}

tasks.getByName<ShadowJar>("shadowJar") {
    this.dependencies {
        this.exclude {
            !it.name.startsWith("rip.hippo")
        }
    }
    relocate("rip.hippo.mml",
        "rip.hippo.example.mml.spigot.libs.rip.hippo.mml")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            from(components["java"])
        }
    }
}