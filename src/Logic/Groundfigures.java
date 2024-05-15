package Logic;

public class Groundfigures extends Figure {
	protected int x;
	protected int y;
	protected Figuretype type;
	public Groundfigures(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Figuretype getType() {
		return type;
	}

	public void setType(Figuretype type) {
		this.type = type;
	}
	@Override
	/**
	 * This method is used to move the ground figures
	 * @return boolean true if the move is successful, false otherwise
	 */
	public boolean move(int x, int y, int new_x, int new_y) {
		// TODO Auto-generated method stub
		return false;
	}
}
