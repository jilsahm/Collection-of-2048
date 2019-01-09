package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Game area which holds all the numbers.
 * @author Jil Sahm
 * @version 1.0
 */
public final class GameArea {

	/**
	 * Internal helper class for storing runtime metadata.
	 */
	private class UpdateDirection {
		
		public final Fetchable callback;
		public final int       from;
		public final int       to;
		public final int       step;
		
		/**
		 * Constructs a helper with following public options.
		 * @param callback An Fetchable object which contains the {@code at()}- Method. This either fetches a row or a column from the game area.
		 * @param from Index from which the numbers are evaluated.
		 * @param to Index to be iterated.
		 * @param step Either 1 or -1 to determine the direction of the iteration.
		 */
		public UpdateDirection(final Fetchable callback, final int from, final int to, final int step) {
			this.callback = callback;
			this.from     = from;
			this.to       = to;
			this.step     = (0 < step) ? 1 : -1;
		}
		
	}
	
	/**
	 * Functional interface to allow function storing in the UpdateDirection-Class.
	 */
	@FunctionalInterface
	private interface Fetchable{
		abstract public ArrayList<MutableInteger> at(final int index);
	}
	
	
	private ArrayList<ArrayList<MutableInteger>> numbers;
	private Random rng;
	private Map<Direction, UpdateDirection> possibleDirections;
	
	public GameArea(final int size) {
		this.numbers = new ArrayList<>();
		this.rng     = new Random(System.currentTimeMillis());				
		this.initialize();
		this.fillDirections();
	}
	
	private void fillDirections() {
		HashMap<Direction, UpdateDirection> directions = new HashMap<Direction, UpdateDirection>();
		directions.put(Direction.NORTH, new UpdateDirection(this.getColumn, 0, this.numbers.size(), 1));
		directions.put(Direction.EAST,  new UpdateDirection(this.getRow, this.numbers.size() - 1, -1, -1));
		directions.put(Direction.SOUTH, new UpdateDirection(this.getColumn, this.numbers.size() - 1, -1, -1));
		directions.put(Direction.WEST,  new UpdateDirection(this.getRow, 0, this.numbers.size(), 1));		
		this.possibleDirections = Collections.unmodifiableMap(directions);
	}
	
	//TODO
	public void initialize() {
		//Arrays.parallelSetAll(this.numbers, element -> this.rng.nextInt(3));
		this.numbers = new ArrayList<>();
		
		ArrayList<MutableInteger> testrow01 = new ArrayList<>();
		testrow01.add(new MutableInteger(0));
		testrow01.add(new MutableInteger(1));
		testrow01.add(new MutableInteger(1));
		testrow01.add(new MutableInteger(2));
		this.numbers.add(testrow01);

		ArrayList<MutableInteger> testrow02 = new ArrayList<>();
		testrow02.add(new MutableInteger(0));
		testrow02.add(new MutableInteger(0));
		testrow02.add(new MutableInteger(0));
		testrow02.add(new MutableInteger(2));
		this.numbers.add(testrow02);

		ArrayList<MutableInteger> testrow03 = new ArrayList<>();
		testrow03.add(new MutableInteger(1));
		testrow03.add(new MutableInteger(1));
		testrow03.add(new MutableInteger(0));
		testrow03.add(new MutableInteger(4));
		this.numbers.add(testrow03);

		ArrayList<MutableInteger> testrow04 = new ArrayList<>();
		testrow04.add(new MutableInteger(4));
		testrow04.add(new MutableInteger(0));
		testrow04.add(new MutableInteger(0));
		testrow04.add(new MutableInteger(0));
		this.numbers.add(testrow04);
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (ArrayList<MutableInteger> row : this.numbers) {
			for (MutableInteger number : row) {
				output.append(number.getValue());
				output.append(" ");
			}
			output.append("\n");
		}
		return output.toString();
	}	
	
	/**
	 * Callback holder for fetching a specific row.<br><br>
	 * Example for retriving the first row:<br>
	 * {@code gameArea.getRow.at(0);}
	 */
	public Fetchable getRow = index -> this.numbers.get(index);
	
	/**
	 * Callback holder for fetching a specific column.<br><br>
	 * Example for retriving the first column:<br>
	 * {@code gameArea.getColumn.at(0);}
	 */
	public Fetchable getColumn = index -> {
		ArrayList<MutableInteger> proxyColumn = new ArrayList<>();
		for (ArrayList<MutableInteger> row : this.numbers) {
			proxyColumn.add(row.get(index));
		}
		return proxyColumn;
	};
	
	/**
	 * Updating all the numbers in the given direction.
	 * @param direction Direction in which the update is to be made.
	 */
	public void update(Direction direction) {
		final UpdateDirection updateDirection = this.possibleDirections.get(direction);
		
		for (int listIndex = 0; listIndex < this.numbers.size(); listIndex++) {
			this.partialUpdate(updateDirection.callback.at(listIndex), updateDirection);
		}
	}
	
	private void partialUpdate(ArrayList<MutableInteger> targetList ,UpdateDirection updateDirection) {
		
		for (int currentElementIndex = updateDirection.from; currentElementIndex != updateDirection.to; currentElementIndex += updateDirection.step) {
			if (targetList.get(currentElementIndex).equals(0)) {
				if (this.moveUpNumber(targetList, currentElementIndex, updateDirection)) {
					currentElementIndex -= updateDirection.step;
				}
			} else {
				int possiblePairIndex = this.findMatchingNumber(targetList, currentElementIndex, updateDirection);
				if (-1 != possiblePairIndex) {
					this.uniteNumbers(targetList, currentElementIndex, possiblePairIndex);
				}
			}			
		}
	}
	
	private boolean moveUpNumber(ArrayList<MutableInteger> targetList, final int targetIndex, final UpdateDirection updateDirection) {
		for (int possibleNumberIndex = targetIndex + updateDirection.step; possibleNumberIndex != updateDirection.to; possibleNumberIndex += updateDirection.step) {
			if (targetList.get(possibleNumberIndex).equals(0)) {
				continue;
			} else {
				this.uniteNumbers(targetList, targetIndex, possibleNumberIndex);
				return true;
			}
		}
		return false;
	}
	
	private int findMatchingNumber(ArrayList<MutableInteger> targetList, final int startIndex, final UpdateDirection updateDirection) {
		for (int compareToElementIndex = startIndex + updateDirection.step; compareToElementIndex != updateDirection.to; compareToElementIndex += updateDirection.step) {
			if (targetList.get(compareToElementIndex).equals(0)) {
				continue;
			} else if (!targetList.get(startIndex).equals(targetList.get(compareToElementIndex))) {
				return -1;
			} else if (targetList.get(startIndex).equals(targetList.get(compareToElementIndex))) {
				return compareToElementIndex;
			}
		}
		return -1;
	}
	
	private void uniteNumbers(ArrayList<MutableInteger> targetList, final int targetIndex, final int otherIndex) {
		if (targetIndex == otherIndex) {
			return;
		}
		targetList.get(targetIndex).addValue(targetList.get(otherIndex));
		targetList.get(otherIndex).setValue(0);
	}
	
	public static void main(String[] args) {
		GameArea gameArea = new GameArea(8);
		System.out.println(gameArea.toString());
		gameArea.update(Direction.SOUTH);
		System.out.println(gameArea.toString());
		gameArea.update(Direction.SOUTH);
		System.out.println(gameArea.toString());
	}
	
}
