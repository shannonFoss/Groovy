import java.util.ArrayList;

/** MazeSolver - Abstract Class 
 * Stores the maze and list.  Inherited by more specific maze solvers.
 * @author Shannon Foss
 * @version 1.0
 */

public abstract class MazeSolver {
	/** Instance of the maze */
	private Maze maze;
	/** Stack to store added nodes */
	private ArrayList <Node> list;
	
	/* ******** Constructor ******* */
	
	/** Creates and Initializes the maze and the list
	 * 
	 * @param l - inputed maze level number
	 */
	public MazeSolver(int l){
		list = new ArrayList<Node>();
		maze = new Maze(l);
	}

	/* ******** Methods ******* */
	
	/** Check if list is empty
	 * 
	 * @return - boolean
	 */
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	/** Returns the list
	 * 
	 * @return - ArrayList
	 */
	public ArrayList <Node> getList(){
		return list;
	}
	
	/** Returns the maze
	 * 
	 * @return - Maze
	 */
	public Maze getMaze(){
		return maze;
	}
	
	/** Method to add a new node to the list
	 * 
	 * @param n - Node
	 */
	public void add(Node n){
		list.add(n);
	}
	
	/** Method to remove the first node in the list
	 *  and return it to where it was called
	 * 
	 * @return - Node
	 */
	public Node remove(){
		return list.remove(0);
	}
	/** Method to remove a specified node in the list
	 *  and return it to where it was called
	 * 
	 * @return - Node
	 */
	public Node remove(int val){
		return list.remove(val);
	}
	/** Method to remove a specified object in the list
	 * 
	 * @return - Node
	 */
	public void remove(Node n){
		list.remove(n);
	}
	/** Abstract - forces the inherited class to implement the solve method
	 * 
	 * @return - true if maze was solvable
	 */
	public abstract boolean solve();

	/** Method to determine the path that was ultimately taken
	 * 
	 */
	public void findPath(){
		Node n = maze.getFinish();
		do{
			n.setInPath();
			n = n.getPrev();
		}while(n != null);
	}
}
