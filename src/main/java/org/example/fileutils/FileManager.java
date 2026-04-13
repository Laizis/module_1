package org.example.fileutils;

import org.example.validate.FileException;
import org.example.validate.Validator;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Чтение и запись файлов
 **/
public class FileManager {

    public static String readFile(String filePath) {
        boolean fileExists = Validator.isFileExists(filePath);
        boolean correctFileFormat = Validator.isCorrectFileFormat(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        if (fileExists && correctFileFormat) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
                while (bufferedReader.ready()) {
                    stringBuilder.append(bufferedReader.readLine());
                    if (bufferedReader.ready()) {
                        stringBuilder.append('\n');
                    }
                }
            } catch (IOException e) {
                throw new FileException(String.format("Непредвиденная ошибка вычитка из файла %s", filePath));
            }
        } else {
            if (!fileExists) throw new FileException("Ошибка при записи в файл/файл не существует");
            else if (!correctFileFormat) throw new FileException("Ожидаемый формат файла .txt");
        }
        return stringBuilder.toString();

    }

    public static void writeFile(String content, String filePath) {
        boolean fileExists = Validator.isFileExists(filePath);
        boolean correctFileFormat = Validator.isCorrectFileFormat(filePath);

        if (fileExists && correctFileFormat) {
            try (Writer writer = new FileWriter(filePath, StandardCharsets.UTF_8)) {
                writer.write(content);
            } catch (IOException e) {
                throw new FileException(String.format("Непредвиденная ошибка записи в файл %s", filePath));
            }
        } else {
            if (!fileExists) throw new FileException("Ошибка при записи в файл/файл не существует");
            else if (!correctFileFormat) throw new FileException("Ожидаемый формат файла .txt");
        }
    }
}
