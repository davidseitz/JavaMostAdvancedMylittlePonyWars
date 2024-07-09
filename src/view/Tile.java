package view;


import Logic.Figures;
import Logic.Model;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Tile extends StackPane{
	
	private final int x;
	private final int y;
	private String type;
	private String classType;
	
	private boolean isSelected;
	private Figures unit;
	private Model model;
	
	public Tile(int x, int y,String type,Image image) {
		this.model = Model.getInstance();
		this.x = x;
	    this.y = y;
	    this.classType = evalClassType(type);
	    this.type = type;
	    this.unit = null;
	    
	    ImageView vt = new ImageView(image);
	    getChildren().add(vt);
	}
	
	private String evalClassType(String type) {
		String[] types = model.getClassTypes();
		String evaledClassType;
		String mountain = types[0];
		String plain = types[1];
		String streets = types[2];
		String rivers = types[3];
		String woods = types[4];
		
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
	
	public Figures getUnit() {
		return this.unit;
	}
	
	public void setNewTile(Image image, String tag) {
		this.setType(tag);
		ImageView vt = new ImageView(image);
		getChildren().set(0,vt);
	}
	
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * Returns the type not the class type of the tile
	 * Use getClassType to get the class type
	 * @return type
	 */
	public String getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setUnitForCreator(Figures unitToSet) {
		this.unit = unitToSet;
	}
	
	public Figures getUnitForCreator() {
		return this.unit;
	}

	public void setUnit(Figures unit) {
		if(this.unit != null) {
			removeUnit();
		}
		int nolife = 1;
	    if(unit.getLifepoints() <= 0) {
	    	nolife = 0;
	    }
		int scale = (model.getScale());
		
		String path = "units/"+ unit.getType().getType() + unit.getFaction() + ".png" ;
	    getChildren().add(new ImageView(new Image(getClass().getClassLoader().getResource(path).toExternalForm(), scale, scale, false, false)));
	    setLivebar(nolife, scale,unit);
	    this.unit = unit;
	}
	
	private void setLivebar(int nolife, int scale, Figures unit) {
		//Live bar
	    int unitlive = unit.getLifepoints();
	    scale -= 10;
	    Line redlive = new Line();
		redlive.setStartX(0.0); 
		redlive.setEndX(scale);
		redlive.setStartY(0.0); 
		redlive.setEndY(0.0);
		if(this.getY() == model.getHeight()-1) {
			redlive.setScaleY(3.0 + ((scale/10)-4));
		}else {
			redlive.setScaleY(7.0 + ((scale/10)-4));
		}
		redlive.setStroke(Color.RED);
		StackPane.setAlignment(redlive, Pos.BOTTOM_CENTER);

	    getChildren().add(redlive);
	    double newscale = scale * ((double)unitlive/100);
	    
	    Line greenlive = new Line();
	    if (nolife == 0) {
	    	greenlive.setStartX(0.0); 
	    	greenlive.setEndX(0.0);
	    	greenlive.setStartY(0.0); 
	    	greenlive.setEndY(0.0);
	    }else {
	    	greenlive.setStartX(0.0); 
	    	greenlive.setEndX(newscale);
	    	greenlive.setStartY(0.0); 
	    	greenlive.setEndY(0.0);
	    }
	    double movelivebar = ((-1)*(scale - newscale))/2;
	    greenlive.setTranslateX(movelivebar);
	    greenlive.setTranslateY(0);
	    if(this.getY() == model.getHeight()-1) {
	    	greenlive.setScaleY(3.0 + ((scale/10)-4));
		}else {
			greenlive.setScaleY(7.0 + ((scale/10)-4));
		}
	    greenlive.setStroke(Color.GREEN);
		StackPane.setAlignment(greenlive, Pos.BOTTOM_CENTER);
		
		if (nolife == 1) {
			getChildren().add(greenlive);
		}
		
	}
	
	public void removeUnit() {
		if(getChildren().size() == 4) {
			getChildren().remove(1);
		}
		this.unit = null;
		getChildren().remove(1);
		getChildren().remove(1);
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
