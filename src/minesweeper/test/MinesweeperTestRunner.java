package minesweeper.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MinesweeperTestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(MinesweeperTest.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}
}