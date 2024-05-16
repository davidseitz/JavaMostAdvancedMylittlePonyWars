package view;

import Logic.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

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

		final int height = model.getHeight();
		final int width = model.getWidth();
		
		
		tiles = new Tile[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Tile s = new Tile(j, i, "Grass", new Image(getClass().getClassLoader().getResource("rook_white.png").toExternalForm(), 50, 50, false, false));

		        add(s, i, j);
		        tiles[i][j] = s;
			}
		}
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
}
