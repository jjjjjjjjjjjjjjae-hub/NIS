############################################################
# NIS - ProGuard / R8 Rules
############################################################

########################
# Source information
########################
-keepattributes SourceFile,LineNumberTable,*Annotation*,Signature,InnerClasses,EnclosingMethod

########################
# Hilt / Dagger
########################
-keep class dagger.** { *; }
-keep class dagger.hilt.** { *; }
-keep class hilt_aggregated_deps.** { *; }
-keep class javax.inject.** { *; }

-dontwarn dagger.**
-dontwarn javax.inject.**

########################
# Kotlin
########################
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**

########################
# Coroutines
########################
-dontwarn kotlinx.coroutines.**

########################
# AndroidX Lifecycle
########################
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**

########################
# Activities
########################
-keep public class * extends android.app.Activity
-keep public class * extends androidx.activity.ComponentActivity

########################
# Services
########################
-keep public class * extends android.app.Service

########################
# Application
########################
-keep public class * extends android.app.Application

########################
# ViewModels
########################
-keep class * extends androidx.lifecycle.ViewModel { *; }

########################
# Repository & UseCase
########################
-keep class com.example.nis.domain.** { *; }
-keep class com.example.nis.data.** { *; }

########################
# Remove logs in Release
########################
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

########################
# Ignore warnings
########################
-ignorewarnings

############################################################
# End
############################################################
