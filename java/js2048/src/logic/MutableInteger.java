package logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * I needed a mutable int wrapper to pass numbers by reference.
 * @author Jil Sahm
 * @version 1.0
 */
public class MutableInteger {

	private int                   value;
	private PropertyChangeSupport changes; 
	
	public MutableInteger() {
		this(0);
	}
	
	public MutableInteger(final int value) {
		this.value   = value;
		this.changes = new PropertyChangeSupport(this);
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.notifyListeners(value);
		this.value = value;
	}
	
	public boolean addValue(Object value) {
		if (value instanceof Integer) {
			int increment = ((Integer)value).intValue();
			this.notifyListeners(increment + this.value);
			this.value += increment;
			return true;
		} else if (value instanceof MutableInteger) {
			int increment = ((MutableInteger)value).getValue();
			this.notifyListeners(increment + this.value);
			this.value += increment;
			return true;
		}
		return false;
	}
	
	private void notifyListeners(final int newValue) {
		changes.firePropertyChange("value", this.value, newValue);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.changes.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.changes.removePropertyChangeListener(listener);
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
	
	@Override
	public String toString() {
		return Integer.toString(this.value);
	}
}
