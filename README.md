# Dokka
## Kotlin Multuplatform
## Issue reproduce project

All necessary information is available in these files:

/build.gradle.kts
/gradle/dokka.gradle.kts
/some_module/build.gradle.kts

See the comments.

### Issue

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