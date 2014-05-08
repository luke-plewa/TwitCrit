package edu.calpoly.twitcrit;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainWindow {

	//temporary public var to use with search bar
	public static String commandLine;
   private static ResultDisplay topDisplay;
   private static ResultDisplay middleDisplay;
   private static ResultDisplay bottomDisplay;
   
   public static ResultDisplay getTopDisplay() {
      return topDisplay;
   }
   
	public static void main(String[] args) {
      if (args.length > 0)
         commandLine = args[0];
      
		//create the main frame and set details
		JFrame mainFrame = new JFrame("TwitCrit");
		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(),BoxLayout.PAGE_AXIS));
		mainFrame.setPreferredSize(new Dimension(500,500));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //panel to hold the search bar and search button
		JPanel searchBar = new SearchBar();
		
		//create result display panels
		topDisplay = new ResultDisplay();
      middleDisplay = new ResultDisplay();
      bottomDisplay = new ResultDisplay();
		
		//add search bar to frame
		mainFrame.add(Box.createRigidArea(new Dimension(0,20)));
		mainFrame.add(searchBar);
		mainFrame.add(Box.createRigidArea(new Dimension(0,20)));
		
		//add result displays to frame
		mainFrame.add(topDisplay);
		mainFrame.add(Box.createRigidArea(new Dimension(0,20)));
		mainFrame.add(middleDisplay);
		mainFrame.add(Box.createRigidArea(new Dimension(0,20)));
		mainFrame.add(bottomDisplay);
		mainFrame.add(Box.createRigidArea(new Dimension(0,20)));
		
		//display the frame
		mainFrame.pack();
		mainFrame.setVisible(true);
		//Tester.printScore(args[0]);
	}

}
