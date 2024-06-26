package view;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Logic.Figuretype;
import Logic.Figures;
import Logic.Model;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class BattleField extends GridPane{
	private Model model;
	
	public BattleField() throws FileNotFoundException {
		model = Model.getInstance();
		
		this.setHgap(-1);
		this.setVgap(-1);
		final int height = model.getHeight();
		final int width = model.getWidth();
		
		loadMap(height,width,model.getScale());
	}
	
	public void loadMap(int height, int width,int scale) throws FileNotFoundException {
		Tile tile;
		Tile[][] tiles = new Tile[width][height];
		int offset = 0;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				ArrayList<String[]> map = model.getMap();
				String[] tileValue = map.get(j+offset);
				
				String tilePath = "groundTiles/"+tileValue[0]+".png";
				
				tile = new Tile(j, i, tileValue[0], new Image(getClass().getClassLoader().getResource(tilePath).toExternalForm(), scale, scale, false, false));
				
				if(tileValue[1].equals("NU") != true) {
					try {
						int player = 0;
						if(tileValue[1].endsWith("E")) {
							player = 0;
						}else {
							player = 1;
						}
						Figures unit = new Figures(j,i, new Figuretype(tileValue[1]), player);
						tile.setUnit(unit);
					} catch (FileNotFoundException e) {
						System.out.println("Error: Unit not found");
						e.printStackTrace();
						throw e;
					}
				}
				
		        add(tile, j, i);
		        tiles[j][i] = tile;
			}
			offset += width;
		}
		model.setField(tiles);
	}
	
}
