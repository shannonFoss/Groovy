/**
 * MazeSolverAStar - inherits from MazeSolver
 * Finds the solution to a maze using the A-Star algorithm
 * @author Shannon Foss
 * @version 1.0
 */
public class MazeSolverAStar extends MazeSolver{
	/** Constructor
	 * 
	 * @param l - maze level
	 */
	public MazeSolverAStar(int l) {
		super(l);
		Node n = getMaze().getStart();
		n.setgCost(0);
		n.setfCost(0);
		add(n);
	}

	@Override
	public boolean solve() {
		boolean found = false;
		//repeat until list is empty or until finish is found
		while(!found && !isEmpty()){
			//visit node with the lowest cost in the list
			Node n = lowestCost();
			n.setVisited();
			remove(n);
			//finish node?
			if(n.getType() == 'f'){
				found = true;
				findPath();
			}else{//not finish node
				//add valid neighbors of node to stack
				Node [] neighbors= getMaze().getNeighbors(n);
				for(int i=0;i<4;i++){
					if(neighbors[i].getType() == ' '||neighbors[i].getType() == 'f'){
						if(!neighbors[i].getVisited() ){
							if(!neighbors[i].getAdded()){//adding a new node to list
								neighbors[i].setPrev(n);
								neighbors[i].setgCost(n.getgCost()+1);
								neighbors[i].setfCost(calcCost(neighbors[i]));
								add(neighbors[i]);
								neighbors[i].setAdded();
							}else{//already in list, if a lower cost path was found, do update
								if(neighbors[i].getfCost()>calcCost(neighbors[i], n.getgCost()+1)){
									neighbors[i].setPrev(n);
									neighbors[i].setgCost(n.getgCost()+1);
									neighbors[i].setfCost(calcCost(neighbors[i]));
								}
							}
						}
					}	
				}
			}
		}
		return found;
	}
	/** Method to find the next node in the list with the lowest cost
	 * 
	 * @return - node from list with lowest cost
	 */
	public Node lowestCost(){
		Node low = getList().get(0);
		for(Node n : getList()){
			if(n.getfCost()<low.getfCost()){
				low = n;
			}
		}
		return low;
	}
	/** Method to calculate the cost of the node
	 * @param n - Node 
	 * @return - calculated cost
	 */
	public int calcCost(Node n){
		Node start = getMaze().getStart();
		int g = n.getgCost();
		int h = Math.abs((int)start.getLoc().getX()-(int)n.getLoc().getX())+Math.abs((int)start.getLoc().getY()-(int)n.getLoc().getY()); 
		return g+h;
	}
	/** Method to calculate the cost of the node and use a different g value
	 * @param n - Node 
	 * @param g - g value of node
	 * @return - calculated cost
	 */
	public int calcCost(Node n, int g){
		Node start = getMaze().getStart();
		int h = Math.abs((int)start.getLoc().getX()-(int)n.getLoc().getX())+Math.abs((int)start.getLoc().getY()-(int)n.getLoc().getY()); 
		return g+h;
	}
}
