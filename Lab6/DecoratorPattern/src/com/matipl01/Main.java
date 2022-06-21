package com.matipl01;

import com.matipl01.data.CompressionDecorator;
import com.matipl01.data.DataSource;
import com.matipl01.data.EncryptionDecorator;
import com.matipl01.data.FileDataSource;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/*
Example commands

- For encryption:
    ENCRYPT data/plain.txt data/encrypted.txt
- For decryption:
    DECRYPT data/encrypted.txt data/decrypted.txt
*/
public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            throw new Exception("Please provide <"
                    + Arrays.stream(Mode.values()).map(Mode::toString).collect(Collectors.joining("|"))
                    + "> <input file path> <output file path>");
        }

        Optional<Mode> mode = Mode.fromString(args[0]);
        if (mode.isEmpty()) throw new Exception("Invalid mode specified");
        String inFilePath = args[1];
        String outFilePath = args[2];
        DataSource inDataSource, outDataSource;

        switch (mode.get()) {
            case ENCRYPT -> {
                // Create the data source to read the plain (not encrypted) text file
                inDataSource = new FileDataSource(inFilePath);
                // Create the data source to write the encrypted and compressed file
                outDataSource = new EncryptionDecorator(new CompressionDecorator(new FileDataSource(outFilePath)));
                // Read the input file and write the encrypted and compressed data to the output file
                outDataSource.writeData(inDataSource.readData());
            }
            case DECRYPT -> {
                // Create the data source to read the encrypted and compressed input file
                inDataSource = new CompressionDecorator(new EncryptionDecorator(new FileDataSource(inFilePath)));
                // Create the data source to write the decrypted and uncompressed output file
                outDataSource = new FileDataSource(outFilePath);
                // Read the input file and write decrypted and uncompressed data to the output file
                outDataSource.writeData(inDataSource.readData());
            }
        }
    }
}
