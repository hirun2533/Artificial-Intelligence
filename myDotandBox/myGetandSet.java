package myDotandBox;

public class myGetandSet {

	public int myHorizon;							
	public int myVertical;							
	public boolean myDrawLine;						
	public int tempCount;							
	public int TurnPlayerCount;						
	public String currentPlayer;					
	public  int tempNum;						
    
	public  int getMyHorizon() {				
		return myHorizon;
	}

	public void setMyHorizon(int myHorizon) {		
		this.myHorizon = myHorizon;
	}

	public int getMyVertical() {					
		return myVertical;
	}

	public void setMyVertical(int myVertical) {	
		this.myVertical = myVertical;
	}

	public boolean myDrawLine() {					
		return myDrawLine;
	}

	public void setMyDrawLine(boolean myDrawLine) {	
		 this.myDrawLine = myDrawLine;
	}

	public int getTempCount() {  					
		return tempCount;
	}

	public void setTempCount(int tempCount) { 		
		this.tempCount = tempCount;
	}

	public  int getTurnPlayerCount() {				
		return TurnPlayerCount;
	}


	public  void setTurnPlayerCount(int turnPlayerCount) { 
		TurnPlayerCount = turnPlayerCount;
	}

     boolean checkPlayer() {								
         return currentPlayer != " ";
     }

     int gettempNum() {									
         return tempNum;
     }

     
     String getPlayerState() { 								
    	 return currentPlayer.replace(currentPlayer,"#");
     	}

     void setPlayerState(String owner) { 				
     	this.currentPlayer = owner;
     	}

     myGetandSet tempArray() {								
         return new myGetandSet(this.currentPlayer, this.tempNum);
     }

     public myGetandSet(boolean myDrawLine, int myHorizon, int myVertical){  
 		this.myHorizon = myHorizon;				
 		this.myVertical = myVertical;			
 		this.myDrawLine = myDrawLine;		
 		
 	}
	
     public myGetandSet(int tempNum) {					
         this.currentPlayer = " ";
         this.tempNum = tempNum;
     }

     public myGetandSet(String currentPlayer, int tempNum) {
         this.currentPlayer = currentPlayer;
         this.tempNum = tempNum;
     }
 
}
