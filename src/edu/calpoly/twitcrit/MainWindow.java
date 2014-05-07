package edu.calpoly.twitcrit;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainWindow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//create the main frame and set details
		JFrame mainFrame = new JFrame("TwitCrit");
		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(),BoxLayout.PAGE_AXIS));
		mainFrame.setPreferredSize(new Dimension(200,200));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//panel to hold the search bar and search button
		JPanel searchBar = new JPanel();
		searchBar.setLayout(new BorderLayout());
		//the search field
		final JTextField search = new JTextField();
		final Font searchFont = search.getFont();
		search.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent e){
				search.setFont(new Font(searchFont.getName(),
						searchFont.getStyle(),searchFont.getSize()));
				search.setForeground(Color.BLACK);
				search.setText("");
			}

			public void focusLost(FocusEvent e) {
				search.setFont(new Font(searchFont.getName(),Font.ITALIC,searchFont.getSize()));
				search.setForeground(Color.GRAY);
				search.setText("Movie title...");
			}
		});
		searchBar.add(search,"Center");
		//the search button
		JButton searchButton = new JButton("Search");
		final String inputHashtag = args[0];
		searchButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//FOR NOW ALWAYS SEARCHES CAPTAIN AMERICA
				//Tester.printScore(Tester.movieToHashtag(search.getText()));
				
				Tester.printScore(inputHashtag);
			}
			
		});
		searchBar.add(searchButton,"East");
		
		mainFrame.add(searchBar);
		
		//display the frame
		mainFrame.pack();
		mainFrame.setVisible(true);
		//Tester.printScore(args[0]);
	}

}
