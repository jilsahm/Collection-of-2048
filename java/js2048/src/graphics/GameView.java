package graphics;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import logic.GameArea;

public class GameView {

	private static final double NUMBER_PANE_PIXELSIZE = 300d;
	private static final int    GAMEAREA_SIZE         = 4;
	
	private GameArea    gameArea;
	
	private Label       score;
	private NumbersPane numbersPane;
	private Scene       gameScene;
	
	public GameView() {
		this.gameArea = new GameArea(GAMEAREA_SIZE);
		this.buildComponents();
	}
	
	private void buildComponents() {
		GridPane gridPane = new GridPane();
		
		Label scoreLabel = new Label("Score:");
		gridPane.add(scoreLabel, 0, 0, 1, 1);
		
		this.score = new Label("0");
		this.score.setAlignment(Pos.CENTER_RIGHT);
		gridPane.add(this.score, 1,	0, 1, 1);
		
		this.numbersPane = new NumbersPane(GAMEAREA_SIZE);
		gridPane.add(numbersPane, 0, 5, 2, 1);
		
		this.gameScene = new Scene(gridPane);
		 
		
	}
		
	public Scene getScene() {
		return this.gameScene;
	}
	
}
