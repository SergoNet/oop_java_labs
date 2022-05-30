package ru.nsu.netesov.lab2;

import ru.nsu.netesov.lab2.exceptions.CantReadFileException;
import ru.nsu.netesov.lab2.exceptions.CantWriteFileException;
import ru.nsu.netesov.lab2.exceptions.IsNotFileException;

import java.io.File;

import java.io.IOException;

public class Utility {
    public static final String anyWordBetweenWhiteSpaces = "\\S+";

    public static void validateFile(File file, String option) throws IOException {

        if (!file.isFile()) {
            throw new IsNotFileException("is not file: " + file.getName());
        } else if ("r".equals(option) && !file.canRead()) {
            throw new CantReadFileException("cant read file " + file.getName());
        } else if ("w".equals(option) && !file.canWrite()) {
            throw new CantWriteFileException("cant write to file " + file.getName());
        }
    }
}

