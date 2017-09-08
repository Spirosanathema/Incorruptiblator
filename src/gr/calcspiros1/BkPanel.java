package gr.calcspiros1;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class BkPanel extends JPanel{
	ImageIcon ic;
	
	
	public BkPanel(){
		this.setLayout(new MigLayout("wrap, debug", "[]", "[]"));
		this.setBackground(new Color(0,0,0));
		//ic = new ImageIcon("E://icedearthfixed.jpg");
		//ic = new ImageIcon("images/icedearth.png");
		ic = new ImageIcon("E://icedearth.png");
	}
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .6f);
		g2.setComposite(composite);
		repaint();
		
		g2.drawImage(ic.getImage(), -0, 0, getWidth(), getHeight(), this);
	}

}
