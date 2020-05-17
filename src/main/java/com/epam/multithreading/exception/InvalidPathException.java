package com.epam.multithreading.exception;

import java.io.IOException;

public class InvalidPathException extends Throwable {
    public InvalidPathException(String path, IOException ex) {
        super(path, ex);
    }
}
