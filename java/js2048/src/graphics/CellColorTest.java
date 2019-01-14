package graphics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellColorTest {

	@Test
	void testCalculateColorAlteration() {
		final int testAlteration01 = CellColor.calculateColorAlteration(CellColor.BaseColors.RED, 2);
		assertTrue(0 <= testAlteration01 && CellColor.BASECOLOR_RANGE >= testAlteration01);
	
		final int testAlteration02 = CellColor.calculateColorAlteration(CellColor.BaseColors.RED, 2048);
		assertTrue(0 <= testAlteration02 && CellColor.BASECOLOR_RANGE >= testAlteration02);
		
		final int testAlteration03 = CellColor.calculateColorAlteration(CellColor.BaseColors.RED, CellColor.MAX_VALUE);
		assertTrue(0 <= testAlteration03 && CellColor.BASECOLOR_RANGE >= testAlteration03);
	
		final int testAlteration04 = CellColor.calculateColorAlteration(CellColor.BaseColors.RED, 0);
		assertTrue(0 <= testAlteration04 && CellColor.BASECOLOR_RANGE >= testAlteration04);
	}
	
	@Test
	void testRgbToHexString() {
		final int testR01 = 0xFF;
		final int testG01 = 0xFF;
		final int testB01 = 0xFF;		
		assertEquals("#ffffff", CellColor.rgbToHexString(testR01, testG01, testB01));
 	
		final int testR02 = 0x09;
		final int testG02 = 0x0A;
		final int testB02 = 0x00;		
		assertEquals("#090a00", CellColor.rgbToHexString(testR02, testG02, testB02));
		
		final int testR03 = 0x19;
		final int testG03 = 0x32;
		final int testB03 = 0x21;		
		assertEquals("#193221", CellColor.rgbToHexString(testR03, testG03, testB03));
	}
}
