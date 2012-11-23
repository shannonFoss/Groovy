/**
 * MazeSolverBFS - inherits from MazeSolver
 * Finds the solution to a maze using a breadth-first-search
 * @author Shannon Foss
 * @version 1.0
 */
public class MazeSolverBFS extends MazeSolver{
	/** Constructor
	 * 
	 * @param l - pass along inputed maze level value
	 */
	public MazeSolverBFS(int l) {
		super(l);
		add(getMaze().getStart());
	}

	@Override
	public boolean solve() {
		boolean found = false;
		//repeat until list is empty or until finish is found
		while(!isEmpty() && !found){
			//visit node from beginning of list
			Node t = remove();
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
								add(neighbors[i]);
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
