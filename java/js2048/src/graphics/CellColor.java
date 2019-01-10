package graphics;

public final class CellColor {

	private String background;
	private String foreground;
	
	public CellColor(final String background, final String foreground) {
		this.background = background;
		this.foreground = foreground;
	}

	public String getBackground() {
		return background;
	}

	public String getForeground() {
		return foreground;
	}
	
	public static CellColor cellColorFactory(final int value) {
		//TODO
	}
	
}
