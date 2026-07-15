# Hilt / Dagger ережелері
-keep,allowobfuscation,allowshrinking class dagger.**
-keep,allowobfuscation,allowshrinking class hilt_aggregated_deps.**
-keep,allowobfuscation,allowshrinking class dagger.hilt.**

# Біздің код толық obfuscate және shrink етіледі.
# Тек EntryPoint-терді (Activity/Service/Application) R8 өзі автоматты түрде сақтайды.
