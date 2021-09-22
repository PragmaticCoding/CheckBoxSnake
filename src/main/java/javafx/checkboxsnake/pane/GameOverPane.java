package javafx.checkboxsnake.pane;

import javafx.checkboxsnake.game.GameFrameController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class GameOverPane extends VBox{
    
    private StackPane parentPane;
    
    private GameFrameController gameFrameController;
    
    private Label labelGameOver = new Label("Game Over");
    private Label labelPoints = new Label();
    private Button buttonRestart = new Button("Restart");

    public GameOverPane(GameFrameController gameFrameController, StackPane parentPane, int points) {
        super(30);
        
        this.gameFrameController = gameFrameController;
        this.parentPane = parentPane;
        
        initPane();
        setPoints(points);
        initButtons();
    }
    
    private void initPane(){
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.WHITE.deriveColor(1, 1, 1, .8), CornerRadii.EMPTY, Insets.EMPTY)));
        
        this.getChildren().add(labelGameOver);
        this.getChildren().add(labelPoints);
        this.getChildren().add(buttonRestart);
        
        labelPoints.setFont(Font.font(null, FontWeight.BOLD, 15));
    }
    
    private void initButtons(){
        buttonRestart.setOnAction((ActionEvent event) -> {
            hide();
            gameFrameController.runGame();
        });
    }
    
    private void setPoints(int points){
        labelPoints.setText(String.format("You got %d points!", points));
    }
    
    public void show(){
        parentPane.getChildren().add(this);
    }
    
    public void hide(){
        parentPane.getChildren().remove(this);
    }

}
