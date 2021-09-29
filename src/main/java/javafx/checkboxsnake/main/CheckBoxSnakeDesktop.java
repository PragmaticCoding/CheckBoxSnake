package javafx.checkboxsnake.main;

import javafx.application.Application;
import javafx.checkboxsnake.game.GameController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckBoxSnakeDesktop extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameController controller = new GameController();
        Scene scene = new Scene(controller.getView());
        primaryStage.setScene(scene);
        primaryStage.setTitle("CheckBoxSnake");
        primaryStage.show();
    }
}
