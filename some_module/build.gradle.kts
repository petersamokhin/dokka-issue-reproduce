plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common", "1.3.72"))
            }
        }
        val commonTest by getting {}
        val jvmMain by getting { 
            dependencies {
                implementation(kotlin("stdlib-jdk7", "1.3.72"))
            }
        }
        val jvmTest by getting {}
        val nativeMain by creating { dependsOn(commonMain) }
        val nativeTest by creating { dependsOn(commonTest) }
    }

    listOf(
        "iosX64", "iosArm32", "iosArm64", "tvosX64", "tvosArm64", "watchosX86", "watchosArm32", "watchosArm64", "macosX64", "linuxX64", "mingwX64"
    ).forEach {
        targetFromPreset(presets[it], it) {
            compilations["main"].source(sourceSets["nativeMain"])
            compilations["test"].source(sourceSets["nativeTest"])
        }
    }
}

// or here:
// apply(from = "${rootDir}/gradle/0.9.18_dokka.gradle")