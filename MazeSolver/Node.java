import java.awt.Point;
/**
 * Node class to represent spaces in a Maze
 * @author Shannon Foss
 * @version 1.0
 */
public class Node {
	/** Coordinate location of the space (row = 0->height, col = 0->width) */
	private Point loc;
	/** True if the space was ever visited */
	private boolean visited;
	/** True if the space was added to the list */
	private boolean added;
	/** True if it was added to the final path */
	private boolean inPath;
	/** True if this node is the current location
	 * used for user solved mazes.*/
	private boolean current;
	/** Character type of the space:
	 * '*' - wall
	 * ' ' - space
	 * 's' - start
	 * 'f' - finish
	 */
	private char type;
	/** Parent node of this node */
	private Node prev;
	/** Calculated cost of traversing to this node. 
	 * Used for heuristic searches.
	 */
	private int gCost;
	/** Calculated total cost of traversing to this node. 
	 * Used for heuristic searches.
	 */
	private int fCost;
	/* ******* Constructor ********** */
	/** Initializes a new node.
	 * 
	 * @param p - position of the space
	 * @param t - character type of the space
	 * All nodes are initialized without a parent node, 
	 * 0 cost, and not visited or added.
	 */
	public Node(Point p, char t){
		loc = p;
		type = t;
		visited=false;
		added = false;
		inPath = false;
		prev = null;
		gCost = 0;
		fCost = 0;
		current = false;
	}
	
	/* ******** Methods *********** */
	/** 
	 * Method to retrieve the location of this node.
	 * @return - Point
	 */
	public Point getLoc(){
		return loc;
	}	
	/** 
	 * Method returns true if the node has been visited.
	 * @return - boolean
	 */
	public boolean getVisited(){
		return visited;
	}	
	/** 
	 * Method returns true if the node is the current node.
	 * @return - boolean
	 */
	public boolean getCurrent(){
		return current;
	}	
	/** 
	 * Method returns true if the node has been added to the list.
	 * @return - boolean
	 */
	public boolean getAdded(){
		return added;
	}	
	/** 
	 * Method returns true if the node has been added to the path.
	 * @return - boolean
	 */
	public boolean getInPath(){
		return inPath;
	}
	/** 
	 * Method returns the type of space.
	 * @return - char
	 */
	public char getType(){
		return type;
	}	
	/** 
	 * Method to retrieve the parent node.
	 * @return - Node
	 */
	public Node getPrev(){
		return prev;
	}
	/** 
	 * Method to retrieve the gcost of the node.
	 * @return - int
	 */
	public int getgCost(){
		return gCost;
	}
	/** 
	 * Method to retrieve the fcost of the node.
	 * @return - int
	 */
	public int getfCost(){
		return fCost;
	}
	/** 
	 * Method to set that the node has been visited.
	 */
	public void setVisited(){
		visited=true;
	}
	/** 
	 * Method to set that the node has been added to the list.
	 */
	public void setAdded(){
		added=true;
	}
	/** 
	 * Method to toggle the current node.
	 */
	public void setCurrent(){
		current=!current;
	}
	/** 
	 * Method to set that the node has been added to the path.
	 */
	public void setInPath(){
		inPath=true;
	}
	/** 
	 * Method to set the parent node.
	 */
	public void setPrev(Node n){
		prev = n;
	}
	/** 
	 * Method to set the cost.
	 */
	public void setgCost(int c){
		gCost = c;
	}
	/** 
	 * Method to set the cost.
	 */
	public void setfCost(int c){
		fCost = c;
	}
	/** 
	 * Method to display a node's location and type.
	 * @return - String
	 */
	public String toString(){
		String node = "Row="+(int)loc.getX()+ " Col="+(int)loc.getY()+" "+ "Type="+type;
		return node;
	}
}
