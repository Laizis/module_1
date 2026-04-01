package org.example;

import org.example.validate.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс, реализующий функциональность шифра Цезаря и дешифровки
 **/
public class Cipher {
    /**
     * Русский алфавит
     */
    private static final List<Character> alphabet = new ArrayList<>(Arrays.asList(
            'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф',
            'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'
    ));

    /**
     * Возвращает зашифрованную строку со сдвигом на shift
     **/
    public String encrypt(String text, int shift) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] textToCharArray = text.toLowerCase().toCharArray();
        if (Validator.isValidKey(shift, alphabet)) {
            for (char characterFromText : textToCharArray) {

                if (Character.isLetter(characterFromText)) {
                    if (!alphabet.contains(characterFromText)) {
                        continue;
                    }
                    int indexNewCharacter = alphabet.indexOf(characterFromText) + shift;
                    char encryptLetter = indexNewCharacter > alphabet.size() - 1 ? alphabet.get(indexNewCharacter - alphabet.size()) : alphabet.get(indexNewCharacter);
                    stringBuilder.append(encryptLetter);
                } else {
                    stringBuilder.append(characterFromText);
                }

            }
            return stringBuilder.toString();
        } else {
            throw new IndexOutOfBoundsException("Указанный сдвиг больше или меньше допустимого возможного значения");
        }
    }

    /**
     * Возвращает расшифрованную  строку со сдвигом на shift
     **/
    public String decrypt(String encryptedText, int shift) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] textToCharArray = encryptedText.toLowerCase().toCharArray();
        if (Validator.isValidKey(shift, alphabet)) {
            for (char characterFromText : textToCharArray) {

                if (Character.isLetter(characterFromText)) {
                    if (!alphabet.contains(characterFromText)) {
                        continue;
                    }
                    int indexNewCharacter = alphabet.indexOf(characterFromText) - shift;
                    char decryptLetter = indexNewCharacter < 0 ? alphabet.get(alphabet.size() + indexNewCharacter) : alphabet.get(indexNewCharacter);
                    stringBuilder.append(decryptLetter);
                } else {
                    stringBuilder.append(characterFromText);
                }

            }
            return stringBuilder.toString();
        } else {
            throw new IndexOutOfBoundsException("Указанный сдвиг больше или меньше допустимого возможного значения");
        }
    }
}
