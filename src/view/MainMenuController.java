package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Logic.Model;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {
	
	@FXML
	private Button startButton;
	@FXML
	private Button quitButton;
	@FXML
	private Button mapCreation;
	@FXML
	private ImageView levelOne;
	@FXML
	private ImageView levelTwo;
	@FXML
	private ImageView levelThree;
	private Model model;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.model = Model.getInstance();
		levelOne.setOnMouseClicked(new LevelSelectedEventHandler());
		levelTwo.setOnMouseClicked(new LevelSelectedEventHandler());
		levelThree.setOnMouseClicked(new LevelSelectedEventHandler());
	}
	
	
	public void startGame() {
		quitButton.setVisible(false);
		startButton.setVisible(false);
		levelOne.setImage(new Image(getClass().getClassLoader().getResource("groundTiles/PL.png").toExternalForm()));
		levelTwo.setImage(new Image(getClass().getClassLoader().getResource("groundTiles/WT.png").toExternalForm()));
		levelThree.setImage(new Image(getClass().getClassLoader().getResource("groundTiles/WO.png").toExternalForm()));
	}
	
	public void mapCreation() {
		model.setWidth(15);
		model.setHeight(15);
		
		BattleFieldLoader loader = new BattleFieldLoader();
		loader.customMap();
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("mapCreator.fxml"));
			Scene newScene = new Scene(root);
			Scene currentScene = mapCreation.getScene();
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
	
	public void quitGame() {
		Platform.exit();
        System.exit(0);
	}
	
	private class LevelSelectedEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			Object level = event.getSource();
			
			if(level == levelOne) {
				model.setLevel(1);
			}
			else if(level == levelTwo) {
				model.setLevel(2);
			}
			else if(level == levelThree) {
				model.setLevel(0);
			}
			
			model.setScale(40);
			
			BattleFieldLoader mapLoader = new BattleFieldLoader();
			try {
				mapLoader.loadField(model.getLevel());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("battleField.fxml"));
				Scene newScene = new Scene(root);
				Scene currentScene = levelOne.getScene();
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
		
	}

	
}
