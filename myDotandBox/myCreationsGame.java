package myDotandBox;


import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

/*
 * this class is variety for the board game, most will generate and implements the board game 
 * draw the square of the table box and generate value score random. Once the game start
 * it will invoke and run the game along with algorithm class and main class.
 */


	public class myCreationsGame {

	private int myVertical; 	//this one for column
	private int myHorizon;  	//this one for row
	
	
	private boolean myDrawLine;  //my boolean for checking

	myGetandSet MyTempList = new myGetandSet( myDrawLine, myHorizon,  myVertical);  //instatiate get and set class for using.
	private List<myCreationsGame> MyExecute;				//declare list
	private int AIScore = 0;								//declare for AI score 
	private int HumanScore = 0;								//declare for human score
	private final boolean[][] xAxis;						//declare for xxaxis 2d
	private final boolean[][] yAxis;						//declare for yxaxis 2d
	private final int myTableNum;							//for game board number
	private int TurnPlayerCount;							//declare for track the game state
	private String Player = "AI";							//declare for string player
	public final myGetandSet[][] myTable; 					//declare variable for array


	/*
	 * this function is work for minimax algorithm and return the value from max and min that come 
	 * myAlgorithm.java. So, it will be called in the main function.
	 */

	public static myGetandSet myMinimaxwithAlphaBeta(myCreationsGame gameNode, int myDepth) {
		int max = 1000;  																	//declare value for max
		int min = -1000;																	//declare value for min
		myAlgorithm myTemp = new myAlgorithm();  											//instantiate algorithm class for using 
		myCreationsGame myRes = myTemp.myAlphaBetaPruningMax(myDepth, gameNode, min, max); 	//pass the parameter on it
		return myRes.ExecuteMoveTable();													//and return the value
	}
/*
 * this function will store list of the move that it can be moved at the present node
 *  it will add the direction movement to the lists, then the algorithm class will invoke this method for do the game.
 */
	

	public  List<myCreationsGame> ExecuteGame(String myPlays) {

		MyExecute = new ArrayList<>();
		int horizonX = 0;
		while(horizonX < myTableNum + 1) {														//while loop through the game size
			int verticalX = 0;
			while(verticalX < myTableNum) {														//while loop through the game size

				if (!xAxis[horizonX][verticalX]) {												//condition for XAxis
					myCreationsGame Tempcloning = new myCreationsGame(myTableNum); 				//create temporary store the value 
					//take all the value from other methods keep it in array for algorithm minimax.

					Tempcloning.MyTempList.setMyHorizon(horizonX); 								//put the XAxis value 
					Tempcloning.MyTempList.setMyVertical(verticalX); 							//put the XAxis value
					Tempcloning.MyTempList.setMyDrawLine(true);									//set horizon for true
					Tempcloning.setTableSquare(true, horizonX, verticalX, myPlays); 			//invoke value from the settableSquare method
					MyExecute.add(Tempcloning); 												//add the all value into array
				}
				else {
				}
				verticalX = verticalX + 1;														//increment value through the while-loop
			}
			horizonX = horizonX + 1;															//increment value through the while-loop
		}	

		int horizonY = 0;
		while(horizonY < myTableNum) {

			int verticalY = 0;
			while(verticalY < myTableNum + 1) {													//loop through the game size
				if(!yAxis[horizonY][verticalY]) {												//condition for YAxis
					myCreationsGame Tempcloning = new myCreationsGame(myTableNum); 				//create temporary store the value 
					Tempcloning.MyTempList.setMyHorizon(horizonY); 								//put the YAxis value
					Tempcloning.MyTempList.setMyVertical(verticalY); 							//put the YAxis value
					Tempcloning.MyTempList.setMyDrawLine(false);								//set vertical for false

					Tempcloning.setTableSquare(false, horizonY, verticalY, myPlays); 			//invoke value from the settableSquare method
					MyExecute.add(Tempcloning); 												//add the all value into array
				}
				verticalY = verticalY + 1;														//increment value through the while-loop
			}
			horizonY = horizonY + 1;															//increment value through the while-loop
		}
		return MyExecute;																		//return value
	}




	public  myGetandSet ExecuteMoveTable() {													//for invoke getand set class

		return MyTempList;																		//return the value
	}




	/*
	 *  constructor for myCreationGame class store array for the algorithm minimax
	 *  and generate random score for the game.
	 *  
	 */

	public myCreationsGame(int myTableNum) {

		boolean[][] TempXaxis = new boolean[myTableNum + 1][myTableNum];				//declare new array boolean for XAxis
		boolean[][] TempYyaxis = new boolean[myTableNum][myTableNum + 1];				//declare new array boolean for YAxis
		myGetandSet[][] TempSquare = new myGetandSet[myTableNum][myTableNum];           //declare new array boolean for temporary myGetandSet class to be array boolean
		this.myTableNum = myTableNum;
		Boolean[] tempArr = new Boolean[100];											//declare new array
		Random myRand = new Random();  													//generate random score for table board
		
		myTable = new myGetandSet[myTableNum][myTableNum];								//declare to new array 2d
		xAxis = new boolean[myTableNum + 1][myTableNum];								//declare to new array 2d
		yAxis = new boolean[myTableNum][myTableNum + 1];								//declare to new array 2d
		
		/*
		 * get random number when game start with number 1 to 5
		 */
		int count = 0;
		while(count < myTable.length) {													//loop through the game length

			myGetandSet[] tempRand = myTable[count];   									//from mygetandset class
			int countRand = 0;
			while(countRand < tempRand.length) {										//while-loop through array
				tempRand[countRand] = new myGetandSet( 1 + myRand.nextInt(5));  		//random number from 1 to 5
				countRand = countRand + 1;												//increment value through the while-loop
			}
			count = count + 1;															//increment value through the while-loop
		}
		
		int tempX = 0; 
		while(tempX < xAxis.length) {													//loop through the xaxis length
			Arrays.fill(tempArr, Boolean.TRUE);  										// it will be filled up with true value
			tempX = tempX + 1;															//increment value through the while-loop
		}

		int tempY = 0; 
		while(tempY < yAxis.length) {													//loop through the yaxis length
			Arrays.fill(tempArr, Boolean.TRUE);   										// it will be filled up with true value
			tempY = tempY + 1;															//increment value through the while-loop
		}

		

		int tempXaxis = 0;
		while(tempXaxis < myTableNum + 1) {												//loop through the game size
			TempXaxis[tempXaxis] = Arrays.copyOfRange(xAxis[tempXaxis], myTableNum, myTableNum);  		// it will help to copy range from whole table size
			tempXaxis = tempXaxis + 1;													//increment value through the while-loop
		}

		int tempYaxis = 0;
		while(tempYaxis < myTableNum) {													//loop through the game size
			TempYyaxis[tempYaxis] = Arrays.copyOfRange(xAxis[tempYaxis], myTableNum, myTableNum + 1); 	// it will help to copy range from whole table size to size + 1
			tempYaxis = tempYaxis + 1;													//increment value through the while-loop
		}

		//iterate value into temporary array, since I need algorithm use this array for alpha-beta pruning.
		
		int sqrOuter = 0;
		while(sqrOuter < myTableNum) {													 //loop through the game size
			int sqrIn = 0;
			while(sqrIn < myTableNum) {													 //loop through the game size
				TempSquare[sqrOuter][sqrIn] = myTable[sqrOuter][sqrIn].tempArray(); 	 // for store temporary array from get and set class
				
				sqrIn = sqrIn + 1;														 //increment value through the while-loop
			}

			sqrOuter = sqrOuter + 1;													 //increment value through the while-loop
		}

		return;
	}

	/*
	 * this function will check true and false for horizon line and vertical line for the table game.
	 * and then display the line for each dot to dot according to the condition.
	 * it will set the value into the get and set class and then main will it.
	 */


	boolean setDrawLine( boolean myDrawLine, int myHorizon, int myVertical ) {

		try {
			TurnPlayerCount = ((myTableNum)) * (myTableNum + 1) * 2;				//count the game search possibility and keep track on it
			boolean check = true;
			if (!myDrawLine || xAxis[myHorizon][myVertical] ) {						//check the condition for Xaxis
				if (!myDrawLine && !yAxis[myHorizon][myVertical]){					//otherwise, check the condition for Yaxis
					
					yAxis[myHorizon][myVertical] = check;							//compare the boolean
					MyTempList.setMyHorizon(myHorizon);  							// store for YAxis
					MyTempList.setMyVertical(myVertical); 							// store for YAxis
					MyTempList.setMyDrawLine(false); 								//then display this (|)
					TurnPlayerCount =  TurnPlayerCount - TurnPlayerCount;			//keep track the game until its done
					return true;
				}
			} else {
				xAxis[myHorizon][myVertical] = check;								//compare the boolean
					MyTempList.setMyHorizon(myHorizon); 							// store for XAxis
					MyTempList.setMyVertical(myVertical); 							// store for XAxis
					MyTempList.setMyDrawLine(true); 								//then display this (-)
				TurnPlayerCount =  TurnPlayerCount - TurnPlayerCount;				//keep track the game until its done
				return true;
			}
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}

		System.out.println("try a new pair of dots!!!"); 							//alert will be popped when dots are already played
		return false;

	}

/*
 * this function will generate the table for the game depend on the input size from user
 *  
 */
	void myTableGame() {


		int HorizonNumber = 0;

		while(HorizonNumber < myTableNum + 1) {									//loop through the game size

			String Horizon = Integer.toString(HorizonNumber);					//take string become integer for each of the horizon number on the game table

			String stringconcat = new String();									//declare for concat string together
			String myDot = "*";													//dot on the game
			String StarSpace = "*    ";											//for concat the dot in the table game

			stringconcat = stringconcat.concat(Horizon).concat(" ");			//string concat together for create the game table
			String Star = "* -- ";												//in order to got the horizon order from user


			int myHorizon = 0;
			while(myHorizon < myTableNum ) {									//loop through the game size for horizon ones

				if (xAxis[HorizonNumber][myHorizon]) {							//condition for Xaxis
					stringconcat =  stringconcat.concat(Star); 					// it will create XAxis line when user type (-)
				} else {
					stringconcat =  stringconcat.concat(StarSpace); 			// it will use for generate dot with space in XAAis 
				}
				myHorizon =  myHorizon + 1;										//increment value through the while-loop
			}
			stringconcat =  stringconcat + myDot;								//concat string together
			System.out.println(stringconcat);	

			if (HorizonNumber < myTableNum) {									//condition for Yaxis

				String stringconcatY = new String();							//declare for concat string togather

				int myVertical = 0;
				String temp = "  "; 											//string for make table looks ok
				String tempScore = "  ";										//string for temporary score 
				String LineY = "| ";											//in order to got the vertical order from user	

				stringconcatY = stringconcatY + temp;							//concat string together



				String getPlayer = myTable[HorizonNumber][myVertical].getPlayerState();   //invoke the get and set to take the value
				//invoke player string from get and set class which that replace the full dots with sharp(#)
				
				
				while (myVertical < myTableNum + 1) {							//loop through the game size for vertical ones
					// for display score after player play until the square is full with 4 sides
					if (myVertical < myTableNum) {								//condition for score each player
						String checks = Integer.toString(myTable[HorizonNumber][myVertical].gettempNum());  //declare and invoke for take the score
						boolean playerCheck = myTable[HorizonNumber][myVertical].checkPlayer();		        //check who is playing
						
						if (playerCheck) {  													//if players are either human or AI
							tempScore = getPlayer;  											// then put the sharp in the score, to show that the score is counted
						} else {
							tempScore = checks; 												//otherwise, keep play it
						}
					} else {
						tempScore = temp; 														//otherwise, do this line
					}
					
					if(yAxis[HorizonNumber][myVertical]) {										// for display YAxis line 
						stringconcatY = stringconcatY.concat(LineY).concat(tempScore).concat(temp);   // it will create XAxis line when user type |
					} else {
						stringconcatY = stringconcatY.concat(temp).concat(tempScore).concat(temp); 	  // it will use for generate dot with space in YAxis 
					}
					myVertical = myVertical + 1;												//increment value through the while-loop

				}
				System.out.println(stringconcatY);
			}

			HorizonNumber = HorizonNumber + 1;													//increment value through the while-loop
		}

		MyTempList.setTurnPlayerCount(TurnPlayerCount); 										//take the possible turns of the game
		System.out.println("Score(Human)-- " + HumanScore + " " + "Score(AI)-- " + AIScore); 	// display score at the console
		System.out.print("\n");

	}
/*
 * this function will track after the game start for 4 sides of the square box
 * if the square is full with 4 lines then count that score for the square box.
 * it will compare with input of the game table size for the condition
 */


	void setTableSquare( boolean myDrawLine, int myHorizon, int myVertical, String trackPlay) {


		if (myDrawLine) {

			/*
			 * if the square box is done with top line of the square box
			 */
		if (myHorizon > 0 && xAxis[myHorizon-1][myVertical] && xAxis[myHorizon][myVertical] && yAxis[myHorizon-1][myVertical] && yAxis[myHorizon-1][myVertical+1]) {  //condition top one

				myTable[myHorizon - 1][myVertical].setPlayerState(trackPlay);						//calculate the square of the box for top one
				if (trackPlay.equals(Player)) {														//condition for check who is playing and got the complete square box
					AIScore = AIScore + myTable[myHorizon - 1][myVertical].gettempNum(); 			//count the score when AI is get the points
				} else {
					HumanScore = HumanScore + myTable[myHorizon - 1][myVertical].gettempNum(); 		//count the score when human is get the points
					}
				} 
				else {
			}

			/*
			 * if the square box is done with bottom line of the square box
			 */
		if (myHorizon < myTableNum && xAxis[myHorizon][myVertical] && xAxis[myHorizon+1][myVertical] && yAxis[myHorizon][myVertical] && yAxis[myHorizon][myVertical+1]) { //condition bottom one

				myTable[myHorizon][myVertical].setPlayerState(trackPlay);							//calculate the square of the box for bottom one
				if (trackPlay.equals(Player)) {														//condition for check who is playing and got the complete square box
					AIScore = AIScore + myTable[myHorizon][myVertical].gettempNum(); 				//count the score when AI is get the points
				} else {
					HumanScore = HumanScore + myTable[myHorizon][myVertical].gettempNum(); 			//count the score when human is get the points
				}
			}

		} else {
			
			
			
			/*
			 * if the square box is done with right line of the square box
			 */
	 if (myVertical < myTableNum && myVertical > 0 && xAxis[myHorizon][myVertical] && xAxis[myHorizon+1][myVertical] && yAxis[myHorizon][myVertical] && yAxis[myHorizon][myVertical+1]) { //condition right one

				myTable[myHorizon][myVertical].setPlayerState(trackPlay);							//calculate the square of the box for right one
				if (trackPlay.equals(Player)) {														//condition for check who is playing and got the complete square box
					AIScore = AIScore +  myTable[myHorizon][myVertical].gettempNum(); 				//count the score when AI is get the points
				} else {
					HumanScore = HumanScore + myTable[myHorizon][myVertical].gettempNum(); 			//count the score when human is get the points
				}
			}
		
			/*
			 * if the square box is done with left line of the square box
			 */
		if (myVertical > 0 && xAxis[myHorizon][myVertical-1] && xAxis[myHorizon+1][myVertical-1] && yAxis[myHorizon][myVertical-1] && yAxis[myHorizon][myVertical]) {  //condition left one
				myTable[myHorizon][myVertical - 1].setPlayerState(trackPlay);						//calculate the square of the box for left one
				if (trackPlay.equals(Player)) {														//condition for check who is playing and got the complete square box
					AIScore =  AIScore + myTable[myHorizon][myVertical - 1].gettempNum(); 			//count the score when AI is get the points
				} else {
					HumanScore = HumanScore + myTable[myHorizon][myVertical - 1].gettempNum(); 		//count the score when human is get the points
				}

			}

		}
	}

}
