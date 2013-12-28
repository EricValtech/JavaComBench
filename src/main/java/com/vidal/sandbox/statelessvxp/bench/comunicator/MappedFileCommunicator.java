package com.vidal.sandbox.statelessvxp.bench.comunicator;

import com.vidal.sandbox.statelessvxp.pojo.PojoFactory;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedFileCommunicator extends BenchCommunicator {

    public MappedByteBuffer MEM;

    @Override
    public void startCommunication() throws Exception {
        if (getServerProcess() == null) { throw new RuntimeException("Server is down.. can't continue.."); }
        MEM= new RandomAccessFile(MappedFileServerCommunicator.FILE_NAME, "rw").getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, MappedFileServerCommunicator.FILE_SIZE);
    }

    @Override
    public Object doBench(Class<? extends PojoFactory> factory, Integer nbPojo) throws Exception {
        if (getServerProcess() == null) { throw new RuntimeException("Server is down.. can't continue.."); }
//        if (! MEM.isLoaded()) { throw new RuntimeException("Memory file not loaded.."); }
        byte [] data = getToSerialise().toString().getBytes();
        MEM.rewind();
        MEM.putInt(MappedFileServerCommunicator.SERVER_WAITING);
        MEM.put(data);
        MEM.rewind();
        MEM.putInt(data.length);

        MEM.rewind();
        int sizeFromServer;
        while((sizeFromServer=MEM.getInt()) != MappedFileServerCommunicator.SERVER_WAITING) { MEM.rewind(); Thread.sleep(0); }
        return MEM.getInt();
    }

    @Override
    public void stopCommunication() throws Exception{
       //DO NOTHINK according to documentation :
       //"A mapped byte buffer and the file mapping that it represents remain valid until the buffer itself is garbage-collected..."
    }

    @Override
    public String getName() {
        return "MappedFileCommunicator";
    }

    @Override
    public String getShortName() {
        return "mf";
    }

    @Override
    public String getDescription() {
        return "Uses mapped file to share data should use file :"+MappedFileServerCommunicator.FILE_NAME;
    }

    @Override
    public Class<?> getServerClass() {
        return MappedFileServerCommunicator.class;
    }




}
