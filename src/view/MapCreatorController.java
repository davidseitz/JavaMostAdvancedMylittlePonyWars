package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Logic.Figures;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
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
	private Label fileNameErrorLabel;
	@FXML
	private TextField sizeTextfield;
	@FXML
	private TextField fileNameText;
	
	private Model model;
	private String tagToChange;
	private Figures unitToChange;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.model = Model.getInstance();
		this.background.setPadding(new Insets(10.0,20.0,100.0,100.0));
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
		for (Tile lines[] : menu.getUnits()) {
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
		    	  fieldVal.setOnDragDetected(mouseEvent -> fieldVal.startFullDrag());
		    	  fieldVal.setOnMouseDragEntered(mouseEvent -> changeTile((Tile) mouseEvent.getSource()));
		    	  fieldVal.setOnMousePressed(mouseEvent -> changeTile((Tile) mouseEvent.getSource()));
		      }
		}
	}
	
	public void changeTile(Tile source) {
		//System.out.println(source);
		Tile tile = source;
		
		if(tagToChange != null) {
			if(tagToChange.equals("RUT")) {
				if(tile.getUnit() != null) {
					tile.removeUnit();
				}
			}else {
				tile.setNewTile(new Image(getClass().getClassLoader().getResource("groundTiles/"+tagToChange+".png").toExternalForm(), model.getScale(), model.getScale(), false, false), tagToChange);
			}
			unitToChange = null;
		}
		if(unitToChange != null) {
			if(tile.getUnit() != null) {
				tile.removeUnit();
			}
			tile.setUnit(unitToChange);
			tagToChange = null;
		}
		
		
	}
	
	public void saveMap() {
		MapSaver saver = new MapSaver();
		if(fileNameText.getText().length() != 0) {
			saver.saveMap(model.getField(),fileNameText.getText());
			fileNameErrorLabel.setText("File successfully created/overwritten");
		}else {
			fileNameErrorLabel.setText("File Name is required:");
		}
	}
	
	public void onSliderChanged() {
		sliderValue.setText(""+((int)slider.getValue()));
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
			if(tile.getType().equals("NAT")) {
				unitToChange = tile.getUnitForCreator();
				tagToChange = null;
			}else if(tile.getType().equals("RUT")) {
				tagToChange = tile.getType();
				unitToChange = null;
			}
			else {
				tagToChange = tile.getType();
			}
		}
	}
	private class FieldClickedEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			Tile tile = (Tile) event.getSource();
			if(tagToChange != null) {
				if(tagToChange.equals("RUT")) {
					if(tile.getUnit() != null) {
						tile.removeUnit();
					}
				}else {
					tile.setNewTile(new Image(getClass().getClassLoader().getResource("groundTiles/"+tagToChange+".png").toExternalForm(), model.getScale(), model.getScale(), false, false), tagToChange);
				}
				unitToChange = null;
			}
			if(unitToChange != null) {
				if(tile.getUnit() != null) {
					tile.removeUnit();
				}
				tile.setUnit(unitToChange);
				tagToChange = null;
			}
			
		}
	}
	
}
