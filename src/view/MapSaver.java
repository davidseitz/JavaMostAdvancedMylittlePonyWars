package view;

import java.io.BufferedWriter;
import java.io.File;
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
			for(int i = 0; i < tiles.length;i++) {
				for (int j = 0; j < tiles[0].length;j++) {
					writer.write(tiles[j][i].getType()+","+"NU"+";");
				}
				writer.write((char) 10);
			}
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
