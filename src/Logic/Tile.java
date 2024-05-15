package Logic;

public class Tile {
	private String type;
	private Figure figure;
	public Tile(String type, Figure figure) {
		super();
		this.type = type;
		this.figure = figure;
	}
	@Override
	public String toString() {
		return "Tile [type=" + type + ", figure=" + figure + "]";
	}
	public Figure getFigure() {
		return figure;
	}
	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	public String getType() {
		return type;
	}
	
	

}
