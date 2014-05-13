package edu.calpoly.twitcrit;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageDisplayPane extends JOptionPane {
	private static JFrame display;
	public static void displayMessage(String text){
		showMessageDialog(display,text);
	}
}
