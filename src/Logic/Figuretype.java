package Logic;

public class Figuretype {
	private String type;
	
	

	public Figuretype(String type) {
		
		this.type = type_converter(type);
	}

	private String type_converter(String type) {
        return type.charAt(0) + "";
        }
	
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Figuretype [type=" + type + "]";
	}

}
