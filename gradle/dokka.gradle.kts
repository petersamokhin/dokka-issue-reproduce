/*
1. Without this `buildscript` block, build failed:

Unresolved reference: jetbrains

at the line `tasks.withType(org.jetbrains.dokka...`

Or if I use `import org.jetbrains.dokka...`, then build failed at the first line.
But all is ok and highlighted in the IDEA.

But, in this case, I can't remove the `classpath` line from the *root* build.gradle.kts file.
*/


buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.10.1")
    }
}

// 2.
// if this line is placed in `allproject` block of the root project build.gradle.kts,
// it can be removed from here
apply(plugin = "org.jetbrains.dokka")

/*
3. In case of this:
`tasks.withType(org.jetbrains.dokka...`

Build failed with the error:

```
* What went wrong:
Element 'dokka' of type 'org.jetbrains.dokka.gradle.DokkaTask_Decorated' from container 'org.gradle.kotlin.dsl.TaskContainerScope@102b804d' cannot be cast to 'org.jetbrains.dokka.gradle.DokkaTask'.
```
*/

tasks.withType(org.jetbrains.dokka.gradle.DokkaTask::class.java).all {
    // This exception is never thrown, so this task block is not executed
    throw GradleException("Never been here")
    
    outputDirectory = "$rootDir/docs/0.0.x"
    outputFormat = "gfm"

    // `subProjects` does nothing, as far as I think, because
    // the task output is the same in cases with or without this
    subProjects = mutableListOf(":some_module") 

    // In the dokka repo readme, said that:
    // << In the multiplatform mode, instead of using the configuration block, 
    // you should use a multiplatform block with inner blocks for each platform. >>
    // `subProjects` are available only on DokkaTask object
    configuration {
        // this does nothing
        impliedPlatforms = mutableListOf("common")
        
        skipEmptyPackages = true
        perPackageOption {
            prefix = "com.petersamokhin.dokkaissuereproduce.internal"
            suppress = true
        }

        // `kotlinTasks` block does nothing, as far as I think, because
        // the task output is the same in cases with or without this.
        // It only available inside the `configuration` block
        kotlinTasks {
            defaultKotlinTasks().apply {
                listOf(":core", ":http-clients:jvm-okhttp-http-client", ":http-clients:common-ktor-http-client")
                    .map { "$it:compileKotlin" }
            }
        }
    }

    if (project.plugins.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
        multiplatform {
            create("common") {
                sourceRoot {
                    // `kotlin` is not available here, 
                    // but kotlin.jvm plugin is available in the all modules
                    // applying this plugin to this file also does nothing
                    path = "${project.rootDir}/${project.name}/src/${name}Main/kotlin"
                    platform = "common"
                }
            }

            create("jvm") {
                sourceRoot {
                    path = "${project.rootDir}/${project.name}/src/${name}Main/kotlin"
                    platform = "jvm"
                }
            }

            listOf("darwin", "linux", "mingw", "native", "pthread")
                .forEach {
                    create(it) {
                        sourceRoot {
                            path = "${project.rootDir}/${project.name}/src/${name}Main/kotlin"
                            targets = mutableListOf("native")
                            platform = "native"
                        }
                    }
                }
        }
    } else {
        // should be handled automatically, 
        // because all the other modules usually are simple jvm-based
    }
}