package src1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {

	private Graph graph = new Graph();
	
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		return graph.addEdge(getTown(town1), getTown(town2), weight, roadName) != null;
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) {
		if(graph.getEdge(getTown(town1), getTown(town2))!= null)
			return graph.getEdge(getTown(town1), getTown(town2)).getName();
		else return null;
			
	}

	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}
	
	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	
	@Override
	public Town getTown(String name) {
		 Iterator<Town> townI = graph.vertexSet().iterator();
		 Boolean townFound = false;
		 Town result = null;
		while(townI.hasNext() && !townFound)
		{
			Town t = townI.next();
			if(t.getName().equals(name)) {
				townFound = true;
				result = t;
			}
		}
		return result;
	}

	
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		Iterator<Town> townI = graph.vertexSet().iterator();
		Boolean townFound = false;
		
		while(townI.hasNext() && !townFound)
		{
			Town t = townI.next();
			if(t.getName().equals(v)) {
				townFound = true;
			}
		}
		return townFound;
	}

	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(getTown(town1), getTown(town2));
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		Iterator<Road> allRoads = graph.edgeSet().iterator();
		
		ArrayList<String> abcRoads = new ArrayList<String>();
		while(allRoads.hasNext())
		{
			Road r = allRoads.next();
			abcRoads.add(r.getName());
		}
		
		Collections.sort(abcRoads);
		return abcRoads;
	}

	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		graph.removeEdge(getTown(town1), getTown(town2), 1, road);
		return containsRoadConnection(town1, town2) == false;
	}

	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		graph.removeVertex(getTown(v));
		return containsTown(v);
	}


	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() {
Iterator<Town> allTowns = graph.vertexSet().iterator();
		
		ArrayList<String> abcTowns = new ArrayList<String>();
		while(allTowns.hasNext())
		{
			Town r = allTowns.next();
			abcTowns.add(r.getName());
		}
		
		Collections.sort(abcTowns);
		return abcTowns;
	}


	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		ArrayList<String> path = new ArrayList<>();
		path = graph.shortestPath(getTown(town1), getTown(town2));
		return path;
		
	}

	/**
	 * adds to a graph using data from the provided file
	 * @param selectedFile
	 * @throws FileNotFoundException
	 */
	public void populateTownGraph(File selectedFile) throws FileNotFoundException{
		String line[];
		Scanner inputFile = new Scanner(selectedFile);
		// add data from file into graph
		while(inputFile.hasNext())
		{
			line = inputFile.nextLine().split("[,;]");
			String roadName = line[0];
			int distance = Integer.parseInt(line[1]);
			addTown(line[2]);
			addTown(line[3]);
			addRoad(line[2], line[3], distance, roadName);
			
		}
		inputFile.close();
	}

}
