package shell;


class CI {
	private String prompt;
	

String getPrompt() {
		return prompt;
	}

	void setPrompt(String prompt) {
		this.prompt = prompt;
	}


	//	Prompt method interprets command that starts with "prompt" and return adequate prompt value.
	String prompt(String commandLine)
	{
		if (commandLine.substring(7).equals("$cwd")) {
			prompt = ("[MyShell] "+ System.getProperty("user.dir") + ">");
			return prompt;
		}
		else if (commandLine.substring(7).equals("reset")) {
			prompt = "[MyShell] $>";
			return prompt;
		}
		else {
			prompt = ("[MyShell] " + commandLine.substring(7) +">");
			return prompt;
		}
	}
	

	//	Method that shows content of actual folder.
	void dir() {
		System.out.println("Zawartosc folderu: FILE|DIR");
	
	
	}
	
	
}
