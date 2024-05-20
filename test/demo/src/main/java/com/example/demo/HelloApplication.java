package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {


    @Override

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("APP");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest( event ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);//создаем окно подтверждения
            //далее заголовки и текст по желанию
            alert.setTitle("Закрытие приложения");
            alert.setHeaderText("Вы уверены что хотите выйти?");
            alert.setContentText("Изменения могут не сохраниться.");
            //создаем кнопки "да" и "нет"
            ButtonType buttonTypeOne = new ButtonType("Да");
            ButtonType buttonTypeSave = new ButtonType("Очистить таблицу и выйти");
            ButtonType buttonTypeCancel = new ButtonType("Нет", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeSave, buttonTypeCancel);//додаем кнопки к самому окну подтверждения
            Optional<ButtonType> result = alert.showAndWait();//вызываем окно подтверждения
            if(result.get() == buttonTypeCancel) event.consume();
            if(result.get() == buttonTypeSave) {


            }




        });
    }

    public static void main(String[] args) {
        launch();
    }
}