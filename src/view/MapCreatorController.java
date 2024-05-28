package view;

import java.net.URL;
import java.util.ResourceBundle;

import Logic.Model;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

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
