import java.util.Scanner;
/** Main Method for Maze Solver
 *  Given a maze and a solver, this program will solve the maze.
 * @author Shannon Foss
 * @version 1.0
 */
public class MazeMain {
	public static void main(String[] args) {
		displayMazeMenu();
		int input = checkInput("Enter Value (1-4): ", 1,4)-1;
		Maze m = new Maze(input);
		m.printMaze();
		displaySolverMenu();
		int solver = checkInput("Choose a Solver (1-4); ",1,4);
		switch(solver){
			case 1: MazeSolverBFS bfs = new MazeSolverBFS(input);
					bfs.solve();
					bfs.getMaze().printMaze();
					break;
			case 2: MazeSolverDFS dfs = new MazeSolverDFS(input);
					dfs.solve();
					dfs.getMaze().printMaze();
					break;
			case 3: MazeSolverAStar ast = new MazeSolverAStar(input);
					ast.solve();
					ast.getMaze().printMaze();
					break;
			case 4: MazeSolverUser usr = new MazeSolverUser(input);		
					boolean done = false;
					while (!done){
						usr.displayDirectionMenu();
						int dir = usr.checkInput("Enter Direction: ", 1, 5);
						done = usr.solve(dir);
						usr.printMaze();
					}
					break;
		}
	}
	/** Check input method
	 * 
	 * @param msg - String message
	 * @param low - lower bound value
	 * @param high - upper bound value
	 * @return
	 */
	public static int checkInput(String msg, int low, int high){
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
	/* ******** Menus ****** */
	public static void displaySolverMenu(){
		System.out.println("-Solver Menu-");
		System.out.println("1. BFS");
		System.out.println("2. DFS");
		System.out.println("3. A-Star");
		System.out.println("4. User");
		
	}
	public static void displayMazeMenu(){
		System.out.println("-Maze Menu-");
		System.out.println("1. Easy");
		System.out.println("2. Beginner");
		System.out.println("3. Intermediate");
		System.out.println("4. Difficult");
	}
}
