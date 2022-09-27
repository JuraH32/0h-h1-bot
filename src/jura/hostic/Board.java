package jura.hostic;

public class Board {
	
	int dimension;
	String[] boardS;
	public Square[][] board;
	int filled;
	
	public Board (int dimension)  {
		this.dimension = dimension;
		this.filled = 0;
		this.board = new Square[this.dimension][this.dimension];
	}
	public void setSquare (int x, int y, int value) {
		this.board [y] [x] = new Square (value);
	}
	
	public boolean Solve(int x, int y, int lx, int ly) {
		boolean work = true, r, c;
		r = checkRow (ly);
		c = checkColumn (lx);
		//System.out.printf("Row: %b\nColumn:%b\nPos: %d %d\n\n", r, c, y, x);
		if (r == false || c == false) return false;
		if (y == this.dimension) return true;
		//System.out.printf("Row: %b\nColumn:%b\nPos: %d %d\n\n", r, c, y, x);
		if (this.board[y][x].value > 0) {
			return Solve ((x + 1) % this.dimension, y + ((x + 1) / this.dimension), x, y);
		}
		this.board[y][x].value = 1;
		work = Solve ((x + 1) % this.dimension, y + ((x + 1) / this.dimension), x, y);
		if (work == false) {
			this.board[y][x].value = 2;
			work = Solve ((x + 1) % this.dimension, y + ((x + 1) / this.dimension), x, y);
			if (work == false) {
				this.board[y][x].value = 0;
			}
		}
		return work;
	}
	
	private boolean checkRow (int row) {
		int red = 0, blue = 0;
		int conRed = 0, conBlue = 0;
		int val;
		int same[] = new int [this.dimension];
		for (int i = 0; i < this.dimension; i++) {
			val = this.board [row] [i].value;
			if (val == 1) {
				red++;
				conRed++;
				conBlue = 0;
			}
			if (val == 2) {
				blue++;
				conBlue++;
				conRed = 0;
			}
			if (val == 0) {
				conBlue = 0;
				conRed = 0;
			}
			if (conBlue == 3 || conRed == 3) {
				return false;
			}
			for (int j = 0; j < this.dimension; j++) {
				if (j != row && val == this.board [j] [i].value) same [j]++;
				if (same [j] == this.dimension && red + blue == this.dimension) return false;
			}
		}
		if (red + blue == this.dimension && red != blue) return false;
		return true;
	}
	
	private boolean checkColumn (int column) {
		int red = 0, blue = 0;
		int conRed = 0, conBlue = 0;
		int val;
		int same[] = new int [this.dimension];
		for (int i = 0; i < this.dimension; i++) {
			val = this.board [i] [column].value;
			if (val == 1) {
				red++;
				conRed++;
				conBlue = 0;
			}
			if (val == 2) {
				blue++;
				conBlue++;
				conRed = 0;
			}
			if (val == 0) {
				conBlue = 0;
				conRed = 0;
			}
			if (conBlue == 3 || conRed == 3) {
				return false;
			}
			for (int j = 0; j < this.dimension; j++) {
				if (j != column && val == this.board [i] [j].value) same [j]++;
				if (same [j] == this.dimension && red + blue == this.dimension) return false;
			}
		}
		if (red + blue == this.dimension && red != blue) return false;
		return true;
	}
	
	public int getClicks (int j, int i) {
		if (board [i] [j].StartValue > board [i] [j].value) return 2 + board [i] [j].value - board [i] [j].StartValue;
		return board [i] [j].value - board [i] [j].StartValue;
	}
}
