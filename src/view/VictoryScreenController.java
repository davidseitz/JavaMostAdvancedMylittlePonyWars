package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Logic.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
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
import javafx.stage.Screen;
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
		Image backgroundImage = new Image(getClass().getClassLoader().getResource("uiFiles/starwarsPixelart.jpeg").toExternalForm());
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		background.setBackground(new Background(new BackgroundImage(backgroundImage,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize)));
		
		model = Model.getInstance();
		
		int winner = model.checkVictory();
		if(winner == 1) {
			victoryLabel.setText("Rebels Wins");
		}else if (winner == 2) {
			victoryLabel.setText("Empire Wins");
		}

		mainMenu.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource("uiFiles/backToMenuButton.png").toExternalForm())));
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
