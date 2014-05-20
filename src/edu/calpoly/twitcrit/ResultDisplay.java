package edu.calpoly.twitcrit;

import java.awt.*;
import javax.swing.*;

public class ResultDisplay extends JPanel {
	private JTextArea textArea, retweetArea, favoriteArea;

	public ResultDisplay(){
		//setLayout
		textArea = new JTextArea();
    favoriteArea = new JTextArea();
    retweetArea = new JTextArea();
    retweetArea.setLineWrap(true);
    favoriteArea.setLineWrap(true);
		setMaximumSize(new Dimension(400,800));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(textArea);
    add(favoriteArea);
    add(retweetArea);
	}

   public void setTextArea(String text) {
      textArea.setText(text);
   }

   public void setFavoriteArea(String text) {
      favoriteArea.setText(text);
   }

   public void setRetweetArea(String text) {
      retweetArea.setText(text);
   }

   public String getTextArea(){
	   return textArea.getText();
   }

   public String getFavoriteArea() {
      return favoriteArea.getText();
   }

   public String getRetweetArea() {
      return retweetArea.getText();
   }
}
