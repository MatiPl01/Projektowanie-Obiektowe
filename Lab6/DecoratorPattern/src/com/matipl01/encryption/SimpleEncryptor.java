package com.matipl01.encryption;

public class SimpleEncryptor implements Encryptor {
    private final int key;

    public SimpleEncryptor(int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String data) {
        char[] chars = data.toCharArray();
        for (int i = 0; i < data.length(); i++) chars[i] += key;
        return String.valueOf(chars);
    }

    @Override
    public String decrypt(String data) {
        char[] chars = data.toCharArray();
        for (int i = 0; i < data.length(); i++) chars[i] -= key;
        return String.valueOf(chars);
    }
}
