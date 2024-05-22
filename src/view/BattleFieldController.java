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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
	private Model model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.model = Model.getInstance();
		this.background.setPadding(new Insets(100.0,100.0,100.0,100.0));
		battlefield.setPadding(new Insets(0.0,0.0,0.0,0.0));
		slider.setValue(model.getScale());
		sliderValue.setText("" + (int)slider.getValue());

		for (Tile lines[] : battlefield.getTiles()) {
		      for (Tile field : lines) {
		        field.setOnMouseClicked(new FieldClickedEventHandler());
		      }
		}
		
	}
	
	public void loadMap() throws IOException {
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
	
	private class FieldClickedEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			Tile tile = (Tile) event.getSource();
			model.printPossibleMoves(tile.getRow(), tile.getColumn(),tile);
			System.out.println("With Unit: " + tile.getUnit());

		}
	}

}
