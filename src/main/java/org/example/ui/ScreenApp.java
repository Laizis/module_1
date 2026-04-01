package org.example.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class ScreenApp extends Application {
    /** Режим, выбранный на первом экране (пример «логики по кнопке»). */
    private String selectedMode;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Демо: два экрана");
        Scene scene1 = buildFirstScene(primaryStage);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    private Scene buildFirstScene(Stage stage) {
        Label label = new Label("Выберите действие: ");
        Button btnA = new Button("Вариант A");
        Button btnB = new Button("Вариант B");
        btnA.setOnAction(e -> {
            selectedMode = "A";
            // здесь можно вызвать вашу логику для A
            stage.setScene(buildSecondScene(stage));
        });
        btnB.setOnAction(e -> {
            selectedMode = "B";
            // здесь можно вызвать вашу логику для B
            stage.setScene(buildSecondScene(stage));
        });
        VBox root = new VBox(12, btnA, btnB);
        root.setPadding(new Insets(16));
        return new Scene(root, 400, 220);
    }

    private Scene buildSecondScene(Stage stage) {
        Label modeLabel = new Label("Режим: " + selectedMode);
        TextField path1 = new TextField();
        path1.setPromptText("Путь к первому файлу: data.txt");

        TextField path2 = new TextField();
        path2.setPromptText("Путь ко второму файлу: data.txt");
        Button send = new Button("Отправить");
        send.setOnAction(e -> {
            String p1 = path1.getText().trim();
            String p2 = path2.getText().trim();
            // ваша логика «отправки»: копирование, чтение, HTTP и т.д.
            System.out.println("Режим=" + selectedMode + ", file1=" + p1 + ", file2=" + p2);
        });
        Button back = new Button("Назад");
        back.setOnAction(e -> stage.setScene(buildFirstScene(stage)));
        VBox root = new VBox(12, modeLabel, path1, path2, send, back);
        root.setPadding(new Insets(16));
        return new Scene(root, 480, 320);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
