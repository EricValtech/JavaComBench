#generate needed header file
%JAVA_HOME%\bin\javah -o PipeWindowsJNI.h -classpath ..\..\..\..\..\target\classes -jni com.vidal.sandbox.statelessvxp.util.PipeWindowsJNI

#compile the stuff
#   - cl.exe is needed download Visual studio express it if not present in path
#   - JAVA_HOME should be set properly..
pushd generated
set VS_HOME=d:\softs\vs
%VS_HOME%\VC\vcvarsall.bat
%VS_HOME%\VC\bin\cl -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" -LD ..\PipeWindowsJNI.c -FePipeWindowsJNI.dll
popd
