package shell;

import java.io.IOException;

public class MyShell {

	
	public static void main(String[] args) throws IOException {
	
//	Shell initialization

		CI cI = new CI();
		Prompt prompt = new Prompt();
		
//	Shell body
		prompt.displayPrompt();
		cI.readCommand();
		boolean exit = false;
		
		while (!exit) {
			exit = cI.commandInterpreter();
			if (!exit) {
				prompt.displayPrompt();
				cI.readCommand();
			}
			
		}
	
//	End program
	System.out.println("Bye!");
	
	}	
	
	
}