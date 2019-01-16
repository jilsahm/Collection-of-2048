package logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameAreaTest {
	
	private GameArea prepareNewGameArea(final int[][] predefinedValues) {
		for (int[] row : predefinedValues) {
			assert row.length == predefinedValues.length;
		}
		
		GameArea gameArea = new GameArea(predefinedValues.length);
		
		for (int rowIndex = 0; rowIndex < predefinedValues.length; rowIndex++) {
			for (int columnIndex = 0; columnIndex < predefinedValues[rowIndex].length; columnIndex++) {
				gameArea.setValueAt(
					predefinedValues[rowIndex][columnIndex],
					rowIndex,
					columnIndex
				);
			}
		}		
		return gameArea;
	}
	
	@Test
	void testIsGameWon() {
		GameArea gameArea = this.prepareNewGameArea(
			new int[][] {
				{   4,    0},
				{2048, 4096}
			}
		);		
		assertTrue(gameArea.isGameWon());
		
		gameArea = this.prepareNewGameArea(
			new int[][] {
				{ 4,  0},
				{32, 64}
			}
		);
		assertFalse(gameArea.isGameWon());
	}
	
	@Test
	void testIsNotGameOver() {
		GameArea gameArea = this.prepareNewGameArea(
			new int[][] {
				{ 4,  8, 16, 32},
				{ 8, 16, 32,  4},
				{16, 32,  4,  8},
				{32,  4,  8, 16}
			}
		);
		assertFalse(gameArea.isNotGameOver());
		
		gameArea = this.prepareNewGameArea(
			new int[][] {
				{ 8,  8, 16, 32},
				{ 8, 16, 32,  4},
				{16, 32,  4,  8},
				{32,  4,  8, 16}
			}
		);
		assertTrue(gameArea.isNotGameOver());
		
		gameArea = this.prepareNewGameArea(
			new int[][] {
				{ 4,  8, 16, 32},
				{ 8, 16,  0,  4},
				{16, 32,  4,  8},
				{32,  4,  8, 16}
			}
		);
		assertTrue(gameArea.isNotGameOver());
	}

}
