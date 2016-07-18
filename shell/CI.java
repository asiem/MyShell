package shell;

import java.io.File;
import java.io.IOException;

//	Command Interpreter class.
class CI {
	private String prompt;
	private Boolean promptFlag = false;
	

String getPrompt() {
		return prompt;
	}

	void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
Boolean getPromptFlag() {
		return promptFlag;
	}


//	Prompt method interprets command that starts with "prompt" and return adequate prompt value.
	String prompt(String commandLine, String path)
	{
		if (commandLine.substring(7).equals("$cwd")) {
			prompt = ("[MyShell] "+ path + ">");
			this.promptFlag = true;
			return prompt;
		}
		else if (commandLine.substring(7).equals("reset")) {
			prompt = "[MyShell] $>";
			this.promptFlag = false;
			return prompt;
		}
		else {
			prompt = ("[MyShell] " + commandLine.substring(7) +">");
			this.promptFlag = false;
			return prompt;
		}
	}
	

//	Method that shows content of actual folder. All files got prefix FILE and Directories have DIR.
	void dir(String path) throws IOException {
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
//	Method display folder structure of actual directory. Every level of directory path gets adequate amount of "-" prefix.
	void tree(String path, String pref) throws IOException {
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
//	Method use actual directory path and return new path to parent directory. If prompt was set using $cwd to 'working directory', then method set new temporary prompt.  Use getPrompt() to get updated prompt.
	String cdUp(String path,Boolean promptFlag) throws IOException {
		String newPath;
		newPath = path.substring(0, path.lastIndexOf("\\"));
		if (newPath.length()==2)
			newPath = newPath+"\\";
		if (promptFlag=true)
			prompt = ("[MyShell] "+ newPath + ">");
		return newPath;
	}
//	Method use typed directory, directory path, promptFlag and returns new directory path or null if typed directory doesn't exist. If prompt was set using $cwd to 'working directory', then method set new temporary prompt. Use getPrompt() to get updated prompt.
	String cdDown(String dir, String path, Boolean promptFlag) throws IOException {
		String newPath=null;
		File dirName = new File(path);
		File[] dirContent = dirName.listFiles();
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
		if (promptFlag=true)
			prompt = ("[MyShell] "+ newPath + ">");
		return newPath;
	}
	
	
}
