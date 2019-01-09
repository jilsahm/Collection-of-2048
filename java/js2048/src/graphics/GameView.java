package graphics;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import logic.GameArea;

public class GameView {

	private static final double CANVAS_SIZE = 300d;
	
	private GameArea      gameArea;
	private GraphicNumber testNumber;
	
	private Canvas   numbersCanvas;
	private Label    score;
	private Scene    gameScene;
	
	public GameView() {
		this.gameArea = new GameArea(4);
		this.testNumber = new GraphicNumber(10000, 10, 10, 50);
		this.buildComponents();
		this.paintNumbers();
	}
	
	private void buildComponents() {
		GridPane gridPane = new GridPane();
		
		Label scoreLabel = new Label("Score:");
		gridPane.add(scoreLabel, 0, 0, 1, 1);
		
		this.score = new Label("0");
		this.score.setTextAlignment(TextAlignment.RIGHT);
		gridPane.add(this.score, 1,	0, 1, 1);
		
		this.numbersCanvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
		gridPane.add(this.numbersCanvas, 0, 1, 2, 1);
		
		this.gameScene = new Scene(gridPane);
	}
	
	private void paintNumbers() {
		GraphicsContext painter = this.numbersCanvas.getGraphicsContext2D();
		painter.setFill(Color.GRAY);
		painter.fillRect(0, 0, CANVAS_SIZE, CANVAS_SIZE);
		this.testNumber.paint(painter);
	}
	
	public Scene getScene() {
		return this.gameScene;
	}
	
}
