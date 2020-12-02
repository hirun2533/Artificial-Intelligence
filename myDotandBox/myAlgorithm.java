package myDotandBox;

import java.util.List;

public class myAlgorithm {
	static String AI;							
	static String Human;						
	public myCreationsGame Nodemax = null;		
	public myCreationsGame NodeMin = null;		
	public List<myCreationsGame> tempMin;		
	public List<myCreationsGame> tempMax;		
	
	
   public myCreationsGame myAlphaBetaPruningMax(int myDepth, myCreationsGame gameNode, int myAlpha, int myBeta) {
	   
	   tempMax = gameNode.ExecuteGame(Human);										
   		int myBest = -1000; 														
   		myCreationsGame Nodemax = null;												
    	
    	int mmax = 0; 																
        if (myDepth == 0) { 														
            return gameNode;
        }
        	
        	while(mmax < tempMax.size()) {  										
			myCreationsGame maxiterator = tempMax.get(mmax); 						

			
			if((gameNode.ExecuteGame(Human) != null) && myAlphaBetaPruningMin(myDepth - 1, maxiterator, myAlpha, myBeta).MyTempList.getTempCount() > myBest) {		
			myBest = Math.max(myBest, myAlphaBetaPruningMin(myDepth - 1, maxiterator, myAlpha, myBeta).MyTempList.getTempCount()); 
        	Nodemax = maxiterator;													
			}
		   
        		myAlpha = Math.max(myAlpha, myBest);  							

      	        if (myBeta <= myAlpha) break;										
            	mmax++;
        	}
        	
		    Nodemax.MyTempList.setTempCount(myBest);							
        	return Nodemax; 													

        }       
   
   
      public myCreationsGame myAlphaBetaPruningMin(int myDepth, myCreationsGame gameNode, int myAlpha, int myBeta) {								
    	  	
    	  	
        	 tempMin = gameNode.ExecuteGame(AI); 									
        	  int myBest = 1000; 												
    		  int mmin = 0;
    		  myCreationsGame NodeMin = null;										
    		  if (myDepth == 0) { 													
    	            return gameNode;
    	      }   
    		 while(mmin < tempMin.size())  { 										
	    		myCreationsGame miniterator = tempMin.get(mmin); 				
	    		
	    		if((gameNode.ExecuteGame(AI) != null) && myAlphaBetaPruningMax(myDepth - 1, miniterator, myAlpha, myBeta).MyTempList.getTempCount() < myBest) {  
					myBest = Math.min(myBest, myAlphaBetaPruningMax(myDepth - 1, miniterator, myAlpha, myBeta).MyTempList.getTempCount());		
		        	NodeMin = miniterator;											

				}     		
				myBeta = Math.min(myBeta, myBest); 						 		    

				if (myBeta <= myAlpha)  break;										 
				mmin++;
    		 	}
				NodeMin.MyTempList.setTempCount(myBest);							 
				return NodeMin; 													 
   		}
   
   }
	
