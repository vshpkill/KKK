package com.youlehuo.app.basejava.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by xiaohe on 17-12-25.
 */

public class FileUtils {

    public void methodIn() throws IOException {
        InputStream inputStream = null;
        Reader reader = null;

        inputStream.read();
        reader.read();
//        ByteArrayInputStream;
//        StringBufferInputStream;
//        FileInputStream;
//        PipedInputStream;
//        SequenceInputStream;
//        FilterInputStream;
//        DataInputStream;
//        BufferedInputStream;
//        LineNumberInputStream;
//        PushbackInputStream;
//        PrintStream;
//        InputStreamReader;
//
//        FileReader;
//
//        StringReader;
//        CharArrayReader;
//        PipedReader;
//
//        BufferedReader;
//        BufferedWriter;
//        PrintWriter;
//        LineNumberReader;
//        StreamTokenizer(使用接受Reader的构造器)；
//        PushbackReader；
        RandomAccessFile accessFile = new RandomAccessFile("/data","rwd");

    }

    public void methodOut() throws IOException {
        OutputStream outputStream = null;
        Writer writer = null;

        writer.write(1);
        outputStream.write(1);
//        OutputStreamWriter
    }

}
