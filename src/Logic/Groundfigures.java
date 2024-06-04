package Logic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Groundfigures extends Figure {
	protected int x;
	protected int y;
	protected int lifepoints;
	protected int movement_range;
	public int getLifepoints() {
		return lifepoints;
	}

	public void setLifepoints(int lifepoints) {
		this.lifepoints = lifepoints;
	}
	protected Figuretype type;
	public Groundfigures(int x, int y, Image image, Figuretype type) {
		this.x = x;
		this.y = y;
		this.lifepoints = 100;
		this.type = type;
		this.movement_range = 1;	
		this.setImage(image);
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
	@Override
	/**
	 * This method is used to move the ground figures
	 * @return boolean true if the move is successful, false otherwise
	 */
	public boolean move(int x, int y, int new_x, int new_y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getMovement_range() {
		return movement_range;
	}
}
