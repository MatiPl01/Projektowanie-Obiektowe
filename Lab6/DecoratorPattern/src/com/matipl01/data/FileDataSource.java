package com.matipl01.data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDataSource implements DataSource {
    private final String filename;

    public FileDataSource(String filename) {
        this.filename = filename;
    }

    @Override
    public void writeData(String data) throws IOException {
        Files.writeString(Paths.get(filename), data);
    }

    @Override
    public String readData() throws IOException {
        return Files.readString(Paths.get(filename));
    }
}
