package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane{
	
	private final int row;
	private final char column;
	private boolean isSelected;

	public Tile(int row, char column, Color color) {
		this.row = row;
	    this.column = column;

	    Rectangle backgroundLayer = new Rectangle(50, 50);
	    backgroundLayer.setFill(color);
	    getChildren().add(backgroundLayer);
	}
	
	public void setTileImage(TileImage tile) {
		if (tile == null) {
		      getChildren().remove(1);
		    } else if (getChildren().size() == 2) {
		      getChildren().set(1, tile);
		    } else {
		      getChildren().add(tile);
		    }
	}

	public int getRow() {
		return row;
	}

	public char getColumn() {
		return column;
	}
}
