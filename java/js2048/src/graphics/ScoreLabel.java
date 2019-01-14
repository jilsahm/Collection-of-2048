package graphics;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class ScoreLabel extends Label implements PropertyChangeListener{
	
	public ScoreLabel() {
		super("0");
		this.setAlignment(Pos.CENTER_RIGHT);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		this.setText(event.getNewValue().toString());
	}
}
