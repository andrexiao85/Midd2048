// 2048 Game Applet
//
// CS 201 Final Project
//
// Andre Xiao, Grant Gutstein (Section B)

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TwentyFortyEight extends Applet implements ActionListener, KeyListener {

	//Instance variables
	GameCanvas gC;
	Button restartButton;
	Button leftButton;
	Button upButton;
	Button rightButton;
	Button downButton;
	Button undoButton;
	Label score;
	Label scoreNumber;
	Image img;
	static final Color lblue = new Color(200, 220, 255);
	Font titleFont = new Font("Times", Font.BOLD, 60);
	Font scoreFont = new Font("Times", Font.PLAIN, 25);
	Font scoreNumFont = new Font("Times", Font.BOLD, 40);

	//Makes applet layout and components
	public void init() {
		setBackground(Color.blue);
		setLayout(new BorderLayout());

		// Center Panel
		gC = new GameCanvas();
		gC.addKeyListener(this);
		add("Center", gC);

		// Top panel
		Panel top = new Panel();
		top.setLayout(new BorderLayout());
		top.setBackground(Color.blue);

		Label titleLeft = new Label();
		titleLeft.setForeground(lblue);
		titleLeft.setFont(titleFont);
		titleLeft.setText(" MIDD 2048  ");

		Panel scoreRight = new Panel(new GridLayout(2, 1));
		score = new Label(" Score:");
		score.setFont(scoreFont);
		score.setBackground(lblue);
		score.setForeground(Color.blue);
		scoreNumber = new Label("");
		scoreNumber.setForeground(Color.blue);
		scoreNumber.setBackground(lblue);
		scoreNumber.setFont(scoreNumFont);
		show(gC.grid.score());
		scoreRight.add(score);
		scoreRight.add(scoreNumber);

		top.add("West", titleLeft);
		top.add("Center", scoreRight); 

		add("North", top);

		// Bottom panel
		Panel bottom = new Panel();
		bottom.setLayout(new BorderLayout());

		Panel controls = new Panel();
		controls.setLayout(new BorderLayout());

		leftButton = new Button("LEFT");
		leftButton.setBackground(lblue);
		leftButton.setForeground(Color.blue);
		leftButton.addActionListener(this);
		controls.add("West", leftButton);

		upButton = new Button("UP");
		upButton.setBackground(lblue);
		upButton.setForeground(Color.blue);
		upButton.addActionListener(this);
		controls.add("North", upButton);

		rightButton = new Button("RIGHT");
		rightButton.setBackground(lblue);
		rightButton.setForeground(Color.blue);
		rightButton.addActionListener(this);
		controls.add("East", rightButton);

		downButton = new Button("DOWN");
		downButton.setBackground(lblue);
		downButton.setForeground(Color.blue);
		downButton.addActionListener(this);
		controls.add("South", downButton);

		bottom.add("Center", controls);

		restartButton = new Button("RESTART");
		restartButton.setBackground(lblue);
		restartButton.setForeground(Color.blue);
		restartButton.addActionListener(this);
		bottom.add("West", restartButton);

		undoButton = new Button("UNDO");
		undoButton.setBackground(lblue);
		undoButton.setForeground(Color.blue);
		undoButton.addActionListener(this);
		bottom.add("East", undoButton);

		// Add Middlebury image to center of control panel
		img = getImage(getDocumentBase(), "Midd_Panther2.jpg");
		ImageIcon icon = new ImageIcon(img);
		JLabel mp = new JLabel(icon);
		mp.setBackground(lblue);
		controls.add("Center", mp);

		add("South", bottom);
	}

	//Handles button presses
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == restartButton) {
			p("Restart was pressed");
			show(gC.grid.restart());
			gC.grid.printGrid();
			gC.repaint();
		} else if (e.getSource() == undoButton) {
			p("Undo was pressed");
			show(gC.grid.undo());
			gC.grid.printGrid();
			gC.repaint();
		} else if (e.getSource() == leftButton) {
			p("Left was pressed");
			gC.grid.left();
			gC.grid.printGrid();
			show(gC.grid.score());
			gC.repaint();
		} else if (e.getSource() == upButton) {
			p("Up was pressed");
			gC.grid.up();
			gC.grid.printGrid();
			show(gC.grid.score());
			gC.repaint();
		} else if (e.getSource() == rightButton) {
			p("Right was pressed");
			gC.grid.right();
			gC.grid.printGrid();
			show(gC.grid.score());
			gC.repaint();
		} else {//(e.getSource() == downButton) {
			p("Down was pressed");
			gC.grid.down();
			gC.grid.printGrid();
			show(gC.grid.score());
			gC.repaint();
		}
	}

	//Handles pressing of arrow keys, r for restart, and backspace for undo
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();               
		if (keyCode == KeyEvent.VK_UP) { // up arrow
			p("Up was pressed");
			gC.grid.up();
			gC.grid.printGrid();
			show(gC.grid.score());
			gC.repaint();
		} else if (keyCode == KeyEvent.VK_DOWN) {
			p("Down was pressed");
			gC.grid.down();
			gC.grid.printGrid();
			show(gC.grid.score());
			gC.repaint();
		} else if (keyCode == KeyEvent.VK_LEFT ) {
			p("Left was pressed");
			gC.grid.left();
			gC.grid.printGrid();
			show(gC.grid.score());
			gC.repaint();
		} else if (keyCode == KeyEvent.VK_RIGHT ) {
			p("Right was pressed");
			gC.grid.right();
			gC.grid.printGrid();
			show(gC.grid.score());
			gC.repaint();
		} else if (keyCode == KeyEvent.VK_BACK_SPACE) {
			p("Undo was pressed");
			show(gC.grid.undo());
			gC.grid.printGrid();
			gC.repaint();
		} else if (keyCode == KeyEvent.VK_R) {
			p("Restart was pressed");
			show(gC.grid.restart());
			gC.grid.printGrid();
			gC.repaint();
		} else { // A key was pressed but the key has no function in applet
			p("invalid key entry");
		}
	}

	// Display score
	protected void show(int n) {
		scoreNumber.setFont(scoreFont);
		scoreNumber.setText(" " + Integer.toString(n));
	}
	// Helper/misc functions 
	public void p(String s) {
		System.out.println(s);
	}
	public void keyTyped(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
}
