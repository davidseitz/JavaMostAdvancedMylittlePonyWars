package Logic;

import javafx.scene.image.Image;

public class Footsoldier extends Groundfigures {

	public Footsoldier(int x, int y, Image oldImage, Figuretype type) {
		super(x, y, oldImage, type);
		// TODO Auto-generated constructor stub
	}
	/**
     * This method is used to move the footsoldier
     * @return boolean true if the move is successful, false otherwise
     */
     public boolean moveFootsoldier(int x, int y, int new_x, int new_y) {
    	/* TODO add additional rules for the footsoldier*/
    	if (this.x == x && this.y == y) {
    		return move(x, y, new_x, new_y);
    	}
		return false;
	}
}

