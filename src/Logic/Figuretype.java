package Logic;

public class Figuretype {
	private String type;
	
	

	public Figuretype(String type) {
		
		this.type = type_converter(type);
	}

	private String type_converter(String type) {
        if(type.equals("INF")) {
            return type;
        }else {
			// TODO: Add more types
			return "INF";
        }
        }
	
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Figuretype [type=" + type + "]";
	}

}
