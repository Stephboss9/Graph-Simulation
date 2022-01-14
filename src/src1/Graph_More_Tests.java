package src1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {
	private GraphInterface<Town,Road> graph;
	private Town[] town;

	@Before
	public void setUp() throws Exception {
		 graph = new Graph();
		  town = new Town[12];
		  
		  for (int i = 1; i < 9; i++) {
			  town[i] = new Town("Town_" + i);
			  graph.addVertex(town[i]);
		  }
		  
		  graph.addEdge(town[1], town[4], 3, "UniversityBlvd");
		  graph.addEdge(town[4], town[5], 8, "NorfolkAvenue");
		  graph.addEdge(town[2], town[1], 5, "SligoAvenue");
		  graph.addEdge(town[5], town[6], 1, "AeroRoad");
		  graph.addEdge(town[1], town[6], 2, "CloudStreet");
		  graph.addEdge(town[4], town[8], 3, "BlackCloverRoad");
	
	}
	@Test
	public void testGetEdge() {
		assertEquals(new Road(town[4], town[8],3, "BlackCloverRoad"), graph.getEdge(town[4], town[8]));
		assertEquals(new Road(town[1], town[6],2, "CloudStreet"), graph.getEdge(town[1], town[6]));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[5], town[7]));
		graph.addEdge(town[5], town[7], 4, "KingdomRoad");
		assertEquals(true, graph.containsEdge(town[5], town[7]));
	}

	@Test
	public void testAddVertex() {
		Town newTown = new Town("Town_bob");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(false, graph.containsEdge(town[7], town[3]));
		assertEquals(true, graph.containsEdge(town[4], town[1]));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("AeroRoad", roadArrayList.get(0));
		assertEquals("BlackCloverRoad", roadArrayList.get(1));
		assertEquals("CloudStreet", roadArrayList.get(2));
		assertEquals("NorfolkAvenue", roadArrayList.get(3));
	}

	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(town[1], town[4]));
		graph.removeEdge(town[1], town[4], 3,"UniversityBlvd");
		assertEquals(false, graph.containsEdge(town[1], town[4]));
	}

	@Test
	public void testVertexSet() {
		Set<Town> roads = graph.vertexSet();
		assertEquals(true,roads.contains(town[1]));
		assertEquals(true, roads.contains(town[8]));
		assertEquals(true, roads.contains(town[7]));
		assertEquals(true, roads.contains(town[2]));
		assertEquals(true, roads.contains(town[3]));
	}

}
