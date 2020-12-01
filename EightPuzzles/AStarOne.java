package myEightPuzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;


public class AStarOne {

	public String myGoal;
	public NodeLists topmostNode;

	private int maxQ;

	public void FindMaxSizeQ(int FindMaxSize) {
		
		this.maxQ = Math.max(this.maxQ, FindMaxSize);
		
	}
	
	/*
	 *A*one basically, it will search the goal by get the cost start from the current node
	 *calculating with the approximately value of the wrong position of the tiles until we hit the goal.
	 */
	
	public void AStarone(NodeLists topmostNode, String myGoal) {

		boolean checkEqual = true;
		int numNodePop = 0;
		char indexcount = '0';
		HashSet<String> tmpstate = new HashSet<String>();

		NodeLists curNode = new NodeLists(topmostNode.getCurState());   //declare new nodelist to store current state
		curNode.setCost(0);
		PriorityQueue<NodeLists> myPriorityQ = new PriorityQueue<NodeLists>(NodeLists.comparenode);

		
		while(checkEqual) {
			if(!curNode.getCurState().contentEquals(myGoal)) { 									 //once it doesn't have the goalstate yet then add it
				tmpstate.add(curNode.getCurState());											 //add the current state to the set
				FindMaxSizeQ(myPriorityQ.size());												 //keep track of the size of the queue

				ArrayList<String> mySuccessor = new ArrayList<String>(); 						 //create array list for successor
				mySuccessor = myExecuteSuccessor.MySearch(curNode.getCurState()); 				 //to generate the current state of the puzzle
				Myloop:	for(int i = 0; i < mySuccessor.size(); i++) { 							 //my iterator loop arraylist	
					String tempNode = mySuccessor.get(i); 										 //loop through the index

					if(!tmpstate.contains(tempNode)) { 											//if tempState doesn't have a set then do, otherwise, get out of here
					tmpstate.add(tempNode); 													//add state from successors

					NodeLists child = new NodeLists(tempNode); 									//myChild nodelists
					curNode.addState(child); 													//add myChild to current node
					child.setCurNode(curNode);													//update current node
					
					String string = child.getCurState(); 										//get the current stage

					int myResApprox = 0;
					int s = 0;
					while (s < string.length())  {
			            if (string.charAt(s) != myGoal.charAt(s))            					//the calculation of approximately cost of the wrong tiles of the position
			            	myResApprox++;
			        	s++;
					}
				
					
					int cost = curNode.getTotalCost();											//summing up the total cost
					String countchar = child.getCurNode().getCurState();
					char convertCha = child.getCurState().charAt(countchar.indexOf(indexcount)); //first index of current state

					int approxCost = Integer.parseInt(String.valueOf(convertCha));				//convert string to integer to take the value
					//total between cuurent state and approximately value of the current state to the goal state
					int total = cost + approxCost;												//summing up the total cost
					child.setTotalaprox(total, myResApprox);									//update the total cost 
					
					myPriorityQ.add(child);														//then add myChild to the queue	
					}
				}

				try {
					curNode = myPriorityQ.poll();												//pull the first element of the queue and make the curNode take it for re-check 
																								//weather the goal state or not, which will equal to current node.

				}catch (Exception err) {
					err.printStackTrace();
				}
				numNodePop = numNodePop + 1;													//this is the total of the node pop of the queue
			}

			else {
				checkEqual = false;
			}

		}
		//execute these values into the main func.
		myPuzzleMain.myMainExecute(curNode, topmostNode);										//send for summary
		myPuzzleMain.FindMaxSizeQ(maxQ, numNodePop);											//send to reveal the size of queue and number node pop.
		
	}	
	
	
}
