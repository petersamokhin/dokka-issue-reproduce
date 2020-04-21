// See the 0.10.1 example first!

// buildscript block is also required
buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.9.18")
    }
}

apply(plugin = "org.jetbrains.dokka")

tasks.withType(org.jetbrains.dokka.gradle.DokkaTask::class) {
    // Yes.
    throw Exception("Never been here")
    
    outputDirectory = "$rootDir/docs/0.0.x"
    outputFormat = "gfm"

    skipEmptyPackages = true

    packageOptions {
        prefix = "com.petersamokhin.dokkaissuereproduce.internal"
        suppress = true
    }
    if (project.plugins.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
        impliedPlatforms = mutableListOf("common")
        kotlinTasks {
            listOf()
        }

        sourceRoot {
            path = "${project.rootDir}/${project.name}/src/commonMain/kotlin"
            platforms = mutableListOf("common")
        }
        sourceRoot {
            path = "${project.rootDir}/${project.name}/src/jvmMain/kotlin"
            platforms = mutableListOf("jvm")
        }

        listOf("darwin", "linux", "mingw", "native", "pthread")
            .forEach { itName ->
                val srcRootPath = "${project.rootDir}/${project.name}/src/${itName}Main/kotlin"
                val srcRootPathExists = File(srcRootPath).exists()

                if (srcRootPathExists) {
                    sourceRoot {
                        path = srcRootPath
                        platforms = mutableListOf("native")
                    }
                }
            }
    }
}