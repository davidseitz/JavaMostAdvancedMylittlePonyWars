package view;

import java.util.ArrayList;

import Logic.Model;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class BattleField extends GridPane{
	private final Tile[][] tiles;
	private Model model;
	
	public BattleField() {
		model = Model.getInstance();
		/**
		 * 
		 * @TODO Place Units while Map generation and dynamic map generation
		 * 
		 */
		this.setHgap(-1);
		this.setVgap(-1);
		final int height = model.getHeight();
		final int width = model.getWidth();
		Tile tile;
		tiles = new Tile[width][height];
		int offset = 0;
		
		// Size of Tiles
		final int scale = 50;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				ArrayList<String[]> map = model.getMap();
				String[] tileValue = map.get(j+offset);
				
				if (tileValue[0].equals("PL")) {
					tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource("groundTiles/PlainTile.png").toExternalForm(), scale, scale, false, false));
				}
				else if(tileValue[0].equals("MO")) {
					tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource("groundTiles/MountainTile.png").toExternalForm(), scale, scale, false, false));
				}else if(tileValue[0].equals("WO")) {
					tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource("groundTiles/WoodsTile.png").toExternalForm(), scale, scale, false, false));
				}else if(tileValue[0].equals("WT")) {
					tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource("groundTiles/WaterTile.png").toExternalForm(), scale, scale, false, false));
				}else if(tileValue[0].equals("LD")) {
					tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource("groundTiles/StreetLeftDownTile.png").toExternalForm(), scale, scale, false, false));
				}else if(tileValue[0].equals("LR")) {
					tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource("groundTiles/StreetLeftRightTile.png").toExternalForm(), scale, scale, false, false));
				}else if(tileValue[0].equals("LU")) {
					tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource("groundTiles/StreetLeftUpTile.png").toExternalForm(), scale, scale, false, false));
				}else if(tileValue[0].equals("RD")) {
					tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource("groundTiles/StreetRightDownTile.png").toExternalForm(), scale, scale, false, false));
				}else if(tileValue[0].equals("RU")) {
					tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource("groundTiles/StreetRightUpTile.png").toExternalForm(), scale, scale, false, false));
				}else if(tileValue[0].equals("UD")) {
					tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource("groundTiles/StreetUpDownTile.png").toExternalForm(), scale, scale, false, false));
				}
				else {
					tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource("groundTiles/PlainTile.png").toExternalForm(), scale, scale, false, false));
				}
				
		        add(tile, j, i);
		        tiles[j][i] = tile;
			}
			offset += width;
		}
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
}
