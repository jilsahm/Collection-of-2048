package graphics;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

import javafx.scene.Group;
import javafx.scene.Parent;
import logic.GameArea;
import logic.PropertyChangeListenerMismatchException;

public class NumbersPane extends Parent{

	public static final double PIXELPADDING = 10d;
	
	private final int size;
	private ArrayList<NumberTile> numbers;
	
	public NumbersPane(GameArea gameArea) {
		this.size    = gameArea.getSize();
		this.numbers = new ArrayList<>();
		this.buildNumbers();
		this.observeMutableIntegers(gameArea);
	}
	
	private void buildNumbers() {	
		Group numbersGroup = new Group();
		
		IntStream.range(0, this.size).forEach(row -> {
			IntStream.range(0, this.size).forEach(column -> {
				NumberTile currentNumberTile = new NumberTile(
					column * (NumberTile.PIXELSIZE + PIXELPADDING), 
					row *  (NumberTile.PIXELSIZE + PIXELPADDING)
				);				
				this.numbers.add(currentNumberTile);				
				numbersGroup.getChildren().add(currentNumberTile);
			});
		});
		
		this.getChildren().add(numbersGroup);
	}
	
	private void observeMutableIntegers(GameArea gameArea) {
		ArrayList<PropertyChangeListener> listeners = new ArrayList<>();
		this.numbers.forEach(number -> listeners.add((PropertyChangeListener) number));
		try {
			gameArea.registerListeners(listeners);
		} catch (PropertyChangeListenerMismatchException e) {
			e.printStackTrace();
		}
	}
}
