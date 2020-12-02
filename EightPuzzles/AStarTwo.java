package myEightPuzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarTwo {
	public String myGoal;
	public NodeLists topmostNode;
	private int maxQ;
	public int tmpRowCount = 3;
	public void FindMaxSizeQ(int FindMaxSize) {
		
		this.maxQ = Math.max(this.maxQ, FindMaxSize);
		
	}
	
	public void AStartwo(NodeLists topmostNode, String myGoal) {

		boolean checkEqual = true;
		int numNodePop = 0;
		char indexcount = '0';
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

					NodeLists child = new NodeLists(tempNode); 								
					curNode.addState(child); 												
					child.setCurNode(curNode);												
					
					
					String string = child.getCurState(); 									

					int myResApprox = 0;
					int s = 0; 
					
				
					while(s < string.length()) {
						int calmyGoal = myGoal.indexOf(string.codePointAt(s));

						int calManhattan = Math.abs(s%tmpRowCount - calmyGoal%tmpRowCount)   
								+ Math.abs(s/tmpRowCount - calmyGoal/tmpRowCount);
						myResApprox += calManhattan;

						s++;
					}
				
						
					int cost = curNode.getTotalCost();																
					String countchar = child.getCurNode().getCurState();
					char convertCha = child.getCurState().charAt(countchar.indexOf(indexcount));  

					int approxCost = Integer.parseInt(String.valueOf(convertCha));				
					
					
					int total = cost + approxCost;												
					child.setTotalaprox(total, myResApprox);									
					myPriorityQ.add(child);														
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
