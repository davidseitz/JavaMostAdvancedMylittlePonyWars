package Logic;


public class Model {
	
	private int level;
	private int width;
	private int height;
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


	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;


	public void printPossibleMoves(int x, int y)
	{
		System.out.println("Tile at position: (" + x + " " + y + ") clicked ");
		System.out.println("Possible Moves: ");
	}
}
