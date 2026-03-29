package org.example;

public class FileException extends RuntimeException{

    public FileException(String errorMessage) {
        System.out.println(errorMessage);
    }
}
