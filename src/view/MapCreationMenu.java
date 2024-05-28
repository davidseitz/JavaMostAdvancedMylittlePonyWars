package view;

import Logic.Model;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

public class MapCreationMenu extends GridPane {
	private final Tile[][] tiles;
	private final String[] tags = {"BH","BV","C0","C1","C2","C3","C4","C5","C6","C7","CA","CB","CC","CD"
			,"LD","LR","LU","MO","PL","RD","RH","RU","RV","UD","WO","WT"};
	private Model model;
	
	public MapCreationMenu() {
		this.tiles = new Tile[9][3];
		this.model = Model.getInstance();

		this.setHgap(5);
		this.setVgap(5);
		
		Tile tile;
		int offset = 0;
		int scale = model.getScale();
		
		for(int i = 0; i < 9; i++) {
			for (int j = 0; j < 3; j++) {
				try {
					String tilePath = "groundTiles/"+tags[j+offset]+".png";
					tile = new Tile(i, j, tags[j+offset], new Image(getClass().getClassLoader().getResource(tilePath).toExternalForm(), scale, scale, false, false));	
				}catch(IndexOutOfBoundsException e) {
					continue;
				}
				add(tile, j, i);
		        tiles[i][j] = tile;
			}
			offset += 3;
		}
	}
	
	public Tile[][] getMenu(){
		return tiles;
	}
	
}
