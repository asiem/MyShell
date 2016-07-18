package shell;

import java.io.IOException;
import java.util.Scanner;
import shell.CI;

public class MyShell {

	static String commandLine;
	static String shellPrompt;
	static boolean shellPromptFlag = false;
	
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

	
	public static void main(String[] args) throws IOException {

		MyShell myShell = new MyShell();
		myShell.readCommandLine();
		String path = System.getProperty("user.dir");
		
//		My Shell body.
		CI cI = new CI();
		while (!MyShell.commandLine.equals("exit")) {
//			Set prompt. (prompt ..)
			if (MyShell.commandLine.length()>6 && MyShell.commandLine.substring(0, 7).equals("prompt ")) 
			{
				shellPrompt = cI.prompt(MyShell.commandLine, path);
				MyShell.shellPromptFlag = cI.getPromptFlag();
			}
//			Show folder content. (dir)
			else if (MyShell.commandLine.equals("dir")) 
			{
				cI.dir(path);
			}
//			Show folder structure. (tree)
			else if (MyShell.commandLine.equals("tree")) 
			{
				System.out.println(path.substring(path.lastIndexOf("\\")+1));
				cI.tree(path,"");
			}
//			Change folder up (cd..)
			else if (MyShell.commandLine.equals("cd..")) 
			{
				path = cI.cdUp(path,MyShell.shellPromptFlag);
				if (MyShell.shellPromptFlag == true)
					MyShell.shellPrompt = cI.getPrompt();
			}
//			Change folder to typed directory (cd + TypedDirectory)
			else if (MyShell.commandLine.length()>3 && MyShell.commandLine.substring(0, 3).equals("cd ")) 
			{
				String tmp = cI.cdDown(MyShell.commandLine.substring(3), path, MyShell.shellPromptFlag);
				if (tmp == null)
					System.out.println("Folder does't exist.");
				else path = tmp;
				if (MyShell.shellPromptFlag == true)
					MyShell.shellPrompt = cI.getPrompt();
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
