import java.util.Scanner;

public class mazeRunnerFinal {

    //makes Maze from Maze.java
    public static Maze myMap = new Maze();

    //Scan for user input
    static Scanner userInput = new Scanner(System.in);	

    //Number of moves user has done
    static int currentMoves = 0;

    //While loop that goes on until game ends
    static boolean cont = true;

    //Stores previous move (for computer algorithm)
    static String previousMove;

    //User input of whether they want to do maze themselves or have computer do it
    static String userChoice;

    public static void main(String[] args) {

        System.out.println("Hello, welcome to Maze Runner. Do you want do finish the maze (enter A) or do you want a computer to do so (enter B)?");
        userChoice = userInput.nextLine();

        //User finish maze
        if (userChoice.equals("A")){
            System.out.println("Good luck!");

            while (cont){
                userMovement();
                checkArea();
                currentMoves++;
                checkWinOrLose();
            }

        }

        //Computer finish maze
        else if (userChoice.equals("B")){
            System.out.println("Cool. Sit back and let the computer do the rest :)");
            myMap.moveRight();//moves right at very first move so that it starts on the map. Important bc algorithm relies on 
                                // scanning the area around user, which can't happen if user isn't on the map
            
            while (cont){
                previousMove = move; // stores previousMove as move before move is changed in computerMovement() to the new move user wants to do
                computerMovement();
                checkArea();
                currentMoves++;
                checkWinOrLose();
            }
        }

    }

    //move = "U", "D", "L", or "R" – the next move to do
    public static String move;

    public static void userMovement() {
        move = userMove(userInput);//sets move equal to userInput
    }

    public static void computerMovement(){
        move = "";

        //PRIORITY #1: if there is a dot "." (unexplored space) around the user (X), the user will move to the dot
        if (myMap.computerPreferenceUp()==1){
            move = "U";
        }
        else if (myMap.computerPreferenceDown()==1){
            move = "D";
        }
        else if (myMap.computerPreferenceLeft()==1){
            move = "L";
        }
        else if (myMap.computerPreferenceRight()==1){
            move = "R";
        }

        //PRIORITY #2: if there are no dots around the user (X), the user moves to an asterisk * or hole 0 (free space in maze),
        //AND NOT THE OPPOSITE MOVE OF WHAT IT DID BEFORE. e.g. if I moved right as move #1, I'm not going to move left as move #2. This wastes moves
        if (move.equals("")){//if move is still "", it means the if statements above have not been fulfilled
            if (myMap.computerPreferenceUp()==2 && !previousMove.equals("D")){
                move = "U";
            }
            else if (myMap.computerPreferenceDown()==2 && !previousMove.equals("U")){
                move = "D";
            }
            else if (myMap.computerPreferenceLeft()==2 && !previousMove.equals("R")){
                move = "L";
            }
            else if (myMap.computerPreferenceRight()==2 && !previousMove.equals("L")){
                move = "R";
            }
        }

        //PRIORITY #3: if there are no dots around the user (X), the user moves to an asterisk * or hole 0 (free space in maze),
        //AND DOES THE OPPOSITE MOVE OF WHAT IT DID BEFORE. e.g. if I moved right as move #2, I WILL move left as move #2.
        //Priority #3 only occurs when there are three walls around user and they hit dead end, which means they now HAVE to move back from where they came from
        if (move.equals("")){//if move is still "", it means the if statements above have not been fulfilled
            if (previousMove.equals("R")){
                move = "L";//does move opposite of previousMove
            }
            else if (previousMove.equals("U")){
                move = "D";
            }
            else if (previousMove.equals("D")){
                move = "U";
            }
            else if (previousMove.equals("L")){
                move = "R";
            }
        }
    }


    //Checks around user
    public static void checkArea(){

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
        }
        //Same for left
        else if (move.equals("L") && (myMap.canIMoveLeft())) {
            myMap.moveLeft();
        }
        //If none of the else if statements worked, it means that the user encounters a wall
        else {
            System.out.println("Unable to move, system has run into wall; pick new direction");
        }

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
        if (userChoice.equals("A")){
            System.out.println("There is a pit :O  Would you like to jump? (y,n) ");
            String input = userInput.next();
            
            if (input.substring(0,1).equals("y")) {
                System.out.println("You jumped. \\(O.o)/ ");
                m.curveJump(dir);
            } else {
                System.out.println("You didn't jump");
            }
        }
        else if (userChoice.equals("B")){
            m.curveJump(dir);
        }
    }
    
    //Checks number of moves
    public static void checkWinOrLose(){

        //Reminds user how many moves they have. We changed total number of moves to 150 instead of 100
        if (currentMoves == 50) {
            System.out.println("Warning, 100 moves remaining.");
        } else if (currentMoves == 75) {
            System.out.println("Warning, 75 moves remaining.");
        } else if (currentMoves == 100) {
            System.out.println("Warning, 50 moves remaining.");
        } else if (currentMoves == 125) {
            System.out.println("Warning, 25 moves remaining.");
        } 
        
        //Checks if user won or lost (lost = ran out of moves)
        if (myMap.didIWin()) {
            cont = false;
            System.out.println("Congratulations! You completed the game in " + currentMoves + " moves.");
        } else if (currentMoves == 150) {
            cont = false;
            System.out.println("You ran out of moves and lost the game.");
        }
    }
}
