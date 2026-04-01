package org.example.fileutils;

import org.example.validate.FileException;
import org.example.validate.Validator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 * Чтение и запись файлов
 **/
public class FileManager {

    public static String readFile(String filePath) {
        boolean fileExists = Validator.isFileExists(filePath);
        boolean correctFileFormat = Validator.isCorrectFileFormat(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        if (fileExists && correctFileFormat) {
            final Path path = Paths.get(filePath);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(path, StandardOpenOption.READ)))) {
                while (bufferedReader.ready()) {
                    stringBuilder.append(bufferedReader.readLine());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
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
            try (Scanner scanner = new Scanner(content);
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
                while (scanner.hasNext()) {
                    bufferedWriter.write(scanner.nextLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (!fileExists) throw new FileException("Ошибка при записи в файл/файл не существует");
            else if (!correctFileFormat) throw new FileException("Ожидаемый формат файла .txt");
        }
    }
}
