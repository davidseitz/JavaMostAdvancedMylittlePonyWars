package Logic;

import java.io.FileNotFoundException;

public class Figures {
	private int x;
	private int y;
	private int lifepoints;
	private Unit_Loader unit_stats;
	private int player;
	private Figuretype type;
	private boolean hasMoved = false;
	private boolean hasAttacked = false;
	
	/**
	 * TODO Figure Movement at the edges
	 * 
	 * @param x
	 * @param y
	 * @param type
	 * @param player
	 * @throws Exception
	 */
	public Figures(int x, int y, Figuretype type, int player) throws FileNotFoundException {
		this.x = x;
		this.y = y;
		this.lifepoints = 100;
		this.type = type;
		this.player = player;
		try {
			this.unit_stats = new Unit_Loader(type.getType());
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: Unit " + type.getType() + " not found doing Inf instead");
			this.unit_stats = new Unit_Loader("I");
		}
	}
	public boolean isHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	
	public boolean isHasAttacked() {
		return hasAttacked;
	}
	public void setHasAttacked(boolean hasAttacked) {
		this.hasAttacked = hasAttacked;
	}

	public char getFaction() {
		if (this.player%2 == 0) {
			return 'E';
		}else {
			return 'R';
		}
	}
	public int getLifepoints() {
		return lifepoints;
	}

	public void setLifepoints(int lifepoints) {
		this.lifepoints = lifepoints;
	}

	public int getPlayer() {
		return player;
	}

	public Figuretype getType() {
		return type;
	}

	public void setType(Figuretype type) {
		this.type = type;
	}
	
	public String toString() {
		return "Groundfigures [type = "+ this.type+" x = "+ this.x +" y = "+ this.y +"]";
	}
	
	public int getMovement_range() {
		if (this.unit_stats != null) {
			return this.unit_stats.getMovement_range();
		}else {
			return 0;
		}
	}

	public Unit_Loader getUnitStats() {
		return this.unit_stats;
	}
}
