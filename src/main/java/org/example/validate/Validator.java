package org.example.validate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Валидация входных данных, файл существует, ключ допустим
 **/
public class Validator {
    public static boolean isValidKey(int key, List<Character> alphabet) {
        if (key <= alphabet.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isFileExists(String filePath) {
        return Files.exists(Path.of(filePath));
    }

    public static boolean isCorrectFileFormat(String filePath) {
        return filePath.endsWith("txt");
    }

}
