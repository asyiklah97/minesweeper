package minesweeper.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import minesweeper.Minesweeper;

public class MinesweeperTest {
//	Minesweeper minesweeper = new Minesweeper();

	@Test
	public void testGenerateMineMatrix_boardSize_1() {
		// Create a new Minesweeper object with board size 1 and 0 mine.
		Minesweeper m = new Minesweeper(1, 0);

		int[][] mineMatrix = m.createMineMatrix();

//		List<Integer> dim = new ArrayList<Integer>();
//		dim.add(mineMatrix.length, 1);
//		dim.add(mineMatrix[0].length,1);
//		assertEquals(mineMatrix, dim);

		int[] dim = new int[2];
		dim[0] = mineMatrix.length;
		dim[1] = mineMatrix[0].length;
		int[] expected = { 1, 1 };
		assertArrayEquals(dim, expected);
	}

	@Test
	public void testCreateMineMatrix_boardSize_26() {
		// Create a new Minesweeper object with board size 26 and 0 mine.
		Minesweeper m = new Minesweeper(26, 0);

		int[][] mineMatrix = m.createMineMatrix();

//		List<Integer> dim = new ArrayList<Integer>();
//		dim.add(mineMatrix.length, 1);
//		dim.add(mineMatrix[0].length,1);
//		assertEquals(mineMatrix, dim);

		int[] dim = new int[2];
		dim[0] = mineMatrix.length;
		dim[1] = mineMatrix[0].length;
		int[] expected = { 26, 26 };
		assertArrayEquals(dim, expected);
	}

//	@Test public void testGenerateMineMatrix_boardSize_27() {}

//	@Test public void testGenerateMineMatrix_boardSize_0() { }

	@Test
	public void testCreateRevealedMatrix() {
		// Create a new Minesweeper object with board size 5x5 and 0 mine.
		Minesweeper m = new Minesweeper(5, 0);

		boolean[][] revealedMatrix = m.createRevealedMatrix();

		for (int i = 0; i < revealedMatrix.length; i++) {
			for (int j = 0; j < revealedMatrix[0].length; j++) {
				if (revealedMatrix[i][j] == true) {
					assertTrue(false);
					return;
				}
			}
		}
		assertTrue(true);
	}

//	@Test public void testNumMines_mineMatrix(int numMines, int[][] mineMatrix) {}

	@Test
	public void testOpenSquare_mine() {
		// Create a new Minesweeper object with board size 4x4 and 1 mine.
		Minesweeper m = new Minesweeper(4, 1);

		int[][] mineMatrix = m.createMineMatrix();
		boolean[][] revealedMatrix = m.createRevealedMatrix();

		for (int i = 0; i < revealedMatrix.length; i++) {
			for (int j = 0; j < revealedMatrix[0].length; j++) {
				if (mineMatrix[i][j] == Minesweeper.MINE) {
					assertTrue(Minesweeper.openSquare(mineMatrix, revealedMatrix, i, j) == false);
					return;
				}
			}
		}
	}

	@Test
	public void testOpenSquare_adjacentMine() {
		// Create a new Minesweeper object with board size 4x4 and 5 mines.
		Minesweeper m = new Minesweeper(4, 5);

		int[][] mineMatrix = m.createMineMatrix();
		boolean[][] revealedMatrix = m.createRevealedMatrix();

		for (int i = 0; i < revealedMatrix.length; i++) {
			for (int j = 0; j < revealedMatrix[0].length; j++) {
				if (mineMatrix[i][j] < Minesweeper.MINE && mineMatrix[i][j] > 0) {
					assertTrue(Minesweeper.openSquare(mineMatrix, revealedMatrix, i, j));
					return;
				}
			}
		}
	}

	@Test
	public void testOpenSquare_empty_boardNoMines() {
		// Create a new Minesweeper object with board size 4x4 and 0 mine.
		Minesweeper m = new Minesweeper(4, 0);

		int[][] mineMatrix = m.createMineMatrix();
		boolean[][] revealedMatrix = m.createRevealedMatrix();

//		boolean openSquare = false;

//		for (int i = 0; i < mineMatrix.length; i++) {
//			for (int j = 0; j < mineMatrix[0].length; j++) {
//				if (mineMatrix[i][j] == 0) {
//					m.openSquare(mineMatrix, revealedMatrix, i, j);
//					openSquare = true;
//				}
//			}
//		}

//		int i = 0, j = 0;
//		while (!openSquare && mineMatrix[i][j] != 0) {
//			if (i < 4)
//				i++;
//			else if (j < 4) {
//				j++;
//				i = 0;
//			}
//		}

		boolean openSquare = Minesweeper.openSquare(mineMatrix, revealedMatrix, 1, 2);

		for (int i = 0; i < revealedMatrix.length; i++) {
			for (int j = 0; j < revealedMatrix[0].length; j++) {
				if (revealedMatrix[i][j] == false) {
					assertTrue(false);
					return;
				}
			}
		}
		assertTrue(true);
	}

	@Test
	public void testOpenSquare_empty_board1Mine() {
		// Create a new Minesweeper object with board size 4x4 and 1 mine.
		Minesweeper m = new Minesweeper(4, 1);

		int[][] mineMatrix = m.createMineMatrix();
		boolean[][] revealedMatrix = m.createRevealedMatrix();

		// open a square that is empty
		boolean openSquare;
		int x = 1, y = 1;
//		openSquare = Minesweeper.openSquare(mineMatrix, revealedMatrix, x, y);
//		if (openSquare == false) {
//		System.out.println("testOpenSquare_empty_board1Mine | mineMatrix[1][1]: " + mineMatrix[x][y]);
		if (mineMatrix[x][y] > 0) {
			x = 3;
			y = 3;
		}
//		System.out.println("testOpenSquare_empty_board1Mine | x: " + x + " y: " + y);
		openSquare = Minesweeper.openSquare(mineMatrix, revealedMatrix, x, y);

		// save mine position in variables minePosX, minePosY
//		int minePosX = -1, minePosY = -1;
//		for (int i = 0; i < mineMatrix.length; i++) {
//			for (int j = 0; j < mineMatrix[0].length; j++) {
//				if (mineMatrix[i][j] == Minesweeper.MINE) {
//					minePosX = i;
//					minePosY = j;
//					return;
//				}
//			}
//		}

		// check if minefield is opened correctly
		for (int i = 0; i < revealedMatrix.length; i++) {
			for (int j = 0; j < revealedMatrix[0].length; j++) {
				if (mineMatrix[i][j] == Minesweeper.MINE)
					continue;
				if (mineMatrix[i][j] == 0 && revealedMatrix[i][j] == false) {
					assertTrue(false);
					return;
				}
			}
		}
		assertTrue(true);
	}

//	@Test	public void testGameOver() {}

	@Test
	public void testWinGame() {
		// Create a new Minesweeper object with board size 4 and 0 mine.
		Minesweeper m = new Minesweeper(4, 0);

		int[][] mineMatrix = m.createMineMatrix();
		boolean[][] revealedMatrix = m.createRevealedMatrix();

		boolean openSquare = Minesweeper.openSquare(mineMatrix, revealedMatrix, 1, 2);

		boolean winGame = m.hasWonGame(mineMatrix, revealedMatrix);
		assertTrue(winGame);
	}
}
