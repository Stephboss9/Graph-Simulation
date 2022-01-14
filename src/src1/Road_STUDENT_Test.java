package src1;

import static org.junit.Assert.*;

import org.junit.Test;

public class Road_STUDENT_Test {
	Town town1 = new Town("Town_1");
	Town town2 = new Town("Town_2");
	Road diamondRoad = new Road(town1, town2, 4, "DiamondRoad");

	@Test
	public void testContains() {
		assertEquals(true, diamondRoad.contains(town1));
	}

	@Test
	public void testEqualsObject() {
		assertEquals(true, diamondRoad.equals(new Road(town1, town2, 4, "DiamondRoad")));
	}

	@Test
	public void testGetName() {
		assertEquals("DiamondRoad", diamondRoad.getName());
	}

	@Test
	public void testGetDestination() {
		assertEquals(town2, diamondRoad.getDestination());
	}

	@Test
	public void testGetSource() {
		assertEquals(town1, diamondRoad.getSource());
	}

}
