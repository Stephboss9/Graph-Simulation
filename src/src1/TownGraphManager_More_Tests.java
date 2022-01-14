package src1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  town = new String[12];
		  
		  for (int i = 1; i < 9; i++) {
			  town[i] = "Town_" + i;
			  graph.addTown(town[i]);
		  }
		  
		  graph.addRoad(town[1], town[4], 3, "UniversityBlvd");
		  graph.addRoad(town[4], town[5], 8, "NorfolkAvenue");
		  graph.addRoad(town[2], town[1], 5, "SligoAvenue");
		  graph.addRoad(town[5], town[6], 1, "AeroRoad");
		  graph.addRoad(town[1], town[6], 2, "CloudStreet");
		  graph.addRoad(town[4], town[8], 3, "BlackCloverRoad");
	
	}


	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("AeroRoad", roads.get(0));
		assertEquals("BlackCloverRoad", roads.get(1));
		assertEquals("CloudStreet", roads.get(2));
		assertEquals("NorfolkAvenue", roads.get(3));
		graph.addRoad(town[2], town[8], 13,"HillandaleRoad");
		roads = graph.allRoads();
		assertEquals("AeroRoad", roads.get(0));
		assertEquals("BlackCloverRoad", roads.get(1));
		assertEquals("CloudStreet", roads.get(2));
		assertEquals("HillandaleRoad", roads.get(3));
		assertEquals("NorfolkAvenue", roads.get(4));
	}

	@Test
	public void testGetRoad() {
		assertEquals("SligoAvenue", graph.getRoad(town[2], town[1]));
		assertEquals("CloudStreet", graph.getRoad(town[1], town[6]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_10"));
		graph.addTown("Town_10");
		assertEquals(true, graph.containsTown("Town_10"));
	}

	@Test
	public void testGetTown() {
		assertEquals(false, graph.containsTown("Town_10"));
		graph.addTown("Town_10");
		assertEquals(new Town("Town_10"), graph.getTown("Town_10"));
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_1"));
		assertEquals(true, graph.containsTown("Town_2"));
		assertEquals(true, graph.containsTown("Town_3"));
		assertEquals(true, graph.containsTown("Town_4"));
		assertEquals(true, graph.containsTown("Town_5"));
		assertEquals(true, graph.containsTown("Town_6"));
		assertEquals(true, graph.containsTown("Town_7"));
		assertEquals(true, graph.containsTown("Town_8"));
		assertEquals(false, graph.containsTown("CloverKingdom"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(false, graph.containsRoadConnection("Town_1", "Town_7"));
		graph.addRoad("Town_1", "Town_7", 3, "CloverStreet");
		assertEquals(true, graph.containsRoadConnection("Town_1", "Town_7"));
		
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("AeroRoad", roads.get(0));
		assertEquals("BlackCloverRoad", roads.get(1));
		assertEquals("CloudStreet", roads.get(2));
		assertEquals("SligoAvenue", roads.get(4));
		assertEquals("UniversityBlvd", roads.get(5));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[1], town[4]));
		graph.deleteRoadConnection(town[1], town[4], "UniversityBlvd");
		assertEquals(false, graph.containsRoadConnection(town[1], town[4]));
	}


}
