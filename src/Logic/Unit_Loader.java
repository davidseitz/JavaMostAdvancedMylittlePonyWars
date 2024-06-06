package Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Unit_Loader {
	String unit_tag;
	String unit_name;
	int movement_range;
	Weapon weapon1;
	Weapon weapon2;
	
	public Unit_Loader(String UNIT_TAG) throws FileNotFoundException {
		this.unit_tag = UNIT_TAG;
		loadUnits(UNIT_TAG);
	}
	public void loadUnits(String UNIT_TAG) throws FileNotFoundException {
		String filename = "resources/unit_stats/"+UNIT_TAG+".unit";
		File file = new File(filename);
		Scanner scanner = new Scanner(file);
		int counter = 0;
		while (scanner.hasNextLine()) {
			counter += 1;
			String line = scanner.nextLine();
			System.out.println("Line " + counter + ": " + line);
			if (line.isEmpty()) {
				continue;
			}
			if (line.startsWith("UNIT_NAME")) {
				int start_name = line.indexOf("\"");
				int end_name = line.indexOf("\"", start_name+1);
				this.unit_name = line.substring(start_name+1, end_name);
			}else if (line.startsWith("MOVEMENT_RANGE")) {
                this.movement_range = Integer.parseInt(line.substring(line.indexOf(":")+1));
                System.out.println("Movement Range: " + this.movement_range);
			}else if (line.startsWith("MOVEMENT_COST")) {
				//TODO
			}else if (line.startsWith("WEAPON1")) {
                //TODO
				weapon1 = new Weapon(1, null);
			}else if (line.startsWith("WEAPON2")) {
				// TODO
			} else {
				System.out.println("Error: Unknown line in unit file: Line " + counter + ": " + line);
			}
		}
		scanner.close();
		
		
	}
	
	public String getUnit_tag() {
		return unit_tag;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public int getMovement_range() {
		return movement_range;
	}
	public Weapon getWeapon1() {
		return weapon1;
	}
	public Weapon getWeapon2() {
		return weapon2;
	}
}
