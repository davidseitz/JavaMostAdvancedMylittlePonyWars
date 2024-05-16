package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Logic.Model;

public class BattleFieldLoader {
	
	public static void main(String[] args) throws FileNotFoundException {
		BattleFieldLoader l = new BattleFieldLoader();
		l.loadField();
	}
	
	private Model model;
	
	public void loadField() throws FileNotFoundException {
		model = Model.getInstance();
		
		File file = new File("resources/maps/test.map");
		Scanner s = new Scanner(file);
		
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			System.out.println(line.indexOf(':'));
			if(line.indexOf(':') != -1) {
				line = line.replaceFirst(";", "");
				String[] mapSize = line.split(":");
				System.out.println("Loop");
				model.setHeight(Integer.parseInt(mapSize[0]));
				model.setWidth(Integer.parseInt(mapSize[1]));
				System.out.println(model.getHeight() + " " + model.getWidth() + " ");
			}
		}
	}
}
