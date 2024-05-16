package view;

import java.net.URL;
import java.util.ResourceBundle;

import Logic.Model;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class BattleFieldController implements Initializable {
	
	@FXML
	private BattleField battlefield;
	@FXML
	private BorderPane background;
	private Model model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.model = Model.getInstance();
		this.background.setPadding(new Insets(100.0,100.0,100.0,100.0));
		battlefield.setPadding(new Insets(0.0,0.0,0.0,0.0));
		

		for (Tile lines[] : battlefield.getTiles()) {
		      for (Tile field : lines) {
		        field.setOnMouseClicked(new FieldClickedEventHandler());
		      }
		}
		
	}
	
	private class FieldClickedEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			Tile tile = (Tile) event.getSource();
			model.printPossibleMoves(tile.getRow(), tile.getColumn(),tile);

		}
	}

}
