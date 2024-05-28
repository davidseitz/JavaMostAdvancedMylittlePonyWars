package view;

import java.util.ArrayList;

import Logic.Figuretype;
import Logic.Groundfigures;
import Logic.Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class BattleField extends GridPane{
	private final Tile[][] tiles;
	//private final String mode;
	private Model model;
	
	public BattleField() {
		model = Model.getInstance();
		
		this.setHgap(-1);
		this.setVgap(-1);
		final int height = model.getHeight();
		final int width = model.getWidth();
		
		this.tiles = new Tile[width][height];
		
		loadMap(height,width,model.getScale());
	}
	
	public void loadMap(int height, int width,int scale) {
		Tile tile;
		int offset = 0;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				ArrayList<String[]> map = model.getMap();
				String[] tileValue = map.get(j+offset);
				
				String tilePath = "groundTiles/"+tileValue[0]+".png";
				
				tile = new Tile(i, j, tileValue[0], new Image(getClass().getClassLoader().getResource(tilePath).toExternalForm(), scale, scale, false, false));
				
				if(tileValue[1].equals("NU") != true) {
					String unitpath = "units/ATST.png";
					if(tileValue[1].equals("AS")) {
						unitpath = "units/ATST.png";
					}else if (tileValue[1].equals("TF")) {
						unitpath = "units/TieFighter.png";
					}else if (tileValue[1].equals("AR")) {
						unitpath = "units/AntiAirRebells.png";
					}else if (tileValue[1].equals("IA")) {
						unitpath = "units/ImperialArtillery.png";
					}
					ImageView unit = new Groundfigures(j,i,new Image(getClass().getClassLoader().getResource(unitpath).toExternalForm(), scale, scale, false, false), new Figuretype("Test"));
					tile.setUnit(unit);
				}
				
		        add(tile, j, i);
		        tiles[j][i] = tile;
			}
			offset += width;
		}
	}
	
	public void setTile(int x, int y,String tag) {
		tiles[y][x].setNewTile(new Image(getClass().getClassLoader().getResource("groundTiles/"+tag+".png").toExternalForm(), model.getScale(), model.getScale(), false, false), tag);
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
	
}
