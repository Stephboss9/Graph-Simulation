package src1;

import static org.junit.Assert.*;

import org.junit.Test;

public class Town_STUDENT_Test {
	Town town1 = new Town("town_1");
	Town town3 = new Town("town_3");

	@Test
	public void testGetName() {
		assertEquals("town_1", town1.getName());
	}

	@Test
	public void testToString() {
		assertEquals("town_1", town1.getName());
	}

	@Test
	public void testGetPredecessor() {
		town1.setPredecessor(town3);
		assertEquals(town3, town1.getPredecessor());
	}

	@Test
	public void testGetCost() {
		town1.setCost(4);
		assertEquals(4, town1.getCost());
	}

}
