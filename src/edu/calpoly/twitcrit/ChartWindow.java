package edu.calpoly.twitcrit;

import java.util.*;
import javax.swing.JFrame;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChartWindow extends JFrame {
	public ChartWindow(HashMap<String, Integer> words) {
		JFrame f = new JFrame();
		f.setSize(800, 600);
   		
   		Integer[] values = new Integer[words.size()];
    	String[] names = new String[words.size()];
    	int i = 0;
    	
    	for (Map.Entry<String, Integer> entry : words.entrySet()) {
	    	names[i] = entry.getKey();
	        values[i] = entry.getValue();
	        i++;
	    }

    	f.getContentPane().add(new ChartPanel(values, names, "Word Usage"));
    	f.setVisible(true);
    }
}