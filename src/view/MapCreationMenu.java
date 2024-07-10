package view;

import java.io.File;
import java.util.Scanner;

import Logic.Figures;
import Logic.Figuretype;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class MapCreationMenu extends GridPane {
	private Tile[][] tiles;
	private Tile[][] units;
	private String[] tiletags;
	private String[] unittags;
	public MapCreationMenu() {
		try {
		String filename = "resources/modification/Map_Creator.txt";
		File file = new File(filename);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.isEmpty()) {
				continue;
			}
			if(line.startsWith("TILE_TAGS")) {
				int start_name = line.indexOf("\"");
				int end_name = line.indexOf("\"", start_name+1);
				tiletags =	line.substring(start_name+1, end_name).split(",").clone();
				
			}else if(line.startsWith("UNIT_TAGS")) {
				int start_name = line.indexOf("\"");
				int end_name = line.indexOf("\"", start_name+1);
				unittags =	line.substring(start_name+1, end_name).split(",").clone();
			}
		}
		this.tiles = new Tile[(tiletags.length/3)+1][3];
		this.units = new Tile[(unittags.length/3)+1][3];

		this.setHgap(5);
		this.setVgap(5);
		
		Tile tile;
		int offset = 0;
		
		for(int i = 0; i < this.tiles.length; i++) {
			for (int j = 0; j < 3; j++) {
				try {
					String tilePath = "groundTiles/"+tiletags[j+offset]+".png";
					tile = new Tile(i, j, tiletags[j+offset], new Image(getClass().getClassLoader().getResource(tilePath).toExternalForm(), 45, 45, false, false));	
				}catch(IndexOutOfBoundsException e) {
					continue;
				}
				add(tile, j, i);
		        tiles[i][j] = tile;
			}
			offset += 3;
		}
		offset = 0;
		String unitPath = "units/TE.png";
		tile = null;
		for(int i = 0; i < this.units.length; i++) {
			for(int j = 0; j<3; j++) {
				try {
					if(i == 0 && j == 0) {
						unitPath = "units/deleteUnitImage2.png";
						tile = new Tile(i, j, "RUT", new Image(getClass().getClassLoader().getResource(unitPath).toExternalForm(), 45, 45, false, false));
						offset -= 1;
					}else {
						unitPath = "units/"+unittags[j+offset]+".png";
						tile = new Tile(i, j, "NAT", new Image(getClass().getClassLoader().getResource(unitPath).toExternalForm(), 45, 45, false, false));	
						int player = 0;
						if(unittags[j+offset].contains("R")) {
							player = 1;
						}
						tile.setUnitForCreator(new Figures(i, j, new Figuretype(unittags[j+offset]), player));
					}
				}catch(IndexOutOfBoundsException | NullPointerException e ) {
					continue;
				}
				add(tile, j, i + this.tiles.length);
				units[i][j] = tile;
			}
			offset += 3;
			scanner.close();
		}
		}catch (Exception e) {
			System.out.println("ERROR loading/finding the Map_Creator.txt");
		}
	}
	
	public Tile[][] getMenu(){
		return tiles;
	}
	
	public Tile[][] getUnits(){
		return units;
	}
	
}
