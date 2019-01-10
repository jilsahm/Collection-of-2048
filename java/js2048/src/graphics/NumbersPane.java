package graphics;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class NumbersPane extends Parent{

	private final int        size;
	private ArrayList<Label> numbers;
	
	public NumbersPane(final int size) {
		this.size    = size;
		this.numbers = new ArrayList<Label>();
		this.buildNumbers();
	}
	
	private void buildNumbers() {	
		Group numbersGroup = new Group();
		for (int x = 0, number = 0; x < 600; x += 60, number++) {
			Label numberLabel = new Label(Integer.toString(number));
			numberLabel.setStyle("-fx-background-color: #cccccc");
			numberLabel.setMinSize(50, 50);
			numberLabel.setAlignment(Pos.CENTER);
			numberLabel.setLayoutX(x);
			numbersGroup.getChildren().add(numberLabel);
		}
		this.getChildren().add(numbersGroup);
	}
	
}
