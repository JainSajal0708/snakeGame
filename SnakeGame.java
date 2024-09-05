package com.snakeGame;

import javax.swing.*;
public class SnakeGame extends JFrame {
	SnakeGame(){
		super("Snake And Ladder");
		add(new Board());
		pack();
//		setSize(300,300);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	public static void main(String[] args) {
		new SnakeGame().setVisible(true);
	}
}
