package src1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road> {
	//fields
	private HashSet<Town> towns;
	private HashSet<Road> roads;
	private Map<String, Town> shortestPaths; // to hold cheapest paths for all towns from a source town
	//constructor to initialize sets
	public Graph()
	{
		Map<String, Town> towns;
		//towns = new HashSet<Town>();
		//roads = new HashSet<Road>();
		shortestPaths = new HashMap<String, Town>();
	}

	 /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
	Road road = null; // road that connects source and destination towns
	Road result = null;
	Set<Road> roadSet = edgeSet();
	//check if source or destination town are empty values or null
	if(sourceVertex == null || destinationVertex == null)
	{
		return road; // no such road exists
	}
	
	for(Road road1: roadSet)
	{
		if(road1.getSource().equals(sourceVertex) && road1.getDestination().equals(destinationVertex))
		{
			result = road1;
		}
			
	}
	return result;
	}
	// run through list of roads that exist, and check if any of them are connected to the source and destination town
	/*
	Iterator<Road> newRoads = roads.iterator();
	boolean edgeFound = false; 
	
	while(newRoads.hasNext() && !edgeFound)
	{
		road = newRoads.next();
		if(road.contains(sourceVertex) && road.contains(destinationVertex))
		{
			edgeFound = true;
			result = road;
		}
	}
		return result;
		*/
	
	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		//Declare Road variable to hold created Road
		Road newRoad = null;
		//check if the two towns are in the graph, if they are not return null, if they are add a new road connecting the two towns
		if(towns.contains(sourceVertex) && towns.contains(destinationVertex))
		{
			if(roads.add(new Road(sourceVertex, destinationVertex, weight, description)))
			{
				newRoad = new Road(sourceVertex, destinationVertex, weight, description);
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
		return newRoad;
	}
	
	
	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */

	@Override
	public boolean addVertex(Town v) {
		return towns.add(v);
	}


    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		boolean hasEdge = false;
		Road road = null; // to store data of existing roads 
		//check if specefied vertices are null and exist in the graph
		if((sourceVertex == null || destinationVertex == null))
		{
			return hasEdge;
		}
		else if(!towns.contains(sourceVertex) || !towns.contains(destinationVertex)) // if the towns do not exist, return false
		{
			return hasEdge;
		}
		else //if the towns exist check if there is an road connecting them
		{
			Iterator<Road> newRoads = roads.iterator();
			//while there are more roads to check and there hasnt been a road connecting the two towns found, keep looking
			while(newRoads.hasNext() && !hasEdge)
			{
				road = newRoads.next();
				if(road.contains(sourceVertex) && road.contains(destinationVertex))
					hasEdge = true;
			}
		}
		return hasEdge;
	}
	
	/**
	 * Gets the road name for the road connecting the source and destination town
	 * @param sourceVertex
	 * @param destinationVertex
	 * @return roadName
	 */
	public String getRoadName(Town sourceVertex, Town destinationVertex) {
		boolean hasEdge = false;
		String roadName = "";
		Road road = null; // to store data of existing roads 
		//check if specefied vertices are null and exist in the graph
		if((sourceVertex == null || destinationVertex == null))
		{
			return "";
		}
		else if(!towns.contains(sourceVertex) || !towns.contains(destinationVertex)) // if the towns do not exist, return false
		{
			return "";
		}
		else //if the towns exist check if there is an road connecting them
		{
			Iterator<Road> newRoads = roads.iterator();
			//while there are more roads to check and there hasnt been a road connecting the two towns found, keep looking
			while(newRoads.hasNext() && !hasEdge)
			{
				road = newRoads.next();
				if(road.contains(sourceVertex) && road.contains(destinationVertex))
					hasEdge = true;
					roadName = road.getName();
				
			}
		}
		return roadName;
	}

	 /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		//check if the specefied vertex is null
		if(v == null)
			return false;
		
		return towns.contains(v);
		
		/*
		//create an iterator to run through list of towns
		Iterator<Town> townsies = towns.iterator();
		
		/* while there are towns left to process and while a town has not been found that
		 matches the town provided keep looking
		 
		while(townsies.hasNext() && !townFound)
		{
			townToCheck = townsies.next(); // get next town
			townFound = townToCheck.equals(v); //returns true or false whether the town 
												//from iterator matches town provided}
		}
		return townFound;
		*/
	}


    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
		//define and instantiate a set object of type Road
		Set<Road> r =  new HashSet<Road>();
		Iterator<Road> streets = roads.iterator();
		Road road = null;
		while(streets.hasNext())
		{
			road = streets.next();
			r.add(new Road(road));
		}
		return r;
	}

	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		// define set to hold roads connected to the specefied town
		Set<Road> connectingRoads =  new HashSet<Road>();
		//iterator to process each of the roads
		Iterator<Road> streets = roads.iterator();
		// to temporarily hold each road that exists
		Road road = null;
		while(streets.hasNext()) // while there are roads to process...
		{
			road = streets.next();
			if(road.contains(vertex)) //if the current road is connected to the specefied town, add the road to the set
			{
				connectingRoads.add(new Road(road));
			}
		}
		return connectingRoads;
	}

	  /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		//define road to hold removed road
		Road roadToBeRemoved = null;
		Road result = null;
		Boolean roadFound = false;
		Iterator<Road> roadsies = roads.iterator();
		// use iterator to run through list of edges checking if a road exists with the provided info
		while(roadsies.hasNext() && !roadFound)
		{
			roadToBeRemoved = roadsies.next(); //get next road to be checked
			//if the road currently being checked contains the source town and destination town, 
			//check for the name and description
			if(roadToBeRemoved.contains(sourceVertex) && roadToBeRemoved.contains(destinationVertex))
			{
				//check for name and description if needed
				if(weight > 1 && description!=null)
				{
					roadFound = roadToBeRemoved.getWeight() == weight && (roadToBeRemoved.getName().equals(description));
				}
				else if(weight > 1 && description == null)
				{
					roadFound = roadToBeRemoved.getWeight() == weight;
					
				}
				else if(weight < 1 && description != null)
				{
					roadFound = roadToBeRemoved.getName().equals(description);
				}
				else if(weight == 1 && description!= null)
					roadFound = roadToBeRemoved.getName().equals(description);
				//if the road has been found, set result to the road to be removed, and remove the road
				if(roadFound)
				{
					result = roadToBeRemoved;
					roadsies.remove();
				}
					
			else {
				roadFound = false;	
			}
		}
	  }
		return result;
	}

    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	
	@Override
	public boolean removeVertex(Town v) {
		//check if provided vertex is null
		if(v == null)
			return false;
		
		//if the provided town exists then remove all roads connected to this town and the town itself
		if(towns.contains(v))
		{
			Iterator<Road> r = roads.iterator();
			while(r.hasNext())
			{
				if(r.next().contains(v))
					r.remove();
			}
			towns.remove(v);
			return true;
		}
		else
			return false;
	}

	  /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	
	@Override
	public Set<Town> vertexSet() {
		//define and instantiate a set object of type town
		Set<Town> t =  new HashSet<Town>();
		Iterator<Town> townsies = towns.iterator();
		Town town = null;
		while(townsies.hasNext())
		{
			town = townsies.next();
			t.add(new Town(town));
		}
		return t;
	}

	 /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */ 
	
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) 
	{
		//define arraylist to hold shortest path nbetween the source town and the destination town
		ArrayList<String> shortestPath = new ArrayList<String>();
		
		//define Town object to hold cheapest path between the source town and the destination town
		Town cheapestPath;
		//define stack to hold path of towns leading from source town to the destination town
		ArrayDeque<Town> stack = new ArrayDeque<>();
		
		
		String path = "";
		
		//call dijkstraShortedPath algorithm to construct cheapest paths
		dijkstraShortestPath(sourceVertex);
		
		cheapestPath = shortestPaths.get(destinationVertex.getName()); //get cheapest path between sourceVertex and destinationVertex
		
		Town town = cheapestPath; //store cheapest path in town object
		
		//add the path from the source town to the destination town to the stack
		while(town!= null)
		{
			stack.addLast(town);
			town = town.getPredecessor();
		}
	
		// while the stack is not empty add the path stored in the stack as strings to an arraylist
		while(!stack.isEmpty())
		{
			Town townToAdd= stack.getLast();
			path+= stack.removeLast().getName() + " via ";
			 
			//store path for two vertices at a time, until we reach the destination vertex
			if(!stack.isEmpty())
			{
				path += getRoadName(townToAdd, stack.getLast()) + " to " + stack.getLast().getName() + " " + getDistance(townToAdd, stack.getLast()) + " mi ";
				shortestPath.add(path); // add the path to the arraylist
		
			}
			path = "";
			
		}
		return shortestPath;
	}
	
	 /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		Comparator<Town> compareTowns = new Town();
		
		//define hashset of type twon to hold visisted vertices
		Set<Town> visitedVertices = new HashSet<Town>();
		
		//define iterator to hold neighbors of a vertex
		Iterator<Town> neighbors;
		
		//define priorityQueue to hold the paths in entryObjects
		Queue<Town> priorityQueue = new PriorityQueue<Town>(11, compareTowns);
		
		//define town object to hold front entries at the front of the priority queue
		Town frontEntry;
	
		//set the predecessor of the source town as null,and its path as 0 since its the source
		sourceVertex.setPredecessor(null);
		sourceVertex.setCost(0);
		shortestPaths.put(sourceVertex.getName(), sourceVertex); //ad the source vertex to the global hashmap which holds the shortest paths for all vertices from a specefic source
		priorityQueue.add(sourceVertex); //add the source town to the queue
		visitedVertices.add(sourceVertex); // set source vertex as visited
		
		// while the priotiy queue is not empty process entries
		while(!priorityQueue.isEmpty())
		{
				frontEntry = priorityQueue.remove(); // get entry at front of priority Queue(cheapest path)
				neighbors = getNeigbors(frontEntry);
				// go through neighbors 
				while(neighbors.hasNext())
				{
					Town neighbor = neighbors.next(); // get next neighbor of frontVertex
					int distanceToNeighbor = getDistance(frontEntry, neighbor);//calculte distance between front vertex and its nieghbor
					//if the neighboring town has not been visited yet create a path between them and add it to the priorityQueue
					if(!visitedVertices.contains(neighbor)) 
					{
					
						neighbor.setPredecessor(frontEntry);// set the predecessor of the neighbor as the current entry were processing
						neighbor.setCost(distanceToNeighbor + frontEntry.getCost()); //set distance of neighbor from source vertex
						visitedVertices.add(neighbor); // set this visited neighbor as visited, so we dont visit it again
						priorityQueue.add(neighbor); //add this neighbor holding path from source vertex to the priority queue
						shortestPaths.put(neighbor.getName(), neighbor); //add this path to the hashmap, if a new and better path is found, replace the old
					}
				}
				
			}

		}
		

	
	/**
	 * gets the distance between two towns
	 * @param sourceVertex
	 * @param destinationVertex
	 * @return distance
	 */
	public int getDistance(Town sourceVertex, Town destinationVertex)
	{
		int distance = 0; // to hold distance between two of provided towns
		boolean edgeFound = false; //to indicate if road is found connecting provided towns
		Iterator<Road> roadsies = roads.iterator(); // to go through roads
		Road road = null; // to hold road instances from iterator
		//go through the roads seeing if there is one connecting provided towns
		while(roadsies.hasNext() && !edgeFound)
		{
			road = roadsies.next();//get next road
			//if there is a road connecting to towns, an edge has been found and get the distance between the towns
			if(road.contains(sourceVertex) && road.contains(destinationVertex))
			{
				edgeFound = true;
				distance = road.getWeight();
				break;
			}
		}
		return distance;
	}
	
	/**
	 * gets an iterator with the neighbors for a specefic town
	 * @param town
	 * @return
	 */
	public Iterator<Town> getNeigbors(Town town)
	{
		//define a set to hold neighbors of provided town
		Set<Town> neighbors = new HashSet<>();
		Road road = null;
		//define iterator to go through list of roads conneted to provided town
		Iterator<Road> roadsies = roads.iterator();
		/*
		run through roads and check if any contain provided town if they do add the other town
		connected to it to the set of neighbors
		 */
		while(roadsies.hasNext())
		{
			road = roadsies.next();//get next road
			//if the town is connected to the current road, add the neighboring town to the set
			if(road.contains(town))
			{
				if(road.getSource().equals(town)) {
				neighbors.add(road.getDestination());	
				}
				else 
				{
					neighbors.add(road.getSource());
				}
			}
		}
	
		return neighbors.iterator();
	}
}



