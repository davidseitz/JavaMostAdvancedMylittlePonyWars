package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Logic.Model;

public class BattleFieldLoader {
	
	private Model model;
	
	public void loadField(String level) throws FileNotFoundException {
		model = Model.getInstance();
		
		File file = new File(level);
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
	
	public void reloadMap(Tile[][] tiles) {
		model = Model.getInstance();
		ArrayList<String[]> tmpMap = new ArrayList<>();
		String[] tmpTile;
		for(int j = 0; j < tiles[0].length;j++) {
			for (int i = 0; i < tiles.length;i++) {
				String unit = "NU";
				if(tiles[i][j].getUnit() != null) {
					unit = tiles[i][j].getUnit().getType().getType() +"" + tiles[i][j].getUnit().getFaction();
				}
				tmpTile = new String[] {tiles[i][j].getType(), unit};
				tmpMap.add(tmpTile);
			}
			
		}
		model.setMap(tmpMap);
	}
	
	public void customMap() {
		model = Model.getInstance();
		ArrayList<String[]> map = new ArrayList<>();
		String[] nt = {"PL","NU"};
		for(int i = 0; i < model.getHeight()*model.getWidth(); i++) {
			map.add(nt);
		}
		model.setMap(map);
		model.setScale(40);
	}
}
