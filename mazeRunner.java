package Maze;

import java.util.Scanner;

public class mazeRunner {

	public static void main(String[] args) {
		
	Scanner p = new Scanner(System.in);	
	
		Maze myMap = new Maze();
		
		int currentMoves = 0;
		
		//Call for the introduction
		
		intro();
		
		//Call for print map function to display the map
		
		
		boolean cont = true;
		
		while (cont) {
			
			String move = userMove(p);
			
			currentMoves++;
			
			if (move.equals("U")) {
				
				if (myMap.isThereAPit("U")) {
					navigatePit(myMap, "U", p);
					myMap.printMap();
				} else if (myMap.canIMoveUp()) {
					
					myMap.moveUp();
					myMap.printMap();
				} else {
					System.out.println("Unable to move, system has run into wall, pick new direction");
					myMap.printMap();
				}
				
				
			} else if (move.equals("D")) {
				
				if (myMap.isThereAPit("D")) {
					navigatePit(myMap, "D", p);
					myMap.printMap();
				} else if (myMap.canIMoveDown()) {
					myMap.moveDown();
					myMap.printMap();
				} else {
					System.out.println("Unable to move, system has run into wall, pick new direction");
					myMap.printMap();
				}
				
			} else if (move.equals("L")) {
				
				if (myMap.isThereAPit("L")) {
					navigatePit(myMap, "L", p);
					myMap.printMap();
				} else if (myMap.canIMoveLeft()) {
					myMap.moveLeft();
					myMap.printMap();
				} else {
					System.out.println("Unable to move, system has run into wall, pick new direction");
					myMap.printMap();
				}
				
			} else if (move.equals("R")) {
				
				if (myMap.isThereAPit("R")) {
					navigatePit(myMap, "R", p);
					myMap.printMap();
				} else if (myMap.canIMoveRight()) {
					myMap.moveRight();
					myMap.printMap();
				} else {
					System.out.println("Unable to move, system has run into wall, pick new direction");
					myMap.printMap();
				}
				
			}
			
			if (currentMoves == 50) {
				System.out.println("Warning, 50 moves remaining.");
			} else if (currentMoves == 75) {
				System.out.println("Warning, 15 moves remaining.");
			} else if (currentMoves == 90) {
				System.out.println("Warning, 10 moves remaining.");
			} else if (currentMoves == 100) {
				System.out.println("0 moves remaining.");
			} 
			
			if (myMap.didIWin()) {
				cont = false;
				System.out.println("Congratulations! You completed the game.");
			} else if (currentMoves == 100) {
				cont = false;
				System.out.println("You ran out of moves and lost the game.");
			}
		} 
		
		
		
	}

	//Introduce users to maze
	
	public static void intro() {
		
		System.out.println("Hello, welcome to Maze Runner.");
		
	}
	
	public static String userMove(Scanner p) {
		
		//get user input for desired movement direction
		System.out.println("Which direction would you like to move? (U,D,L,R)");
		String input = p.next();
		
		
		//check that user is using correct input
		while (!(input.equals("U") || input.equals("D") || input.equals("L") || input.equals("R"))) {
			System.out.println("Please use U,D,L or R");
			input = p.next();
		}
		
		return input;
		
	}
	
	public static void navigatePit(Maze m, String dir, Scanner p) {
		System.out.println("There is a pit :O  Would you like to jump? (y,n) ");
		String input = p.next();
		
		if (input.substring(0,1).equals("y")) {
			System.out.println("You jumped. \\(O.o)/ ");
			m.curveJump(dir);
		} else {
			System.out.println("You didn't jump");
		}
	}
	
	
}

