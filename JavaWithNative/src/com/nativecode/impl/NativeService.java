package com.nativecode.impl;

public class NativeService {

    static {
        // check printing java.library.path and put the dll in that path.
        // else u need to put -Djava.library.path to your tomcat application saying that look for native dll in this path :)
        System.loadLibrary("JNI_DLL");
    }
    public native String getMsgFromNative();
}
