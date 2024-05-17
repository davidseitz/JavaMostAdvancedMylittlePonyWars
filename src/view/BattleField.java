package view;

import java.util.ArrayList;

import Logic.Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
				String tilePath = "units/ATST.png";
				
				if (tileValue[0].equals("PL")) {
					tilePath = "groundTiles/PlainTile.png";
				}else if(tileValue[0].equals("MO")) {
					tilePath = "groundTiles/MountainTile.png";
				}else if(tileValue[0].equals("WO")) {
					tilePath = "groundTiles/WoodsTile.png";
				}else if(tileValue[0].equals("WT")) {
					tilePath = "groundTiles/WaterTile.png";
				}else if(tileValue[0].equals("LD")) {
					tilePath = "groundTiles/StreetLeftDownTile.png";
				}else if(tileValue[0].equals("LR")) {
					tilePath = "groundTiles/StreetLeftRightTile.png";
				}else if(tileValue[0].equals("LU")) {
					tilePath = "groundTiles/StreetLeftUpTile.png";
				}else if(tileValue[0].equals("RD")) {
					tilePath = "groundTiles/StreetRightDownTile.png";
				}else if(tileValue[0].equals("RU")) {
					tilePath = "groundTiles/StreetRightUpTile.png";
				}else if(tileValue[0].equals("UD")) {
					tilePath = "groundTiles/StreetUpDownTile.png";
				}
				
				tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource(tilePath).toExternalForm(), scale, scale, false, false));
				if(tileValue[1].equals("NU")) {
					System.out.println("NoUnit");
				}else {
					String unitpath = "units/ATST.png";
					if(tileValue[1].equals("AS")) {
						unitpath = "units/ATST.png";
					}else if (tileValue[1].equals("TF")) {
						unitpath = "units/TieFighter.png";
					}
					ImageView unit = new ImageView(new Image(getClass().getClassLoader().getResource(unitpath).toExternalForm(), scale, scale, false, false));	
					tile.setUnit(unit);
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
