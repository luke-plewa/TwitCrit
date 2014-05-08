package edu.calpoly.twitcrit;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainWindow {

	//temporary public var to use with search bar
	public static String commandLine;
	
	public static void main(String[] args) {
		//create the main frame and set details
		JFrame mainFrame = new JFrame("TwitCrit");
		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(),BoxLayout.PAGE_AXIS));
		mainFrame.setPreferredSize(new Dimension(500,500));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//panel to hold the search bar and search button
		commandLine = args[0];
		JPanel searchBar = new SearchBar();
		
		mainFrame.add(Box.createRigidArea(new Dimension(0,20)));
		mainFrame.add(searchBar);
		
		//display the frame
		mainFrame.pack();
		mainFrame.setVisible(true);
		//Tester.printScore(args[0]);
	}

}
