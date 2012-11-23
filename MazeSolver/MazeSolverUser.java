import java.util.Scanner;

/** MazeSolverUser
 *  Allows the user to solve the maze
 * @author Shannon Foss
 * @version 1.0
 */
public class MazeSolverUser{
	/** Instance of the maze */
	private Maze maze;
	Node cur;
	Node prev;
	public MazeSolverUser(int l) {
		maze = new Maze(l);
		maze.getStart().setCurrent();
		cur = maze.getStart();
		prev = cur;
	}

	public boolean solve(int dir) {
		switch(dir){
			case 1: //up
					if(maze.getSpace((int)cur.getLoc().getX()-1,(int)cur.getLoc().getY()).getType() != '*'){
						prev = cur;
						cur = maze.getSpace((int)prev.getLoc().getX()-1,(int)prev.getLoc().getY());
						cur.setCurrent();
						prev.setCurrent();
					}
					break;
			case 2: //down
					if(maze.getSpace((int)cur.getLoc().getX()+1,(int)cur.getLoc().getY()).getType() != '*'){
						prev = cur;
						cur = maze.getSpace((int)prev.getLoc().getX()+1,(int)prev.getLoc().getY());
						cur.setCurrent();
						prev.setCurrent();
					}
					break;
			case 3: //left
					if(maze.getSpace((int)cur.getLoc().getX(),(int)cur.getLoc().getY()-1).getType() != '*'){
						prev = cur;
						cur = maze.getSpace((int)prev.getLoc().getX(),(int)prev.getLoc().getY()-1);
						cur.setCurrent();
						prev.setCurrent();
					}
					break;
			case 4: //right
					if(maze.getSpace((int)cur.getLoc().getX(),(int)cur.getLoc().getY()+1).getType() != '*'){
						prev = cur;
						cur = maze.getSpace((int)prev.getLoc().getX(),(int)prev.getLoc().getY()+1);
						cur.setCurrent();
						prev.setCurrent();
					}
					break;
			case 5: return true;
		}
		if(cur.getType()=='f'){
			return true;
		}
		return false;
	}
	/** Prints Maze */
	public void printMaze(){
		maze.printMaze();
	}
	/** Returns a string of the printed maze */
	public String printMazeStr(){
		return maze.printMazeStr();
	}
	/** Check input method
	 * 
	 * @param msg - String message
	 * @param low - lower bound value
	 * @param high - upper bound value
	 * @return
	 */
	public int checkInput(String msg, int low, int high){
		Scanner in = new Scanner(System.in);
		int input = 0;
		boolean test = true;
		while(test){
			System.out.print(msg);
			if(in.hasNextInt()){
				input = in.nextInt();
				if(input>=low && input<=high){
					test = false;
				}else{
					System.out.println("Value not in specified range.");
				}
			}else{
				System.out.println("Invalid Input");
				in.next(); //clear buffer
			}
		}
		return input;
	}
	/* ******** Menu ****** */
	public void displayDirectionMenu(){
		System.out.println("-Direction Menu-");
		System.out.println("1. Up");
		System.out.println("2. Down");
		System.out.println("3. Left");
		System.out.println("4. Right");
		System.out.println("5. Quit");		
	}
}
