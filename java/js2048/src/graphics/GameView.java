package graphics;

import java.beans.PropertyChangeListener;

import controls.KeyBoard;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
	
	private GridPane    gridPaneLayout;
	private Label       messageLabel;
	private NumbersPane numbersPane;
	private ScoreLabel  score;	
	private Scene       gameScene;
	
	
	public GameView() {
		this.gameArea = new GameArea(GAMEAREA_SIZE);
		this.buildComponents();
		this.gameArea.startNewGame(GAMEAREA_STARTINGNUMBERS);
	}
	
	private void buildComponents() {
		this.gridPaneLayout = new GridPane();
		this.gridPaneLayout.setPadding(new Insets(NumbersPane.PIXELPADDING, 0, 0, NumbersPane.PIXELPADDING));
		this.gridPaneLayout.getRowConstraints().add(new RowConstraints(ROW_HEIGHT));
		
		Label scoreLabel = new Label("Score:");
		this.gridPaneLayout.add(scoreLabel, 0, 0, 1, 1);
		
		this.score = new ScoreLabel();
		this.gameArea.addPropertyChangeListener((PropertyChangeListener) this.score);		
		GridPane.setHalignment(this.score, HPos.RIGHT);
		this.gridPaneLayout.add(this.score, 1,	0, 1, 1);		
		
		this.numbersPane = new NumbersPane(this.gameArea);
		this.gridPaneLayout.add(numbersPane, 0, 5, 2, 1);
		
		this.messageLabel = new Label("");
		GridPane.setHalignment(this.messageLabel, HPos.CENTER);
		this.gridPaneLayout.add(messageLabel, 0, 6, 2, 1);
		
		this.gameScene = new Scene(this.gridPaneLayout); 
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
			if (!this.gameArea.isNotGameOver()) {
				this.displayMessage("Game Over");
			}
		}
	}
	
	private void displayMessage(final String message) {
		this.messageLabel.setText(message);
	}
	
}
