plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("7.1.0")
    id("maven-publish")
}

allprojects {
    group = "rip.hippo.mml"
    version = "1.0.3"
}

repositories {
    mavenCentral()
}

dependencies {
}

subprojects {
    val sub = this
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = sub.group.toString()
                artifactId = sub.name
                version = sub.version.toString()
                from(components["java"])
            }
        }
    }
}