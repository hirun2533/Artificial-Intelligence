package myEightPuzzle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class SearchAlgorithm {

	public String myGoal;
	public NodeLists topmostNode;
	private int maxQ;

	public void FindMaxSizeQ(int FindMaxSize) { 
		
		this.maxQ = Math.max(this.maxQ, FindMaxSize);
		
	}
	
	
	public void BFS(NodeLists topmostNode, String myGoal) {

		boolean checkEqual = true;
		int numNodePop = 0;
		HashSet<String> tmpstate = new HashSet<String>();  

		NodeLists curNode = new NodeLists(topmostNode.getCurState());				 

		ArrayDeque<NodeLists> myQ = new ArrayDeque<NodeLists>();                     
		
		while(checkEqual) {
			
		
				if(!curNode.getCurState().contentEquals(myGoal)) {                  
				tmpstate.add(curNode.getCurState());                              
				
				FindMaxSizeQ(myQ.size());                                          
				
				ArrayList<String> mySuccessor = new ArrayList<String>();             
				mySuccessor = myExecuteSuccessor.MySearch(curNode.getCurState());   
				
				Myloop:	for(int i = 0; i < mySuccessor.size(); i++) {            
					String tempNode = mySuccessor.get(i);                          
					
					NodeLists myChild = new NodeLists(tempNode);                     
					if(!tmpstate.contains(tempNode)) {               			
					
					tmpstate.add(tempNode); 									
					
					curNode.addState(myChild); 										
					
					myChild.setCurNode(curNode);									
					
					myQ.add(myChild); 											
					
					}
					else {
						
					}
				}
				try {
					curNode = myQ.poll(); 											
																				

				}catch (Exception err) {
					err.printStackTrace();
				}
				
				numNodePop = numNodePop + 1; 

			}

			else {
				checkEqual = false;
			}
		
		}
		
		myPuzzleMain.myMainExecute(curNode, topmostNode);						
		myPuzzleMain.FindMaxSizeQ(maxQ, numNodePop); 							


	}


	public void DFS(NodeLists topmostNode, String myGoal) {

		boolean checkEqual = true;
		int numNodePop = 0;
		HashSet<String> tmpstate = new HashSet<String>(); 							
		NodeLists curNode = new NodeLists(topmostNode.getCurState());				

		ArrayDeque<NodeLists> myQ = new ArrayDeque<NodeLists>(); 				

		while(checkEqual) {
			
			if(!curNode.getCurState().contentEquals(myGoal)) {						
				FindMaxSizeQ(myQ.size());											

				tmpstate.add(curNode.getCurState());								

				ArrayList<String> mySuccessor = new ArrayList<String>();			
				
																					
				ArrayDeque<NodeLists> mySuccessorq = new ArrayDeque<NodeLists>();   
				
				mySuccessor = myExecuteSuccessor.MySearch(curNode.getCurState()); 	
				
				
				Myloop:	for(int i = 0; i < mySuccessor.size(); i++) {					
					String tempNode = mySuccessor.get(i);							

					if(!tmpstate.contains(tempNode)) {			  					
					NodeLists myChild = new NodeLists(tempNode);				
					
					tmpstate.add(tempNode);										

					curNode.addState(myChild);
					myChild.setCurNode(curNode);									
					mySuccessorq.push(myChild);									

					}
					
				}
			
				
				myQ.addAll(mySuccessorq);

				try {
				
					curNode = myQ.pollLast(); 
				} catch(Exception err) {
					err.printStackTrace();
				}

				numNodePop = numNodePop + 1;										

			}

			else {
				checkEqual = false;
			}

		}
		
		myPuzzleMain.myMainExecute(curNode, topmostNode);					
		myPuzzleMain.FindMaxSizeQ(maxQ, numNodePop);							
		
	}

	
	
	public void BestFS(NodeLists topmostNode, String myGoal) {

		boolean checkEqual = true;
		int numNodePop = 0;
		HashSet<String> tmpstate = new HashSet<String>(); 						

		NodeLists curNode = new NodeLists(topmostNode.getCurState());			
		curNode.setCost(0);													
		
		PriorityQueue<NodeLists> myPriorityQ = new PriorityQueue<NodeLists>(NodeLists.comparenode); 
		
		while(checkEqual) {
			
			if(!curNode.getCurState().contentEquals(myGoal)) {													
				tmpstate.add(curNode.getCurState());								
				
				FindMaxSizeQ(myPriorityQ.size());
				
				
				ArrayList<String> mySuccessor = new ArrayList<String>();				
				mySuccessor = myExecuteSuccessor.MySearch(curNode.getCurState());		
				Myloop:	for(int i = 0; i < mySuccessor.size(); i++) {					
					String tempNode = mySuccessor.get(i);								

					if(!tmpstate.contains(tempNode)) {									
					tmpstate.add(tempNode);												 

					NodeLists myChild = new NodeLists(tempNode);						
					curNode.addState(myChild);											
					myChild.setCurNode(curNode);										
					
					
					String string = myChild.getCurState(); 							

					int myRes = 0;
					int s = 0;
					while (s < string.length())  {
			            if (string.charAt(s) != myGoal.charAt(s)) 					
			            	myRes++;
			        	s++;
					}

					myChild.setTotalCost(myRes);									
					
					myPriorityQ.add(myChild);												
				}
				}
				try {
					curNode = myPriorityQ.poll();								
																				

				}catch (Exception err) {
					err.printStackTrace();
				}
				numNodePop = numNodePop + 1;										
			}

			else {
				checkEqual = false;
			}

		}
		
		myPuzzleMain.myMainExecute(curNode, topmostNode);							
		myPuzzleMain.FindMaxSizeQ(maxQ, numNodePop);								

		
	}	
	
	
	
	
	public void UCS(NodeLists topmostNode, String myGoal) {

		boolean checkEqual = true;
		int numNodePop = 0;
		char indexcount = '0';
		HashSet<String> tmpstate = new HashSet<String>(); 

		NodeLists curNode = new NodeLists(topmostNode.getCurState());									
		curNode.setCost(0);
		PriorityQueue<NodeLists> myPriorityQ = new PriorityQueue<NodeLists>(NodeLists.comparenode); 

		
		while(checkEqual) {
			if(!curNode.getCurState().contentEquals(myGoal)) {
				
				FindMaxSizeQ(myPriorityQ.size());

				tmpstate.add(curNode.getCurState());

				ArrayList<String> mySuccessor = new ArrayList<String>();
				mySuccessor = myExecuteSuccessor.MySearch(curNode.getCurState());			
				Myloop:	for(int i = 0; i < mySuccessor.size(); i++) {						
					String tempNode = mySuccessor.get(i);									

					
					if(!tmpstate.contains(tempNode)) {									
						tmpstate.add(tempNode);											

						NodeLists myChild = new NodeLists(tempNode);					
						curNode.addState(myChild);											
						myChild.setCurNode(curNode);								

					
						int cost = curNode.getTotalCost();
						String countchar = myChild.getCurNode().getCurState();	
						char convertCha = (char) myChild.getCurState().codePointAt(countchar.indexOf(indexcount));  

						int approxCost = Integer.parseInt(String.valueOf(convertCha));
						int total = cost + approxCost;									
						myChild.setTotalCost(total);									
						
						myPriorityQ.add(myChild);										
					}
					
					
				}

				try {
					curNode = myPriorityQ.poll();	

				}catch (Exception err) {
					err.printStackTrace();
				}
				numNodePop = numNodePop + 1;										
			}

			else {
				checkEqual = false;
			}

		}
		
		myPuzzleMain.myMainExecute(curNode, topmostNode);  					
		myPuzzleMain.FindMaxSizeQ(maxQ, numNodePop);     						
		
	}	

}





