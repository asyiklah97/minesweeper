package minesweeper;

import java.util.Scanner;

public class MinesweeperRunner {
	private static int MAX_BOARD_SIZE = 26;

	private static int boardSize;

	/*
	 * This 2D array contains mine positions, indicated by a 9. The matrix elements
	 * around a mine indicate the number of adjacent mines.
	 */
	private static int[][] mineMatrix;

	/*
	 * This 2D array indicates if the square has been opened (true) or not (false).
	 */
	private static boolean[][] revealedMatrix;

	/*
	 * Number of mines on the board.
	 */
	private static int numMines;

	// private static List<Coordinate> mineLocations;

	private static boolean winGame = false;

	private static boolean loseGame = false;

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boardSize = -1;
		while (boardSize <= 0 || boardSize > MAX_BOARD_SIZE) {
			System.out.println("Enter board size (max. " + MAX_BOARD_SIZE + "): ");
//		boardSize = 3;
			boardSize = sc.nextInt();
//			System.out.println("boardSize: " + boardSize);
		}
		numMines = boardSize * boardSize;
		while (numMines > (int) (0.35 * boardSize * boardSize)) {
			System.out.println("Enter number of mines (max. 35% of total number of squares): ");
//			numMines = 3;
			numMines = sc.nextInt();
		}
//		mineLocations = new ArrayList<Minesweeper.Coordinate>();

		Minesweeper m = new Minesweeper(boardSize, numMines);

		mineMatrix = m.createMineMatrix();
		revealedMatrix = m.createRevealedMatrix();

		while (!winGame && !loseGame) {
			printMatrix();

			try {
				String square = "";
//			int row = -1, col = -1;
				while (square.length() < 2) {
					System.out.println("Enter square to be opened (e.g. A1, B2, etc): ");
					square = sc.next();
				}
				int row = square.substring(0, 1).toLowerCase().toCharArray()[0] - 'a';
				int col = Integer.parseInt(square.substring(1, square.length())) - 1;
//				System.out.println(row + " " + col);

				while (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
					System.out.println("Wrong entry - Enter square to be opened: ");
					square = sc.next();

					row = square.substring(0, 1).toLowerCase().toCharArray()[0] - 'a';
					col = Integer.parseInt(square.substring(1, square.length())) - 1;
//					System.out.println(row + " " + col);
				}
				loseGame = !Minesweeper.openSquare(mineMatrix, revealedMatrix, row, col);
				if (loseGame) {
					System.out.println("Oh no, you detonated a mine. Game over!");
					return;
				}

				winGame = m.hasWonGame(mineMatrix, revealedMatrix);
				if (winGame) {
					System.out.println("Congrats, you win!");
					return;
				}
			} catch (NumberFormatException e) {
				System.out.println("Wrong square entry, please try again ");
			}
		}
	}

	/*
	 * Print the minefield matrix on screen.
	 */
	private static void printMatrix() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (revealedMatrix[i][j])
					System.out.print(mineMatrix[i][j] + " ");
				else
					System.out.print("_ ");
			}
			System.out.println();
		}
	}
}
