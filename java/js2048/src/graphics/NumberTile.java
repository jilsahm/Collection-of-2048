package graphics;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class NumberTile extends Label implements PropertyChangeListener{
	
	public static final double PIXELSIZE = 100d;
	
	private CellColor cellColor;
	
	public NumberTile(final double rootX, final double rootY) {
		super("");
		this.setLayoutX(rootX);
		this.setLayoutY(rootY);
		this.setPrefSize(PIXELSIZE, PIXELSIZE);
		this.setAlignment(Pos.CENTER);
		
		InnerShadow is = new InnerShadow();
        is.setOffsetX(1.0f);
        is.setOffsetY(1.0f);		
		this.setEffect(is);		
        
		this.cellColor = CellColor.cellColorFactory(0);
		this.updateColor(0);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String newNumber    = event.getNewValue().toString();
		int    parsedNumber = Integer.parseInt(newNumber);
		
		if (0 > this.getText().compareTo(newNumber) && !this.getText().equals("")) {
			this.playAnimation(parsedNumber);
		} else {
			this.updateColor(parsedNumber);
		}
		this.setText(newNumber.equals("0") ? "" : newNumber);
	}
	
	private void playAnimation(final int value) {
		final NumberTile me        = this;		
		final Animation  animation = new Transition() {
            {
                setCycleDuration(Duration.millis(200));
                setInterpolator(Interpolator.EASE_OUT);
                setCycleCount(2);
                setAutoReverse(true);
            }
            @Override
            protected void interpolate(double frac) {
                Color backgroundColor = new Color(1 - frac, 1 - frac, 1 - frac, 1);
                Color textColor       = new Color(0 + frac, 0 + frac, 0 + frac, 1);
                
                me.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
                me.setTextFill(textColor);
            }            
        };
        animation.setOnFinished(event -> me.updateColor(value));
        animation.play();
	}
	
	private void updateColor(final int value) {		
		CellColor newColor = CellColor.cellColorFactory(value);
				
		this.setStyle(String.format("-fx-background-color: %s", newColor.getBackground()));		
	}
}
