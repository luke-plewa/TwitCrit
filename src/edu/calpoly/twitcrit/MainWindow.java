package edu.calpoly.twitcrit;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private static ResultDisplay topDisplay;
	private static ResultDisplay middleDisplay;
	private static ResultDisplay bottomDisplay;
   
   public static ResultDisplay getTopDisplay() {
      return topDisplay;
   }
   
   public static void updateSearchHistory(String result){
	   bottomDisplay.setTextArea(middleDisplay.getTextArea());
	   middleDisplay.setTextArea(topDisplay.getTextArea());
	   topDisplay.setTextArea(result);
   }
   
	public MainWindow() {      
		//create the main frame and set details
		this.setTitle("TwitCrit");
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
		this.setPreferredSize(new Dimension(500,500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
		//panel to hold the search bar and search button
		JPanel searchBar = new SearchBar();
		
		//create result display panels
		bottomDisplay = new ResultDisplay();
		middleDisplay = new ResultDisplay();
		topDisplay = new ResultDisplay();
		
		//add search bar to frame
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.add(searchBar);
		this.add(Box.createRigidArea(new Dimension(0,20)));
		
		//add result displays to frame
		this.add(topDisplay);
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.add(middleDisplay);
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.add(bottomDisplay);
		this.add(Box.createRigidArea(new Dimension(0,20)));
		
		//display the frame
		this.pack();
		this.setVisible(true);
		//Tester.printScore(args[0]);
	}

}
