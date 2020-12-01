package myDotandBox;

import java.util.Scanner;

/*
 * this main will work for take input from user which is board game size and plies
 * and when game start it also take input of the horizon and column number for run the game
 * at the end, it will also show the result between human and AI, when game done.
 */

public class myDotandBox {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);			//initial scanner for input parameters
		String myLines;									//declare string
		boolean myDrawLine;								//boolean checking
		int myHorizon;									//for horizon
		int myVertical; 								//for vertical
		int myboardNum = 0; 							//for board size numberinitial to zero	
		int myplies = 0;								//for plys number initial to zero
		
			System.out.print("Put your board size you want to play: ");							//ask for put the size of the game
			myboardNum = Integer.parseInt(in.next());											//keep string convert to integer

			System.out.print("Put you plies number you want to play: "); 						//ask for put the plys number of the game
			myplies = Integer.parseInt(in.next());						 						//keep string convert to integer
			System.out.print("\n");


		myCreationsGame gameNode = new myCreationsGame(myboardNum);								//declare creation class for take the board size number
		String Player  = "Human";																//first player is Maximizer
		in.nextLine();

		while ((gameNode.MyTempList.getTurnPlayerCount() > 0) || (gameNode.MyTempList.getTurnPlayerCount() == 0) ) {   //loop through the game state

			System.out.println(Player + ": Turn" + "\n" );
			gameNode.myTableGame();
			//for human turn, for play the game type the horizon and vertical
			if(Player.equals("Human")) {
				System.out.print("Choose the Horizon(x) and Vertical(Y) and Line : ");			 //for take input from user
				in = new Scanner(System.in);

				String myString = in.nextLine();

				Scanner inscan = new Scanner(myString);
				//take input value from user which are Horizon number and Vertical number for play the board with direction - or |
				//from dot to dot.

				while(inscan.hasNextLine())  {

					myHorizon = inscan.nextInt();												 //take Horizon
					System.out.println("Horizon: "+ myHorizon);
					myVertical = inscan.nextInt();												 //take Vertical
					System.out.println("Vertical: "+ myVertical);
					System.out.print("\n");
					myLines = inscan.next().trim();         									 //cut the char which is - or |
				//for AI to make a decision to play the game
					switch(myLines) {
					case "-" : 																	 //if this sign is ordered then do the following code
						myDrawLine = true;														 //if it set to true then

						boolean checkAIX = gameNode.setDrawLine(myDrawLine, myHorizon, myVertical); 	//invoke this boolean to the set direction for game

						if (checkAIX) {																	//check to see AI player
							gameNode.setTableSquare(myDrawLine, myHorizon, myVertical, Player);			//do this line, for check to see the line of the box that is complete yet
							Player = "AI";																//for AI turns

						}

						break;

					case "|":																		//if this sign is ordered then do the following code
						myDrawLine = false;															//if it set to false then
						boolean checkAIY = gameNode.setDrawLine(myDrawLine, myHorizon, myVertical);	//invoke this boolean to the set direction for game

						if (checkAIY) {																//check to see AI player
							gameNode.setTableSquare(myDrawLine, myHorizon, myVertical, Player);		//do this line, for check to see the line of the box that is complete yet	
																									//and return the score once it complete
							Player = "AI";															//for AI turns

						}

						break;

					default: break;
					}
				}
			}
				
				//this block, it will invoke minimax algorithm for do the max and min
				else {
			
				myGetandSet ExecuteGame  = myCreationsGame.myMinimaxwithAlphaBeta(gameNode, myplies);  //invoke the minimax and alpha beta pruning
				myHorizon = ExecuteGame.getMyHorizon();												 //return the Horizon 
				myVertical = ExecuteGame.getMyVertical();											 // return the vertical
				myDrawLine = ExecuteGame.myDrawLine();												 //also, the specific direction for the square box 

				boolean check = gameNode.setDrawLine(myDrawLine, myHorizon, myVertical);			 //invoke this boolean to the set direction for game
				if (check) {	
					gameNode.setTableSquare(myDrawLine, myHorizon, myVertical, Player);				//do this line, for check to see the line of the box that is complete yet
																									//and return the score once it complete
					Player = "Human";																//for Human turns

				}

			} 

		}
	}
}
