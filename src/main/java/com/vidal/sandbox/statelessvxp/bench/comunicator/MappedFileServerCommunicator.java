package com.vidal.sandbox.statelessvxp.bench.comunicator;


import com.vidal.sandbox.statelessvxp.util.NicePrinterHelper;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedFileServerCommunicator {
    public static final String FILE_NAME=System.getProperty("java.io.tmpdir")+"JCBMappedFile.mf";
    public static final int FILE_SIZE = 100 * 1024 * 1024;// 100Mo
    public static final int SERVER_WAITING = -1;


    public static void main(String[] args) throws Exception {

        MappedByteBuffer mem =
                new RandomAccessFile(FILE_NAME, "rw").getChannel()
                        .map(FileChannel.MapMode.READ_WRITE, 0, FILE_SIZE);
        mem.rewind(); mem.putInt(SERVER_WAITING);mem.rewind();
        System.out.println("Using file :"+FILE_NAME+ "    size:"+ NicePrinterHelper.printMemory(FILE_SIZE));
        while(true){
            int sizeToRead;
            mem.rewind();
            while((sizeToRead=mem.getInt()) ==SERVER_WAITING) { mem.rewind(); Thread.sleep(0); } // waiting for client request
            //somethink to read.. read it..
            byte[] readedBuff = new byte[sizeToRead];
            mem.get(readedBuff);
            System.out.println("READ MEMORY : " + readedBuff.length);

            // sending the reply
            mem.rewind();
            mem.putInt(sizeToRead);
            mem.putInt(sizeToRead); // sending the reply
            mem.rewind();
            mem.putInt(SERVER_WAITING);
        }
    }
}
