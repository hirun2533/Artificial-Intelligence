package myDotandBox;

import java.util.List;

public class myAlgorithm {
	static String AI;							//for AI player
	static String Human;						//for human player
	public myCreationsGame Nodemax = null;		//for return node using for max
	public myCreationsGame NodeMin = null;		//for return node using for min
	public List<myCreationsGame> tempMin;		//for array list using for min
	public List<myCreationsGame> tempMax;		//for array list using for max
	/*
	 * this function work for alpha value. if will start with maximizing player which is Human at the MAx state
	 * and then it will prune the branch when it has to.
	 */
	
   public myCreationsGame myAlphaBetaPruningMax(int myDepth, myCreationsGame gameNode, int myAlpha, int myBeta) {
	   
	   tempMax = gameNode.ExecuteGame(Human);										//when maximize player is playing
   		int myBest = -1000; 														//let max take minus infinity value
   		myCreationsGame Nodemax = null;												//set my game state to the null for update the value
    	
    	int mmax = 0; 																//let min take minus infinity	
        if (myDepth == 0) { 														//if myindepth get the terminal branch then return 
            return gameNode;
        }
        	
        	while(mmax < tempMax.size()) {  										//for iterate each of adjacent node then do following code
			myCreationsGame maxiterator = tempMax.get(mmax); 						//for every elements adjacent node

			// it will get the maximun value 
			if((gameNode.ExecuteGame(Human) != null) && myAlphaBetaPruningMin(myDepth - 1, maxiterator, myAlpha, myBeta).MyTempList.getTempCount() > myBest) {		//for check condition									
			myBest = Math.max(myBest, myAlphaBetaPruningMin(myDepth - 1, maxiterator, myAlpha, myBeta).MyTempList.getTempCount()); //make the max value
        	Nodemax = maxiterator;													//update the node 
			}
		   
        		myAlpha = Math.max(myAlpha, myBest);  								//take the alpha value

      	        if (myBeta <= myAlpha) break;										//whenever it see beta value that less that alpha then prune actions(alpha > Bata)
            	mmax++;
        	}
        	
		    Nodemax.MyTempList.setTempCount(myBest);								//then, set the value
        	return Nodemax; 														//return best val for maximum

        }       
   
   	/*
	 * this function work for Beta value. if will start with minimizing player which is AI at the Min state
	 * and then it will prune the branch when it has to.
	 */
      public myCreationsGame myAlphaBetaPruningMin(int myDepth, myCreationsGame gameNode, int myAlpha, int myBeta) {								
    	  	
    	  	
        	 tempMin = gameNode.ExecuteGame(AI); 									//when minimize player is playing
        	  int myBest = 1000; 													//let min take minus infinity
    		  int mmin = 0;
    		  myCreationsGame NodeMin = null;										//set my game state to the null for update the value
    		  if (myDepth == 0) { 													//if myindepth get the terminal branch then return 
    	            return gameNode;
    	      }   
    		 while(mmin < tempMin.size())  { 										//for iterate each of adjacent node then do following code
	    		myCreationsGame miniterator = tempMin.get(mmin); 					//for every elements adjacent node
	    		// it will get the minimum value 
	    		if((gameNode.ExecuteGame(AI) != null) && myAlphaBetaPruningMax(myDepth - 1, miniterator, myAlpha, myBeta).MyTempList.getTempCount() < myBest) {  //for check condition
					myBest = Math.min(myBest, myAlphaBetaPruningMax(myDepth - 1, miniterator, myAlpha, myBeta).MyTempList.getTempCount());		//make the min value
		        	NodeMin = miniterator;											//and then update the game node

				}     		
				myBeta = Math.min(myBeta, myBest); 						 		     //take the beta value

				if (myBeta <= myAlpha)  break;										 //whenever it see beta value that less that alpha then prune actions (alpha > Bata)
				mmin++;
    		 	}
				NodeMin.MyTempList.setTempCount(myBest);							 //then, set the vaue
				return NodeMin; 													 //return best val for minimum	
   		}
   
   }
	
