object deps {
    object android {
        object arch {
            val lifecycle = "android.arch.lifecycle:extensions:${versions.archLifecycle}"
            val lifecycle_compiler = "android.arch.lifecycle:compiler:${versions.archLifecycle}"
            val lifecycle_test = "android.arch.core:core-testing:${versions.archLifecycle}"
            val room = "android.arch.persistence.room:runtime:1.1.0"
            val room_compiler = "android.arch.persistence.room:compiler:1.1.0"
            val room_test = "android.arch.persistence.room:testing:1.1.0"
            val paging = "android.arch.paging:runtime:1.0.0"
        }
        object support {
            val v7 = "com.android.support:appcompat-v7:${versions.supportLibrary}"
            val cardview = "com.android.support:cardview-v7:${versions.supportLibrary}"
            val design = "com.android.support:design:${versions.supportLibrary}"
            val v4 = "com.android.support:support-v4:${versions.supportLibrary}"
            val constraint_layout = "com.android.support.constraint:constraint-layout:1.0.2"
            val preference = "com.android.support:preference-v7:${versions.supportLibrary}"
            val multidex = "com.android.support:multidex:1.0.1"
        }
        val databinding = "com.android.databinding:compiler:${versions.gradle}"
    }
    object kotlin {
        object stdlib {
            val jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${versions.kotlin}"
        }
        object coroutines {
            val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.coroutines}"
            val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.coroutines}"
        }
        object ktor {
            val servlet = "io.ktor:ktor-server-servlet:${versions.ktor}"
            val location = "io.ktor:ktor-locations:${versions.ktor}"
            val gson = "io.ktor:ktor-gson:${versions.ktor}"
            val auth_jwt = "io.ktor:ktor-auth-jwt:${versions.ktor}"
            val html_builder = "io.ktor:ktor-html-builder:${versions.ktor}"
        }
    }
    object retrofit {
        val client = "com.squareup.retrofit2:retrofit:${versions.retrofit}"
        val converter_scalars = "com.squareup.retrofit2:converter-scalars:2.3.0"
        val converter_gson = "com.squareup.retrofit2:converter-gson:2.3.0"
    }
    object leakcanary {
        val debug = "com.squareup.leakcanary:leakcanary-android:${versions.leakcanary}"
        val release = "com.squareup.leakcanary:leakcanary-android-no-op:${versions.leakcanary}"
    }
    val circleimageview = "de.hdodenhof:circleimageview:+"
    val jsoup = "org.jsoup:jsoup:+"
    val javaee_api = "javax:javaee-api:7.0"
    val gson = "com.google.code.gson:gson:2.8.2"
    val slf4j = "org.slf4j:slf4j-jdk14:1.8.0-beta2"
    object apache {
        val dbutils = "commons-dbutils:commons-dbutils:+"
        val fileupload = "commons-fileupload:commons-fileupload:+"
        object tomcatEmbed {
            val core = "org.apache.tomcat.embed:tomcat-embed-core:${versions.tomcat}"
            val jasper = "org.apache.tomcat.embed:tomcat-embed-jasper:${versions.tomcat}"
        }
    }
    val mariadb_client = "org.mariadb.jdbc:mariadb-java-client:+"
    object test {
        val junit = "junit:junit:${versions.junit}"
        val mockito = "org.mockito:mockito-core:${versions.mockito}"
        val mockito_android = "org.mockito:mockito-android:${versions.mockito}"
        val hamcrest = "org.hamcrest:hamcrest-all:${versions.hamcrest}"
        val runner = "com.android.support.test:runner:${versions.runner}"
        val rules = "com.android.support.test:rules:${versions.rules}"
        object espresso {
            val core = "com.android.support.test.espresso:espresso-core:${versions.espresso}"
            val contrib = "com.android.support.test.espresso:espresso-contrib:${versions.espresso}"
            val intents = "com.android.support.test.espresso:espresso-intents:${versions.espresso}"
            val idling_concurrent = "com.android.support.test.espresso.idling:idling-concurrent:${versions.espresso}"
            val idling_resource = "com.android.support.test.espresso:espresso-idling-resource:${versions.espresso}"
        }
    }
    object buildPlugins {
        val android = "com.android.tools.build:gradle:${versions.gradle}"
        val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
        val tomcat = "com.bmuschko:gradle-tomcat-plugin:2.4.2"
    }
}