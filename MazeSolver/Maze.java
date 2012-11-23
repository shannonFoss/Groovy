import java.awt.Point;
import java.io.*;
import java.util.Scanner;

/** Maze Class
 * Object that holds the working maze in a 2D array.
 * @author Shannon Foss
 * @version 1.0
 */
public class Maze {
	/** Number of Rows in the maze. */
	private int sizeRows;
	/** Number of Columns in the maze. */
	private int sizeCols;
	/** Storage of the maze as an array of nodes. */
	private Node [][] maze;
	/** Starting node. */
	private Point start;
	/** Finishing node. */
	private Point finish;
	
	/* ********** Constructor ********* */
	/** Initializes the maze from a file.
	 * @param c - maze level
	 */
	public Maze(int l){
		try{
			Scanner read = new Scanner(new File("Maze-Level"+l+".txt"));
			sizeRows = read.nextInt();
			sizeCols = read.nextInt();
			read.nextLine();
			maze = new Node[sizeRows][sizeCols];
			for(int i = 0;i<sizeRows;i++){
				String line = read.nextLine();
				for(int j=0;j<sizeCols;j++){
					Point p = new Point(i, j);
					maze[i][j] = new Node(p, line.charAt(j));
					if(line.charAt(j) == 's'){
						start = p;
					}
					if(line.charAt(j) == 'f'){
						finish = p;
					}
				}
			}
		}catch(FileNotFoundException ex){
			System.out.println("File Not Found.");
		}
	}

	/* ********** Methods ******* */
	/** Returns the number of rows in the maze.
	 * 
	 * @return - number of rows in the maze.
	 */
	public int getRows(){
		return sizeRows;
	}	
	/** Returns the number of columns in the maze.
	 * 
	 * @return - number of columns in the maze.
	 */
	public int getCols(){
		return sizeCols;
	}	
	/** Returns the start node.
	 * 
	 * @return - starting node.
	 */
	public Node getStart(){
		return getSpace((int)start.getX(), (int)start.getY());
	}
	/** Returns the finish node.
	 * 
	 * @return - finish node.
	 */
	public Node getFinish(){
		return getSpace((int)finish.getX(), (int)finish.getY());
	}
	/** Returns the node at a particular location.
	 * @param x - row location
	 * @param y - column location
	 * 
	 * @return - a node in the maze given a location.
	 */
	public Node getSpace(int x, int y){
		if(x>=0 && x<sizeRows && y>=0 && y<sizeCols){
			return maze[x][y];	
		}else{
			System.out.println("Index out of bounds");
			return null;
		}
	}

	/**
	 * Finds the four neighbors of a node (left,up,right,down)
	 * @param n - Node n
	 * @return - Array of neighboring nodes (left,up,right,down)
	 */
	public Node [] getNeighbors(Node n){
		Node [] neighbors = new Node [4];
		Point p = n.getLoc();
		if(p.getX()>0 && p.getX()<sizeRows-1 && p.getY()>0 && p.getY()<sizeCols-1){
			neighbors [0]=maze[(int)p.getX()][(int)p.getY()-1];//left
			neighbors [1]=maze[(int)p.getX()-1][(int)p.getY()];//up
			neighbors [2]=maze[(int)p.getX()][(int)p.getY()+1];//right
			neighbors [3]=maze[(int)p.getX()+1][(int)p.getY()];//down
		}
		return neighbors;
	}
	/**
	 * Prints out the maze to the console
	 */
	public void printMaze(){
		for(int i=0; i< sizeRows; i++){
			for(int j=0; j<sizeCols; j++){
				if(maze[i][j].getCurrent()){
					System.out.print("o ");
				}else if(maze[i][j].getInPath()){
					System.out.print("p ");
				}else if(maze[i][j].getVisited()){
					System.out.print("v ");
				}else{
					System.out.print(maze[i][j].getType()+" ");
				}
			}
			System.out.println();
		}
	}
	/**
	 * Prints out the maze to the console
	 */
	public String printMazeStr(){
		String mazeStr="";
		for(int i=0; i< sizeRows; i++){
			for(int j=0; j<sizeCols; j++){
				if(maze[i][j].getCurrent()){
					mazeStr = mazeStr+"o ";
				}else if(maze[i][j].getInPath()){
					mazeStr = mazeStr+"p ";
				}else if(maze[i][j].getVisited()){
					mazeStr = mazeStr+"v ";
				}else{
					mazeStr = mazeStr+maze[i][j].getType()+" ";
				}
			}
			mazeStr = mazeStr+"\n";
		}
		return mazeStr;
	}
}
