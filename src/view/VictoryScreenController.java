package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Logic.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VictoryScreenController implements Initializable{
	
	@FXML
	private BorderPane background;
	@FXML
	private Label victoryLabel;
	@FXML
	private Button mainMenu;
	
	private Model model;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = Model.getInstance();
		
		int winner = model.checkVictory();
		String winnerBackground = "uiFiles/empirePixelart.jpeg";
		if(winner == 1) {
			victoryLabel.setText("Rebels Wins");
			winnerBackground = "uiFiles/rebelPixelart.png";
		}else if (winner == 2) {
			victoryLabel.setText("Empire Wins");
			winnerBackground = "uiFiles/empirePixelart.jpeg";
		}

		mainMenu.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource("uiFiles/backToMenuButton.png").toExternalForm())));
		
		Image backgroundImage = new Image(getClass().getClassLoader().getResource(winnerBackground).toExternalForm());
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		background.setBackground(new Background(new BackgroundImage(backgroundImage,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize)));
	}
	
	public void backToMenu() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
			Scene newScene = new Scene(root);
			Scene currentScene = mainMenu.getScene();
			Stage levelStage = (Stage) currentScene.getWindow();
			levelStage.setScene(newScene);
			levelStage.setFullScreen(true);
			levelStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
