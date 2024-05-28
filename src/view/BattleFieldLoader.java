package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Logic.Model;

public class BattleFieldLoader {
	
	private Model model;
	private File file;
	
	public void loadField(int level) throws FileNotFoundException {
		model = Model.getInstance();
		
		if(level == 1) {
			file = new File("resources/maps/dLittleIsland.map");
		}else if(level == 2) {
			file = new File("resources/maps/dEonSprings.map");
		}else if(level == 3) {
			file = new File("resources/maps/dPistonDam.map");
		}else {
			file = new File("resources/maps/creator.map");
		}
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
		s.close();
	}
}
