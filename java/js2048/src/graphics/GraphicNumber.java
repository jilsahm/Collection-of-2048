package graphics;

import javafx.scene.canvas.GraphicsContext;

public class GraphicNumber {

	private int       value;
	private int       rootX;
	private int       rootY;
	private double    size;
	private CellColor cellColor;
	
	public GraphicNumber(int rootX, int rootY, int size) {
		this(0, rootX, rootY, size);
	}
	
	public GraphicNumber(int value, int rootX, int rootY, int size) {
		this.value     = value;
		this.rootX     = rootX;
		this.rootY     = rootY;
		this.size      = size;
		this.cellColor = new CellColor(value);
	}
	
	public void paint(GraphicsContext painter) {
		painter.setFill(this.cellColor.getBackground());
		painter.fillRect(this.rootX, this.rootY, this.size, this.size);
		if (0 < this.value) {
			painter.setStroke(this.cellColor.getForeground());
			this.paintNumber(painter);
		}
	}
	
	private void paintNumber(GraphicsContext painter) {
		final double fontSize        = painter.getFont().getSize();
		final String valueString     = Integer.toString(this.value);		
		final double numberPositionX = this.rootX + this.size / 2 - fontSize * valueString.length() / 4;
		final double numberPositionY = this.rootY + this.size / 2 + fontSize / 2;
		
		painter.strokeText(valueString, numberPositionX, numberPositionY);
	}
	
}
