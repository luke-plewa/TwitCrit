package edu.calpoly.twitcrit;

import java.awt.*;
import javax.swing.*;

public class ResultDisplay extends JPanel {
	
	public ResultDisplay(){
		//setLayout
		setMaximumSize(new Dimension(400,100));
		add(new JTextArea("movie results here"));
	}
}
