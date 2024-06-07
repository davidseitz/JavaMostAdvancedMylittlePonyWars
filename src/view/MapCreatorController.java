package view;

import java.awt.Toolkit;
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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
	private Label sizeLabel;
	@FXML
	private TextField sizeTextfield;
	
	private Model model;
	private String tagToChange;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.model = Model.getInstance();
		this.background.setPadding(new Insets(10.0,-10.0,100.0,100.0));
		this.slider.setValue(model.getScale());
		this.sliderValue.setText((int) slider.getValue()+"");
		this.sizeLabel.setText("Size = "+ model.getWidth() + " : "+ model.getHeight());
		
		for (Tile lines[] : menu.getMenu()) {
		      for (Tile menuVal : lines) {
		    	  try {
		    		  menuVal.setOnMouseClicked(new MenuTileClickedEventHandler());
		    	  }catch(NullPointerException e) {
		    		  continue;
		    	  }
		      }
		}
		
		for (Tile lines[] : model.getField()) {
		      for (Tile fieldVal : lines) {
		    	  fieldVal.setOnMouseClicked(new FieldClickedEventHandler());
		      }
		}
	}
	
	public void saveMap() {
		MapSaver saver = new MapSaver();
		saver.saveMap(model.getField());
	}
	
	public void onSliderChanged() {
		sliderValue.setText(""+((int)slider.getValue()));
		System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
	}
	
	public void NewField() {
		try {
			String[] sizes = sizeTextfield.getText().split(":");
			model.setHeight(Integer.parseInt(sizes[1]));
			model.setWidth(Integer.parseInt(sizes[0]));
			if(model.getHeight() > 20 || model.getWidth() > 26) {
				throw new Exception();
			}
			sizeLabel.setText("Size = " + sizes[0] + " : " + sizes[1]);
			
			BattleFieldLoader loader = new BattleFieldLoader();
			loader.customMap();
			model.setScale(40);
			reloadStage();
		}catch (Exception e) {
			System.out.println("Wrong Format");
		}
	}
	
	public void reload() {
		BattleFieldLoader loader = new BattleFieldLoader();
		loader.reloadMap(model.getField());
		
		final int scale = (int)slider.getValue();
		model.setScale(scale);
		reloadStage();
	}
	
	
	private void reloadStage() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("mapCreator.fxml"));
			Scene newScene = new Scene(root);
			Scene currentScene = background.getScene();
			Stage levelStage = (Stage) currentScene.getWindow();
			levelStage.setScene(newScene);
			levelStage.setFullScreen(true);
			levelStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private class MenuTileClickedEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			Tile tile = (Tile) event.getSource();
			tagToChange = tile.getType();
		}
	}
	private class FieldClickedEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			Tile tile = (Tile) event.getSource();
			if(tagToChange != null) {
				model.setTile(tile.getX(), tile.getY(), tagToChange);
			}
		}
	}
	
}
