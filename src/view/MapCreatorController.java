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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MapCreatorController implements Initializable {
	
	@FXML
	private BorderPane background;
	@FXML
	private BattleField field;
	@FXML
	private MapCreationMenu menu;
	@FXML
	private Slider slider;
	@FXML
	private Label sliderValue;
	@FXML
	private Button saveButton;
	
	private Model model;
	
	private String tagToChange;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.model = Model.getInstance();
		this.background.setPadding(new Insets(10.0,-10.0,100.0,100.0));
		this.slider.setValue(model.getScale());
		this.sliderValue.setText(slider.getValue()+"");
		
		for (Tile lines[] : menu.getMenu()) {
		      for (Tile menuVal : lines) {
		    	  try {
		    		  menuVal.setOnMouseClicked(new MenuTileClickedEventHandler());
		    	  }catch(NullPointerException e) {
		    		  continue;
		    	  }
		      }
		}
		
		for (Tile lines[] : field.getTiles()) {
		      for (Tile fieldVal : lines) {
		    	  fieldVal.setOnMouseClicked(new FieldClickedEventHandler());
		      }
		}
	}
	
	public void saveMap() {
		MapSaver saver = new MapSaver();
		saver.saveMap(field.getTiles());
	}
	
	public void onSliderChanged() {
		sliderValue.setText(""+((int)slider.getValue()));
	}
	
	public void reload() {
		BattleFieldLoader loader = new BattleFieldLoader();
		loader.reloadMap(field.getTiles());
		
		final int scale = (int)slider.getValue();
		model.setScale(scale);
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("mapCreator.fxml"));
			Scene newScene = new Scene(root);
			Scene currentScene = background.getScene();
			Stage levelStage = (Stage) currentScene.getWindow();
			levelStage.setScene(newScene);
			levelStage.setFullScreen(true);
			levelStage.show();
			
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			System.out.println(screenBounds.getHeight());
			System.out.println(screenBounds.getWidth());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class MenuTileClickedEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			Tile tile = (Tile) event.getSource();
			System.out.println("Choosen Tile: " + tile.getType());
			tagToChange = tile.getType();
		}
	}
	private class FieldClickedEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			Tile tile = (Tile) event.getSource();
			System.out.println("To Change Tile: " + tile.getType() +" with "+tagToChange);
			if(tagToChange != null) {
				field.setTile(tile.getX(), tile.getY(), tagToChange);
			}
		}
	}
	
}
