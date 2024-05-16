package view;

import java.net.URL;
import java.util.ResourceBundle;

import Logic.Model;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;

public class BattleFieldController implements Initializable {
	
	@FXML
	private BattleField battlefield;
	private Model model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.model = Model.getInstance();
		if(model.getLevel()==1) {
			battlefield.setPadding(new Insets(200.0,25.0,25.0,300.0));
		}
		else if(model.getLevel()==2) {
			battlefield.setPadding(new Insets(5.0,25.0,25.0,300.0));
		}
		else if(model.getLevel()==3) {
			battlefield.setPadding(new Insets(100.0,25.0,25.0,50.0));
		}

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
			//System.out.println(tile.getRow());
			//System.out.println(tile.getColumn());
			System.out.println("Tile klicked");
			model.printPossibleMoves(tile.getRow(), tile.getColumn(), tile.getUnit());
		}
		
	}

}
