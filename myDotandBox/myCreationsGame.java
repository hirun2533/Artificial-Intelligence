package myDotandBox;


import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;



	public class myCreationsGame {

	private int myVertical; 	
	private int myHorizon;  	
	
	
	private boolean myDrawLine;  

	myGetandSet MyTempList = new myGetandSet( myDrawLine, myHorizon,  myVertical);  
	private List<myCreationsGame> MyExecute;				
	private int AIScore = 0;								
	private int HumanScore = 0;								
	private final boolean[][] xAxis;						
	private final boolean[][] yAxis;						
	private final int myTableNum;							
	private int TurnPlayerCount;							
	private String Player = "AI";						
	public final myGetandSet[][] myTable; 				


	public static myGetandSet myMinimaxwithAlphaBeta(myCreationsGame gameNode, int myDepth) {
		int max = 1000;  																	
		int min = -1000;																	
		myAlgorithm myTemp = new myAlgorithm();  											
		myCreationsGame myRes = myTemp.myAlphaBetaPruningMax(myDepth, gameNode, min, max); 
		return myRes.ExecuteMoveTable();													
	}

	public  List<myCreationsGame> ExecuteGame(String myPlays) {

		MyExecute = new ArrayList<>();
		int horizonX = 0;
		while(horizonX < myTableNum + 1) {														
			int verticalX = 0;
			while(verticalX < myTableNum) {														

				if (!xAxis[horizonX][verticalX]) {											
					myCreationsGame Tempcloning = new myCreationsGame(myTableNum); 			
				

					Tempcloning.MyTempList.setMyHorizon(horizonX); 							
					Tempcloning.MyTempList.setMyVertical(verticalX); 							
					Tempcloning.MyTempList.setMyDrawLine(true);									
					Tempcloning.setTableSquare(true, horizonX, verticalX, myPlays); 			
					MyExecute.add(Tempcloning); 												
				}
				else {
				}
				verticalX = verticalX + 1;														
			}
			horizonX = horizonX + 1;															
		}	

		int horizonY = 0;
		while(horizonY < myTableNum) {

			int verticalY = 0;
			while(verticalY < myTableNum + 1) {													
				if(!yAxis[horizonY][verticalY]) {												
					myCreationsGame Tempcloning = new myCreationsGame(myTableNum); 				
					Tempcloning.MyTempList.setMyHorizon(horizonY); 								
					Tempcloning.MyTempList.setMyVertical(verticalY); 							
					Tempcloning.MyTempList.setMyDrawLine(false);								

					Tempcloning.setTableSquare(false, horizonY, verticalY, myPlays); 			
					MyExecute.add(Tempcloning); 												
				}
				verticalY = verticalY + 1;														
			}
			horizonY = horizonY + 1;														
		}
		return MyExecute;																		
	}




	public  myGetandSet ExecuteMoveTable() {													

		return MyTempList;																		
	}


	public myCreationsGame(int myTableNum) {

		boolean[][] TempXaxis = new boolean[myTableNum + 1][myTableNum];				
		boolean[][] TempYyaxis = new boolean[myTableNum][myTableNum + 1];				
		myGetandSet[][] TempSquare = new myGetandSet[myTableNum][myTableNum];           
		this.myTableNum = myTableNum;
		Boolean[] tempArr = new Boolean[100];										
		Random myRand = new Random();  													
		
		myTable = new myGetandSet[myTableNum][myTableNum];								
		xAxis = new boolean[myTableNum + 1][myTableNum];								
		yAxis = new boolean[myTableNum][myTableNum + 1];								
		
		
		int count = 0;
		while(count < myTable.length) {													

			myGetandSet[] tempRand = myTable[count];   									
			int countRand = 0;
			while(countRand < tempRand.length) {										
				tempRand[countRand] = new myGetandSet( 1 + myRand.nextInt(5));  		
				countRand = countRand + 1;												
			}
			count = count + 1;															
		}
		
		int tempX = 0; 
		while(tempX < xAxis.length) {													
			Arrays.fill(tempArr, Boolean.TRUE);  										
			tempX = tempX + 1;															
		}

		int tempY = 0; 
		while(tempY < yAxis.length) {													
			Arrays.fill(tempArr, Boolean.TRUE);   										
			tempY = tempY + 1;														
		}

		

		int tempXaxis = 0;
		while(tempXaxis < myTableNum + 1) {												
			TempXaxis[tempXaxis] = Arrays.copyOfRange(xAxis[tempXaxis], myTableNum, myTableNum);  		
			tempXaxis = tempXaxis + 1;												
		}

		int tempYaxis = 0;
		while(tempYaxis < myTableNum) {												
			TempYyaxis[tempYaxis] = Arrays.copyOfRange(xAxis[tempYaxis], myTableNum, myTableNum + 1); 
			tempYaxis = tempYaxis + 1;												
		}

		
		
		int sqrOuter = 0;
		while(sqrOuter < myTableNum) {													
			int sqrIn = 0;
			while(sqrIn < myTableNum) {													 
				TempSquare[sqrOuter][sqrIn] = myTable[sqrOuter][sqrIn].tempArray(); 	
				
				sqrIn = sqrIn + 1;														
			}

			sqrOuter = sqrOuter + 1;													
		}

		return;
	}

	

	boolean setDrawLine( boolean myDrawLine, int myHorizon, int myVertical ) {

		try {
			TurnPlayerCount = ((myTableNum)) * (myTableNum + 1) * 2;				
			boolean check = true;
			if (!myDrawLine || xAxis[myHorizon][myVertical] ) {						
				if (!myDrawLine && !yAxis[myHorizon][myVertical]){					
					
					yAxis[myHorizon][myVertical] = check;							
					MyTempList.setMyHorizon(myHorizon);  							
					MyTempList.setMyVertical(myVertical); 							
					MyTempList.setMyDrawLine(false); 								
					TurnPlayerCount =  TurnPlayerCount - TurnPlayerCount;		
					return true;
				}
			} else {
				xAxis[myHorizon][myVertical] = check;						
					MyTempList.setMyHorizon(myHorizon); 						
					MyTempList.setMyVertical(myVertical); 							
					MyTempList.setMyDrawLine(true); 								
				TurnPlayerCount =  TurnPlayerCount - TurnPlayerCount;			
				return true;
			}
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}

		System.out.println("try a new pair of dots!!!"); 						
		return false;

	}

	void myTableGame() {


		int HorizonNumber = 0;

		while(HorizonNumber < myTableNum + 1) {									

			String Horizon = Integer.toString(HorizonNumber);					

			String stringconcat = new String();								
			String myDot = "*";												
			String StarSpace = "*    ";										

			stringconcat = stringconcat.concat(Horizon).concat(" ");		
			String Star = "* -- ";										


			int myHorizon = 0;
			while(myHorizon < myTableNum ) {									
				if (xAxis[HorizonNumber][myHorizon]) {							
					stringconcat =  stringconcat.concat(Star); 				
				} else {
					stringconcat =  stringconcat.concat(StarSpace); 			
				}
				myHorizon =  myHorizon + 1;										
			}
			stringconcat =  stringconcat + myDot;								
			System.out.println(stringconcat);	

			if (HorizonNumber < myTableNum) {								

				String stringconcatY = new String();						

				int myVertical = 0;
				String temp = "  "; 											
				String tempScore = "  ";										
				String LineY = "| ";											

				stringconcatY = stringconcatY + temp;						



				String getPlayer = myTable[HorizonNumber][myVertical].getPlayerState();   
				
				while (myVertical < myTableNum + 1) {							
					if (myVertical < myTableNum) {								
						String checks = Integer.toString(myTable[HorizonNumber][myVertical].gettempNum());  
						boolean playerCheck = myTable[HorizonNumber][myVertical].checkPlayer();		        
						
						if (playerCheck) {  													
							tempScore = getPlayer;  											
						} else {
							tempScore = checks; 												
						}
					} else {
						tempScore = temp; 													
					}
					
					if(yAxis[HorizonNumber][myVertical]) {										
						stringconcatY = stringconcatY.concat(LineY).concat(tempScore).concat(temp);   
					} else {
						stringconcatY = stringconcatY.concat(temp).concat(tempScore).concat(temp); 	  
					}
					myVertical = myVertical + 1;											

				}
				System.out.println(stringconcatY);
			}

			HorizonNumber = HorizonNumber + 1;											
		}

		MyTempList.setTurnPlayerCount(TurnPlayerCount); 									
		System.out.println("Score(Human)-- " + HumanScore + " " + "Score(AI)-- " + AIScore); 
		System.out.print("\n");

	}


	void setTableSquare( boolean myDrawLine, int myHorizon, int myVertical, String trackPlay) {


		if (myDrawLine) {

		if (myHorizon > 0 && xAxis[myHorizon-1][myVertical] && xAxis[myHorizon][myVertical] && yAxis[myHorizon-1][myVertical] && yAxis[myHorizon-1][myVertical+1]) {  

				myTable[myHorizon - 1][myVertical].setPlayerState(trackPlay);						
				if (trackPlay.equals(Player)) {														
					AIScore = AIScore + myTable[myHorizon - 1][myVertical].gettempNum(); 			
				} else {
					HumanScore = HumanScore + myTable[myHorizon - 1][myVertical].gettempNum(); 		
					}
				} 
				else {
			}

		if (myHorizon < myTableNum && xAxis[myHorizon][myVertical] && xAxis[myHorizon+1][myVertical] && yAxis[myHorizon][myVertical] && yAxis[myHorizon][myVertical+1]) { 

				myTable[myHorizon][myVertical].setPlayerState(trackPlay);							
				if (trackPlay.equals(Player)) {														
					AIScore = AIScore + myTable[myHorizon][myVertical].gettempNum(); 				
				} else {
					HumanScore = HumanScore + myTable[myHorizon][myVertical].gettempNum(); 			
				}
			}

		} else {
		
	 if (myVertical < myTableNum && myVertical > 0 && xAxis[myHorizon][myVertical] && xAxis[myHorizon+1][myVertical] && yAxis[myHorizon][myVertical] && yAxis[myHorizon][myVertical+1]) { 

				myTable[myHorizon][myVertical].setPlayerState(trackPlay);							
				if (trackPlay.equals(Player)) {														
					AIScore = AIScore +  myTable[myHorizon][myVertical].gettempNum(); 			
				} else {
					HumanScore = HumanScore + myTable[myHorizon][myVertical].gettempNum(); 			
				}
			}
		
		if (myVertical > 0 && xAxis[myHorizon][myVertical-1] && xAxis[myHorizon+1][myVertical-1] && yAxis[myHorizon][myVertical-1] && yAxis[myHorizon][myVertical]) {
				myTable[myHorizon][myVertical - 1].setPlayerState(trackPlay);					
				if (trackPlay.equals(Player)) {													
					AIScore =  AIScore + myTable[myHorizon][myVertical - 1].gettempNum(); 		
				} else {
					HumanScore = HumanScore + myTable[myHorizon][myVertical - 1].gettempNum(); 		
				}

			}

		}
	}

}
