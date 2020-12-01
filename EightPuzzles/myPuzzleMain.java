package myEightPuzzle;


import java.util.ArrayList;
import java.util.Scanner;


public class myPuzzleMain {
	
	/*
	 * main state to store the string of tiles in different level
	 */
	final static public String Stateeasy = "134862705";
    final static public String StateMedium = "281043765";
    final static public String StateHard = "567408321";
    final static public String StateGoal = "123804765";
	public static NodeLists topmostNode;
    
	
	/*
	 * this step will provide the choice to the user and let them choose the particular algorithm
	 * and it will send to the condition switch case to show the output in the console
	 */
    public static void main(String[] arg) {
    	
    
    	Scanner in = new Scanner(System.in);
        
        System.out.println("1\t BreadthFirst");
        System.out.println("2\t DepthFirst");
        System.out.println("3\t UniformCost");
        System.out.println("4\t BestFirst");
        System.out.println("5\t A*1");
        System.out.println("6\t A*2");

        System.out.println("Please choose the algorithms");

        
        
        int choice = in.nextInt();

        switch (choice) {
        case 1: 
        

    	SearchAlgorithm searchbfs =  new SearchAlgorithm ();

        
    	searchbfs.BFS( new NodeLists (Stateeasy), StateGoal);
    	searchbfs.BFS( new NodeLists (StateMedium), StateGoal);
    	searchbfs.BFS( new NodeLists (StateHard), StateGoal);

        		break;
        	
        
    	case 2: 
    

    		SearchAlgorithm searchdfs =  new SearchAlgorithm ();

    
    		searchdfs.DFS( new NodeLists (Stateeasy), StateGoal);
    		searchdfs.DFS( new NodeLists (StateMedium), StateGoal);
    		searchdfs.DFS( new NodeLists (StateHard), StateGoal);

    		break;
    		
    	case 3: 
    	    

    		SearchAlgorithm searchucs =  new SearchAlgorithm ();

    
    		searchucs.UCS( new NodeLists (Stateeasy), StateGoal);
    		searchucs.UCS( new NodeLists (StateMedium), StateGoal);
    		searchucs.UCS( new NodeLists (StateHard), StateGoal);

    		break;
    		
    	case 4: 
    	    

    		SearchAlgorithm searchBestFS =  new SearchAlgorithm ();
    
    		searchBestFS.BestFS( new NodeLists (Stateeasy), StateGoal);
    		searchBestFS.BestFS( new NodeLists (StateMedium), StateGoal);
    		searchBestFS.BestFS( new NodeLists (StateHard), StateGoal);

    		break;
    		
    		
    	case 5: 
    	    

    		AStarOne searchA1 =  new AStarOne();

    
    		searchA1.AStarone( new NodeLists (Stateeasy), StateGoal);
    		searchA1.AStarone( new NodeLists (StateMedium), StateGoal);
    		searchA1.AStarone( new NodeLists (StateHard), StateGoal);

    		break;
    		
    	case 6: 
    	    

    		AStarTwo searchA2 =  new AStarTwo();

    
    		searchA2.AStartwo ( new NodeLists (Stateeasy), StateGoal);
    		searchA2.AStartwo( new NodeLists (StateMedium), StateGoal);
    		searchA2.AStartwo( new NodeLists (StateHard), StateGoal);

    		break;	
    		
    		
    		
}
    }
    
  /*
   * this is direction of the tile when its move
   * up, left, write and down moving function, which will calculate the tiles value 
   * it will send the result in string when its called.   
   */
    public static  String MyExecuteDirection(String tmpState, String myTempMove) {
		
		int tmpTilesMove = Integer.parseInt(String.valueOf(myTempMove.indexOf('0')));
		int tmpTileState = Integer.parseInt(String.valueOf(tmpState.indexOf('0')));

       if(tmpTilesMove - tmpTileState == -3) {					//when the move equal minus 3 will be up
    	   return "UP";
       }
       else if (tmpTilesMove - tmpTileState == 3) {				//when the move equal 3 will be down
    	   return "DOWN";
       }
       else if(tmpTilesMove - tmpTileState == -1) {				//when the move equal minus 1 will be left
    	   return "LEFT";
       }
       
       else if(tmpTilesMove - tmpTileState == 1) {				//when the move equal 1 will be right
    	   return "RIGHT";
       }
		
		return null;

    }
    
    /*
     * myMainExecute will be called with SearchAlgorithm class and it will come with particular node
     * from particular algorithm depends on user. it will be stored to the ArrayList and iterate out the loop
     * and count those numbers at the end.
     * this function will display the result in the screen console, included algorithms summary.
     */

  public static void myMainExecute(NodeLists myGoal, NodeLists topmostNode) {
	 boolean checkEqual = true;
	 String myState = topmostNode.getCurState();					//get the curent state
     int tmptotal = 0;
     int indexcount = '0';
     
	  ArrayList<NodeLists> res = new ArrayList<NodeLists>();		//declare arraylist
    	res.add(myGoal);											//add the goal state to arraylist	
    	
        while (checkEqual) {
        	if(!myGoal.getCurState().contentEquals(myState)) {		//if it doesn't have goalstate yet then add
        	res.add(myGoal.getCurNode());							//add goal which is current one to the array
           	myGoal = myGoal.getCurNode();							//set it up
          }
        
        	else {
				checkEqual = false;
			}
        }
        
        System.out.println("\n");
        System.out.println(" start state");
        int temp = res.size()-1;									//take the array with the algorithm result at the end and loop down to zero
        while(temp >= 0) {

        	String myTempMove = res.get(temp).getCurState();		//get the value to the myTempMove
            if (!myState.contentEquals(myTempMove)) {				//check to see when it has the same value or not
           		char convertCha = (char) myTempMove.codePointAt(myState.indexOf(indexcount));	//step for convert string to integer	
				int tmpcost = Integer.parseInt(String.valueOf(convertCha));						//step for convert string to integer	
				tmptotal = tmptotal + tmpcost;   												//keep count the total of the cost
				
				
                System.out.println(" " + MyExecuteDirection(myState, myTempMove) 				//print the value
                + ", cost = " + tmpcost + ", total cost = " + tmptotal);
                
            }
            myState = myTempMove;

        	System.out.println(" Tiles >> " + res.get(temp).getCurState());
        	System.out.println("\n");
            
        	temp--;
        	
        }
        	System.out.println("	  ==Summary==       ");
        	System.out.println(">> Length:  " + (res.size()-1) + " " + "    >> Cost:  " + tmptotal);
        	
  }
  
  /*
   * this function will show the size of the particular queue that come from particular algorithm
   * it will take the math max function to present the size and the node pop of the queue
   * which will be called in searchAlgorithm class.
   */
	private static int maxQ;
	public static void FindMaxSizeQ(int FindMaxSizeQ, int numNodePop) {
			maxQ = Math.max(maxQ, FindMaxSizeQ);												//find max size of the queue
		System.out.println(">> Space:  " +  maxQ + "   >> Time:  " + numNodePop);

		}
}
