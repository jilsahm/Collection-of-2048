package logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MutableIntegerTest {

	private MutableInteger testObject;
	
	@BeforeEach
	private void initialize() {
		this.testObject = new MutableInteger(100);
	}
	
	@Test
	void testAddValue() {
		this.testObject.addValue(-40);
		assertEquals(this.testObject.getValue(), 60);
		this.testObject.addValue(7);
		assertEquals(this.testObject.getValue(), 67);
	}

	@Test
	void testEqualsInt() {
		assertTrue(this.testObject.getValue() == 100);
		assertFalse(this.testObject.getValue() == -100);
	}

	@Test
	void testEqualsObject() {
		MutableInteger other = new MutableInteger(99);
		MutableInteger same  = new MutableInteger(100);
		
		assertFalse(this.testObject.equals(other));
		assertFalse(this.testObject.equals(null));
		assertFalse(this.testObject.equals(new Object()));
		assertTrue(this.testObject.equals(same));
	}

}
