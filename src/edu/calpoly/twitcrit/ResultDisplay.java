package edu.calpoly.twitcrit;

import java.awt.*;
import javax.swing.*;

public class ResultDisplay extends JPanel {
	private JTextArea textArea;
   
	public ResultDisplay(){
		//setLayout
		textArea = new JTextArea();
		setMaximumSize(new Dimension(400,100));
		add(textArea);
	}
   
   public void setTextArea(String text) {
      textArea.setText(text);
   }
   
   public String getTextArea(){
	   return textArea.getText();
   }
}
