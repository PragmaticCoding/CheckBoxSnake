/*
 * Copyright (c) 2018 by ARM GmbH, www.ablex.com
 * CheckBoxSnake.java
 * created on 06.04.2018 - 14:10:15
 * edited by dnolte 06.04.2018 - 14:10:15
 */
package checkboxsnake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class CheckBoxSnake extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameFrame.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("GameFrame.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        GameFrameController controller = fxmlLoader.<GameFrameController>getController();
        controller.setScene(scene);

        controller.initGame();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((WindowEvent event) -> {
            System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
