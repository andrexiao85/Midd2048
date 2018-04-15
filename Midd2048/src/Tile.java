// Creates tile class to be implemented in Grid class
//
// CS 201 Final Project
//
// Andre Xiao, Grant Gutstein (Section B)

public class Tile {

	protected int value; //value of the tile
	protected boolean isCombined; //if tiles has just been combined or not

	public Tile () {
		//constructor
		value = 2;
		isCombined = false;
	}

	public int value() {
		//returns value of tile
		return value;
	}

	public void setVal(int val) {
		//sets value of tile
		value = val;
	}

	public boolean isCombined() {
		//returns if the tile is combined
		return isCombined;
	}

	public void justCombined(){
		//changes isCombined to true
		isCombined = true;
	}

	public void resetCombined() {
		//changes isCombined to false
		isCombined = false;
	}
}
