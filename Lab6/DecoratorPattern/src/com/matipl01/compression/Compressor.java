package com.matipl01.compression;

public interface Compressor {
    String compress(String data);
    String decompress(String data);
}
