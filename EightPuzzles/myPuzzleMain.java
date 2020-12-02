package myEightPuzzle;


import java.util.ArrayList;
import java.util.Scanner;


public class myPuzzleMain {
	

	final static public String Stateeasy = "134862705";
    final static public String StateMedium = "281043765";
    final static public String StateHard = "567408321";
    final static public String StateGoal = "123804765";
	public static NodeLists topmostNode;
    
	
	
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
    
  
    public static  String MyExecuteDirection(String tmpState, String myTempMove) {
		
		int tmpTilesMove = Integer.parseInt(String.valueOf(myTempMove.indexOf('0')));
		int tmpTileState = Integer.parseInt(String.valueOf(tmpState.indexOf('0')));

       if(tmpTilesMove - tmpTileState == -3) {					
    	   return "UP";
       }
       else if (tmpTilesMove - tmpTileState == 3) {				
    	   return "DOWN";
       }
       else if(tmpTilesMove - tmpTileState == -1) {				
    	   return "LEFT";
       }
       
       else if(tmpTilesMove - tmpTileState == 1) {				
    	   return "RIGHT";
       }
		
		return null;

    }
    
   

  public static void myMainExecute(NodeLists myGoal, NodeLists topmostNode) {
	 boolean checkEqual = true;
	 String myState = topmostNode.getCurState();					
     int tmptotal = 0;
     int indexcount = '0';
     
	  ArrayList<NodeLists> res = new ArrayList<NodeLists>();		
    	res.add(myGoal);											
    	
        while (checkEqual) {
        	if(!myGoal.getCurState().contentEquals(myState)) {		
        	res.add(myGoal.getCurNode());							
           	myGoal = myGoal.getCurNode();							
          }
        
        	else {
				checkEqual = false;
			}
        }
        
        System.out.println("\n");
        System.out.println(" start state");
        int temp = res.size()-1;									
        while(temp >= 0) {

        	String myTempMove = res.get(temp).getCurState();		
            if (!myState.contentEquals(myTempMove)) {				
           		char convertCha = (char) myTempMove.codePointAt(myState.indexOf(indexcount));		
				int tmpcost = Integer.parseInt(String.valueOf(convertCha));							
				tmptotal = tmptotal + tmpcost;   												
				
				
                System.out.println(" " + MyExecuteDirection(myState, myTempMove) 				
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
  
  
	private static int maxQ;
	public static void FindMaxSizeQ(int FindMaxSizeQ, int numNodePop) {
			maxQ = Math.max(maxQ, FindMaxSizeQ);												
		System.out.println(">> Space:  " +  maxQ + "   >> Time:  " + numNodePop);

		}
}
