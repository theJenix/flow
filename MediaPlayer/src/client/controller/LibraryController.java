package client.controller;

import client.model.Library;

public class LibraryController {

	private Library model;

	public LibraryController(Library model) {
		this.model = model;
	}

	public void updateLibrary(String actionCommand) {
		if (actionCommand.equals("REFRESH")) { 
			model.refresh(actionCommand);
		}
	}
	

	public Library getModel() {
		return model;
	}

	public void switchview(String actionCommand) {
		model.refresh(actionCommand);
	}

}
