package edu.calpoly.twitcrit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageDisplayPane extends JOptionPane {
	private static final long serialVersionUID = 1L;
	private static JFrame display;
	public static void displayMessage(String text){
		showMessageDialog(display,text);
	}
}
