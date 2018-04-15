// Creates grid that will be represented in the applet
//
// CS 201 Final Project
//
// Andre Xiao, Grant Gutstein (Section B)

//For scoring: Making tile 2n = (n-1)2n points

import java.util.Random;
import java.util.Stack;

public class Grid {

	protected Tile [][] tiles; //2d array to store coordinates of tiles
	protected int score; //score
	protected boolean changed; //keeps track if the grid changed or not after left, right, up, or down
	protected Stack<Tile[][]> undoStack = new Stack<Tile[][]>(); //stack of tiles[][] for undo
	protected Stack<Integer> scoreStack = new Stack<Integer>(); // stack for score for undo

	public Grid() {
		//constructor for Grid
		Random r = new Random();
		tiles = new Tile[4][4];
		tiles[r.nextInt(4)][r.nextInt(4)] = new Tile();
		undoStack.push(copy(tiles));
		changed = false;
		score = 2;
		scoreStack.push(score);
	}

	public Tile value (int row, int col) {
		//returns value of tile at index row, col
		return tiles[row][col];
	}

	public void setVal(Tile t, int row, int col) {
		//changes val of index row, col
		tiles[row][col] = t;
	}

	public int rowLength() {
		//returns length of the first array
		return tiles.length;
	}

	public int colLength(int row) {
		//returns length of secondary arrays of index row
		return tiles[row].length;
	}

	public void addVal() {
		//adds value to grid at random spot that is null
		Random r = new Random();
		int row = r.nextInt(4);
		int col = r.nextInt(4);
		while (value(row, col) != null) {
			row = r.nextInt(4);
			col = r.nextInt(4);
		}
		setVal(new Tile(), row, col);
	}

	public void tileRight(int r, int c) {
		//shift a tile to the right as far as it can go
		//meaning until it reaches the side or another value
		//if values are same, combine them by adding them together
		Tile val = value(r, c);
		while (c < tiles[r].length - 1 && value(r, c+1) == null){
			if (value(r, c) != null) {
				setVal(val, r, c+1);
				setVal(null, r, c);
				changed = true;
			} 
			c++;
		}
		if (c < tiles[r].length-1)
			combine(1, r, c);
	}

	public void right() {
		//goes through entire grid to shift each tile right
		undoStack.push(copy(tiles));
		for (int r = 0; r < tiles.length; r++){
			for (int c = tiles[r].length - 2; c >= 0; c--){
				tileRight(r, c);
			}
		}
		if (changed)
			addVal();    
		changed = false;
		resetCombined();
	}

	public void tileLeft(int r, int c) {
		//shift a tile to the left as far as it can go
		//meaning until it reaches the side or another value
		//if values are same, combine them by adding them together
		Tile val = value(r, c);
		while (c > 0 && value(r, c-1) == null){
			if (value(r, c) !=null) {
				setVal(val, r, c-1);
				setVal(null, r, c);
				changed = true;
			} 
			c--;
		}
		if (c > 0)
			combine(2, r, c);
	}

	public void left() {
		//calls tileLeft to shift every tile in the Grid
		undoStack.push(copy(tiles));
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 1; c < tiles[r].length; c++) {
				tileLeft(r, c);   

			}
		}
		if (changed)
			addVal();
		changed = false;
		resetCombined();
	}

	public void tileUp(int r, int c) {
		//shift a tile to up as far as it can go
		//meaning until it reaches the side or another value
		//if values are same, combine them by adding them together
		Tile val = value(r, c);
		while (r > 0 && value(r-1, c) == null){
			if (value(r, c) != null) {
				setVal(val, r-1, c);
				setVal(null, r, c);
				changed = true;
			} 
			r--;
		}
		if (r > 0)
			combine(3, r, c);
	}

	public void up() {
		//calls tileUp to move every tile up in the Grid
		undoStack.push(copy(tiles));
		for (int r = 1; r < tiles.length; r++){
			for (int c = 0; c < tiles[r].length; c++){
				tileUp(r, c);
			}
		}
		//if (!Arrays.equals(test, tiles))
		if (changed)
			addVal();
		changed = false;
		resetCombined();
	}

	public void tileDown(int r, int c) {
		//shift a tile down as far as it can go
		//meaning until it reaches the side or another value
		//if values are same, combine them by adding them together
		Tile val = value(r, c);
		while (r < tiles.length - 1 && value(r+1, c) == null){
			if (value(r, c) != null) {
				setVal(val, r+1, c);
				setVal(null, r, c);
				changed = true;
			}
			r++;
		}
		if (r < tiles.length-1)
			combine(4, r, c);
	}

	public void down() {
		//calls tileDown to move all tiels in the Grid downwards
		undoStack.push(copy(tiles));
		for (int r = tiles.length-2; r >=0; r--){
			for (int c = 0; c < tiles[r].length; c++){
				tileDown(r, c);
			}
		}
		//if (!Arrays.equals(test, tiles))
		if (changed)
			addVal();
		changed = false;
		resetCombined();
	}

	public void combine(int num, int r, int c) {
		//combines numbers by adding them together depending on
		//if right, left, up, or down is pressed
		switch(num) {
		case 1:
			if (value(r, c+1) != null && value(r, c) != null) {
				if (!value(r, c+1).isCombined()) {
					if (value(r, c).value() == value(r, c+1).value()) {
						value(r, c+1).setVal(value(r, c).value() + value(r, c+1).value());
						setVal(null, r, c);
						value(r, c+1).justCombined();	
					}
				}
			}
			break;
		case 2:
			if (value(r, c-1) != null && value(r, c) != null) {
				if (!value(r, c-1).isCombined()) {
					if (value(r, c).value() == value(r, c-1).value()) {
						value(r, c-1).setVal(value(r, c).value() + value(r, c-1).value());
						setVal(null, r, c);
						value(r, c-1).justCombined();
					}
				}
			}
			break;
		case 3:
			if (value(r-1, c) != null && value(r, c) != null) {
				if (!value(r-1, c).isCombined()) {
					if (value(r, c).value() == value(r-1, c).value()) {
						value(r-1, c).setVal(value(r, c).value() + value(r-1, c).value());
						setVal(null, r, c);
						value(r-1, c).justCombined();
					}
				}
			}
			break;
		case 4:
			if (value(r+1, c) != null && value(r, c) != null){
				if (!value(r+1, c).isCombined()) {
					if (value(r, c).value() == value(r+1, c).value()) {
						value(r+1, c).setVal(value(r, c).value() + value(r+1, c).value());
						setVal(null, r, c);
						value(r+1, c).justCombined();
					}
				}
			}
			break;
		}
	}

	public int score() {
		//keeps track of the score
		score += 2 * Math.sqrt(score);
		scoreStack.push(score);
		return score;
	}

	public void resetCombined() {
		//reset (all to false) whether or not the tiles have been combined or not
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				if (value(r, c) != null)
					value(r, c).resetCombined();
			}
		}
	}

	public void printGrid() {
		//prints grid for testing
		for (int r = 0; r < tiles.length; r++){
			for (int c = 0; c < tiles[r].length; c++){
				if (value(r, c) == null)
					System.out.print("*" + "\t");
				else
					System.out.print(value(r, c).value() + "\t");
			}
			System.out.println();
		}
	} 

	public int restart() {
		//resets board by deleting all tiles and randomly placing a tile in the grid
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				setVal(null, r, c);
			}
		}
		Random r = new Random();
		tiles[r.nextInt(4)][r.nextInt(4)] = new Tile();
		return score = 2;
	}

	public int undo() {
		//undoes a move and goes back to the previous grid
		//uses a stack to do so
		if (!undoStack.empty())
			tiles = undoStack.pop();
		if (scoreStack.size() > 2)
			scoreStack.pop();
		score = scoreStack.pop();
		scoreStack.push(score);
		return score;
	}

	public Tile [][] copy(Tile [][] t) {
		//returns a copied version of the tile[][]
		Tile [][] clone = new Tile[t.length][t[0].length];

		for (int r = 0; r < t.length; r++) {
			for (int c = 0; c < t[r].length; c++) {
				if (t[r][c] != null) {
					clone[r][c] = new Tile();
					clone[r][c].setVal(t[r][c].value());
				} else {
					clone[r][c] = t[r][c];
				}
			}
		}
		return clone;
	}

	public static void main (String [] args) {
		Grid g = new Grid();
		System.out.println("Start");
		g.printGrid();
		System.out.println();
		for(int i = 0; i < 3; i++) {
			g.right();
			System.out.println("Right");
			g.printGrid();
			System.out.println();
			g.left();
			System.out.println("Left");
			g.printGrid();
			System.out.println();
			g.up();
			System.out.println("Up");
			g.printGrid();
			System.out.println();
			g.down();
			System.out.println("Down");
			g.printGrid();
			System.out.println();
		}

	}
}
