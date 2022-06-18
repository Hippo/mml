plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("7.1.0")
    id("maven-publish")
}

group = "rip.hippo.mml"
version = "1.0.9"

repositories {
    mavenCentral()
}

dependencies {
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            from(components["java"])
        }
    }
}