package com.matipl01.data;

import com.matipl01.compression.Compressor;
import com.matipl01.compression.SimpleCompressor;

import java.io.IOException;

public class CompressionDecorator extends DataSourceDecorator {
    Compressor compressor = new SimpleCompressor();

    public CompressionDecorator(DataSource wrapper) {
        super(wrapper);
    }

    @Override
    public void writeData(String data) throws IOException {
        System.out.println("Compressed: " + compressor.compress(data));
        wrapper.writeData(compressor.compress(data));
    }

    @Override
    public String readData() throws IOException {
        System.out.println("Decompressed: " + compressor.decompress(wrapper.readData()));
        return compressor.decompress(wrapper.readData());
    }
}
