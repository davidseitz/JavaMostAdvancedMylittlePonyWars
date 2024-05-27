package Logic;

import javafx.scene.image.ImageView;

public abstract class Figure extends ImageView{
	public abstract boolean move(final int x, final int y,final int new_x,final int new_y);
}