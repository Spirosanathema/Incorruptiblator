package gr.calcspiros1;



public class CalcLogic {
	
	private double numA, numB;
	private Boolean numAFlag;
	@SuppressWarnings("unused")
	private String[] buttonNames;
	private String prosimo, equalProsimo;
	
	
	public CalcLogic(){
		numA = 0;
		numB = 0;
		numAFlag = false;
		prosimo = "";
		equalProsimo = "";
	}
	
	// GETTERS and SETTERS
	public void setNumA(String s, String p){
		if(s != "")
			numB = Double.parseDouble(s);
//		else if(s == "")
//			numB = 0;
		
		if(!numAFlag){
							
			numA = numB;
			numAFlag = true;
		}
		else if(numAFlag && p != "="){
			
			if( prosimo == "+"){
				if(s == "")
					numB = 0;
				
				numA += numB;
			}
			else if(prosimo == "-"){
				if(s == "")
					numB = 0;
				
				numA -= numB;
			}
			else if(prosimo == "*"){
				if(s == "")
					numB = 1;
				
				numA *= numB;
			}
			else if(prosimo == "/"){
				if(s == "")
					numB = 1;
				
				numA /= numB;
			}
			
		}
		if(prosimo != "=")
			equalProsimo = prosimo;
		prosimo = p;
		
		if(p == "="){
			if(equalProsimo == "+")
				numA += numB;
			else if(equalProsimo == "-")
				numA -= numB;
			else if(equalProsimo == "*")
				numA *= numB;
			else if(equalProsimo == "/")
				numA /= numB;
		}
	}
	
	public void setButtonNames(String[] bn){
		buttonNames = bn;
	}
	
	public double getNumA(){ return numA; }
	
	public void clearAll(){ numA = 0; numAFlag = false; numB = 0; prosimo = ""; equalProsimo = ""; }
	
	public boolean isNumAEmpty(){ if(numA == 0)return true; else return false; }
	

}
