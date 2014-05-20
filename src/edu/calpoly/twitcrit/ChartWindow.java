package edu.calpoly.twitcrit;

import javax.swing.JFrame;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChartWindow extends JFrame {
	public ChartWindow() {
		JFrame f = new JFrame();
		f.setSize(400, 300);
   		double[] values = new double[3];
    	String[] names = new String[3];
    	values[0] = 1;
    	names[0] = "Item 1";

   		values[1] = 2;
    	names[1] = "Item 2";

    	values[2] = 4;
    	names[2] = "Item 3";

    	f.getContentPane().add(new ChartPanel(values, names, "title"));

    	WindowListener wndCloser = new WindowAdapter() {
      		public void windowClosing(WindowEvent e) {
        		System.exit(0);
      		}
    	};
    	f.addWindowListener(wndCloser);
    	f.setVisible(true);
    }
}