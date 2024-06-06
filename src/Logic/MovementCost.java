package Logic;

public class MovementCost {
	private int cost;
	private String type;

	public MovementCost(int cost, String type) {
		this.cost = cost;
		this.type = type;
	}

	public int getCost() {
		return cost;
	}

	public String getType() {
		return type;
	}
	
	public String toString() {
		return "MovementCost [type = " + this.type + " cost = " + this.cost + "]";
	}

}
