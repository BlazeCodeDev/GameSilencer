import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.compose") version "1.1.0"
}

group = "me.ralfl"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)
    //KEY LISTENER
    implementation("com.github.kwhat:jnativehook:2.2.2")
    //PROCESS LISTENER
    implementation("net.java.dev.jna:jna-platform:5.12.1")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Exe, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "GameSilencer"
            packageVersion = "1.1.0"
            windows {
                iconFile.set(project.file("ic_icon.png"))
                // FOR ALL WINDOWS DISTIBUTABLES
                packageVersion = "1.1.0"
                // FOR MSI DISTIBUTABLE
                msiPackageVersion = "1.1.0"
                // FOR EXE DISTIBUTABLE
                exePackageVersion = "1.1.0"
            }
        }
    }
}