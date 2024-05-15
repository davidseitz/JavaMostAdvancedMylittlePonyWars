package javaMostAdvancedMyLittlePonyWars;

import Logic.field;

public class Model {
	private static int boardWidth;
	private static int boardHeight;
	private field board;
	
	public static int getBoardWidth() {
		return boardWidth;
	}

	public static void setBoardWidth(int boardWidth) {
		Model.boardWidth = boardWidth;
	}

	public static int getBoardHeight() {
		return boardHeight;
	}

	public static void setBoardHeight(int boardHeight) {
		Model.boardHeight = boardHeight;
	}

	public field getBoard() {
		return board;
	}


	
	Model(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        this.board = board.getInstance();
    }
	
	
}
