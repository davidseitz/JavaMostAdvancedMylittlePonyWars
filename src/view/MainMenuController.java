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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {
	
	@FXML
	private Button startButton;
	@FXML
	private Button quitButton;
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
		levelOne.setImage(new Image(getClass().getClassLoader().getResource("rook_white.png").toExternalForm()));
		levelTwo.setImage(new Image(getClass().getClassLoader().getResource("rook_white.png").toExternalForm()));
		levelThree.setImage(new Image(getClass().getClassLoader().getResource("rook_white.png").toExternalForm()));
	}
	
	public void quitGame() {
		Platform.exit();
        System.exit(0);
	}
	
	private class LevelSelectedEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			Object level = event.getSource();
			
			BattleFieldLoader mapLoader = new BattleFieldLoader();
			try {
				mapLoader.loadField();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			if(level == levelOne) {
				Parent root;
				model.setLevel(1);
				model.setHeight(10);
				model.setWidth(19);
				try {
					root = FXMLLoader.load(getClass().getResource("battleField.fxml"));
					Scene newScene = new Scene(root);
					Scene currentScene = levelOne.getScene();
					Stage levelOneStage = (Stage) currentScene.getWindow();
					levelOneStage.setScene(newScene);
					levelOneStage.setFullScreen(true);
					levelOneStage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(level == levelTwo) {
				Parent root;
				model.setLevel(2);
				model.setHeight(17);
				model.setWidth(19);
				try {
					root = FXMLLoader.load(getClass().getResource("battleField.fxml"));
					Scene newScene = new Scene(root);
					Scene currentScene = levelOne.getScene();
					Stage levelTwoStage = (Stage) currentScene.getWindow();
					levelTwoStage.setScene(newScene);
					levelTwoStage.setFullScreen(true);
					levelTwoStage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(level == levelThree) {
				Parent root;
				model.setLevel(3);
				model.setHeight(14);
				model.setWidth(26);
				try {
					root = FXMLLoader.load(getClass().getResource("battleField.fxml"));
					Scene newScene = new Scene(root);
					Scene currentScene = levelOne.getScene();
					Stage levelThreeStage = (Stage) currentScene.getWindow();
					levelThreeStage.setScene(newScene);
					levelThreeStage.setFullScreen(true);
					levelThreeStage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

	
}
