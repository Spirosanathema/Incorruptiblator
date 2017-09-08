package gr.calcspiros1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

public class CalcGui {
	private JFrame frame;
	private JPanel calcPanel, screenPanel, keysPanel;
	private int calcWidth, calcHeight;
	private JTextArea scr;
	private JScrollPane scrollBar;
	private String[] buttonNames = {"bclear", "bspace", "b7", "b8", "b9", "bdiv", "b4", "b5", "b6", "bmul", "b1", "b2", "b3", 
									"bminus", "b0", "bcoma", "bplus", "bequal"};
	private String[] buttonIconNames = {"C", "Bk", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "+", "="};
	private char[] specialChars = {'!', '@', '#', '$', '%', '^', '`', '~', '&', '(', ')', '_', '?', '<', '>', ';', ':', '"', '[', ']',
									'{', '}', '|'};
	private ArrayList<JButton> buttons;
	private CalcLogic calcLogic;
	private String numAString;
	private static boolean periodFlag, plusFlag, minusFlag, multiFlag, divFlag, zeroNumberOnStart, equalFlag;
	
	private DecimalFormat df;
	private BkPanel bpan;
	
	public CalcGui(){
		buttons = new ArrayList<JButton>();
		calcLogic = new CalcLogic();
		calcLogic.setButtonNames(buttonIconNames);
		numAString = "";
		periodFlag = false;
		plusFlag = false;
		minusFlag = false;
		multiFlag = false;
		divFlag = false;
		equalFlag = false;
		zeroNumberOnStart = true;
		//df = new DecimalFormat("###.#");
		df = new DecimalFormat("0");
		//df = new DecimalFormat("0" , DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		df.setMaximumFractionDigits(340);
		bpan = new BkPanel();
	}	
	
	public void runIt(){
		frame = new JFrame("Incorruptiblator  (ver 2.6)");
		calcHeight = 600;
		calcWidth = 400;
		
		calcPanel = new JPanel(new MigLayout("wrap","[200:400:400]","[100:150:150][200:450:450]"));
		screenPanel = new JPanel(new MigLayout("wrap", "[grow, fill]", "[grow, fill]"));
		keysPanel = new JPanel(new MigLayout("wrap 4","[grow, fill][grow, fill][grow, fill][grow, fill]",
													"[grow, fill][grow, fill][grow, fill][grow, fill][grow, fill]"));
		
		scr = new JTextArea();
		scr.setText("0");
		
		scr.addKeyListener(new TheKeysListener());
		scr.requestFocus();
		
		// KEYS - SET KEYS FONT - ADD ACTIONLISTENER - ADD TO THE PANEL
		
		for(int i=0; i<buttonNames.length; i++){
			buttons.add(new JButton(buttonIconNames[i]));
			buttons.get(i).setName(buttonNames[i]);
			buttons.get(i).setFont(new Font("Arial", Font.BOLD, 30));
			buttons.get(i).addActionListener(new TheButtonsListener());
			
			if(buttons.get(i).getName() == "bclear"){
				keysPanel.add(buttons.get(i), "span 2");
				continue;
			}
			
			if(buttons.get(i).getName() == "bspace"){
				keysPanel.add(buttons.get(i), "span, wrap");
				continue;
			}
			
			keysPanel.add(buttons.get(i));
		}		
		
		// SCREEN
		scr.setFont(new Font("Arial", Font.BOLD, 40));	// Sets the font of JTextArea
		scr.setLineWrap(true);							// Prevents numbers and letters to cross the horizontal edge of JTextArea
		scr.setEditable(false);
		scr.setOpaque(false);
		scr.setBackground(new Color(0,0,0,250));
		scr.setBackground(Color.lightGray);
		scr.getCaret().setVisible(false);
		scrollBar = new JScrollPane(scr);
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollBar.getViewport().setOpaque(false);
		scrollBar.setOpaque(false);
		screenPanel.add(scrollBar);
		//========================================================================================================================
		
		
		calcPanel.add(screenPanel, "grow");
		calcPanel.add(keysPanel, "grow");
		calcPanel.setOpaque(false);
		bpan.add(calcPanel);
		
		frame.add(bpan);
		bpan.repaint();
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(calcWidth, calcHeight);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private void zeroOnStartCheck(){
		if(zeroNumberOnStart){
			scr.setText("");
			zeroNumberOnStart = false;
		}
	}
	
	private void resetEverything(){
		numAString = "";
		scr.selectAll();
		scr.replaceSelection("");
		calcLogic.clearAll();
		scr.setText("0");
		zeroNumberOnStart = true;
	}
	
	private void resetAfterEqual(){
		if(equalFlag){
			numAString = "";
			scr.selectAll();
			scr.replaceSelection("");
			calcLogic.clearAll();
			equalFlag = false;
		}		
	}
	
	private void backSpacing(){		
		int c = numAString.length();
		if(c == 0){
			numAString = "";
			return;
		}
		StringBuilder bl = new StringBuilder(numAString);
		bl.deleteCharAt(c-1);
		numAString = bl.toString();
	}
	
	
	// GETTERS - SETTERS
	public static void resetFlags(){ plusFlag = false; minusFlag = false; multiFlag = false; divFlag = false;}

	// INNER CLASSES LISTENERS........................................................................
	
	private class TheButtonsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
				zeroOnStartCheck();			
			
				if(e.getSource() == buttons.get(10)){
					resetAfterEqual();
					resetFlags();
					
					numAString = numAString + buttonIconNames[10];
					scr.setText(scr.getText() + "1");
				}
					
				else if(e.getSource() == buttons.get(11)){
					resetAfterEqual();
					resetFlags();
					
					numAString = numAString + buttonIconNames[11];
					scr.setText(scr.getText() + "2");
				}
					
				else if(e.getSource() == buttons.get(12)){
					resetAfterEqual();
					resetFlags();
					
					numAString = numAString + buttonIconNames[12];
					scr.setText(scr.getText() + "3");
				}
					
				else if(e.getSource() == buttons.get(6)){
					resetAfterEqual();
					resetFlags();
					
					numAString = numAString + buttonIconNames[6];
					scr.setText(scr.getText() + "4");
				}
					
				else if(e.getSource() == buttons.get(7)){
					resetAfterEqual();
					resetFlags();
					
					numAString = numAString + buttonIconNames[7];
					scr.setText(scr.getText() + "5");
				}
					
				else if(e.getSource() == buttons.get(8)){
					resetAfterEqual();
					resetFlags();
					
					numAString = numAString + buttonIconNames[8];
					scr.setText(scr.getText() + "6");
				}
					
				else if(e.getSource() == buttons.get(2)){
					resetAfterEqual();
					resetFlags();
					
					numAString = numAString + buttonIconNames[2];
					scr.setText(scr.getText() + "7");
				}
					
				else if(e.getSource() == buttons.get(3)){
					resetAfterEqual();
					resetFlags();
					
					numAString = numAString + buttonIconNames[3];
					scr.setText(scr.getText() + "8");
				}
					
				else if(e.getSource() == buttons.get(4)){
					resetAfterEqual();
					resetFlags();
					
					numAString = numAString + buttonIconNames[4];
					scr.setText(scr.getText() + "9");
				}
					
				else if(e.getSource() == buttons.get(14)){
					resetAfterEqual();
					resetFlags();
					
					numAString = numAString + buttonIconNames[14];
					scr.setText(scr.getText() + "0");
				}				
				
				else if(e.getSource() == buttons.get(1)){
					backSpacing();
					int n = scr.getText().length();
					if(n == 0)
						scr.setText("");
					StringBuilder bl = new StringBuilder(scr.getText());
					bl.deleteCharAt(n-1);
					
					scr.setText(bl.toString());
				}				
					
				else if(e.getSource() == buttons.get(16)){
					if(!plusFlag){
					calcLogic.setNumA(numAString, "+");
					numAString = "";
					scr.setText(scr.getText() + " + ");
					resetFlags();
					plusFlag = true;
					equalFlag = false;
					periodFlag = false;
					}
				}
					
				else if(e.getSource() == buttons.get(13)){
					if(!minusFlag){
					calcLogic.setNumA(numAString, "-");
					numAString = "";
					scr.setText(scr.getText() + " - ");
					resetFlags();
					minusFlag = true;
					equalFlag = false;
					periodFlag = false;
					}
				}
					
				else if(e.getSource() == buttons.get(9)){
					if(!multiFlag){
					calcLogic.setNumA(numAString, "*");
					numAString = "";
					scr.setText(scr.getText() + " * ");
					resetFlags();
					multiFlag = true;
					equalFlag = false;
					periodFlag = false;
					}
				}
					
				else if(e.getSource() == buttons.get(5)){
					if(!divFlag){
					calcLogic.setNumA(numAString, "/");
					numAString = "";
					scr.setText(scr.getText() + " / ");
					resetFlags();
					divFlag = true;
					equalFlag = false;
					periodFlag = false;
					}
				}
					
				else if(e.getSource() == buttons.get(15)){
					resetAfterEqual();
					
					if(!periodFlag){
					numAString = numAString + buttonIconNames[15];
					scr.setText(scr.getText() + ".");
					periodFlag = true;
					equalFlag = false;
					}
				}
					
				else if(e.getSource() == buttons.get(17)){
						equalFlag = true;
						calcLogic.setNumA(numAString, "=");
						scr.setText(scr.getText() + "\n= " + df.format(calcLogic.getNumA()));					
				}
					
				else if(e.getSource() == buttons.get(0)){
					resetEverything();
				}
				scr.requestFocus();
				scr.setCursor(Cursor.getDefaultCursor());
		}		
	}
	
	private class TheKeysListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent k) {
			zeroOnStartCheck();
			int lolkey = k.getKeyChar();			
			
			if(!Character.isAlphabetic(lolkey)){
				scr.setEditable(true);
				
				for(int i=0; i<specialChars.length; i++){	//SPECIAL CHARACTERS NOT PRINTED!!!!
					if((char)lolkey == specialChars[i]){
						scr.setEditable(false);
					}
				}
				
				if(Character.isDigit(lolkey)){
					resetAfterEqual();
					resetFlags();
					numAString = numAString + (char)lolkey;
				}				
				
				else if(lolkey == 43){	// 43 is "+" in ASSCI
					scr.setEditable(false);
					if(!plusFlag){
						calcLogic.setNumA(numAString, "+");
						numAString = "";
						scr.setText(scr.getText() + " + ");
						resetFlags();
						plusFlag = true;
						equalFlag = false;
						periodFlag = false;
						}
				}
				else if(lolkey == 45){
					scr.setEditable(false);
					if(!minusFlag){
						calcLogic.setNumA(numAString, "-");
						numAString = "";
						scr.setText(scr.getText() + " - ");
						resetFlags();
						minusFlag = true;
						equalFlag = false;
						periodFlag = false;
						}
				}
				else if(lolkey == 42){
					scr.setEditable(false);
					if(!multiFlag){
						calcLogic.setNumA(numAString, "*");
						numAString = "";
						scr.setText(scr.getText() + " * ");
						resetFlags();
						multiFlag = true;
						equalFlag = false;
						periodFlag = false;
						}
				}
				else if(lolkey == 47){
					scr.setEditable(false);
					if(!divFlag){
						calcLogic.setNumA(numAString, "/");
						numAString = "";
						scr.setText(scr.getText() + " / ");
						resetFlags();
						divFlag = true;
						equalFlag = false;
						periodFlag = false;
						}
				}
				
				else if(lolkey == KeyEvent.VK_PERIOD || lolkey == KeyEvent.VK_COMMA){
					scr.setEditable(false);
					
					if(!periodFlag){
						resetAfterEqual();
						scr.setEditable(true);
						numAString = numAString + (char)lolkey;
						periodFlag = true;
						equalFlag = false;
						}
				}
				
				else if(lolkey == KeyEvent.VK_ENTER){
					equalFlag = true;
					scr.setEditable(false);
						calcLogic.setNumA(numAString, "=");
						scr.setText(scr.getText() + "\n= " + df.format(calcLogic.getNumA()));
				}
				
				else if(lolkey == KeyEvent.VK_ESCAPE){
					numAString = "";
					scr.selectAll();
					scr.replaceSelection("0");
					calcLogic.clearAll();
					zeroNumberOnStart = true;
					scr.setEditable(false);
				}
				
				else if(lolkey == KeyEvent.VK_BACK_SPACE){
					backSpacing();
				}				
			}			
			
			else{
				scr.setEditable(false);
			}						
		}

		@Override
		public void keyReleased(KeyEvent k) {
		}

		@Override
		public void keyTyped(KeyEvent k) {
		}
		
	}
	
	
}
