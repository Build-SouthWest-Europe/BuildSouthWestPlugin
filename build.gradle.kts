plugins {
    id("java-library")
    id("xyz.jpenilla.run-paper") version "3.0.2"
}

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.enginehub.org/repo/" )
    maven {
        name = "bteReleases"
        url = uri("https://maven.buildtheearth.net/releases")
    }

    flatDir { dirs("libs") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.3.build.+")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.18"){
        exclude(group = "com.google.guava", module = "guava")
        exclude(group = "com.google.code.gson", module = "gson")
    }
    compileOnly("net.buildtheearth.terraminusminus:terraminusminus-core:2.2.1-1.21.8")
//    compileOnly("net.buildtheearth.terraminusminus:terraminusminus-core:2.0.0rc1-1.21.4")
    compileOnly(":TerraPlusMinus")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("26.3")
        jvmArgs("-Xms2G", "-Xmx2G")
    }

    processResources {
        val props = mapOf("version" to version)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}
