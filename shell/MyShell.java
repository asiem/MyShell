package shell;

import java.util.Scanner;
import shell.CI;

public class MyShell {

	static String commandLine;
	static String shellPrompt;
	
private MyShell() {
	shellPrompt = "[MyShell] $>";
	System.out.print(shellPrompt);
}

//	This method reads Commands typed by user.
void readCommandLine(){
	commandLine = extracted().nextLine();
}

private Scanner extracted() {
	return new Scanner(System.in);
}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyShell myShell = new MyShell();
		myShell.readCommandLine();
		
//		My Shell body.
		CI cI = new CI();
		while (!MyShell.commandLine.equals("exit")) {
			if (MyShell.commandLine.length()>6 && MyShell.commandLine.substring(0, 7).equals("prompt ")) 
			{
				shellPrompt = cI.prompt(MyShell.commandLine);
			}

			else if (MyShell.commandLine.equals("dir")) 
			{
				cI.dir();
System.out.println("DIRRRRRRR");
			}
			else if (MyShell.commandLine.equals("tree")) 
			{
System.out.println("TREEEEEE");
			}
			else if (MyShell.commandLine.equals("cd..")) 
			{
System.out.println("CD.......");
			}
			else if (MyShell.commandLine.length()>3 && MyShell.commandLine.substring(0, 3).equals("cd ")) 
			{
System.out.println("CD xxxxxxxx");
			}
//			This part covers not known commands and display information : "unknown command"
			else 
			{
				int index = MyShell.commandLine.indexOf(" ");
				if (index > -1) 
				{
					System.out.println(MyShell.commandLine.substring(0, index) + " : unknown command");
				}
				else
				{
					System.out.println(MyShell.commandLine + " : unknown command");
				}
			}

		System.out.print(shellPrompt);
		myShell.readCommandLine();
		}
	}

}
