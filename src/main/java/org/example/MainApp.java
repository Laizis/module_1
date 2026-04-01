package org.example;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainApp extends Application {
    private static String selectedMode;
    private static String inputPathFile;
    private static String outputPathFile;
    private static int shift;

    public static void main(String[] args) throws Exception {

        launch();
        System.out.println(selectedMode);
        Cipher cipher = new Cipher();
        String content = FileManager.readFile(inputPathFile);
        String result = null;
        switch (selectedMode) {
            case "encrypt" -> result = cipher.encrypt(content, shift);
            case "decrypt" -> result = cipher.decrypt(content, shift);
        }
        FileManager.writeFile(result, outputPathFile);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cipher Cesar");
        Scene scene1 = buildFirstScene(primaryStage);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    private Scene buildFirstScene(Stage stage) {
        Label label = new Label("Выберите действие: ");
        Button btnA = new Button("encrypt");
        Button btnB = new Button("decrypt");
        btnA.setOnAction(e -> {
            selectedMode = "encrypt";
            stage.setScene(buildSecondScene(stage));
        });
        btnB.setOnAction(e -> {
            selectedMode = "decrypt";
            stage.setScene(buildSecondScene(stage));
        });
        VBox root = new VBox(10, btnA, btnB);
        root.setPadding(new Insets(60));
        return new Scene(root, 260, 200);
    }

    private Scene buildSecondScene(Stage stage) {
        Label modeLabel = new Label("Режим: " + selectedMode);
        TextField inputField = new TextField();
        inputField.setPromptText("Абсолютный путь до файла с выходными значениями, в формате txt");
        TextField outputField = new TextField();
        outputField.setPromptText("Абсолютный путь до файла с выходными значениями, в формате txt");
        Button send = new Button("Отправить");
        send.setOnAction(e -> {
            inputPathFile = inputField.getText().trim();
            outputPathFile = outputField.getText().trim();
            stage.setScene(buildThirdScene(stage));
        });
        Button back = new Button("Назад");
        back.setOnAction(e -> stage.setScene(buildFirstScene(stage)));
        VBox root = new VBox(12, modeLabel, inputField, outputField, send, back);
        root.setPadding(new Insets(16));
        return new Scene(root, 480, 320);
    }

    private Scene buildThirdScene(Stage stage) {
        Label modeLabel = new Label("Сдвиг");
        TextField inputShift = new TextField();
        inputShift.setPromptText("Введите число сдвига");
        Button send = new Button("send");
        send.setOnAction(e -> {
            shift = Integer.parseInt(inputShift.getText().trim());
            stage.close();
        });
        Button back = new Button("back");
        back.setOnAction(e -> {
            stage.setScene(buildFirstScene(stage));
        });
        VBox root = new VBox(12, modeLabel, inputShift, send, back);
        root.setPadding(new Insets(16));
        return new Scene(root, 480, 320);
    }
}
