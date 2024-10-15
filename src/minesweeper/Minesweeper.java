package minesweeper;

import java.util.Random;

public class Minesweeper {
	/*
	 * Constant to indicate a mine on the board. Must be >8 as there can be up to 8
	 * mines adjacent to a square on board, hence numbers 1 to 8 are reserved for
	 * this purpose.
	 */
	public static final int MINE = 9;

	private int boardSize;

	/*
	 * Number of mines on the board.
	 */
	private int numMines;

	// private static List<Coordinate> mineLocations;

	/*
	 * Constructor with board size and number of mines as inputs.
	 */
	public Minesweeper(int boardSize, int numMines) {
		super();
		this.boardSize = boardSize;
		this.numMines = numMines;
	}

	/*
	 * Create a 2-D matrix with each square indicating a mine, or number of adjacent
	 * mines.
	 */
	public int[][] createMineMatrix() {
		int[][] mineMatrix = new int[boardSize][boardSize];

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				mineMatrix[i][j] = 0;
			}
		}

		// Generate mines in random locations on minefield.
		for (int z = 0; z < numMines; z++) {
			Random random = new Random();
			int i = random.nextInt(boardSize);
			int j = random.nextInt(boardSize);
			mineMatrix[i][j] = MINE;
//			mineLocations.add(new Coordinate(i, j));
//			System.out.println("Mine " + z + " location: row " + i + ", col " + j);

			// Generate number of adjacent mines on squares adjacent to a mine
			if (i > 0 && mineMatrix[i - 1][j] < MINE)
				mineMatrix[i - 1][j]++;
			if (i < boardSize - 1 && mineMatrix[i + 1][j] < MINE)
				mineMatrix[i + 1][j]++;
			if (j > 0 && mineMatrix[i][j - 1] < MINE)
				mineMatrix[i][j - 1]++;
			if (j < boardSize - 1 && mineMatrix[i][j + 1] < MINE)
				mineMatrix[i][j + 1]++;
			if (i > 0 && j > 0 && mineMatrix[i - 1][j - 1] < MINE)
				mineMatrix[i - 1][j - 1]++;
			if (i > 0 && j < boardSize - 1 && mineMatrix[i - 1][j + 1] < MINE)
				mineMatrix[i - 1][j + 1]++;
			if (i < boardSize - 1 && j > 0 && mineMatrix[i + 1][j - 1] < MINE)
				mineMatrix[i + 1][j - 1]++;
			if (i < boardSize - 1 && j < boardSize - 1 && mineMatrix[i + 1][j + 1] < MINE)
				mineMatrix[i + 1][j + 1]++;
		}

		return mineMatrix;
	}

	/*
	 * Create the 2D revealedMatrix array, same dimension as the main mine matrix,
	 * with all elements false, as no squares are opened yet.
	 */
	public boolean[][] createRevealedMatrix() {
		boolean[][] revealedMatrix = new boolean[boardSize][boardSize];

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				revealedMatrix[i][j] = false;
			}
		}
		return revealedMatrix;
	}

	/*
	 * Opens a square on the minefield and reveal adjacent squares if necessary.
	 * This is a recursive function. First, the inputted square is opened, and, if
	 * the current square has no adjacent mines, the function calls itself again
	 * checking the adjacent square, and so on, until the adjacent square has an
	 * adjacent mine.
	 * 
	 * @return true if square is not a mine, false if a mine.
	 */
	public static boolean openSquare(int[][] mineMatrix, boolean[][] revealedMatrix, int x, int y) {
//		System.out.println("openSquare | start | x: " + x + " | y: " + y);
		if (mineMatrix[x][y] == MINE) {
			return false;
		}

		// open the square
		revealedMatrix[x][y] = true;

		// this square has an adjacent mine
		if (mineMatrix[x][y] > 0) {
			return true;
		}

		// check the surrounding squares
		if (x > 0 && revealedMatrix[x - 1][y] == false && mineMatrix[x - 1][y] == 0)
			openSquare(mineMatrix, revealedMatrix, x - 1, y);
		if (x < mineMatrix.length - 1 && revealedMatrix[x + 1][y] == false && mineMatrix[x + 1][y] == 0)
			openSquare(mineMatrix, revealedMatrix, x + 1, y);
		if (y > 0 && revealedMatrix[x][y - 1] == false && mineMatrix[x][y - 1] == 0)
			openSquare(mineMatrix, revealedMatrix, x, y - 1);
		if (y < mineMatrix.length - 1 && revealedMatrix[x][y + 1] == false && mineMatrix[x][y + 1] == 0)
			openSquare(mineMatrix, revealedMatrix, x, y + 1);
		if (x > 0 && y > 0 && revealedMatrix[x - 1][y - 1] == false && mineMatrix[x - 1][y - 1] == 0)
			openSquare(mineMatrix, revealedMatrix, x - 1, y - 1);
		if (x > 0 && y < mineMatrix.length - 1 && revealedMatrix[x - 1][y + 1] == false
				&& mineMatrix[x - 1][y + 1] == 0)
			openSquare(mineMatrix, revealedMatrix, x - 1, y + 1);
		if (x < mineMatrix.length - 1 && y > 0 && revealedMatrix[x + 1][y - 1] == false
				&& mineMatrix[x + 1][y - 1] == 0)
			openSquare(mineMatrix, revealedMatrix, x + 1, y - 1);
		if (x < mineMatrix.length - 1 && y < mineMatrix.length - 1 && revealedMatrix[x + 1][y + 1] == false
				&& mineMatrix[x + 1][y + 1] == 0)
			openSquare(mineMatrix, revealedMatrix, x + 1, y + 1);

		return true;
	}

	/*
	 * Checks if the player has won the current game.
	 */
	public boolean hasWonGame(int[][] mineMatrix, boolean[][] revealedMatrix) {
		for (int i = 0; i < mineMatrix.length; i++) {
			for (int j = 0; j < mineMatrix[i].length; j++) {
				if (revealedMatrix[i][j] == false && mineMatrix[i][j] < MINE)
					return false;
			}
		}
		return true;
	}
}
