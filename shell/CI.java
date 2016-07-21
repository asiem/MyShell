package shell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//	Commands Interpreter
class CI {

	String command;
	String commandParameter;
	String path;
	Prompt prompt;
	ArrayList<Command> commandList = new ArrayList<>();

CI() {
	path = System.getProperty("user.dir");
	prompt = new Prompt();
	
//	Creating list of commands:
		commandList.add(new Command(0,"prompt"));
		commandList.add(new Command(1,"dir"));
		commandList.add(new Command(2,"tree"));
		commandList.add(new Command(3,"cd.."));
		commandList.add(new Command(4,"cd"));
		commandList.add(new Command(5,"exit"));
	
}
	
//	Method reads command line that was typed, splits it and saves it as "command" (first word) and "commandParameter" (rest of the sentence). Method return true if exit was typed.
void readCommand() {
	Scanner input = new Scanner(System.in);
	this.command = input.next();
	String param = input.nextLine();
	if (param.equals("")||param.equals(" ")) {
		this.commandParameter = null;
	}
	else this.commandParameter = param.substring(1);
	
}

//	Method interprets typed command line, choose adequate method that is used. Method returns boolean value: 'true' if command close MyShell application or false in other cases.
boolean commandInterpreter() throws IOException	{
	int commandCase = -1;
	for (int i=0; i<commandList.size() ;i++) {
		if (command.equals(commandList.get(i).command)) {
			commandCase = i;
			break;
		}
	}
	if (commandCase > -1) {
		switch (commandCase) {
		case 0: {
			prompt.setPrompt(commandParameter, path);
			return false;
		}
		case 1: {
			dir(path);
			return false;
		}
		case 2: {
			tree(path,"");
			return false;
		}
		case 3: {
			cdUp(path);
			return false;
		}
		case 4: {
			cdDown(commandParameter, path);
			return false;
		}
		case 5: {

			return true;
		}
		
		
		}
	}
	else System.out.println(command + " : unknown command");
	return false;
}



//	Case 0: PROMPT. Method run Prompt class that choose proper action adn store prompt information.
private void prompt(String commandParameter,String path) {
	prompt.setPrompt(commandParameter, path);
}

//	Case 1: DIR. Method that shows content of actual folder. All files get prefix FILE and Directories get DIR.
private void dir(String path) {
	File dirName = new File(path);
	File[] dirContent = dirName.listFiles();
	System.out.println(path);
	for (File test : dirContent) 
	{
		
		if (test.isFile())
			System.out.println("FILE    " + test.getName());
		else if (test.isDirectory())
			System.out.println("DIR     " + test.getName());
	}
}

//	Case 2: TREE. Method display folder structure of actual directory. Every level of directory path gets adequate amount of "-" prefix.
private void tree(String path, String pref) throws IOException {
	File dirName = new File(path);
	File[] dirContent = dirName.listFiles();
	for (File test : dirContent)
	{
		String prefix = pref+"-";
		if (test.isDirectory()) 
		{
			System.out.println(prefix + test.getName());
			tree(test.getPath(),prefix);
		}
	}

}	

//	Case 3: CD.. Method uses actual directory path and sets new path to parent directory. If prompt was set using $cwd to 'working directory', then method set new prompt. 
void cdUp(String path) throws IOException {
	String newPath;
	newPath = path.substring(0, path.lastIndexOf("\\"));
	if (newPath.length()==2)
		newPath = newPath+"\\";
	if (Prompt.promptFlag==true)
		Prompt.prompt = ("[MyShell] "+ newPath + ">");
	this.path = newPath;
}

//	Case 4: CD + command. Method uses typed directory, directory path and sets new directory path or null if typed directory doesn't exist. If prompt was set using $cwd to 'working directory', then method set new prompt. 
void cdDown(String dir, String path) throws IOException {
	String newPath=null;
	File dirName = new File(path);
	File[] dirContent = dirName.listFiles();
	if (dir != null) {
		for (File test : dirContent)
		{
			if (test.isDirectory() && dir.equals(test.getName()))
			{
				if (path.length()>3) 
				{
					newPath = path+ "\\"+ dir;
				}
				else newPath = path + dir;
			}
		
		}
	
		if (newPath != null)	{
			if (Prompt.promptFlag==true) {
				Prompt.prompt = ("[MyShell] "+ newPath + ">");
			}
			this.path = newPath;
		}
		else System.out.println("Directory : '" + dir + "' doesn't exist.");
	}
	else System.out.println("No directory typed.");
}


//	Case 5: exit


}