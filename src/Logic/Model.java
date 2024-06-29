package Logic;

import java.util.ArrayList;

import view.Tile;

/**
 * Model class that holds the game state
 */
public class Model {
	
	private int level;
	private int width;
	private int height;
	private int scale;
	private ArrayList<String[]> map;
	private Tile[][] field;
	private static Model instance;
	private int round = -1; // Startbutton must be pressed before the game starts
	private Model() {

	}
	
	public static Model getInstance() {
	    if (instance == null) {
	      instance = new Model();
	    }
	    return instance;
	}
	
	public int getRound() {
		return round;
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
	
	public char roundToFaction() {
		if (this.round % 2 == 0) {
            return 'E';
            } else {
            	return 'R';
            }
	}

	/**
	 * Checks if a player has won
	 * 
	 * @return 1 if Rebels have won
	 * @return 2 if Empire has won
	 * @return 0 if no player has won yet
	 */
	private int checkVictory() {
		int faction1 = 0;
		int faction2 = 0;
		for (Tile[] allTiles : this.getField()) {
			for (Tile field : allTiles) {
				if (field.getUnit() != null) {
					if (field.getUnit().getPlayer() == 0) {
						faction1++;
					} else {
						faction2++;
					}
				}
			}
		}
		if(faction1 == 0) {
            System.out.println("Rebels win");
            return 1;
		} else if (faction2 == 0) {
			System.out.println("Empire wins");
			return 2;
		}
		return 0;
	}
	
	public void endRound() {
		checkVictory();
        this.round++;
        for (Tile[] allTiles : this.getField()) {
            for (Tile field : allTiles) {
                if (field.getUnit() != null) {
                	if (field.getUnit().getPlayer() == this.round % 2){
                		field.getUnit().setHasMoved(false);
                		field.getUnit().setHasAttacked(false);
                	}else {
                		field.getUnit().setHasMoved(true);
                		field.getUnit().setHasAttacked(true);
                	}
                }
        	}
        }
    }


	public void printPossibleMoves(int x, int y, Tile tile)
	{
		System.out.println("Tile with Type: "+ tile.getType() + " at position: (" + x + " " + y + ") clicked ");
		if (tile.getUnit() == null) {
			System.out.println("No Unit on this Tile");
			
		}else {
			System.out.println("Possible Moves: " + tile.getUnit().getUnitStats().getMovementCost("ST"));
			System.out.println("Map size: " + this.getWidth() + " " + this.getHeight());
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
		
		Figures unit = moveUnit.getUnit();
		Figures target = tile.getUnit();
		if (unit != null && target == null && !unit.isHasMoved()) {
			if (this.findPath(moveUnit, tile, unit.getMovement_range(), unit.getUnitStats())) {
				unit.setHasMoved(true);
				return true;
			}
			
		}
		
		return false;
	}
	/**
	 * Checks if a unit can attack another unit
	 * @note There might be edge cases where Weapon 1 can't reach target but Weapon 2 can
	 * @param unit
	 * @param target
	 * @return true if unit can attack target
	 */
	public boolean attackUnit(Tile unit, Tile target) {
		if (unit.getUnit().isHasAttacked() || unit.getUnit().getPlayer() == target.getUnit().getPlayer()) {
			return false;
		}
		if (unit.getUnit() != null && target.getUnit() != null) {
			Unit_Loader unitStats = unit.getUnit().getUnitStats();
			Unit_Loader targetStats = target.getUnit().getUnitStats();
			// Check if unit has a weapon to attack target
			if (unitStats.getWeapon1() != null)  {
				Weapon weapon = unitStats.getWeapon1();
				if (weapon.getCan_attack().contains(targetStats.getUnit_tag())) {
					if (weapon.getCan_attack().contains(targetStats.getUnit_tag())) {
						if (this.attackPossible(unit, target, weapon.getRange())) {
							target.getUnit().setLifepoints(target.getUnit().getLifepoints()-20);
							if(target.getUnit().getLifepoints() <= 0) {
								target.setUnit(target.getUnit());
								target.removeUnit();
							}else {
								target.setUnit(target.getUnit());
							}
							return true;
						}
						
					}
				}
			}
			if (unitStats.getWeapon2() != null) {
				Weapon weapon = unitStats.getWeapon2();
				if (weapon.getCan_attack().contains(targetStats.getUnit_tag())) {
					if (this.attackPossible(unit, target, weapon.getRange())) {
						System.out.println("Unit: " + unit + " attacked target: " + target);
						target.getUnit().setLifepoints(target.getUnit().getLifepoints()-10);
						if(target.getUnit().getLifepoints() <= 0) {
							target.setUnit(target.getUnit());
							target.removeUnit();
						}else {
							target.setUnit(target.getUnit());
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean attackPossible(Tile unit, Tile target, int range) {
		if (this.findPath(unit, target, range) && !unit.equals(target) && !unit.getUnit().isHasAttacked()) {
			return true;
		}
		return false;
	}
	/**
	 * To be used when Terrain information isn't necessary 
	 * e.g. when firing a weapon
	 * @param start
	 * @param end
	 * @param range
	 * @return true if start and end are only range tiles apart
	 */
	public boolean findPath(Tile start, Tile end, int range) {
		if (range < 0) {
			return false;
		}
		if (start == null) {
			return false;
		}
		if (start.equals(end)) {
			return true;
		}
		try {
			if (findPath(this.getNeighbourNorth(start), end, range-1) || 
					findPath(this.getNeighbourEast(start), end, range-1) || 
					findPath(this.getNeighbourSouth(start), end, range-1) || 
					findPath(this.getNeighbourWest(start), end, range-1)
					) {
				return true;
			}
		} catch (NullPointerException e) {
			return false;
		}
		return false;
	}
	/**
	 * To be used when a unit needs to traverse terrain
	 * @param start
	 * @param end
	 * @param range
	 * @param unit_stats
	 * @return true if a path exists
	 * @return false if a path doesn't exist
	 */
	public boolean findPath(Tile start, Tile end, int range, Unit_Loader unit_stats) {
		if (range < 0) {
			return false;
		}
		if (start == null) {
			return false;
		}
		if (start.equals(end)) {
			return true;
		}
		try {
			if (findPath(this.getNeighbourNorth(start), end, range-unit_stats.getMovementCost(this.getNeighbourNorth(start).getClassType()),unit_stats)) {
				return true;
			}
		} catch (NullPointerException e) {
			//Pass
		}
		try {
			if (findPath(this.getNeighbourWest(start), end, range-unit_stats.getMovementCost(this.getNeighbourWest(start).getClassType()),unit_stats)) {
				return true;
			}
		} catch (NullPointerException e) {
			//Pass
		}
		try {
			if (findPath(this.getNeighbourEast(start), end, range-unit_stats.getMovementCost(this.getNeighbourEast(start).getClassType()),unit_stats)) {
				return true;
			}
		} catch (NullPointerException e) {
			//Pass
		}
		try {
			if (findPath(this.getNeighbourSouth(start), end, range-unit_stats.getMovementCost(this.getNeighbourSouth(start).getClassType()),unit_stats)) {
				return true;
			}
		} catch (NullPointerException e) {
			//Pass
		}

		return false;
	}

	public Tile getTile(int x, int y) {
		return this.field[x][y];
	}
	
	public void setfield(Tile[][] field) {
		this.field = field;
	}
	
	public Tile getNeighbourNorth(Tile tile) {
		if (tile.getY() != 0) {
			return this.getTile(tile.getX(), tile.getY()-1);
		}
		return null;

	}
	
	public Tile getNeighbourEast(Tile tile) {
		if (tile.getX() != 0) {
			return this.getTile(tile.getX()-1, tile.getY());
		}
		return null;
	}
	
	public Tile getNeighbourSouth(Tile tile) {
		if (tile.getY() != this.getHeight()-1) {
			return this.getTile(tile.getX(), tile.getY()+1);
		}
		return null;
	}
	
	public Tile getNeighbourWest(Tile tile) {
		
		if (tile.getX() != this.getWidth()-1) {
			return this.getTile(tile.getX()+1, tile.getY());
		}
		return null;
	}
	
	
}
