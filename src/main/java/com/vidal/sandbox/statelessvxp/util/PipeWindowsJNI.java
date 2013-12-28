package com.vidal.sandbox.statelessvxp.util;


public class PipeWindowsJNI {
    static
    {
        try {System.loadLibrary("PipeWindowsJNI"); }
        catch (Exception e) {
            System.out.println("ERROR: linking dll 'PipeWindowsJNI' verify your path or -Djava.library.path='lib'(if you are not in windows just skip this test)");
            throw new RuntimeException("DLL link error",e);
        }
    }

    public static final native int CreateNamedPipe(String pipeName,
                                                   int ppenMode, int pipeMode, int maxInstances,
                                                   int outBufferSize, int inBufferSize, int defaultTimeOut,
                                                   int securityAttributes);

    public static final native boolean ConnectNamedPipe(int namedPipeHandle,
                                                        int overlapped);
    public static final native int GetLastError();
    public static final native boolean CloseHandle(int bbject);
    public static final native byte[] ReadFile(int file, int numberOfBytesToRead);
    public static final native int WriteFile(int file, byte[] buffer,
                                             int numberOfBytesToWrite);
    public static final native boolean FlushFileBuffers(int file);
    public static final native boolean DisconnectNamedPipe(int namedPipeHandle);
    public static final native int CreateFile(String fileName,
                                              int desiredAccess, int shareMode, int securityAttributes,
                                              int creationDisposition, int flagsAndAttributes,
                                              int templateFile);

    public static final native boolean WaitNamedPipe(String namedPipeName, int timeOut);
    public static final native String FormatMessage(int errorCode);
    public static final native void Print(String message);
}
