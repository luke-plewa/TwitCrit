package edu.calpoly.twitcrit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChartPanel extends JPanel {
  
  private Integer[] values;
  private String[] names;
  private String title;
  private String[][] keywordList;
  private int[] scores;

  /* Constructor to initialize contents of chart */
  public ChartPanel(Integer[] I, String[] n, String t) {
    names = n;
    values = I;
    scores = new int[names.length];
    title = t;
    int i = 0, j = 0, k = 0;
    
    keywordList = Tester.getKeywords();
    for (k = 0; k < scores.length; k++) {
    	for (i = 0; i < keywordList.length; i++) {
    		for (j = 0; j < keywordList[i].length; j++) {
    			if (keywordList[i][j].equals(names[k]))
    				scores[k] = i;
    		}
    	}
    }
    
    for (j = 1; j < scores.length; j++) {
    	int sortScore = scores[j];
    	String sortName = names[j];
    	Integer sortValue = values[j];

        for (i = j - 1; (i >= 0) && (scores[i] > sortScore); i--) {
        	scores[i + 1] = scores[i];
        	names[i + 1] = names[i];
        	values[i + 1] = values[i];
        }
        scores[i + 1] = sortScore;
        names[i + 1] = sortName;
        values[i + 1] = sortValue;
    }
  }

  /* Draw contents of chart */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    if (values == null || values.length == 0)
      return;
      
    double minValue = 0;
    double maxValue = 0;
    
    //find the minimum and maximum values to scale to
    for (int i = 0; i < values.length; i++) {
      if (minValue > values[i])
        minValue = values[i];
      if (maxValue < values[i])
        maxValue = values[i];
    }

    Dimension d = getSize();
    int clientWidth = d.width;
    int clientHeight = d.height;
    int barWidth = clientWidth / values.length;

    Font titleFont = new Font("SansSerif", Font.BOLD, 20);	//change the title font
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
    Font labelFont = new Font("SansSerif", Font.PLAIN, 12);	//change the label font
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

	//Draw the title in the window
    int titleWidth = titleFontMetrics.stringWidth(title);
    int y = titleFontMetrics.getAscent();
    int x = (clientWidth - titleWidth) / 2;
    g.setFont(titleFont);
    g.drawString(title, x, y);

    int top = titleFontMetrics.getHeight();
    int bottom = labelFontMetrics.getHeight();
    
    if (maxValue == minValue)
      return;
    
    double scale = (clientHeight - top - bottom) / (maxValue - minValue);
    y = clientHeight - labelFontMetrics.getDescent();
    g.setFont(labelFont);

    for (int i = 0; i < values.length; i++) {
      int valueX = i * barWidth + 1;
      int valueY = top;
      int height = (int) (values[i] * scale);
      if (values[i] >= 0)
        valueY += (int) ((maxValue - values[i]) * scale);
      else {
        valueY += (int) (maxValue * scale);
        height = -height;
      }

	  if (scores[i] <= 3)
      	g.setColor(Color.red);
      else if (scores[i] >= 6) 
      	g.setColor(Color.green);
      else
      	g.setColor(Color.yellow);
      g.fillRect(valueX, valueY, barWidth - 2, height);
      g.setColor(Color.black);
      g.drawRect(valueX, valueY, barWidth - 2, height);
      int labelWidth = labelFontMetrics.stringWidth(names[i]);
      x = i * barWidth + (barWidth - labelWidth) / 2;
      g.drawString(names[i], x, y);
      g.drawString(Integer.toString(values[i]), x, valueY - 2);
    }
  }
}