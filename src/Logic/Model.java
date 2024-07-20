package Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import view.Tile;

/**
 * Model class that holds the game state
 */
public class Model {
	
	private int width;
	private int height;
	private int scale;
	private ArrayList<String[]> map;
	private Tile[][] field;
	private String[] classTypes;
	private static Model instance;
	private int round = -1; // Startbutton must be pressed before the game starts
	private Model() {
		
	}
	
	public void initClassTypes() {
		this.classTypes = new String[5];
		try {
			String filename = "resources/modification/Class_Types.txt";
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				if(line.startsWith("MOUNTAIN")) {
					int start_name = line.indexOf("\"");
					int end_name = line.indexOf("\"", start_name+1);
					classTypes[0]=line.substring(start_name+1, end_name);
				}else if(line.startsWith("PLAIN")) {
					int start_name = line.indexOf("\"");
					int end_name = line.indexOf("\"", start_name+1);
					classTypes[1]=line.substring(start_name+1, end_name);
				}else if(line.startsWith("STREETS")) {
					int start_name = line.indexOf("\"");
					int end_name = line.indexOf("\"", start_name+1);
					classTypes[2]=line.substring(start_name+1, end_name);
				}else if(line.startsWith("RIVERS")) {
					int start_name = line.indexOf("\"");
					int end_name = line.indexOf("\"", start_name+1);
					classTypes[3]=line.substring(start_name+1, end_name);
				}else if(line.startsWith("WOODS")) {
					int start_name = line.indexOf("\"");
					int end_name = line.indexOf("\"", start_name+1);
					classTypes[4]=line.substring(start_name+1, end_name);
				}
			}
			scanner.close();
		}catch(FileNotFoundException e) {
			
		}
	}
	
	public String[] getClassTypes() {
		return this.classTypes;
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
	
	public void resetRound() {
		this.round = -1;
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
	public int checkVictory() {
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
            return 1;
		} else if (faction2 == 0) {
			return 2;
		}
		return 0;
	}
	
	public int endRound() {
		int isFinished = checkVictory();
		if (isFinished != 0) {
			return isFinished;
		}
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
        return 0;
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
			UnitLoader unitStats = unit.getUnit().getUnitStats();
			//UnitLoader targetStats = target.getUnit().getUnitStats();
			boolean returnValue = false;
			// Check if unit has a weapon to attack target
			if (unitStats.getWeapon1() != null)  {
				Weapon weapon = unitStats.getWeapon1();
				returnValue = doDamage(weapon, target, unit, 20);
			}
			if (unitStats.getWeapon2() != null && returnValue == false) {
				Weapon weapon = unitStats.getWeapon2();
				returnValue = doDamage(weapon, target, unit, 10);
				System.out.println("Weapon 2: "+ returnValue);
			}
			System.out.println(returnValue);
			//System.out.println(returnValue);
			if (returnValue) {
				return true;
			}
		}
		return false;
	}

	private boolean doDamage(Weapon weapon, Tile target, Tile unit, int damage) {
		if (target.getUnit() == null) {
			return false;
		}
		UnitLoader targetStats = target.getUnit().getUnitStats();
		if (weapon.getCan_attack()!= null && weapon.getCan_attack().contains(targetStats.getUnit_tag())) {
			if (this.attackPossible(unit, target, weapon.getRange())) {
				target.getUnit().setLifepoints(target.getUnit().getLifepoints()-damage);
				unit.getUnit().setHasAttacked(true);
				if(target.getUnit().getLifepoints() <= 0) {
					target.setUnit(target.getUnit());
					target.removeUnit();
				}else {
					target.setUnit(target.getUnit());
				}
				return true;
			}
		}
		return false;
	}
	public boolean attackPossible(Tile unit, Tile target, int range) {
			boolean canAttack = false;
			UnitLoader unitStats = unit.getUnit().getUnitStats();
			if (target.getUnit() == null) {
	            return false;
	        }
			
			UnitLoader targetStats = target.getUnit().getUnitStats();
			Weapon weapon1 = unitStats.getWeapon1();
			Weapon weapon2 = unitStats.getWeapon2();
			if ((weapon1.getCan_attack()!= null && (weapon1.getCan_attack().contains(targetStats.getUnit_tag()) ) 
					|| (weapon2.getCan_attack()!= null && weapon2.getCan_attack().contains(targetStats.getUnit_tag())))) {
				canAttack = true;
			}
			
			
			if (this.findPath(unit, target, range) && !unit.equals(target) && !unit.getUnit().isHasAttacked() && canAttack) {
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
	 * @param start
	 * @param end
	 * @param range
	 * @param weight
	 *
	 *private void dijkstraSetValues(Tile start, Tile end, int weight) {
	 *	boolean visited[] = new boolean[this.height*this.width];
	 *	Arrays.fill(visited, false);
	 *	int distanceMatrix[] = new int[this.height*this.width];
	 *	int predecessor[] = new int[this.height*this.width];
	 *	Arrays.fill(distanceMatrix, Integer.MAX_VALUE);
	 *	Arrays.fill(predecessor, Integer.MAX_VALUE);
	 *	distanceMatrix[(int) (this.height*start.getHeight() + this.width)] = 0;
	 *	predecessor[(int) (this.height*start.getHeight() + this.width)] =
	 *			-1;
	 *	visited[(int) (this.height*start.getHeight() + this.width)] = true;
	 *	int currentyVisiting =(int) (this.height*start.getHeight() + this.width);
	 *	while(!visited[(int) (this.height*end.getHeight() + this.width)]){
	 *		if (currentyVisiting + this.getHeight() > this.height*this.width) {
	 *			distanceMatrix[currentyVisiting + this.getHeight()] =
	 *					distanceMatrix[currentyVisiting] + weight;
	 *		}
	 *		if (currentyVisiting + this.getHeight() > this.height*this.width) {
	 *			distanceMatrix[currentyVisiting + this.getHeight()] =
	 *					distanceMatrix[currentyVisiting] + 1;
	 *		}
	 *	}	
	 *}
	 */
	/**
	 * To be used when a unit needs to traverse terrain
	 * @param start
	 * @param end
	 * @param range
	 * @param unit_stats
	 * @return true if a path in the range
	 * @return false if a path doesn't exist
	 */
	public boolean findPath(Tile start, Tile end, int range, UnitLoader unit_stats) {
		if (range < 0) {
			return false;
		}
		if (start == null) {
			return false;
		}
		if (start.equals(end)) {
			return true;
		}
		if (start.getUnit() != null && start.getUnit().getPlayer() != this.round % 2) {
			return false;
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
