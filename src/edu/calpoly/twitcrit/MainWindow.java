package edu.calpoly.twitcrit;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import twitter4j.*;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private static ResultDisplay topDisplay;
	private static ResultDisplay middleDisplay;
	private static ResultDisplay bottomDisplay;

   public static ResultDisplay getTopDisplay() {
      return topDisplay;
   }

   public static void updateSearchHistory(String result, Status favorited, Status retweeted){
	  bottomDisplay.setTextArea(middleDisplay.getTextArea());
	  bottomDisplay.setFavoriteArea(middleDisplay.getFavoriteArea());
	  bottomDisplay.setRetweetArea(middleDisplay.getRetweetArea());


	  middleDisplay.setTextArea(topDisplay.getTextArea());
	  middleDisplay.setFavoriteArea(topDisplay.getFavoriteArea());
	  middleDisplay.setRetweetArea(topDisplay.getRetweetArea());

	  topDisplay.setTextArea(result);

   	if (retweeted != null) {
   		topDisplay.setRetweetArea("Most retweeted tweet: @" +
   			retweeted.getUser().getScreenName() +
        ": " + retweeted.getText());
   	} else {
   		topDisplay.setRetweetArea("No retweeted tweets :(");
   	}
   	if (favorited != null) {
   		topDisplay.setFavoriteArea("Most favorited tweet: @" +
   			favorited.getUser().getScreenName() +
        ": " + favorited.getText());
   	} else {
   		topDisplay.setFavoriteArea("No favorited tweets :(");
   	}
   }

	public MainWindow() {
		//create the main frame and set details
		this.setTitle("TwitCrit");
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
		this.setPreferredSize(new Dimension(600,600));
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
