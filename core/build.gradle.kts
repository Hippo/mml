plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("7.1.0")
    id("maven-publish")
}

group = "rip.hippo.mml"
version = "1.8.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            from(components["java"])
        }
    }
}