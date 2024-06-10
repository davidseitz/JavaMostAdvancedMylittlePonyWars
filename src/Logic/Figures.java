package Logic;

public class Figures {
	protected int x;
	protected int y;
	protected int lifepoints;
	protected int movement_range;
	protected Unit_Loader unit_stats;
	protected int player;
	public int getLifepoints() {
		return lifepoints;
	}

	public void setLifepoints(int lifepoints) {
		this.lifepoints = lifepoints;
	}
	protected Figuretype type;
	public Figures(int x, int y, Figuretype type, int player) throws Exception {
		this.x = x;
		this.y = y;
		this.lifepoints = 100;
		this.type = type;
		this.movement_range = 1;
		this.player = player;
		try {
			this.unit_stats = new Unit_Loader(type.getType());
			
		} catch (Exception e) {
			System.out.println("Error: Unit not found");
			throw e;
			//TODO throw exception
		}
	}
	
	public char getFaction() {
		if (this.player == 0) {
			return 'E';
		}else {
			return 'R';
		}
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
