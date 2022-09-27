package jura.hostic;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	
	public static void main (String[] args) {
		Scanner input = new Scanner (System.in);
		input.close();
		long start = System.currentTimeMillis();
		Scan scan = new Scan (565, 215, 450, 450);
		while (true) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int nm = scan.getDimensions();
			System.out.println(nm);
			if (nm == 0) continue;
			Board board = new Board(nm);
			boolean uspjeh;
			uspjeh = scan.getPositions(nm, board);
			System.out.print(uspjeh);
			if (uspjeh) {
				for (int i = 0; i < nm; i++) {
					for (int j = 0; j < nm; j++) {
						System.out.printf("%d ", board.board[i][j].value);
					}
					System.out.print("\n");
				}
				long solveBegin = System.currentTimeMillis();
				board.Solve(0, 0, 0, 0);
				scan.click(nm, board);
				long end = System.currentTimeMillis();
				long solveEnd = System.currentTimeMillis();
				System.out.printf ("Time to solve: %d ms\n", solveEnd - solveBegin);
				System.out.printf ("Time elapsed: %d ms\n", end - start);
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				start = System.currentTimeMillis();
			}
		}
	}
}
