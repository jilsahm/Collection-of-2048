package graphics;

import controls.KeyBoard;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import logic.Direction;
import logic.GameArea;

public class GameView implements EventHandler<KeyEvent>{

	private static final int GAMEAREA_SIZE = 4;
	
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
		
		this.numbersPane = new NumbersPane(this.gameArea);
		gridPane.add(numbersPane, 0, 5, 2, 1);
		
		this.gameScene = new Scene(gridPane); 
		this.gameScene.addEventHandler(KeyEvent.KEY_RELEASED, this);
	}
		
	public Scene getScene() {
		return this.gameScene;
	}

	@Override
	public void handle(KeyEvent event) {
		Direction direction = KeyBoard.getDirection(event);
		if (null != direction) {
			this.gameArea.update(direction);
		}
	}
	
}
