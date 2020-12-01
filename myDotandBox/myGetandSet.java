package myDotandBox;

/*
 * this function will keep the variable for get the value and set the value for this game
 * it will work with algorithm class and also execute game class
 */

public class myGetandSet {

	public int myHorizon;							//for horizon using
	public int myVertical;							//for vertical using
	public boolean myDrawLine;						//for boolean checking
	public int tempCount;							//for recursive value
	public int TurnPlayerCount;						//for count the game state possibility
	public String currentPlayer;					//for give string the current player at that moment
	public  int tempNum;							//for score counting
    
	public  int getMyHorizon() {					//to (get)return the vertical 
		return myHorizon;
	}

	public void setMyHorizon(int myHorizon) {		//to set the Horizon at that moment
		this.myHorizon = myHorizon;
	}

	public int getMyVertical() {					//to return the vertical 
		return myVertical;
	}

	public void setMyVertical(int myVertical) {		//to set the vertical at that moment
		this.myVertical = myVertical;
	}

	public boolean myDrawLine() {					//true or false, either one will be return for display the line of the square table
		return myDrawLine;
	}

	public void setMyDrawLine(boolean myDrawLine) {	//for weather check the lines of the square of the table board
		 this.myDrawLine = myDrawLine;
	}

	public int getTempCount() {  					//for minimax algorithm for recursive value
		return tempCount;
	}

	public void setTempCount(int tempCount) { 		//for minimax algorithm for recursive value
		this.tempCount = tempCount;
	}

	public  int getTurnPlayerCount() {				//for check who turn to play
		return TurnPlayerCount;
	}


	public  void setTurnPlayerCount(int turnPlayerCount) { 	//for take who turn to play
		TurnPlayerCount = turnPlayerCount;
	}

     boolean checkPlayer() {								//check whether the player is null or not
         return currentPlayer != " ";
     }

     int gettempNum() {										//for return the score for the game
         return tempNum;
     }

     
     String getPlayerState() { 								//for replace the score when it fill up with 4 sides, then replace with sharp.
    	 return currentPlayer.replace(currentPlayer,"#");
     	}

     void setPlayerState(String owner) { 					//for track the current player for the game
     	this.currentPlayer = owner;
     	}

     myGetandSet tempArray() {								//this is for constructor of this getandset
         return new myGetandSet(this.currentPlayer, this.tempNum);
     }

     public myGetandSet(boolean myDrawLine, int myHorizon, int myVertical){  //for track the horizon and vertical once it be moved.
 		this.myHorizon = myHorizon;				//for horizon
 		this.myVertical = myVertical;			//for vertical
 		this.myDrawLine = myDrawLine;			//check weather line | or -
 		
 	}
	
     public myGetandSet(int tempNum) {						//for construct score at the begining.	
         this.currentPlayer = " ";
         this.tempNum = tempNum;
     }

     public myGetandSet(String currentPlayer, int tempNum) {	//for construct score and current player at that moment
         this.currentPlayer = currentPlayer;
         this.tempNum = tempNum;
     }
 
}
