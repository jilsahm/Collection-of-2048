package controls;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyEvent;
import logic.Direction;

public class KeyBoard {

	private KeyBoard() {}
	
	private static Map<String, Direction> keyMap;
	
	static {
		HashMap<String, Direction> initialKeyMap = new HashMap<>();
		initialKeyMap.put("A", Direction.WEST);
		initialKeyMap.put("W", Direction.NORTH);
		initialKeyMap.put("S", Direction.SOUTH);
		initialKeyMap.put("D", Direction.EAST);
		initialKeyMap.put("Left", Direction.WEST);
		initialKeyMap.put("Up", Direction.NORTH);
		initialKeyMap.put("Down", Direction.SOUTH);
		initialKeyMap.put("Right", Direction.EAST);
		keyMap = Collections.unmodifiableMap(initialKeyMap);
	}
	
	public static Direction getDirection(KeyEvent event) {
		return keyMap.get(event.getCode().getName());
	}
}
