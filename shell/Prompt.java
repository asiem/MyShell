package shell;

class Prompt {

	static String prompt;
	static boolean promptFlag;
	
//	Constructor sets variables to default values.
	Prompt() {
		prompt = ("[MyShell] $>");
		promptFlag = false;
	}
	
//	Method returns actual prompt status: true if prompt is set to show directory or false if prompt is set by user.
	boolean getPromptFlag() {
		return promptFlag;
	}
	
//	Method displays prompt.
	void displayPrompt() {
		System.out.print(prompt);
	}
	
//	Method interprets commands that starts with prompt and sets new prompt value. Input data is command parameter (parameter placed after "prompt" command) and path to actual directory.
	void setPrompt(String commandParameter, String path) {
		if (commandParameter == null) {
			System.out.println("prompt : no 'prompt' parameter found");
		}
		else if (commandParameter.equals("$cwd")) {
			prompt =  ("[MyShell] "+ path + ">");
			promptFlag = true;
		}
		else if (commandParameter.equals("reset")) {
			prompt = ("[MyShell] $>");
			promptFlag = false;
		}
		else {
			prompt = ("[MyShell] " + commandParameter +">");
			promptFlag = false;
		}
	}
	
}
