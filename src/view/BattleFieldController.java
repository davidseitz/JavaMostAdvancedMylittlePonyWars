package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Logic.Model;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Controller for the BattleField
 * Handles the main game loop
 */
public class BattleFieldController implements Initializable {
	
	@FXML
	private BattleField battlefield;
	@FXML
	private BorderPane background;
	@FXML
	private Button reloadButton;
	@FXML
	private Button finishRoundButton;
	@FXML
	private Slider slider;
	@FXML
	private Label sliderValue;
	@FXML
	private HBox topMenu;
	private Model model;
	private Tile moveUnit;
	private Tile oldTile;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.model = Model.getInstance();
		
		this.background.setPadding(new Insets(10.0,100.0,100.0,100.0));
		this.background.setPrefSize(1200.0, 1920.0);
		
		BorderPane.setMargin(battlefield, new Insets(10));
		BorderPane.setMargin(topMenu, new Insets(10));
		
		battlefield.setPadding(new Insets(0.0,0.0,0.0,0.0));
		
		model.resetRound();
		finishRoundButton.setText("Start Game");
		slider.setValue(model.getScale());
		sliderValue.setText("" + (int)slider.getValue());

		for (Tile lines[] : model.getField()) {
		      for (Tile field : lines) {
		        field.setOnMouseClicked(new FieldClickedEventHandler());
		      }
		}
		
	}
	
	public void loadMap() throws IOException {
		
		BattleFieldLoader loader = new BattleFieldLoader();
		loader.reloadMap(model.getField());
		
		final int scale = (int)slider.getValue();
		model.setScale(scale);
		
		// Reload Stage
		Parent root;
		root = FXMLLoader.load(getClass().getResource("battleField.fxml"));
		Scene newScene = new Scene(root);
		Scene currentScene = battlefield.getScene();
		Stage levelStage = (Stage) currentScene.getWindow();
		levelStage.setScene(newScene);
		levelStage.setFullScreen(true);
		levelStage.show();
	}
	
	public void endRound() {
		clearHighlights();
		if(model.getRound() == -1) {
			finishRoundButton.setText("Next Round");
			slider.setVisible(false);
			reloadButton.setVisible(false);
			
		}
		int isFinished = model.endRound();
		if (isFinished != 0) {
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("victoryScreen.fxml"));
				Scene newScene = new Scene(root);
				Scene currentScene = finishRoundButton.getScene();
				Stage levelStage = (Stage) currentScene.getWindow();
				levelStage.setScene(newScene);
				levelStage.setFullScreen(true);
				levelStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(model.getRound()%2 == 0){
			sliderValue.setText("Turn Empire");
		}else {
			sliderValue.setText("Turn Rebels");
		}
		moveUnit = null;
		oldTile = null;
		
	}
	
	public void onSliderChanged() {
		sliderValue.setText(""+((int)slider.getValue()));
	}
	
	private void setHighlightSelected(Tile tile, boolean highlight) {
	    if (highlight) {
	    	tile.getBackgroundLayer().setEffect(new ColorAdjust(0, 0, 0.5, 0));
	    } else {
	    	tile.getBackgroundLayer().setEffect(new ColorAdjust(0, 0, 0, 0));
	    }
	}
	private void setHighlightAttack(Tile tile) {
	    tile.getBackgroundLayer().setEffect(new ColorAdjust(-0.5, 0, 0.5, 0));
	}
	private void setHighlightAllies(Tile tile) {
	    tile.getBackgroundLayer().setEffect(new ColorAdjust(0.5, 0, 0.5, 0));
	}
	
	public void clearHighlights() {
		for (Tile[] allTiles : model.getField()) {
			for (Tile field : allTiles) {
				setHighlightSelected(field, false);
			}
		}
	}
	
	private class FieldClickedEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			if (model.getRound() != -1) {  // Begin of Round if
				
			Tile tile = (Tile) event.getSource();
			
			if (oldTile != null) {
				//Highlight the selected tile
				setHighlightSelected(oldTile, false);
			}
			if (tile != null && tile.getUnit() != null 
					&& oldTile != null && oldTile.getUnit() != null) {
				//Attack enemy
				if(model.attackUnit(oldTile, tile)) {
					// Unit can't move after attacking
					oldTile.getUnit().setHasMoved(true);
					clearHighlights();
				}
			}
			if (tile.getUnit() != null) {
				//Highlight unit tile
				setHighlightAllies(tile);
				moveUnit = tile;
			}else {
				//Move Unit
				if (moveUnit != null && (!moveUnit.getUnit().isHasMoved() || !moveUnit.getUnit().isHasAttacked())) {
					setHighlightSelected(moveUnit, false);
					if (model.move(tile, moveUnit)) {
						tile.setUnit(moveUnit.getUnit());
                        setHighlightSelected(tile, false);
                        clearHighlights();
                        oldTile.removeUnit();
 					}else {
 						clearHighlights();
 					}
				}
				moveUnit = null;
			}
			if (moveUnit != null && moveUnit.getUnit() != null) {
				//Highlight tiles to move to or attack
				this.setHighlightMoveableTiles();
			}
			
			oldTile = tile;
			} // End of Round If
		}
		private void setHighlightMoveableTiles() {
			clearHighlights();
			if (moveUnit.getUnit().getFaction() == model.roundToFaction()) {
				setHighlightAllies(moveUnit);
			}
			for (Tile[] allTiles : model.getField()) {
				for (Tile field : allTiles) {
					if (!moveUnit.getUnit().isHasMoved()
							&& model.findPath(moveUnit, field, moveUnit.getUnit().getUnitStats().getMovement_range(), moveUnit.getUnit().getUnitStats())
							&& field.getUnit() == null) {
						setHighlightSelected(field, true);
					}
					// Only for testing
					int range = 0;
					if (moveUnit.getUnit().getUnitStats().getWeapon1() != null) {
						range = moveUnit.getUnit().getUnitStats().getWeapon1().getRange();
					}
					if (model.attackPossible(moveUnit, field, range)
							&& !moveUnit.getUnit().isHasAttacked()
							&& field.getUnit() != null
							&& field.getUnit().getPlayer() != moveUnit.getUnit().getPlayer()) {
						setHighlightAttack(field);
					}
					if (moveUnit.getUnit().getUnitStats().getWeapon2() != null) {
						range = moveUnit.getUnit().getUnitStats().getWeapon2().getRange();
					}
					if (model.attackPossible(moveUnit, field, range)
							&& !moveUnit.getUnit().isHasAttacked()
							&& field.getUnit() != null
							&& field.getUnit().getPlayer() != moveUnit.getUnit().getPlayer()) {
						setHighlightAttack(field);
					}
				}
			}
		}
	}

}
