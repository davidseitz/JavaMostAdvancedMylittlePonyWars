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
	
	public void saveMap(Tile[][] tiles, String filename) {
		File file = new File("resources/maps/"+ filename +".map");
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/maps/"+ filename +".map"))) {
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
