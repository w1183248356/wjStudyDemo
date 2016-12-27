# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\ruanjian\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#基本配置信息----------start--------
-dontshrink
-dontpreverify
-dontoptimize
-dontusemixedcaseclassnames
-flattenpackagehierarchy
-allowaccessmodification
#当库文件修改的时候 必须重新输出mapping.txt
#-printmapping mapping.txt

-optimizationpasses 7
-verbose
-keepattributes Exceptions,InnerClasses
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-ignorewarnings
#保持相同的混淆映射（规则）否则AndFix不能成功
#-applymapping ./mapping.txt

-keepclassmembers class * {
   private native <methods>;
   public native <methods>;
   protected native <methods>;
   public static native <methods>;
   private static native <methods>;
   static native <methods>;
   native <methods>;
}
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

-dontwarn android.support.**
-dontwarn java.lang.invoke.*
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#基本配置信息----------end--------

#不混淆所有的指定包下的类和这些类的所有成员变量-------start
-keep class com.qpwa.app.afieldserviceoa.bean.**{*;}

#不混淆所有的指定包下的类和这些类的所有成员变量-------end

#realm 第三方数据存储操作----------start--------
#-keep class io.realm.annotations.RealmModule
#-keep @io.realm.annotations.RealmModule class *
#-keep class io.realm.internal.Keep
#-keep @io.realm.internal.Keep class * { *; }
#-dontwarn javax.**
#-dontwarn io.realm.**
#realm 第三方数据存储操作----------end--------

# retrofit2----------start--------
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
# retrofit2----------end--------

#Okhttp3----------start--------
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
#Okhttp3----------end--------

# 七牛----------start--------
-keep class com.qiniu.**{*;}
-keep class com.qiniu.**{public <init>();}
-ignorewarnings
# 七牛----------end--------

#环信-------------------start------------
-keep class com.hyphenate.** {*;}
-dontwarn  com.hyphenate.**

-keep class org.jivesoftware.** {*;}
-keep class org.apache.** {*;}
#2.0.9后的不需要加下面这个keep
#-keep class org.xbill.DNS.** {*;}
#另外，demo中发送表情的时候使用到反射，需要keep SmileUtils
#注意前面的包名，如果把这个类复制到自己的项目底下，比如放在com.example.utils底下，应该这么写（实际要去掉#）
#如果使用EaseUI库，需要这么写
-keep class com.hyphenate.easeui.utils.EaseSmileUtils {*;}

#2.0.9后加入语音通话功能，如需使用此功能的API，加入以下keep
-dontwarn ch.imvs.**
-dontwarn org.slf4j.**
-keep class org.ice4j.** {*;}
-keep class net.java.sip.** {*;}
-keep class org.webrtc.voiceengine.** {*;}
-keep class org.bitlet.** {*;}
-keep class org.slf4j.** {*;}
-keep class ch.imvs.** {*;}
#环信-------------------end------------

#友盟代码混淆----------start--------
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

-keep public class com.qpwa.bclient.R$*{
    public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#友盟代码混淆----------end--------

#EventBus 3.0----------start--------
-keepattributes *Annotation*
-keepclassmembers class ** {
      @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode {
        *;
}
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
      <init>(java.lang.Throwable);
}
#EventBus 3.0----------end--------

#fastjson混淆配置----------start--------
-dontnote com.alibaba.**
-keep class com.alibaba.fastjson.**{*;}
#fastjson混淆配置----------end--------

##---------------Begin: proguard configuration for Gson  ----------
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.android.model.** { *; }
##---------------End: proguard configuration for Gson  ----------

#RxJava----------start--------
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}
#RxJava----------end--------

#Glide-----------start------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#Glide-----------end------

#Picasso-----------Start------
-dontwarn com.squareup.okhttp.**
#picasso-----------end------


#ButterKnife 7.0-----------Start------
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
 @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
@butterknife.* <methods>;
}
#ButterKnife 7.0-----------end------

#zxing 3.2.0-------------------start----
-dontwarn com.google.zxing.**
-keep  class com.google.zxing.**{*;}
#zxing 3.2.0-------------------end----

#百度地图-------------------start----
-keep class com.baidu.** { *; }
-keep class vi.com.gdi.bgl.android.**{*;}
#百度地图-------------------end----

#xtuils-------------------start----
-keep class com.lidroid.** { *; }
#xtuils-------------------start----

#银联-------------------start----
-keep class com.unionpay.**{*;}
-keep class com.UCMobile.PayPlugin.**{*;}
-keep class cn.gov.pbc.tsm.client.mobile.android.bank.service.**{*;}
#银联-------------------end----

#微信-------------------start----
-keep class com.tencent.mm.sdk.** {*;}
#微信-------------------end----

#icepick-----------------------start--------
-dontwarn icepick.**
-keep class icepick.** { *; }
-keep class **$$Icepick { *; }
-keepclasseswithmembernames class * {
    @icepick.* <fields>;
}
-keepnames class * { @icepick.State *;}
#icepick-----------------------end--------

#stetho-------------------start----
-keep class com.facebook.stetho.** { *; }
-dontwarn com.facebook.stetho.**
#stetho-------------------end----

#galleryfinal-----------------------start--------
-keep class cn.finalteam.galleryfinal.widget.*{*;}
-keep class cn.finalteam.galleryfinal.widget.crop.*{*;}
-keep class cn.finalteam.galleryfinal.widget.zoonview.*{*;}
#galleryfinal-----------------------end--------

#工具类开始-----start
-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**
#工具类结束----end
