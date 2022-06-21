package com.matipl01;

import java.util.Arrays;
import java.util.Optional;

public enum Mode {
    ENCRYPT("ENCRYPT"),
    DECRYPT("DECRYPT");

    private final String displayedName;

    Mode(String displayedName) {
        this.displayedName = displayedName;
    }

    @Override
    public String toString() {
        return displayedName;
    }

    public static Optional<Mode> fromString(String mode) {
        return Arrays.stream(values())
                .filter(m -> m.toString().equals(mode))
                .findFirst();
    }
}
