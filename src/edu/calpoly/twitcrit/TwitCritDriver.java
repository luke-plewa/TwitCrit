package edu.calpoly.twitcrit;

import javax.swing.SwingUtilities;

public class TwitCritDriver {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	new MainWindow();
            }
		});
	}
}
