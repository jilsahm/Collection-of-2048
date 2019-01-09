package graphics;

import javafx.scene.paint.Color;

public final class CellColor {

	private Color background;
	private Color foreground;
	
	public CellColor(final int value) {
		//TODO
		this.background = Color.BLACK;
		this.background = Color.WHITE;
	}

	public Color getBackground() {
		return background;
	}

	public Color getForeground() {
		return foreground;
	}
	
}
