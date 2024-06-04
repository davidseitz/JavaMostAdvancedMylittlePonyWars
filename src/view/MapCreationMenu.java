package view;

import Logic.Model;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

public class MapCreationMenu extends GridPane {
	private final Tile[][] tiles;
	private final String[] tags = {"WT","C0","C1","C2","C3","C4","C5","C6","C7","CA","CB","CC","CD","BH","BV",
			"RH","RV","PL","MO","WO","LD","LR","LU","RD","RU","UD",   "PL","PL","PL","PL","PL","PL","PL"};
	private Model model;
	
	public MapCreationMenu() {
		this.tiles = new Tile[11][3];
		this.model = Model.getInstance();

		this.setHgap(5);
		this.setVgap(5);
		
		Tile tile;
		int offset = 0;
		int scale = model.getScale();
		
		for(int i = 0; i < 11; i++) {
			for (int j = 0; j < 3; j++) {
				try {
					String tilePath = "groundTiles/"+tags[j+offset]+".png";
					tile = new Tile(i, j, tags[j+offset], new Image(getClass().getClassLoader().getResource(tilePath).toExternalForm(), 45, 45, false, false));	
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
