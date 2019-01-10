package graphics;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.scene.control.Label;

public class NumberTile extends Label implements PropertyChangeListener{
	
	private static final double PIXELSIZE = 100d;
	
	private CellColor cellColor;
	
	public NumberTile(final double rootX, final double rootY) {
		super("");
		this.setLayoutX(rootX);
		this.setLayoutY(rootY);
		this.setPrefSize(PIXELSIZE, PIXELSIZE);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String newNumber = event.getNewValue().toString();
		this.setText(newNumber.equals("0") ? "" : newNumber);		
	}
}
