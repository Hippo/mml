plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("7.1.0")
    id("maven-publish")
}

allprojects {
    group = "rip.hippo.mml"
    version = "1.0.2"
}

repositories {
    mavenCentral()
}

dependencies {
}

subprojects {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = project.group.toString()
                artifactId = project.name
                version = project.version.toString()
                from(components["java"])
            }
        }
    }
}