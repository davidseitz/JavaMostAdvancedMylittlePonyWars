package view;


import Logic.Groundfigures;
import Logic.Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane{
	
	private final int x;
	private final int y;
	private String type;
	private String classType;
	
	private boolean isSelected;
	private Groundfigures unit;

	public Tile(int x, int y,String type,Image image) {
		this.x = x;
	    this.y = y;
	    this.classType = evalClassType(type);
	    this.type = type;
	    this.unit = null;
	    
	    ImageView vt = new ImageView(image);
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
	
	private String evalClassType(String type) {
		String evaledClassType;
		String mountain = "MO";
		String plain = "PL";
		String streets = "BV BH LD LR LU RD RU UD TU TD B0 B1 B2";
		String rivers = "RV RH";
		String woods = "WO";
		
		if(type.equals(mountain)){
			evaledClassType = GeneralTypes.MO.name();
		}else if(type.equals(plain)) {
			evaledClassType = GeneralTypes.PL.name();
		}else if (type.equals(woods)) {
			evaledClassType = GeneralTypes.WO.name();
		}else if (streets.contains(type)) {
			evaledClassType = GeneralTypes.ST.name();
		}else if (rivers.contains(type)) {
			evaledClassType = GeneralTypes.RI.name();
		}else {
			evaledClassType = GeneralTypes.WT.name();
		}
		return evaledClassType;
	}
	
	public String getClassType() {
		return classType;
	}

	public ImageView getBackgroundLayer() {
		return (ImageView) getChildren().get(0);
	}
	
	public Groundfigures getUnit() {
		return getChildren().size() == 2 ? (Groundfigures) getChildren().get(1) : null;
	}
	
	public void setNewTile(Image image, String tag) {
		this.setType(tag);
		ImageView vt = new ImageView(image);
		//getChildren().remove(0);
		getChildren().set(0,vt);
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setUnit(Groundfigures unit) {   
	    getChildren().add(unit);
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Tile [row=" + x + ", column=" + y + ", type=" + type + ", isSelected=" + isSelected + ", unit="
				+ unit + "]";
	}

	public boolean equals(Tile tile) {
		return this.x == tile.getX() && this.y == tile.getY();
	}


}
