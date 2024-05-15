package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane{
	
	private final int row;
	private final char column;
	private final String type;
	private boolean isSelected;

	public Tile(int row, char column, String type,Image image) {
		this.row = row;
	    this.column = column;
	    this.type = type;
	    
	    ImageView vt = new ImageView(image);//new Image(getClass().getClassLoader().getResource("rook_white.png").toExternalForm()));
	    
	    getChildren().add(vt);
	    
//	    Rectangle backgroundLayer = new Rectangle(50, 50);
//	    backgroundLayer.setFill(color);
//	    getChildren().add(backgroundLayer);
	}
	
	/**
	 * 
	 * @TODO Implement setUnit correctly
	 */
	public void setUnit() {
		Node unit = null;
		if (unit == null) {
		      getChildren().remove(1);
		    } else if (getChildren().size() == 2) {
		      getChildren().set(1, unit);
		    } else {
		      getChildren().add(unit);
		    }
	}

	public int getRow() {
		return row;
	}

	public char getColumn() {
		return column;
	}
}
