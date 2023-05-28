# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#-keep class com.shockwave.**

# ------------------- x -----------

#-dontwarn javax.annotation.**
#-dontwarn org.w3c.dom.**
#
#-ignorewarnings
##-dontobfuscate
##-renamesourcefileattribute SourceFile
#
## ------------ x -----------
#
#-keep class * {
#    public private *;
#}
#
##-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
#
#-keepclassmembers enum * { public static **[] values(); public static ** valueOf(java.lang.String); }
#-keep public class * extends android.app.Activity
#-keep public class * extends android.app.Application
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
#-keep public class * extends android.content.ContentProvider
#-keep public class * extends android.view.View { public <init>(android.content.Context); public <init>(android.content.Context, android.util.AttributeSet); public <init>(android.content.Context, android.util.AttributeSet, int); public void set*(...); }
#
#-keepclassmembers class * extends android.app.Activity { public void *(android.view.View); }
#-keepclassmembers class androidx.fragment.app.Fragment { *** getActivity(); public *** onCreate(); public *** onCreateOptionsMenu(...); }




# --------- x ----------------

#AndroidX
#-keep class com.google.android.material.** { *; }
#
#-dontwarn com.google.android.material.**
#-dontnote com.google.android.material.**
#
#-dontwarn androidx.**
#-keep class androidx.** { *; }
#-keep interface androidx.** { *; }



#AndroidX Annotations
-keep class androidx.annotation.Keep {*;}

-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
#-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from     TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer


#Volly
-keep class com.android.volley.** { *; }
-keep class org.apache.commons.logging.**

-keepattributes *Annotation*

-dontwarn org.apache.**


#Okhttp Okio

#Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*


### pdf viewer
-keep class com.shockwave.**



#My Model Classess
-keep class com.example.valorpaytechsystask.model.** {*;}