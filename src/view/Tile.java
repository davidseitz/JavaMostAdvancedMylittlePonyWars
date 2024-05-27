package view;


import Logic.Groundfigures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

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
	
	public void setUnit(ImageView unit) {
		if (unit == null) {
		      getChildren().remove(1);
		    } else if (getChildren().size() == 2) {
		      getChildren().set(1, unit);
		    } else {
		      getChildren().add(unit);
		    }
	}
	
	public Groundfigures getUnit() {
		return getChildren().size() == 2 ? (Groundfigures) getChildren().get(1) : null;
	}
	
	public String getType() {
		return type;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setUnit(Groundfigures unit) {
		this.unit = unit;
	}
	
	
}
