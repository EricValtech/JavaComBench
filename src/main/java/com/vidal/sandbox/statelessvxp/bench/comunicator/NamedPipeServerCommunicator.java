package com.vidal.sandbox.statelessvxp.bench.comunicator;


import com.vidal.sandbox.statelessvxp.util.PipeWindowsJNI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NamedPipeServerCommunicator {
    static final int ERROR_PIPE_CONNECTED = 535;
    static final int ERROR_BROKEN_PIPE = 109;
    public static final String PIPE_NAME= "\\\\.\\pipe\\JCBTestPipe";
    private int namedPipeHandle;
    private String pipeName, srcFile;
    private int pipeBuffer = 131072, fileBuffer = 8192;


    public NamedPipeServerCommunicator(String pipeName, String srcFile)
    {
        this.pipeName = pipeName;
        this.srcFile = srcFile;
    }

    private void log(String message)
    {
        System.out.println(message);
    }

    private boolean createPipe()
    {
        boolean ok = false;
        namedPipeHandle = PipeWindowsJNI._Java_ibm_Pipes_CreateNamedPipe(pipeName,
                0x00000003, 0x00000000, 2, pipeBuffer,
                pipeBuffer, 0xffffffff, 0);
        if (namedPipeHandle == -1)
        {
            log("CreateNamedPipe failed for " + pipeName +
                    " for error " + " Message " +
                    PipeWindowsJNI.FormatMessage(PipeWindowsJNI.GetLastError()));
            ok = false;
        } else
        {
            log("Named Pipe " + pipeName +
                    " created successfully Handle=" + namedPipeHandle);
            ok = true;
        }
        return ok;
    }

    private boolean connectToPipe()
    {
        log("Waiting for a client to connect to pipe " + pipeName);
        boolean connected = PipeWindowsJNI.ConnectNamedPipe(namedPipeHandle, 0);
        if (!connected)
        {
            int lastError = PipeWindowsJNI.GetLastError();
            if (lastError == ERROR_PIPE_CONNECTED)
                connected = true;
        }
        if (connected)
        {
            log("Connected to the pipe " + pipeName);
        } else
        {
            log("Falied to connect to the pipe " + pipeName);
        }
        return connected;
    }

    public void runPipe()
    {
        if (createPipe())
        {
            if (!connectToPipe())
            {
                log("Connect ConnectNamedPipe failed : " +
                        PipeWindowsJNI.FormatMessage(PipeWindowsJNI.GetLastError()));
                return;
            } else
            {
                log("Client connected.");
            }

            try
            {
                File f1 = new File(this.srcFile);
                InputStream in = new FileInputStream(f1);
                log("Sending data to the pipe");
                byte[] buf = new byte[fileBuffer];
                int len, bytesWritten;
                while ((len = in.read(buf)) > 0)
                {
                    bytesWritten = PipeWindowsJNI.WriteFile(namedPipeHandle, buf, len);
                    log("Sent " + len + "/" + bytesWritten +
                            " bytes to the pipe");
                    if (bytesWritten == -1)
                    {
                        int errorNumber = PipeWindowsJNI.GetLastError();
                        log("Error Writing to pipe " +
                                PipeWindowsJNI.FormatMessage(errorNumber));
                    }
                }
                in.close();
                PipeWindowsJNI.FlushFileBuffers(namedPipeHandle);
                PipeWindowsJNI.CloseHandle(namedPipeHandle);
                PipeWindowsJNI.DisconnectNamedPipe(namedPipeHandle);
                log("Writing to the pipe completed.");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException,
            InterruptedException
    {
        String PIPE_NAME = "\\\\.\\pipe\\mynamedpipe";
        String fileName = "C:\\db2tabledata.txt";;
        NamedPipeServerCommunicator testPipe = new NamedPipeServerCommunicator(PIPE_NAME, fileName);
        testPipe.runPipe();
    }
}

