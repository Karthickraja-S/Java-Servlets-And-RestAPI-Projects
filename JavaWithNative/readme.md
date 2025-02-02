
# Advantages
1.   Native code (C/C++) is generally faster than Java for computationally intensive tasks like image processing, cryptography, 
or mathematical calculations. Useful when optimizing for low-level hardware operations.
2. Native implementations can sometimes be more memory-efficient than Java’s managed heap and garbage collection.
3. Native code allows Java to interact with such OS-specific functionality.
4. Native code cannot be decompiled easily and java class file can be readable
5. We can make APIs and schedulers more faster with cpp implementation since cpp are faster

# DisAdvantages
1. Java is "Write Once, Run Anywhere", but native code breaks this principle.
2. You need to compile and distribute different versions of the native library for Windows, Linux, macOS, etc.
3. Using JNI (Java Native Interface) allows native code to bypass Java’s security mechanisms.
4. A vulnerability in the native library could compromise the entire application.
5. Java’s automatic garbage collector does not manage memory allocated by native code.
6. Developers must manually handle memory in C/C++ to prevent memory leaks.



# How to create and integrate DLL

1. Create a native method 
2. Call javah <your_class_with_pkg_full_name>  <br>
   javah com.nativecode.impl.NativeService   <br>
   ( in the dir where u have class file)
3. The above command will generate header file. 
4. Open VSCode and create a DLL Project
5. Include jdk_path\include & jdk_path\include\win32
 to your internal dependency By right clicking project name in 
solution explorer -> c/c++ -> Add additional dependency
6. Include your header file too
7. Build the project
8. Include the project in your java APP 
9. Declare java.library.path to your dll path
10. Now call the native method. It will call your c++ code.