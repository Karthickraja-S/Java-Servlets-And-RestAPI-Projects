#include <jni.h>        // JNI header provided by JDK
#include <stdio.h>      // C Standard IO Header
#include<com_nativecode_impl_NativeService.h>

// Implementation of the native method sayHello()
JNIEXPORT jstring JNICALL Java_com_nativecode_impl_NativeService_getMsgFromNative(JNIEnv* env, jobject thisObj) {
	return env->NewStringUTF("Hello world from native buddy!");
}