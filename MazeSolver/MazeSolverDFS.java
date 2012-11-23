import java.util.Stack;
/**
 * MazeSolverDFS - inherits from MazeSolver
 * Finds the solution to a maze using a depth-first-search
 * @author Shannon Foss
 * @version 1.0
 */
public class MazeSolverDFS extends MazeSolver{
	Stack <Node> stack;
	/** Constructor
	 * 
	 * @param l - pass along inputed maze level value
	 */
	public MazeSolverDFS(int l) {
		super(l); 
		stack = new Stack <Node> ();
		stack.push(getMaze().getStart());
	}

	@Override
	public boolean solve() {
		boolean found = false;
		//repeat until stack is empty or until finish is found
		while(!stack.isEmpty() && !found){
			//visit node from top of stack
			Node t = stack.pop();
			t.setVisited();
			//finish node?
			if(t.getType() == 'f'){
				found = true;
				findPath();
			}else{//not finish node
				//add valid neighbors of node to stack
				Node [] neighbors= getMaze().getNeighbors(t);
				for(int i=0;i<4;i++){
					if(neighbors[i].getType() == ' '||neighbors[i].getType() == 'f'){
						if(!neighbors[i].getVisited() ){
							if(!neighbors[i].getAdded()){
								neighbors[i].setPrev(t);
								stack.push(neighbors[i]);
								neighbors[i].setAdded();
							}
						}
					}	
				}
			}
		}
		return found;
	}
}
