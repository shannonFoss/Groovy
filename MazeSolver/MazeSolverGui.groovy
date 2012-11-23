import groovy.swing.SwingBuilder
import javax.swing.*
import java.awt.*
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener
// Groovy Gui for Maze Solver

/* 
	User can choose to solve the maze by clicking on the "I Can Solve It" button and then
	using the arrow keys to move the cursor around the maze.
*/

	def sb = new SwingBuilder()
	def input=3 //default to difficult maze
	Maze m = new Maze(input) //initial display maze
	MazeSolverUser usr //allows for user to solve the maze
	def mazeArea  //display area for maze
	def mazeStr = m.printMazeStr() //string to display the maze
	def usrBtn //button that has keyboard listeners
	//main component panel: comboBox, buttons and text area
	def mainPanel = {
		sb.panel(layout:new BorderLayout()){
			vbox(constraints: BorderLayout.WEST){
				comboBox(items: ["Easy", "Beginner","Intermediate", "Difficult"], 
					selectedIndex: 3,
					actionPerformed: {e -> 
						if(e.source.selectedItem == "Easy"){
							input = 0
						}else if(e.source.selectedItem == "Beginner"){
							input = 1
						}else if(e.source.selectedItem == "Intermediate"){
							input = 2
						}else if(e.source.selectedItem == "Difficult"){
							input = 3
						}
						m = new Maze(input)
						mazeStr = m.printMazeStr()
						mazeArea.setText(mazeStr)
					})
				button(text : "BFS Solution ", actionPerformed: {
					MazeSolverBFS bfs = new MazeSolverBFS(input);
					bfs.solve();
					mazeStr = bfs.getMaze().printMazeStr()
					mazeArea.setText(mazeStr)
				})
				button(text : "DFS Solution", actionPerformed:{
					MazeSolverDFS dfs = new MazeSolverDFS(input);
					dfs.solve();
					mazeStr = dfs.getMaze().printMazeStr()
					mazeArea.setText(mazeStr)
				})
				button(text : "A*   Solution", actionPerformed:{
					MazeSolverAStar ast = new MazeSolverAStar(input);
					ast.solve();
					mazeStr = ast.getMaze().printMazeStr()
					mazeArea.setText(mazeStr)
				})
				usrBtn = button(text : "I Can Solve It", actionPerformed:{
					usr = new MazeSolverUser(input);
				})
			}
			panel(constraints: BorderLayout.CENTER){
				mazeArea = textArea(text: mazeStr, font: new Font("Courier", 0,10), enabled:false)
			}
		}
	}
	def frame = sb.frame(title: 'Maze Solver', location: [100,100], size:[1000,600], defaultCloseOperation:WindowConstants.EXIT_ON_CLOSE){
		mainPanel()	
	}
	KeyListener keyListener = new KeyListener(){
		@Override
		public void keyPressed(KeyEvent e) {
			int kbInput = e.getKeyCode();
			switch(kbInput){
				case KeyEvent.VK_UP:	usr.solve(1);
										mazeStr = usr.printMazeStr()
										mazeArea.setText(mazeStr)
										break;
				case KeyEvent.VK_DOWN:	usr.solve(2);
										mazeStr = usr.printMazeStr()
										mazeArea.setText(mazeStr)
										break;
				case KeyEvent.VK_LEFT:	usr.solve(3);
										mazeStr = usr.printMazeStr()
										mazeArea.setText(mazeStr)
										break;
				case KeyEvent.VK_RIGHT: usr.solve(4);
										mazeStr = usr.printMazeStr()
										mazeArea.setText(mazeStr)
										break;
			}
		}
	
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyTyped(KeyEvent e) {}
	};
	usrBtn.addKeyListener(keyListener);
	frame.pack()
	frame.setVisible(true)