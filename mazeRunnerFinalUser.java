import java.util.Scanner;

public class mazeRunnerFinalUser {

    //Scan for user input
    static Scanner userInput = new Scanner(System.in);	
    public static void main(String[] args) {
        //all is being run on mazeRunnerFinal
    }

    public static String userMoveSelection(){
        return userMove(userInput);
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
}
