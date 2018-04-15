// Creates canvas for the applet
//
// CS 201 Final Project
//
// Andre Xiao, Grant Gutstein (Section B)

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class GameCanvas extends Canvas {

	// Instance variables
	int rows = 4;
	int size = 80;
	int border = 20;
	Grid grid;
	static final Color dblue = Color.blue.darker();
	static final Color lblue = new Color(200, 220, 255);
	Font canvasFont1 = new Font("Times", Font.BOLD, 30);
	Font canvasFont2 = new Font("Times", Font.PLAIN, 10);

	// Construct grid
	public GameCanvas() {
		grid = new Grid();
	}

	// Draw grid
	public void paint(Graphics g) {
		for (int row = 0; row < grid.rowLength(); row++) {
			for (int col = 0; col < grid.colLength(row); col++) {
				int y = 20*(row+1) + row*size;
				int x = 20*(col+1) + col*size;
				int y2 = 60 + y - border / 2;
				int x2 = 60 + x - border * 2;
				// If tile not null, draw grid tile with value
				if (grid.value(row, col) != null) {
					g.setColor(dblue);
					g.fillRoundRect(x, y, size, size, 25, 25);
					g.setColor(Color.white);
					g.setFont(canvasFont1);
					g.drawString("" + grid.value(row, col).value(), x2, y2);
				} else { // Empty tile
					g.setColor(lblue);
					g.fillRoundRect(x, y, size, size, 25, 25);
				}
			}
		}
		// About statement and Instructions
		g.setFont(canvasFont2);
		g.drawString("About 2048: 2048 was created in March 2014 by an Italian web "
				+ "developer who was inspired by the game of three's.", 
				border, 2 * border + 5 * size - canvasFont2.getSize());
		g.drawString("The then 19-year old Gabrielle Cirulli created the game in just "
				+ "one weekend and never expected the game to sky-rocket ", 
				border, 2 * border + 5 * size);
		g.drawString("the way it did. It received over 4 million views just "
				+ "after its release. That’s massive. Irulli, however, has made the game ", 
				border, 2 * border + 5 * size + canvasFont2.getSize() * 1);
		g.drawString("freely available and does not want to make money off it "
				+ "as it’s not his original idea.        (Source: http://2048game.info/)", 
				border, 2 * border + 5 * size + canvasFont2.getSize() * 2);
		g.drawString("Instructions: Use arrow keys, r (restart), and backspace (undo) or the "
				+ "buttons below. When two like tiles connect as", 
				border, 2 * border + 5 * size + canvasFont2.getSize() * 4);
		g.drawString("a result of a key or button press, they merge and their sum is displayed "
				+ "on the new tile. The game ends when the ", 
				border, 2 * border + 5 * size + canvasFont2.getSize() * 5);
		g.drawString("board is filled with tiles and no new tile merges are possible.", 
				border, 2 * border + 5 * size + canvasFont2.getSize() * 6);
	}
}
