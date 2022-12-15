package prj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class playGame extends JPanel implements KeyListener, ActionListener{
	//KeyListener for detecting the arrow keys
	//ActionListener for moving the ball
	// the game wouldn't be started once i click run.
	
	private boolean play = true;          //To not make the game start by itself
	private int score = 0;
	private int bricksCollection = 21;
	
	// to specify how fast it should be.
	private Timer timer;
	private int delay = 6;    				  //The speed of the ball
	
	// to specify the coordinates.
	 //starting position of the slider 
	private int playerX =310;                               
	//starting position for ball
	private int ballposX = 120;								
	private int ballposY = 350;
	//Setting the direction of the ball
	private int ballXdir = -1;
	private int ballYdir = -2;
	//create an object of MapGenerator class
	
	private MapGenerator map;
	
	// create a constructor
	public playGame() {
		map=new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false); 
		
		// create an object for the timer
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		// choose the backGround color.
		g.setColor(Color.black);
		
		//g.fillRect(ballposX, ballposY, ballYdir, ballXdir);
		g.fillRect(1, 1, 692, 592);
		
		// create the borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(680, 0, 3, 592);
		
		// create the paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 540, 100, 8);
		
		//drawing the map
				map.draw((Graphics2D)g);
		
		// Create the ball
		
		g.setColor(Color.red);
		g.fillOval(ballposX, ballposY, 20, 20);
		g.dispose();
	}
	
	//method required by ActoinListener
	@Override
	public void actionPerformed(ActionEvent e) {
		// Recall the paint method and repaint the game
		timer.start();
		repaint();
		if(play) {
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
				ballYdir=-ballYdir;
				
			}
			ballposX +=ballXdir;		
			ballposY+=ballYdir;
		}
		if(ballposX<0) {
			ballXdir=-ballXdir;   	 //for the left border  	 //reverse the Xdir of the ball   
		}
		if(ballposY<0) {
			ballYdir=-ballYdir;      // for the top border  	 //reverse the Ydir of the ball
		}
		if(ballposX>670) {
			ballXdir=-ballXdir;     // for the right border		 //reverse the Xdir of the ball
		}
	}
	
		
	//methods required by KeyListener
	
	
	
	//	keyTyped - when the unicode character represented by this key is sent by the keyboard to system input
	@Override
	public void keyTyped(KeyEvent e) {}
	
	//	keyReleased - when the key comes up
	@Override
	public void keyReleased(KeyEvent e) {}

	//	keyPressed - when the key goes down
	@Override
	public void keyPressed(KeyEvent e) { 
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
		
			if(playerX >=600) {			//check if it go outside the panel
				playerX = 600;
			} else {
				moveRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			} else {
				moveLeft();
			}
		}
	}
	
	// Handle the move right and move left Methods
	public void moveRight() {
		play = true;
		playerX+= 20;
	}
	
	public void moveLeft() {
		play = true;
		playerX-= 20;
	}



}
