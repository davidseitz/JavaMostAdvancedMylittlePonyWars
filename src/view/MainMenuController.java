package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Logic.Model;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
	private ImageView levelPreView;
	@FXML
	private ComboBox<String> levelSelection;
	@FXML
	private BorderPane background;
	@FXML
	private VBox vboxButton;
	
	private boolean isLevelSelection = false;
	private Model model;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.isLevelSelection = false;
		this.model = Model.getInstance();
		
		levelSelection.setVisible(false);
		levelPreView.setVisible(false);
		
		Image backgroundImage = new Image(getClass().getClassLoader().getResource("uiFiles/empirePixelart.jpeg").toExternalForm());
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		background.setBackground(new Background(new BackgroundImage(backgroundImage,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize)));
		
		quitButton.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource("uiFiles/quitButton.png").toExternalForm())));
		startButton.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource("uiFiles/startButton.png").toExternalForm())));
		mapCreation.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource("uiFiles/mapCreatorButton.png").toExternalForm())));
	}
	
	
	public void startGame() {
		if(!this.isLevelSelection) {
			isLevelSelection = true;
			quitButton.setVisible(false);
			mapCreation.setVisible(false);
			startButton.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource("uiFiles/selectLevelButton.png").toExternalForm())));
			levelPreView.setVisible(true);
			levelSelection.setVisible(true);
			setMapsFromDir();
		}else {
			model.setScale(40);
			
			BattleFieldLoader mapLoader = new BattleFieldLoader();
			try {
				mapLoader.loadField("resources/maps/"+getSelectedLevel()+ ".map");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("battleField.fxml"));
				Scene newScene = new Scene(root);
				Scene currentScene = startButton.getScene();
				Stage levelStage = (Stage) currentScene.getWindow();
				levelStage.setScene(newScene);
				levelStage.setFullScreen(true);
				levelStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private String getSelectedLevel() {
		return levelSelection.getValue().replace(" ", "_");
	}
	
	public void setMapsFromDir() {
		File[] levels = (new File("resources/maps/")).listFiles();
		
		ObservableList<String> items = FXCollections.observableArrayList();
		
		for(File level : levels) {
			if(level.getName().endsWith(".map")) {
				items.add(level.getName().substring(0,level.getName().length()-4).replace("_", " "));
			}
		}
		levelSelection.setItems(items);
		levelSelection.setValue(items.getFirst());
		levelSelection.setPrefSize(startButton.getWidth()+150.0, startButton.getHeight());
		levelSelection.setStyle("-fx-font: 35px \"Roboto Mono\";-fx-background-color: transparent");
		vboxButton.setSpacing(100);
		vboxButton.setPadding(new Insets(500,0,0,0));
		
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
	
}
