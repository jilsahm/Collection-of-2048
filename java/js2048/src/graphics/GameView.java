package graphics;

import java.beans.PropertyChangeListener;

import controls.KeyBoard;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import logic.Direction;
import logic.GameArea;

public class GameView implements EventHandler<KeyEvent>{

	private static final int    GAMEAREA_SIZE            = 4;
	private static final int    GAMEAREA_STARTINGNUMBERS = 2;
	private static final int    ROW_HEIGHT               = 50;
	private static final String CSS_PATH                 = "/resources/style.css";
	
	private GameArea    gameArea;
	
	private ScoreLabel  score;
	private NumbersPane numbersPane;
	private Scene       gameScene;
	
	public GameView() {
		this.gameArea = new GameArea(GAMEAREA_SIZE);
		this.buildComponents();
		this.gameArea.startNewGame(GAMEAREA_STARTINGNUMBERS);
	}
	
	private void buildComponents() {
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(NumbersPane.PIXELPADDING, 0, 0, NumbersPane.PIXELPADDING));
		gridPane.getRowConstraints().add(new RowConstraints(ROW_HEIGHT));
		
		Label scoreLabel = new Label("Score:");
		gridPane.add(scoreLabel, 0, 0, 1, 1);
		
		this.score = new ScoreLabel();
		this.gameArea.addPropertyChangeListener((PropertyChangeListener) this.score);		
		GridPane.setHalignment(this.score, HPos.RIGHT);
		gridPane.add(this.score, 1,	0, 1, 1);		
		
		this.numbersPane = new NumbersPane(this.gameArea);
		gridPane.add(numbersPane, 0, 5, 2, 1);
		
		this.gameScene = new Scene(gridPane); 
		this.gameScene.getStylesheets().add(getClass().getResource(CSS_PATH).toExternalForm());
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
			this.gameArea.debugPrintGameArea();
		}
	}
	
}
