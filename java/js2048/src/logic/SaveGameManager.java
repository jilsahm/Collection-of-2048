package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class SaveGameManager {

	private static final String          SAVEGAME_FILENAME = "game.sav";
	private static final String          PATH              = "/resources/" + SAVEGAME_FILENAME;
	private static final SaveGameManager INSTANCE          = new SaveGameManager();
	
	private File fileHandle;
	
	private SaveGameManager() {
		Optional<URL> url = Optional.ofNullable(this.getClass().getResource(PATH));
		if (url.isPresent()) {
			this.fileHandle = new File(url.get().getPath());
		} else {
			this.fileHandle = new File(new File("bin").getAbsolutePath() + PATH);
			try {
				this.fileHandle.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * Saving the given game area to the game.sav file.
	 * @param gameArea
	 */
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
	
	/**
	 * Loading a saved game from the game.sav file.
	 * @param gameArea Game area into which the savegame gets inserted.
	 * @return <b>True</b> if the file was successfully parsed. <b>False</b> if the file is corrupted.
	 */
	public boolean loadGame(GameArea gameArea) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileHandle))){
			this.loadValuesFromFileToGameArea(gameArea, bufferedReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (SaveGameCorruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private void loadValuesFromFileToGameArea(GameArea gameArea, BufferedReader bufferedReader) throws IOException, SaveGameCorruptedException {
		try {			
			final int size = Integer.parseInt(bufferedReader.readLine());
			
			if (size != gameArea.getSize()) {
				throw new SaveGameCorruptedException(String.format("The size %d from the file does not matches the game areas site %d.", size, gameArea.getSize()));
			}
			
			gameArea.setScore(Integer.parseInt(bufferedReader.readLine()));
			
			int rowIndex    = 0;
			int columnIndex = 0;
			for (final String stringValue : bufferedReader.readLine().trim().split(" ")) {
				gameArea.setValueAt(Integer.parseInt(stringValue), rowIndex, columnIndex);
				columnIndex++;
				if (columnIndex >= size) {
					rowIndex++;
					columnIndex = 0;
				}
			}
		} catch (NumberFormatException e) {
			throw new SaveGameCorruptedException("Impossible to parse file.");
		}
	}
	
	public static SaveGameManager getInstance() {
		return SaveGameManager.INSTANCE;
	}	
}
