package com.arm.checkboxsnake.main;

import com.arm.checkboxsnake.game.GameFrameController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckBoxSnakeDesktop extends Application {

    private StackPane rootPane = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameFrame.fxml"));


        try {
            rootPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(rootPane));

        GameFrameController controller = fxmlLoader.<GameFrameController>getController();
        controller.setScene(primaryStage.getScene());
        controller.initGame();

        Platform.runLater(() -> {
            primaryStage.setHeight(rootPane.getHeight() + 50);
            primaryStage.setWidth(rootPane.getWidth() + 30);
        });

        primaryStage.setTitle("CheckBoxSnake");
        primaryStage.show();
    }
}
