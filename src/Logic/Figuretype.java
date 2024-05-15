package Logic;

public class Figuretype {
	private int type;

	Figuretype(int type) {
		
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Figuretype [type=" + type + "]";
	}

}
