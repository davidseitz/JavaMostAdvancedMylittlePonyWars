package Logic;


public class Model {
	
	private int level;
	private int width;
	private int hight;
	private static Model instance;
	
	public static Model getInstance() {
	    if (instance == null) {
	      instance = new Model();
	    }
	    return instance;
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

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}
}
