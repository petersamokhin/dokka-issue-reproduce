buildscript {
    repositories {
        mavenCentral()
        google()
        jcenter()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", "1.3.72"))
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.10.1")
    }
}

allprojects {
    apply(plugin = "org.jetbrains.dokka")

    // it makes no sense, where to place this line;
    // as far as I can see the command line output;
    // docs directory is empty in all cases
    // apply(from = "${rootDir}/gradle/dokka.gradle.kts")

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
}

// or this line can be here
// apply(from = "${rootDir}/gradle/dokka.gradle.kts")