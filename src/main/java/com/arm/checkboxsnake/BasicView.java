package com.arm.checkboxsnake;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class BasicView extends View {

    public BasicView()  {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameFrame.fxml"));
        fxmlLoader.setController(new GameFrameController());
//        Parent root = FXMLLoader.load(getClass().getResource("GameFrame.fxml"));

        try {
            this.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameFrameController controller = fxmlLoader.<GameFrameController>getController();

        Platform.runLater(() -> {
            controller.setScene(getScene());
            controller.initGame();
        });
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> System.out.println("Menu")));
        appBar.setTitleText("Basic View");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }

}
