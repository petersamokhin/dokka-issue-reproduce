buildscript {
    repositories {
        mavenCentral()
        google()
        jcenter()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", "1.3.72"))

        // Change version to this and use `dokka.gradle{.kts}` files.
        // classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.10.1")
        
        // Change version to this and use `0.9.18_dokka.gradle{.kts}` files.
        // classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.9.18")
    }
}

allprojects {
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
        JavaVersion.VERSION_1_8.toString().also {
            kotlinOptions.jvmTarget = it
            if (plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
                sourceCompatibility = it
                targetCompatibility = it
            }
        }
    }

    repositories {
        mavenCentral()
        google()
        jcenter()
    }

    group = "com.petersamokhin.dokkaissuereproduce"
    version = "0.0.1-SNAPSHOT"

    // here, or at the end of the each build.gradle in necessary modules
    if (project.name != rootProject.name) {
        afterEvaluate {
            // 0.10.1
            // apply(from = "${rootDir}/gradle/dokka.gradle")
            // apply(from = "${rootDir}/gradle/dokka.gradle.kts")
            
            // 0.9.18
            // apply(from = "${rootDir}/gradle/0.9.18_dokka.gradle")
            // apply(from = "${rootDir}/gradle/0.9.18_dokka.gradle.kts")
        }
    }
}