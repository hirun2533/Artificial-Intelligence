package myDotandBox;

import java.util.Scanner;


public class myDotandBox {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);			
		String myLines;									
		boolean myDrawLine;								
		int myHorizon;									
		int myVertical; 								
		int myboardNum = 0; 							
		int myplies = 0;								
		
			System.out.print("Put your board size you want to play: ");							
			myboardNum = Integer.parseInt(in.next());											

			System.out.print("Put you plies number you want to play: "); 						
			myplies = Integer.parseInt(in.next());						 						
			System.out.print("\n");


		myCreationsGame gameNode = new myCreationsGame(myboardNum);								
		String Player  = "Human";																
		in.nextLine();

		while ((gameNode.MyTempList.getTurnPlayerCount() > 0) || (gameNode.MyTempList.getTurnPlayerCount() == 0) ) {   

			System.out.println(Player + ": Turn" + "\n" );
			gameNode.myTableGame();
	
			if(Player.equals("Human")) {
				System.out.print("Choose the Horizon(x) and Vertical(Y) and Line : ");			 
				in = new Scanner(System.in);

				String myString = in.nextLine();

				Scanner inscan = new Scanner(myString);


				while(inscan.hasNextLine())  {

					myHorizon = inscan.nextInt();												 
					System.out.println("Horizon: "+ myHorizon);
					myVertical = inscan.nextInt();												
					System.out.println("Vertical: "+ myVertical);
					System.out.print("\n");
					myLines = inscan.next().trim();         								
				
					switch(myLines) {
					case "-" : 																	
						myDrawLine = true;														 

						boolean checkAIX = gameNode.setDrawLine(myDrawLine, myHorizon, myVertical); 	

						if (checkAIX) {																	
							gameNode.setTableSquare(myDrawLine, myHorizon, myVertical, Player);			
							Player = "AI";															

						}

						break;

					case "|":																		
						myDrawLine = false;															
						boolean checkAIY = gameNode.setDrawLine(myDrawLine, myHorizon, myVertical);

						if (checkAIY) {																
							gameNode.setTableSquare(myDrawLine, myHorizon, myVertical, Player);		
																								
							Player = "AI";														

						}

						break;

					default: break;
					}
				}
			}
				
				
				else {
			
				myGetandSet ExecuteGame  = myCreationsGame.myMinimaxwithAlphaBeta(gameNode, myplies);  
				myHorizon = ExecuteGame.getMyHorizon();												 
				myVertical = ExecuteGame.getMyVertical();											
				myDrawLine = ExecuteGame.myDrawLine();												 

				boolean check = gameNode.setDrawLine(myDrawLine, myHorizon, myVertical);			
				if (check) {	
					gameNode.setTableSquare(myDrawLine, myHorizon, myVertical, Player);			
																								
					Player = "Human";													

				}

			} 

		}
	}
}
