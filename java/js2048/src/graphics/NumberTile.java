package graphics;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.scene.control.Label;

public class NumberTile extends Label implements PropertyChangeListener{
	
	public static final double PIXELSIZE = 100d;
		
	public NumberTile(final double rootX, final double rootY) {
		super("");
		this.setLayoutX(rootX);
		this.setLayoutY(rootY);
		this.setPrefSize(PIXELSIZE, PIXELSIZE);
		this.updateColor(0);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String newNumber = event.getNewValue().toString();
		this.setText(newNumber.equals("0") ? "" : newNumber);		
		System.out.println(newNumber);
	}
	
	private void updateColor(final int value) {
		CellColor newColor = CellColor.cellColorFactory(value);
		this.setStyle(String.format("-fx-background-color: %s", newColor.getBackground()));
	}
}
