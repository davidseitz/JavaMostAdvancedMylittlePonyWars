package javaMostAdvancedMyLittlePonyWars;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javaMostAdvancedMyLittlePonyWars.Model;

public class BattleField extends GridPane{
	private final Tile[][] tiles;
	private Model model;
	
	public BattleField() {
		model = Model.getInstance();
		if(model.getLevel()==1) {
			tiles = new Tile[19][10];
			for (int i = 0; i < 19; i++) {
		      for (int j = 0; j < 10; j++) {
		        Color color = i % 2 == 0 ? j % 2 == 0 ? Color.SANDYBROWN : Color.SADDLEBROWN
		            : j % 2 == 0 ? Color.SADDLEBROWN : Color.SANDYBROWN;
		        Tile s = new Tile(j + 1, (char) (i + 65), color);
		        add(s, i, j);
		        tiles[i][j] = s;
		      }
		    }
		}
		else if (model.getLevel()==2) {
			tiles = new Tile[19][17];
			for (int i = 0; i < 19; i++) {
			      for (int j = 0; j < 17; j++) {
			        Color color = i % 2 == 0 ? j % 2 == 0 ? Color.SANDYBROWN : Color.SADDLEBROWN
			            : j % 2 == 0 ? Color.SADDLEBROWN : Color.SANDYBROWN;
			        Tile s = new Tile(j + 1, (char) (i + 65), color);
			        add(s, i, j);
			        tiles[i][j] = s;
			      }
			    }
		}
		else {
			tiles = new Tile[26][14];
			for (int i = 0; i < 26; i++) {
			      for (int j = 0; j < 14; j++) {
			        Color color = i % 2 == 0 ? j % 2 == 0 ? Color.SANDYBROWN : Color.SADDLEBROWN
			            : j % 2 == 0 ? Color.SADDLEBROWN : Color.SANDYBROWN;
			        Tile s = new Tile(j + 1, (char) (i + 65), color);
			        add(s, i, j);
			        tiles[i][j] = s;
			      }
			    }
		}
		// Test Figure
	    //tiles[0][0].setTileImage(new TileImage(new Image(getClass().getResourceAsStream("rook_white.png"))));
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
}
