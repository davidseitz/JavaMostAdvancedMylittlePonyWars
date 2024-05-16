package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
		ArrayList<String[]> map = new ArrayList<>();
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			if(line.indexOf(':') != -1) {
				line = line.replaceFirst(";", "");
				String[] mapSize = line.split(":");
				model.setWidth(Integer.parseInt(mapSize[0]));
				model.setHeight(Integer.parseInt(mapSize[1]));
			}else {
				String[] tmpMap = line.split(";");
				for (int i=0;i<tmpMap.length;i++) {
					map.add(tmpMap[i].split(","));
				}
			}
			
		}
		model.setMap(map);
	}
}
