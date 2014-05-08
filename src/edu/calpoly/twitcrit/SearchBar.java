package edu.calpoly.twitcrit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchBar extends JPanel implements FocusListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String BLANK_SEARCH_TEXT = "Enter a movie title...";
	
	//the text field
	JTextField searchField;
	Font searchFont; //the font of the search field
	String currentSearchText;
	
	//the button
	JButton searchButton;
	
	public SearchBar(){
		//set panel properties
		setLayout(new BorderLayout());
		setMaximumSize(new Dimension(250,25));
		
		//set text field properties
		searchField = new JTextField();
		searchFont = searchField.getFont();
		currentSearchText = "";
		searchField.addFocusListener(this);
		add(searchField,"Center");
		
		//set button properties
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		add(searchButton,"East");
	}
	
	public void focusGained(FocusEvent e){
		searchField.setFont(new Font(searchFont.getName(),
				searchFont.getStyle(),searchFont.getSize()));
		searchField.setForeground(Color.BLACK);
		searchField.setText(currentSearchText);
	}

	public void focusLost(FocusEvent e) {
		currentSearchText = searchField.getText();
		if(currentSearchText.length() == 0){
			searchField.setFont(new Font(searchFont.getName(),Font.ITALIC,searchFont.getSize()));
			searchField.setForeground(Color.GRAY);
			searchField.setText(BLANK_SEARCH_TEXT);
		}
		else searchField.setText(currentSearchText);
	}
	
	public void actionPerformed(ActionEvent e) {
		//FOR NOW ALWAYS SEARCHES CAPTAIN AMERICA
		//Tester.printScore(Tester.movieToHashtag(search.getText()));
		
		Tester.printScore(MainWindow.commandLine);
	}

}