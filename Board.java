package com.snakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements java.awt.event.ActionListener  {

	private Image apple;
	private Image dot;
	private Image head;

	private final int totalDots = 900;
	private final int sizeOfDots = 10;
	private final int applePosition=29;
	
	private int apple_x;
	private int apple_y;

	private final int x[] = new int[totalDots];
	private final int y[] = new int[totalDots];

	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;
	
	private boolean inGame=true;
	
	private int dots;
	private Timer timer ;
	
	Board() {
		addKeyListener(new TAdapter());
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(300,300));
		setFocusable(true);

		loadImages();
		initGame();
	}

	public void loadImages() {
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("com/SnakeGame/icons/apple.png"));
		apple = i1.getImage();
		ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("com/SnakeGame/icons/dot.png"));
		dot = i2.getImage();
		ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("com/SnakeGame/icons/head.png"));
		head = i3.getImage();

	}

	public void initGame() {
		dots = 3;

		for (int i = 0; i < dots; i++) {
			y[i] = 50;
			x[i] = 50 - i * sizeOfDots;
		}
		appleLocation();
		timer=new Timer(140,this);
		timer.start();
	}
	public void appleLocation() {
		int r=(int)(Math.random()*applePosition);
			apple_x=r*sizeOfDots;
		 r=(int)(Math.random()*applePosition);
			apple_y=r*sizeOfDots;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		draw(g);
	}

	public void draw(Graphics g) {
		if (inGame) {
		g.drawImage(apple, apple_x, apple_y, this);
		for (int i = 0; i < dots; i++) {
			if (i==0) {
				g.drawImage(head,x[i],y[i],this);
			}
			else {
				g.drawImage(dot,x[i],y[i],this);

			}

		}
		Toolkit.getDefaultToolkit().sync();
		}
		else {
			gameOver(g);
		}
	}
	
	public void gameOver(Graphics g) {
		String show="!!Game Over!!";
		Font font = new Font("SAN SERIF",Font.BOLD,14);
		FontMetrics metrices=getFontMetrics(font);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(show,(300-metrices.stringWidth(show))/2,300/2);
	}
	
	public void move() {
		for(int i=dots;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		if(left) {
			x[0]=x[0]-sizeOfDots;
		}
		if(right) {
			x[0]=x[0]+ sizeOfDots;
		}
		if(up) {
			y[0]=y[0]-sizeOfDots;
		}
		if(down) {
			y[0]=y[0]+sizeOfDots;
		}
//		x[0] += sizeOfDots;

	}
	public void checkApple() {
		if((x[0]==apple_x)&&(y[0]==apple_y)) {
			dots++;
			appleLocation();
		}
	}
	
	public void checkCollision1(){
		for(int i=dots;i>0;i--) {
			if ((i>4)&&(x[0]==x[i])&&(y[0]==y[i])) {
				inGame=false;
			}
		}
		if(y[0]>=300) {
			inGame=false;
		}
		if(x[0]>=300) {
			inGame=false;
		}
		if(y[0]<0) {
			inGame=false;
		}
		if(x[0]<0) {
			inGame=false;
		}
		if (!inGame) {
			timer.stop();
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		checkApple();
		checkCollision1();
		move();
		
		repaint();
	}

	private void checkCollision() {
		// TODO Auto-generated method stub
		
	}

	public class TAdapter extends KeyAdapter{
		@Override
		
	    public void keyPressed(KeyEvent e) {
	    	int key= e.getKeyCode();
	    	if(key== KeyEvent.VK_LEFT && (!right)) {
	    		left=true;
	    		up=false;
	    		down=false;
	    	}
	    	if(key== KeyEvent.VK_RIGHT && (!left)) {
	    		right=true;
	    		up=false;
	    		down=false;
	    	}
	    	if(key== KeyEvent.VK_UP && (!down)) {
	    		up=true;
	    		left=false;
	    		right=false;
	    	}
	    	if(key== KeyEvent.VK_DOWN && (!up)) {
	    		down=true;
	    		left=false;
	    		right=false;
	    	}
	    }

	}
}
