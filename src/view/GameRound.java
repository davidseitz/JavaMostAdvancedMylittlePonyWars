package view;

import Logic.Model;

public class GameRound {
	/*
	private static GameRound gameRound = null;
	private Model model;
	private GameRound() {
		model = Model.getInstance();
	}
	
	public static GameRound getInstance() {
		if (gameRound == null) {
			gameRound = new GameRound();
		}
		return gameRound;
	}
	
	public Tile Round(Tile oldTile, Tile tile, Tile moveUnit) {
		if (oldTile != null) {
			//Highlight the selected tile
			BattleFieldController.setHighlightSelected(oldTile, false);
		}
		
		if (tile.getUnit() != null) {
			//Highlight unit tile
			BattleFieldController.setHighlightAttack(tile);
			moveUnit = tile;
		}else {
			//Move Unit
			
			if (moveUnit != null) {
				BattleFieldController.setHighlightSelected(moveUnit, false);
				if (model.move(tile, moveUnit)) {
                    tile.setUnit(moveUnit.getUnit());
                    BattleFieldController.setHighlightSelected(tile, false);
                    BattleFieldController.clearHighlights();
                    oldTile.removeUnit();
					}
			}
			moveUnit = null;
		}
		if (moveUnit != null && moveUnit.getUnit() != null) {
			BattleFieldController.setHighlightMoveableTiles();
		}
		oldTile = tile;
		
		model.printPossibleMoves(tile.getX(), tile.getY(),tile);
		System.out.println("X = " +tile.getX()+ " Y = " + tile.getY());
		return oldTile;
	}
*/
}
