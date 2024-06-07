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

public class BattleFieldController implements Initializable {
	
	@FXML
	private BattleField battlefield;
	@FXML
	private BorderPane background;
	@FXML
	private Button reloadButton;
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
		
		double padding = 100.0;
		if(model.getLevel() == 3) {
			padding = 350.0;
		}
		this.background.setPadding(new Insets(10.0,100.0,100.0,padding));
		this.background.setPrefSize(1200.0, 1920.0);
		
		BorderPane.setMargin(battlefield, new Insets(10));
		BorderPane.setMargin(topMenu, new Insets(10));
		
		battlefield.setPadding(new Insets(0.0,0.0,0.0,0.0));
		
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
	
	private class FieldClickedEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			Tile tile = (Tile) event.getSource();
			
			if (oldTile != null) {
				//Highlight the selected tile
				setHighlightSelected(oldTile, false);
			}
			oldTile = tile;
			
			if (tile.getUnit() != null) {
				//Highlight unit tile
				setHighlightAttack(tile);
				moveUnit = tile;
			}else {
				//Move Unit
				
				if (moveUnit != null) {
					setHighlightSelected(moveUnit, false);
					if (model.move(tile, moveUnit)) {
                        tile.setUnit(moveUnit.getUnit());
                        setHighlightSelected(tile, false);
                        this.clearHighlights();
 					}
 					moveUnit = null;
				}
			}
			if (moveUnit != null && moveUnit.getUnit() != null) {
				this.setHighlightMoveableTiles();
			}
			model.printPossibleMoves(tile.getX(), tile.getY(),tile);
			System.out.println("With ClassType: " + tile.getClassType());
		}
		private void setHighlightMoveableTiles() {
			for (Tile[] allTiles : model.getField()) {
				for (Tile field : allTiles) {
					if (Model.getInstance().findPath(moveUnit, field, 3, moveUnit.getUnit().getUnitStats())) {
						setHighlightSelected(field, true);
					}
				}
			}
		}
		
		private void clearHighlights() {
			for (Tile[] allTiles : model.getField()) {
				for (Tile field : allTiles) {
					setHighlightSelected(field, false);
				}
			}
		}
	}

}
