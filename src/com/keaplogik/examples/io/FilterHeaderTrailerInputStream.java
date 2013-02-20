package com.keaplogik.examples.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Used for filtering (consuming) header and trailer lines from Input stream.
 */
public class FilterHeaderTrailerInputStream extends FilterInputStream
{
    private Queue<Integer> readBuffer = new LinkedList <Integer>();
    int lastByte;
    boolean hasReadHeader;

    public FilterHeaderTrailerInputStream(InputStream inputStream)
    {
        super(inputStream);
        this.lastByte = -1;
        this.hasReadHeader = false;
    }

    @Override
    public int read(byte[] bytes) throws IOException {
        return read(bytes, 0, bytes.length) ;
    }

    @Override
    public int read(byte[] bytes, int offset, int length) throws IOException {
        int bytesRead = 0;
        final byte[] readBytes = new byte[length - offset];
        while (bytesRead < length - offset) {
            final int readByte = read();
            if (readByte != -1) {
                readBytes[bytesRead++] = (byte) readByte;
            }
            else {
                break;
            }
        }
        if (bytesRead == 0) {
            return -1;
        }
        System.arraycopy(readBytes, 0, bytes, offset, bytesRead);
        return bytesRead;
    }

    @Override
    public int read() throws IOException {

        // The buffer has stuff in it, so serve it off.
        if (!this.readBuffer.isEmpty()) {
            return readBuffer.remove();
        }

        //A previous buffered read contained an extra byte. Place it on the queue.
        if(lastByte != -1){
            readBuffer.offer(lastByte);
        }

        //Read bytes until reaching a line feed or end of file
        int currentRead;
        do{
            currentRead = super.read();
            readBuffer.offer(currentRead);
        }while (currentRead != '\n' && currentRead != '\r' && currentRead != -1);

        //The byte after a newline can either be another '\n', the first byte on the next line or 
        //there are no more bytes left.
        int afterLineByte = currentRead == -1 ? currentRead : super.read();

        //The byte signifies end of file. Guaranteed to have the trailer line in the buffer.
        if(afterLineByte == -1){
            readBuffer.clear(); // Clear out the buffer as to consume the line
            return -1; //return end of file indicator
        }
        //A double line break is found. Add it to the read buffer. 
        //If the next bite is not available, end of file is reached.
        else if(afterLineByte == '\n'){
            readBuffer.offer(afterLineByte);
            if(super.available() == 0){//end of file.  Guaranteed to have the trailer line in the buffer.
                readBuffer.clear();// Clear out the buffer as to consume the line 
            }
        }else{
            //Hold the last byte. Will be added to the queue on the next recursive call.
            lastByte = afterLineByte;
        }

        //When header hasn't been read, take the current line and consume it. Set indicator that header's been read.
        if(!hasReadHeader){
            readBuffer.clear();
            hasReadHeader = true;
        }

        return this.read();
    }
}
