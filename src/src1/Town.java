package src1;
import java.util.*;



public class Town implements Comparator<Town> {

	//fields
	private String town;
	private LinkedList<Road> edgeList;
	private Town predecessor;
	private int cost;
	private boolean visited;
	
	
	private class NeighborIterator implements Iterator<Town>{
		   private Iterator<Road> edges; 
		   
		   public NeighborIterator()
		   {
			   edges = edgeList.iterator();
		   }
		   
		   public boolean hasNext()
		   {
			   return edges.hasNext();
		   }
		   
		   public Town next()
		   {
			   Town result = null;
			   if(hasNext())
			   {
				   Road edgeToNextNeighbor = edges.next();
				   result = edgeToNextNeighbor.getSource().equals(this) ? edgeToNextNeighbor.getDestination(): edgeToNextNeighbor.getSource(); 
			   }
			   else
				   throw new NoSuchElementException();
			   
			   return result;
		   }
		   
	   }
	   
	   private class WeightIterator implements Iterator<Double>
	   {

		   private Iterator<Road> edges;
		   
		   public WeightIterator()
		   {
			   edges = edgeList.iterator();
		   }
		   
		   
		public boolean hasNext()
		{
			return edges.hasNext();
		}
		
		public Double next()
		{
		double result = 0;
			
			if(hasNext())
			{
				Road edgeOfNextNeighbor = edges.next();
				result = edgeOfNextNeighbor.getWeight();
			}
			else
				throw new NoSuchElementException();
				
			return result;
			
		}
		
		   
	   }
	
	
	/**
	 * constructor that initializes dat fields
	 * @param name
	 */
	public Town()
	{
		this("");
	}
	public Town(String name)
	{
		town = name;
		edgeList = new LinkedList<Road>();
		predecessor = null;
		cost = 0;
		visited = false;
	}

	
	/**
	 * copy contructor
	 * @param templateTown
	 */
	public Town(Town templateTown)
	{
		town = templateTown.town;
		edgeList = templateTown.edgeList;
		predecessor = templateTown.predecessor;
		 cost = templateTown.cost;
		 visited = templateTown.visited;
	}
	
	/**
	 * gets the name
	 * @return town
	 */
	public String getName() {
		return town;
	}
	
	/**
	 * return 0 if town names of towns being compared are the same, and -1 if not
	 * @param o
	 * @return 0 if town names are the same and -1 if not
	 */
	public int compareTo(Town o)
	{
		return (town.equals(o.town) ? 0 : -1);
	}
	
	/**
	 * gets town name
	 */
	public String toString()
	{
		return town;
	}
	
	/**
	 * gets hashCode of town name
	 */
	public int hashCode()
	{
		return town.hashCode();
	}
	
	/**
 	* equals method for the town names
 	* @param b
 	* @return true or false whether the town names are equal
 	*/
	public boolean equals(Object b)
	{
		Town t = (Town) b;
		return compareTo(t) == 0;
	}
	
	/**
	 * sets predecessor for town
	 * @param town
	 */
	public void setPredecessor(Town town)
	{
		predecessor = town;
	}
	
	/**
	 * gets predecessor for town
	 * @param town
	 * @return predecessor
	 */
	public Town getPredecessor()
	{
		return predecessor;
	}
	
	/**
	 * sets the path cost for the town
	 * @param c
	 */
	public void setCost(int c)
	{
		cost = c;
	}
	
	/**
	 * gets an iterator for the nieghbors of this town
	 * @return
	 */
	
	public Iterator<Town> getNeighborIterator()
	{
		return new NeighborIterator();
	}
	
	/**
	 * gets an iterator for the 
	 * @return
	 */
	public Iterator<Double> getWeightIterator()
	{
		return new WeightIterator();
	}
	
	/**
	 * gets the cost for the current town
	 * @return cost
	 */
	public int getCost()
	{
		return cost;
	}
	
	public void setVisited(boolean c)
	{
		visited = c;
	}

	@Override
	public int compare(Town o1, Town o2) {
		if(o1.cost > o2.cost)
			return 1;
		else if(o1.cost == o2.cost)
			return 1;
		else
			return -1;
	}

}
