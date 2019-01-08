package logic;

/**
 * I needed a mutable int wrapper to pass numbers by reference.
 * @author Jil Sahm
 * @version 1.0
 */
public class MutableInteger {

	private int value;
	
	public MutableInteger() {
		this(0);
	}
	
	public MutableInteger(final int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean addValue(Object value) {
		if (value instanceof Integer) {
			this.value += ((Integer)value).intValue();
			return true;
		} else if (value instanceof MutableInteger) {
			this.value += ((MutableInteger)value).getValue();
			return true;
		}
		return false;
	}
	
	public boolean equals(final int number) {
		return this.value == number;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other instanceof MutableInteger) {
			return this.value == ((MutableInteger)other).getValue();
		}
		return false;
	}
}
