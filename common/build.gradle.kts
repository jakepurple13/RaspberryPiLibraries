plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("native.cocoapods")
    id("kotlinx-serialization")
    id("maven-publish")
}

val versionNumber = "1.0.11"

group = "com.programmersbox.PiLibraries"
version = versionNumber

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    android {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        publishAllLibraryVariants()
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    jvm("desktop")
    ios()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js(IR) {
        browser()
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "common"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.ui)
                api(compose.foundation)
                api(compose.materialIconsExtended)
                api(compose.material3)
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
            }
        }

        /*val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }*/

        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.6.0")
                api("androidx.core:core-ktx:1.9.0")
                api("com.juul.kable:core:0.21.0")
                api("com.google.accompanist:accompanist-permissions:0.29.1-alpha")
                api("org.jmdns:jmdns:3.5.8")
                api("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
            }
        }

        /*val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }*/

        val desktopMain by getting {
            dependencies {
                api(compose.preview)
                api("org.jmdns:jmdns:3.5.8")
            }
        }

        //val desktopTest by getting

        val jsMain by getting {
            dependencies {
                api(compose.web.core)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependencies {
                api("com.juul.kable:core:0.21.0")
            }
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }

    explicitApi()
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    publications {
        // Creates a Maven publication called "release".
        register<MavenPublication>("release") {
            // You can then customize attributes of the publication as shown below.
            groupId = "com.github.jakepurple13"
            artifactId = "pimodules"
            version = versionNumber
            afterEvaluate { from(components["release"]) }
        }
    }
    repositories {
        maven {
            name = "RaspberryPiLibraries"
            url = uri("https://maven.pkg.github.com/jakepurple13/RaspberryPiLibraries")
            credentials {
                username = System.getenv("USERNAME") ?: "jakepurple13"
                password = System.getenv("TOKEN")
            }
        }
    }
}