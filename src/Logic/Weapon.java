package Logic;

import java.util.LinkedList;

public class Weapon {
	private int range;
	private LinkedList<String> can_attack;
	
	public Weapon(int range, LinkedList<String> can_attack) {
		this.range = range;
		this.can_attack = can_attack;
	}

	public int getRange() {
		return range;
	}

	public LinkedList<String> getCan_attack() {
		return can_attack;
	}

}
