package Logic;

import java.util.ArrayList;

import view.Tile;

public class Model {
	
	private int level;
	private int width;
	private int height;
	private int scale;
	private ArrayList<String[]> map;
	private Tile[][] field;
	private static Model instance;
	
	public static Model getInstance() {
	    if (instance == null) {
	      instance = new Model();
	    }
	    return instance;
	  }

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	public void printPossibleMoves(int x, int y, Tile tile)
	{
		System.out.println("Tile with Type: "+ tile.getType() + " at position: (" + x + " " + y + ") clicked ");
		if (tile.getUnit() == null) {
			System.out.println("No Unit on this Tile");

		}else {
			System.out.println("Possible Moves: "+ tile.getUnit().getMovement_range() + " fields");
		}
	}

	public ArrayList<String[]> getMap() {
		return map;
	}

	public void setMap(ArrayList<String[]> map) {
		this.map = map;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public Tile[][] getField() {
		return field;
	}

	public void setField(Tile[][] field) {
		this.field = field;
	}

	public boolean allowedTerrain(Tile tile) {
		String tileType = tile.getType();
		if (tileType.equals("WT")) {
            return false;
		}else if(tileType.equals("C0")) {
            return false;
		}else if(tileType.equals("C1")) {
            return false;
		}else if(tileType.equals("C2")) {
            return false;
		}else if(tileType.equals("C3")) {
            return false;
		}else if(tileType.equals("C4")) {
            return false;
		}else if(tileType.equals("C5")) {
            return false;
		}else if(tileType.equals("C6")) {
            return false;
		}else if(tileType.equals("C7")) {
            return false;
		}else if(tileType.equals("CA")) {
            return false;
		}else if(tileType.equals("CB")) {
            return false;
		}else if(tileType.equals("CC")) {
            return false;
		}else if(tileType.equals("CD")) {
            return false;
		}else if(tileType.equals("RV")) {
            return false;
		}else if(tileType.equals("RH")) {
            return false;
		}
		return true;
	}
	
	public boolean move(Tile tile /*New*/, Tile moveUnit) {
		
		Groundfigures unit = moveUnit.getUnit();
		Groundfigures target = tile.getUnit();
		if (unit != null && target == null) {
			if (this.findPath(moveUnit, tile, unit.getMovement_range(), unit.getUnitStats())) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean findPath(Tile start, Tile end, int range, Unit_Loader unit_stats) {
		if (start.equals(end)) {
			return true;
		}
		if (range <0) {
			return false;
		}
		if (findPath(start.getNeighbourNorth(), end, range-unit_stats.getMovementCost(start.getNeighbourNorth().getType()),unit_stats)) {
			return true;
		}
		if (findPath(start.getNeighbourEast(), end, range-unit_stats.getMovementCost(start.getNeighbourEast().getType()),unit_stats)) {
			return true;
		}
		if (findPath(start.getNeighbourSouth(), end, range-unit_stats.getMovementCost(start.getNeighbourSouth().getType()),unit_stats)) {
			return true;
		}
		if (findPath(start.getNeighbourWest(), end, range-unit_stats.getMovementCost(start.getNeighbourWest().getType()),unit_stats)) {
			return true;
		}
		return false;
	}

	public Tile getTile(int x, int y) {
		return this.field[x][y];
	}
}
