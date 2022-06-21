package com.matipl01.data;

import com.matipl01.encryption.Encryptor;
import com.matipl01.encryption.SimpleEncryptor;

import java.io.IOException;

public class EncryptionDecorator extends DataSourceDecorator {
    private static final int ENCRYPTION_KEY = 5;
    Encryptor encryptor = new SimpleEncryptor(ENCRYPTION_KEY);

    public EncryptionDecorator(DataSource wrapper) {
        super(wrapper);
    }

    @Override
    public void writeData(String data) throws IOException {
        System.out.println("Encrypted: " + encryptor.encrypt(data));
        wrapper.writeData(encryptor.encrypt(data));
    }

    @Override
    public String readData() throws IOException {
        System.out.println("Decrypted: " + encryptor.decrypt(wrapper.readData()));
        return encryptor.decrypt(wrapper.readData());
    }
}
