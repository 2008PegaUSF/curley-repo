package mainapp;

import view.MenuDriver;


public class App {

	//this is our main entry point, and all we do here is simply kick off into the menu system.
	//the rest is handled by other classes.
	
	public static void main(String[] args) {
		MenuDriver md = new MenuDriver();
		md.showMainMenu();

	}


}
