//Adding Keyboard Function and shit when someone presses letters instead of numbers
//Fixed when someone presses + and then -
//NEED GUI modifications

package gr.calcspiros1;

import java.util.Properties;

import javax.swing.UIManager;

import com.jtattoo.plaf.smart.SmartLookAndFeel;

public class Main {
	
	public static void main(String[] args){
		
		try {
            // setup the look and feel properties
            Properties props = new Properties();
            
            props.put("logoString", "my company"); 
            props.put("licenseKey", "INSERT YOUR LICENSE KEY HERE");
            
            //props.put("selectionBackgroundColor", "180 240 197");
            props.put("selectionBackgroundColor", "240 30 30");
            props.put("menuSelectionBackgroundColor", "240 30 30");
            //props.put("menuSelectionBackgroundColor", "180 240 197");
            
            props.put("controlColor", "218 254 230");
            //props.put("controlColorLight", "218 254 230");
            props.put("controlColorLight", "255 220 185");
            props.put("controlColorDark", "255 155 55"); 
            //props.put("controlColorDark", "180 240 197"); 

            props.put("buttonColor", "218 230 254");
            props.put("buttonColorLight", "255 255 255");
            props.put("buttonColorDark", "244 242 232");

            props.put("rolloverColor", "218 254 230"); 
            //props.put("rolloverColorLight", "218 254 230");
            props.put("rolloverColorLight", "130 40 10");
            //props.put("rolloverColorDark", "180 240 197"); 
            props.put("rolloverColorDark", "230 40 10"); 

            props.put("windowTitleForegroundColor", "200 40 10");
            props.put("windowTitleBackgroundColor", "180 240 197"); 
            props.put("windowTitleColorLight", "80 80 80"); 
            props.put("windowTitleColorDark", "40 40 40"); 
            props.put("windowBorderColor", "210 40 10");
            
            // set your theme
            SmartLookAndFeel.setCurrentTheme(props);
            // select the Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            CalcGui calcgui = new CalcGui();
    		calcgui.runIt();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}