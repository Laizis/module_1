package org.example;


/**
 * Класс - отвечает за обработку команд пользователя
 * Вызов методов
 * Управление потоком работы программы
 **/
public class MainApp {
    public static void main(String[] args) {
        Cipher cipher = new Cipher();
        System.out.println(cipher.encrypt("АбВгД эюЯ'", 2));
    }
}
