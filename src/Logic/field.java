package Logic;

import javaMostAdvancedMyLittlePonyWars.Model;

public class field {
	private static field Instance = null;
	private Tile[][] field;
	private field(int width, int height) {
		field = new Tile[width][height];
    }
	/**
	 * This method is used to get the instance of the field
	 * Implemented in order to guarantee that only one instance of the field is created
	 * If we want to change the size of the field at runtime, this needs editing	
	 * @return field the instance of the field
	 */
	public field getInstance() {
		if (Instance == null) {
			Instance = new field(Model.getBoardWidth(), Model.getBoardHeight() );
		}
		return Instance;
	}
}
