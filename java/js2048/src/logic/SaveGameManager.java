package logic;

import java.awt.image.BufferedImageFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class SaveGameManager {

	private static final String          SAVEGAME_FILENAME = "game.sav";
	private static final String          PATH              = "/resources/" + SAVEGAME_FILENAME;
	private static final SaveGameManager INSTANCE          = new SaveGameManager();
	
	private File fileHandle;
	
	private SaveGameManager() {
		this.fileHandle = new File(this.getClass().getResource(PATH).getPath());
		try {
			this.fileHandle.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveGame(GameArea gameArea) {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.fileHandle))){
			this.writeGameOptionsToFile(gameArea, bufferedWriter);
			this.writeGameAreaToFile(gameArea, bufferedWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeGameOptionsToFile(GameArea gameArea, BufferedWriter bufferedWriter) throws IOException {
		bufferedWriter.write(Integer.toString(gameArea.getSize()));
		bufferedWriter.newLine();
		bufferedWriter.write(Long.toString(gameArea.getScore()));
		bufferedWriter.newLine();
	}
	
	private void writeGameAreaToFile(GameArea gameArea, BufferedWriter bufferedWriter) throws IOException{
		for (int rowIndex = 0; rowIndex < gameArea.getSize(); rowIndex++) {
			for (MutableInteger value : gameArea.getRow.at(rowIndex)) {
				bufferedWriter.write(value.toString() + " ");
			}
		}
	}
	
	public void loadGame() {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileHandle))){
			System.out.println(bufferedReader.readLine());
			System.out.println(bufferedReader.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SaveGameManager getInstance() {
		return SaveGameManager.INSTANCE;
	}
	
	public static void main(String[] args) {
		INSTANCE.saveGame(new GameArea(4));
		INSTANCE.loadGame();
	}
}
