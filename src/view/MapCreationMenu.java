package view;

import Logic.Figures;
import Logic.Figuretype;
import Logic.Model;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class MapCreationMenu extends GridPane {
	private final Tile[][] tiles;
	private final Tile[][] units;
	private final String[] tiletags = {"WT","C0","C1","C2","C3","C4","C5","C6","C7","CA","CB","CC","CD","BH","BV",
			"B0","B1","B2","RH","RV","PL","MO","WO","LD","LR","LU","RD","RU","UD","TU","TD",  "PL","PL"};
	private final String[] unittags = {"IE","AE","KE","BE","FE","ME","CE","TE","IR","AR","KR","BR","FR","MR","CR","TR"};
	private Model model;
	
	public MapCreationMenu() throws Exception {
		this.tiles = new Tile[11][3];
		this.units = new Tile[7][3];
		this.model = Model.getInstance();

		this.setHgap(5);
		this.setVgap(5);
		
		Tile tile;
		int offset = 0;
		int scale = model.getScale();
		
		for(int i = 0; i < 11; i++) {
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
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j<3; j++) {
				try {
					if(i == 0 && j == 0) {
						unitPath = "units/StormtrooperSquad.png";
						tile = new Tile(i, j, "RUT", new Image(getClass().getClassLoader().getResource(unitPath).toExternalForm(), 45, 45, false, false));
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
					if(e.getClass().getCanonicalName().equals("IndexOutOfBoundsException")) {
						continue;
					}else {
						unitPath = "units/TE.png";
						tile = new Tile(i, j, "NAT", new Image(getClass().getClassLoader().getResource(unitPath).toExternalForm(), 45, 45, false, false));	
						tile.setUnitForCreator(new Figures(i, j, new Figuretype("TE"), 0));
					}
				}
				add(tile, j, i + 11);
				units[i][j] = tile;
			}
			offset += 3;
		}
	}
	
	public Tile[][] getMenu(){
		return tiles;
	}
	
	public Tile[][] getUnits(){
		return units;
	}
	
}
