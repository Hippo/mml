plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("7.1.0")
    id("maven-publish")
}

group = "rip.hippo.mml"
version = "1.7.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.8-R0.1-SNAPSHOT")
    implementation(project(":core"))

    implementation("rip.hippo:BukkitVersion:1.0.0")
    implementation("rip.hippo:ChatTranslate:1.2.0")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            from(components["java"])
        }
    }
}
