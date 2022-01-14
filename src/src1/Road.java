package src1;

public class Road {
    //fields
	private String roadName;
	private Town beginTown;
	private Town endTown;
	private int distance;
	
	//methods
	
	//constructor
	/**
	 * Constructor that initializes data fields
	 * @param source
	 * @param destination
	 * @param degrees
	 * @param name
	 */
	public Road(Town source, Town destination, int degrees, String name)
	{
		roadName = name;
		beginTown = source;
		endTown = destination;
		distance = degrees;
		
	}
	
	//constructor 2
	/**
	 * Constructor that initializes data fields with weight preset of 1
	 * @param source
	 * @param destination
	 * @param name
	 */
	public Road(Town source, Town destination, String name)
	{
		this(source, destination, 1, name);
	}
	
	public Road(Road road) {
		roadName = road.roadName;
		beginTown =  new Town(road.beginTown);
		endTown = new Town (road.endTown);
		distance = road.distance;
	}

	/**
	 * Checks to see if the edge is connected to the town provided
	 * @param town
	 * @return
	 */
	public boolean contains(Town town)
	{
		return (town.equals(beginTown) || town.equals(endTown));
	}
	
	/**
	 * checks to see if the calling edge is connected to the same towns of the edge provided
	 * @param road
	 */
	
	public boolean equals(Object road)
	{
	Road road1 = (Road) road;
	return compareTo(road1) == 0;
	}
	/**
	 * return 0 if road names of roads being compared are the same, and -1 if not
	 * @param o
	 * @return 0 if road names are the same and -1 if not
	 */
	public int compareTo(Road o)
	{
		return (roadName.equals(o.roadName)? 0 : -1);
	}
	
	/**
	 *returns name of road
	 * @return roadName
	 */
	public String getName()
	{
		return roadName;
	}
	
	/**
	 * returns a deep copy of destination town
	 * @return endTown
	 */
	
	public Town getDestination()
	{
		return (endTown);
	}
	
	/**
	 * returns a deep copy of begin town
	 * @return beginTown
	 */
	public Town getSource()
	{
		return (beginTown);
	}
	
	/**
	 * returns distance between towns on the current road
	 * @return distance
	 */
	public int getWeight()
	{
		return distance;
	}
	
	
	
	
}
