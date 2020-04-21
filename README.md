# Dokka
### Kotlin Multuplatform
### Issue reproduce project

All necessary information is available in these files:

[/build.gradle.kts](https://github.com/petersamokhin/dokka-issue-reproduce/blob/master/build.gradle.kts)<br>
[/gradle/dokka.gradle.kts](https://github.com/petersamokhin/dokka-issue-reproduce/blob/master/gradle/dokka.gradle.kts)<br>
[/some_module/build.gradle.kts](https://github.com/petersamokhin/dokka-issue-reproduce/blob/master/some_module/build.gradle.kts)<br>

See the comments.

## Issue: 
### files: dokka.gradle.kts or dokka.gradle, library version: 0.10.1

Current task result:

```
./gradlew dokka

> Configure project :some_module
Kotlin Multiplatform Projects are an experimental feature.
Could not find target with name:  in Kotlin Gradle Plugin
Could not find target with name:  in Kotlin Gradle Plugin
Could not find target with name:  in Kotlin Gradle Plugin
Could not find target with name:  in Kotlin Gradle Plugin

Some Kotlin/Native targets cannot be built on this macos_x64 machine and are disabled:
    * In project ':some_module':
        * target 'mingwX64' (can be built with a mingw_x64 host)
To hide this message, add 'kotlin.native.ignoreDisabledTargets=true' to the Gradle properties.


> Task :dokka UP-TO-DATE
Could not find target with name:  in Kotlin Gradle Plugin
Could not find target with name:  in Kotlin Gradle Plugin

> Task :some_module:dokka
Could not find target with name:  in Kotlin Gradle Plugin
Could not find target with name:  in Kotlin Gradle Plugin
Could not find target with name:  in Kotlin Gradle Plugin
warning: duplicate source root: /Users/petersamokhin/Projects/dokka-issue-reproduce/some_module/src/commonMain/kotlin

Deprecated Gradle features were used in this build, making it incompatible with Gradle 7.0.
Use '--warning-mode all' to show the individual deprecation warnings.
See https://docs.gradle.org/6.3/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 1s
2 actionable tasks: 1 executed, 1 up-to-date
```

And there are no files produced by dokka.

### How to fix:
Copy the entire code:

```kotlin
apply(plugin = "org.jetbrains.dokka")
tasks.withType(org.jetbrains.dokka.gradle.DokkaTask::class.java).all {
	/* sure, the entire code */
}
```

And place it at the end of `some_module/build.gradle.kts` file.

## Issue
### files: dokka.gradle.kts or dokka.gradle, library version: 0.9.18
This versions is working with the groovy script.
See:
[/gradle/0.9.18_dokka.gradle](https://github.com/petersamokhin/dokka-issue-reproduce/blob/master/gradle/0.9.18_dokka.gradle)<br>

But with the .kts script
See:
[/gradle/0.9.18_dokka.gradle](https://github.com/petersamokhin/dokka-issue-reproduce/blob/master/gradle/0.9.18_dokka.gradle.kts)<br>

There is an error:
```
* What went wrong:
Could not determine the dependencies of task ':core:dokka'.
> kotlin.KotlinNullPointerException (no error message)
```

# So, the only working variant is groovy script with 0.9.18 version.