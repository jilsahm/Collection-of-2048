package graphics;

public final class CellColor {

	static enum BaseColors { RED, GREEN, BLUE }
	
	static final int BASECOLOR_VALUE = 0xB0;
	static final int BASECOLOR_RANGE = 0xFF - BASECOLOR_VALUE;
	//static final int MAX_VALUE       = 131072;
	
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
		int r = BASECOLOR_VALUE + calculateColorAlteration(BaseColors.RED, value);
		int g = BASECOLOR_VALUE + calculateColorAlteration(BaseColors.GREEN, value);
		int b = BASECOLOR_VALUE + calculateColorAlteration(BaseColors.BLUE, value);
		
		return new CellColor(rgbToHexString(r, g, b), rgbToHexString(r, g, b));
	}
	
	static int calculateColorAlteration(BaseColors baseColor, final int value) {
		//final int colorModification = value * BASECOLOR_RANGE / MAX_VALUE;
		final int colorModification = value % BASECOLOR_RANGE;
		
		switch (baseColor) {
			case RED:   return colorModification;
			case GREEN: return colorModification / 2 * -1;
			case BLUE:  return colorModification * -1;
		}		
		return 0;
	}
	
	static String rgbToHexString(final int r, final int g, final int b) {
		StringBuilder output = new StringBuilder("#");
		
		output.append((0x10 > r) ? "0" + Integer.toHexString(r) : Integer.toHexString(r));
		output.append((0x10 > g) ? "0" + Integer.toHexString(g) : Integer.toHexString(g));
		output.append((0x10 > b) ? "0" + Integer.toHexString(b) : Integer.toHexString(b));
		
		return output.toString();
	}	
}
