package Logic;

public class Figuretype {
	private String type;

	Figuretype(String type) {
		
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Figuretype [type=" + type + "]";
	}

}
