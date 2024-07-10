package Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import view.GeneralTypes;

public class UnitLoader {
	private String unit_tag;
	private String unit_name;
	private int movement_range;
	private Weapon weapon1;
	private Weapon weapon2;
	private ArrayList<MovementCost> movement_costs = new ArrayList<MovementCost>();
	
	public UnitLoader(String UNIT_TAG) throws FileNotFoundException {
		this.unit_tag = UNIT_TAG.trim();
		loadUnits(this.unit_tag);
	}
	public void loadUnits(String UNIT_TAG) throws FileNotFoundException {
		String filename = "resources/unit_stats/"+UNIT_TAG+".unit";
		File file = new File(filename);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.isEmpty()) {
				continue;
			}
			if (line.startsWith("UNIT_NAME")) {
				int start_name = line.indexOf("\"");
				int end_name = line.indexOf("\"", start_name+1);
				this.unit_name = line.substring(start_name+1, end_name);
			}else if (line.startsWith("MOVEMENT_RANGE")) {
                this.movement_range = Integer.parseInt(line.substring(line.indexOf(":")+1));                
			}else if (line.startsWith("MOVEMENT_COST")) {
				this.setMovement_costs(scanner);
			}else if (line.startsWith("WEAPON1")) {
				this.weapon1 = setWeapon(scanner);
			}else if (line.startsWith("WEAPON2")) {
				this.weapon2 = setWeapon(scanner);
			} else if (line.isEmpty()) {
				
			}else if (line.startsWith("UNIT_TAG:")) {
				if (line.substring(line.indexOf(":")+1).trim().contains(UNIT_TAG) == false) {
					System.out.println("Error: Unit tag does not match file name: " + line.substring(line.indexOf(":")+1).trim());
				}
			}else {
				//System.out.println("Error: Unknown line in unit file: Line " + counter + ": " + line);
			}
		}
		scanner.close();
		
		
	}
	private Weapon setWeapon(Scanner scanner) {
		String line;
		int range = 0;
		LinkedList<String> attackables = new LinkedList<String>();
		do {
			line = scanner.nextLine();
			line = line.trim();
			if (line.startsWith("EXISTS: ")) {
				if(line.contains("N")) {
					return null;
				}else if(line.contains("Y")) {
					
				}else {
					System.out.println("Error: Unknown value in exists in weapon: " + line);
					break;
				}
				
			}else if (line.startsWith("FIRE_RANGE:")) {
                String split[] = line.split(":");
                range = Integer.parseInt(split[1].trim());
			}else if (line.startsWith("ATTACKABLE:")) {
                String split[] = line.split(":");
                String split2[] = split[1].trim().split(",");
				for (int i = 0; i < split2.length; i++) {
					attackables.add(split2[i].trim());
				}
			}
		}while (!line.isEmpty());
		if (range == 0) {
			System.out.println("Error: No range found in weapon");
		}
		Weapon weapon = new Weapon(range, attackables);
		return weapon;
		
	}
	
	private void setMovement_costs(Scanner scanner) {
		String line = scanner.nextLine();
		for (int i = 0; i < GeneralTypes.values().length; i++) {
			if (line.startsWith(" ") || line.startsWith("\t")) {
				line = line.trim();
				line = line.replace("(", "");
				line = line.replace(")", "");
				String split[] =line.split(";");
				split[0] = split[0].replace("\"", "");
				//System.out.println("Split: " + split[0] + " + " + split[1] + " General Type:" + GeneralTypes.values()[i].name());
				if (split[0].equals(GeneralTypes.values()[i].name())) {
				
					try {
						int cost = Integer.parseInt(split[1].trim());
						this.movement_costs.add(new MovementCost(cost, GeneralTypes.values()[i].name()));
					}catch (NumberFormatException e) {
						this.movement_costs.add(new MovementCost(100000, GeneralTypes.values()[i].name()));
					}	
				} else {
					System.out.println("Error: Unknown GeneralType: " + split[0]);
				}
				line = scanner.nextLine();
				
			}else {
				System.out.println("Error: Unknown line in movement costs: " + line);
			}
		}
	}

	public int getMovementCost(String tileTag) {
		//System.out.print("TileTag: " + tileTag);
		for (int i = 0; i < movement_costs.size(); i++) {
			if (movement_costs.get(i).getType().equals(tileTag)) {
				return movement_costs.get(i).getCost();
			}
		}
		// Should never happen
		System.out.println("Error: No movement cost found for tile tag: " + tileTag);
        return 100000000;
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

	public ArrayList<MovementCost> getMovement_costs() {
		return movement_costs;
	}
}
