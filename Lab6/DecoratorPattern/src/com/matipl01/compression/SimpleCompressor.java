package com.matipl01.compression;

public class SimpleCompressor implements Compressor {
    @Override
    public String compress(String data) {
        // Do some string compression here
        return data;
    }

    @Override
    public String decompress(String data) {
        // Do string decompression here
        return data;
    }
}
