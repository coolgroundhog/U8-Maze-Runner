import java.util.Scanner;

public class mazeRunnerFinal {

    //makes Maze from Maze.java
    public static Maze myMap = new Maze();

    //Scan for user input
    static Scanner userInput = new Scanner(System.in);	

    //Number of moves user has done
    static int currentMoves = 0;

    //While loop that goes on forever
    static boolean cont = true;

    public static void main(String[] args) {

        System.out.println("Hello, welcome to Maze Runner. Do you want do finish the maze (enter A) or do you want a computer to do so (enter B)?");
        String userChoice = userInput.nextLine();
        if (userChoice.equals("A")){
            System.out.println("Good luck!");

            while (cont){
                userMovement();
                checkArea();
                System.out.println("1234");
                currentMoves++;
            }

            
        }
        else if (userChoice.equals("B")){
            System.out.println("Cool. Sit back and let the computer do the rest :)");
            while (cont){
                //computerMovement();
            }
        }

    }

    /*
    public static void userMovement(){
        String move = userMove(userInput);
    
    */

    public static String move = "";

    public static void userMovement() {

        move = userMove(userInput);
        //return userMove(userInput);
    }

   




    public static void checkArea(){
        //Prints map regardless of wall/hole/free space
        //myMap.printMap();

        //Checks if there's a pit in direction user wants to head
        if (myMap.isThereAPit(move)) {
            navigatePit(myMap, move);//if there's a pit, go to navigatePit method to ask user if they want to jump

        }

        //Checks if user wanted to move up and if they can actually move up (aka if myMap.canIMoveUp = true)
        else if (move.equals("U") && (myMap.canIMoveUp())) {
            myMap.moveUp();
        }
        //Same for down direction
        else if (move.equals("D") && (myMap.canIMoveDown())) {
            myMap.moveDown();
        }
        //Same for right
        else if (move.equals("R") && (myMap.canIMoveRight())) {
            myMap.moveRight();
            System.out.println("Going right");
        }
        //Same for left
        else if (move.equals("L") && (myMap.canIMoveLeft())) {
            myMap.moveLeft();
        }
        //If none of the else if statements worked, it means that the user encounters a wall
        else {
            System.out.println("Unable to move, system has run into wall; pick new direction");
        }
        
        //Prints map regardless of wall/hole/free space

    }
    
    

    //Runs every time user makes a move
    public static String userMove(Scanner p) {

		//Prompts user input for desired movement direction
		System.out.println("Which direction would you like to move? (U,D,L,R)");
		String input = p.next();
		
		
		//check that user is using correct input
		while (!(input.equals("U") || input.equals("D") || input.equals("L") || input.equals("R"))) {
			System.out.println("Please use U,D,L or R");
			input = p.next();
		}
		
		return input;
		
    }
    
    
    //Runs when user runs encounters a pit
    public static void navigatePit(Maze m, String dir) {
		System.out.println("There is a pit :O  Would you like to jump? (y,n) ");
		String input = userInput.next();
		
		if (input.substring(0,1).equals("y")) {
			System.out.println("You jumped. \\(O.o)/ ");
			m.curveJump(dir);
		} else {
			System.out.println("You didn't jump");
		}
    }
    
    //Checks number of moves
    public static void checkWinOrLose(){

        //Reminds user how many moves they have
        if (currentMoves == 50) {
            System.out.println("Warning, 50 moves remaining.");
        } else if (currentMoves == 75) {
            System.out.println("Warning, 15 moves remaining.");
        } else if (currentMoves == 90) {
            System.out.println("Warning, 10 moves remaining.");
        } else if (currentMoves == 100) {
            System.out.println("0 moves remaining.");
        } 
        

        //Checks if user won or lost (lost = ran out of moves)
        if (myMap.didIWin()) {
            cont = false;
            System.out.println("Congratulations! You completed the game.");
        } else if (currentMoves == 100) {
            cont = false;
            System.out.println("You ran out of moves and lost the game.");
        }
    }
}
