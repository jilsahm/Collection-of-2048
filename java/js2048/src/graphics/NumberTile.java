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
		String newNumber = event.getNewValue().toString();
		this.setText(newNumber.equals("0") ? "" : newNumber);
		this.updateColor(Integer.parseInt(newNumber));		
	}
	
	private void updateColor(final int value) {		
		CellColor newColor = CellColor.cellColorFactory(value);
				
		this.setStyle(String.format("-fx-background-color: %s", newColor.getBackground()));

		/*
		final NumberTile me = this;
		
		final Animation animation = new Transition() {

            {
                setCycleDuration(Duration.millis(300));
                setInterpolator(Interpolator.EASE_OUT);
                setCycleCount(2);
                setAutoReverse(true);
            }

            @Override
            protected void interpolate(double frac) {
                Color vColor = new Color(1, 0, 0, 1 - frac);
                me.setBackground(new Background(new BackgroundFill(vColor, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        };
        animation.play();*/
	}
}
