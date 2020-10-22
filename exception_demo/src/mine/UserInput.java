package mine;
import java.util.Scanner;

public class UserInput {
	
	
	
	public static void main(String[] args) {
		System.out.println("Show me what you got ");
		Scanner scan = new Scanner(System.in);
		String userInput = scan.nextLine();
		scan.close();
		MemeChecker meme = new MemeChecker();
		
		try {
			if (meme.isMeme(userInput)) {
				System.out.println(userInput+" is cringe");
			}
			} catch(ExceptionCreation e) {
				System.out.println(e.getMessage());
			}
		}

	}


