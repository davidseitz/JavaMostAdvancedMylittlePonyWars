package view;

import Logic.Groundfigures;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane{
	
	private final int row;
	private final int column;
	private final String type;
	private boolean isSelected;
	private Groundfigures unit;
	

	public Tile(int row, int column, String type,Image image) {
		this.row = row;
	    this.column = column;
	    this.type = type;
	    this.unit = null;
	    
	    ImageView vt = new ImageView(image);//new Image(getClass().getClassLoader().getResource("rook_white.png").toExternalForm()));
	    
	    getChildren().add(vt);
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

	public int getColumn() {
		return column;
	}

	public Groundfigures getUnit() {
		return unit;
	}

	public void setUnit(Groundfigures unit) {
		this.unit = unit;
	}
	
	
}
