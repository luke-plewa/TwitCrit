package edu.calpoly.twitcrit;

import java.awt.*;
import javax.swing.*;

public class ResultDisplay extends JPanel {
	private JTextArea textArea, retweetArea, favoriteArea, scoreArea;
   private double score;
   private JPanel topPanel;

	public ResultDisplay(){
      score = 0;
      topPanel = new JPanel();
      scoreArea = new JTextArea();
		textArea = new JTextArea();

      favoriteArea = new JTextArea();
      retweetArea = new JTextArea();
      retweetArea.setLineWrap(true);
      favoriteArea.setLineWrap(true);

		setMaximumSize(new Dimension(400,800));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      topPanel.add(scoreArea);
		topPanel.add(textArea);
      add(topPanel);
      add(favoriteArea);
      add(retweetArea);
	}

   public void setScore(double score) {
      this.score = score;
      String s_score = String.format("%.2f", score);
      scoreArea.setText(s_score);
      if (score > 7.0) {
         scoreArea.setBackground(Color.green);
      } else if (score > 4.0) {
         scoreArea.setBackground(Color.yellow);
      } else {
         scoreArea.setBackground(Color.red);
      }
   }

   public double getScore(){
      return score;
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
