package view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import Logic.Model;

public class MapSaver {
	
	private Model model;
	
	public MapSaver() {
		this.model = Model.getInstance();
	}
	
	public void saveMap(Tile[][] tiles) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/maps/creator.map"))) {
			writer.write(model.getWidth()+":"+model.getHeight()+";"+(char) 10);
			for(int j = 0; j < tiles[0].length;j++) {
				for (int i = 0; i < tiles.length;i++) {
					if(tiles[i][j].getUnitForCreator() != null) {
						writer.write(tiles[i][j].getType()+","+tiles[i][j].getUnitForCreator().getType().getType()+tiles[i][j].getUnitForCreator().getFaction()+";");
					}else {
						writer.write(tiles[i][j].getType()+",NU;");
					}
				}
				writer.write((char) 10);
			}
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
