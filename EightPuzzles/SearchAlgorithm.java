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

	public void FindMaxSizeQ(int FindMaxSize) { //function for find size of the que at its max
		
		this.maxQ = Math.max(this.maxQ, FindMaxSize);
		
	}
	
	/*
	 * Breadth-first algorithm. basically, it will search the goal state from the root node
	 * check to see whether the goal or not, Then it will poll the first node in the queue to check whether we hit the goal puzzle or not
	 * iterating the loop until we hit the goal state.
	 */
	public void BFS(NodeLists topmostNode, String myGoal) {

		boolean checkEqual = true;
		int numNodePop = 0;
		//my tmpstate will not store the duplicate state because the hashset will not allow it.
		HashSet<String> tmpstate = new HashSet<String>(); //this step, it will provide the node that already passed 

		NodeLists curNode = new NodeLists(topmostNode.getCurState());				 //declare new nodelist to store current state

		ArrayDeque<NodeLists> myQ = new ArrayDeque<NodeLists>();                      //declare arraydeque
		
		while(checkEqual) {
			
		
				if(!curNode.getCurState().contentEquals(myGoal)) {                  //once it doesn't have the goalstate yet then add it
				tmpstate.add(curNode.getCurState());                                //add the current state to the set
				
				FindMaxSizeQ(myQ.size());                                           //keep track of the size of the queue
				
				ArrayList<String> mySuccessor = new ArrayList<String>();             //create array list for successor 
				mySuccessor = myExecuteSuccessor.MySearch(curNode.getCurState());    //to generate the current state of the puzzle
				
				Myloop:	for(int i = 0; i < mySuccessor.size(); i++) {                //my iterator loop arraylist
					String tempNode = mySuccessor.get(i);                            //loop through the index
					
					NodeLists myChild = new NodeLists(tempNode);                     //myChild nodelists
					if(!tmpstate.contains(tempNode)) {               				 //if tempState doesn't have a set then do, otherwise, get out of here
					
					tmpstate.add(tempNode); 										//add state from successors
					
					curNode.addState(myChild); 										//add temporary node
					
					myChild.setCurNode(curNode);									//update current node 
					
					myQ.add(myChild); 												//add the adjacent to the arrayDeque
					
					}
					else {
						
					}
				}
				try {
					curNode = myQ.poll(); 											//pull the first element of the queue and make the curNode take it for re-check 
																					//weather the goal state or not, which will equal to current node.

				}catch (Exception err) {
					err.printStackTrace();
				}
				//count the node that it pop of the queue
				numNodePop = numNodePop + 1; 		//this is the total of the node pop of the queue

			}

			else {
				checkEqual = false;
			}
		
		}
		//execute these values into the main func.
		myPuzzleMain.myMainExecute(curNode, topmostNode);						//send for summary
		myPuzzleMain.FindMaxSizeQ(maxQ, numNodePop); 							//send to reveal the size of queue and number node pop.


	}



	/*
	 * Depth-first search: it will search the goal state from the root node until the last of the leave node
	 * check to see weather the goal or not, once it poll the last element. 
	 * Then, get the current node which is the goal state. 
	 *
	 */

	public void DFS(NodeLists topmostNode, String myGoal) {

		boolean checkEqual = true;
		int numNodePop = 0;
		HashSet<String> tmpstate = new HashSet<String>(); 							//this step, it will provide the node that already passed 
		NodeLists curNode = new NodeLists(topmostNode.getCurState());				//declare new nodelist to store current state

		ArrayDeque<NodeLists> myQ = new ArrayDeque<NodeLists>(); 					//declare empty arraydeque

		while(checkEqual) {
			
			if(!curNode.getCurState().contentEquals(myGoal)) {						//once it doesn't have the goalstate yet then add it
				FindMaxSizeQ(myQ.size());											//get the size of the queue

				tmpstate.add(curNode.getCurState());								//add the current state to the set

				ArrayList<String> mySuccessor = new ArrayList<String>();			//create array list for successor 
				
																					
				ArrayDeque<NodeLists> mySuccessorq = new ArrayDeque<NodeLists>();   //declare arraydeque for working like a stack for DFS.
				
				mySuccessor = myExecuteSuccessor.MySearch(curNode.getCurState()); 	//find leave until the end of leave
				
				
				Myloop:	for(int i = 0; i < mySuccessor.size(); i++) {				//my iterator loop arraylist	
					String tempNode = mySuccessor.get(i);							//loop through the index	

					if(!tmpstate.contains(tempNode)) {			  					//check for visited node in mySearch 
					NodeLists myChild = new NodeLists(tempNode);					//check the node that is the goal or not.
					
					tmpstate.add(tempNode);											//add temporary node

					curNode.addState(myChild);
					myChild.setCurNode(curNode);									//this step will act like a stack in term of arraydeque. 
					mySuccessorq.push(myChild);										//it will push myCHild(adjacent) to arraydeque.

					}
					
				}
				//it will add mysuccessorQ to arraydeque myQ with addAll parameters at the end of arraydeque.
				
				myQ.addAll(mySuccessorq);

				try {
				//it will pop to make currentnode = the last queue elements
					curNode = myQ.pollLast(); 
				} catch(Exception err) {
					err.printStackTrace();
				}

				numNodePop = numNodePop + 1;										//this is the total of the node pop of the queue

			}

			else {
				checkEqual = false;
			}

		}
		
		//execute these values into the main func.
		myPuzzleMain.myMainExecute(curNode, topmostNode);							//send for summary
		myPuzzleMain.FindMaxSizeQ(maxQ, numNodePop);								//send to reveal the size of queue and number node pop.
		
	}

	/*
	 * Greedy-Best-First search: it will search the goal state by using compare function to compare the
	 *  cost which is the sum of the tiles that is wrong position.
	 */

	
	public void BestFS(NodeLists topmostNode, String myGoal) {

		boolean checkEqual = true;
		int numNodePop = 0;
		HashSet<String> tmpstate = new HashSet<String>(); 						//this step, it will provide the node that already passed 

		NodeLists curNode = new NodeLists(topmostNode.getCurState());			//declare new nodelist to store current state
		curNode.setCost(0);														//set the cost to zero
		
		PriorityQueue<NodeLists> myPriorityQ = new PriorityQueue<NodeLists>(NodeLists.comparenode);  //declare new priorrity queue and store comparator
		
		while(checkEqual) {
			
			if(!curNode.getCurState().contentEquals(myGoal)) {						//once it doesn't have the goalstate yet then add it							
				tmpstate.add(curNode.getCurState());								//add the current state to the set	
				
				FindMaxSizeQ(myPriorityQ.size());
				
				
				ArrayList<String> mySuccessor = new ArrayList<String>();				//create array list for successor
				mySuccessor = myExecuteSuccessor.MySearch(curNode.getCurState());		//find leave until the end of leave
				Myloop:	for(int i = 0; i < mySuccessor.size(); i++) {					//my iterator loop arraylist
					String tempNode = mySuccessor.get(i);								//loop through the index

					if(!tmpstate.contains(tempNode)) {									//check for visited node in mySearch 
					tmpstate.add(tempNode);												 

					NodeLists myChild = new NodeLists(tempNode);						//myChild nodelists
					curNode.addState(myChild);											//add myChild to current node
					myChild.setCurNode(curNode);										//updaye it
					
					
					String string = myChild.getCurState(); 								//get the current stage

					int myRes = 0;
					int s = 0;
					while (s < string.length())  {
			            if (string.charAt(s) != myGoal.charAt(s)) 					//the calculation of approximately cost of the wrong tiles of the position
			            	myRes++;
			        	s++;
					}

					myChild.setTotalCost(myRes);									//update the total cost 
					
					myPriorityQ.add(myChild);										//then add myChild to the queue			
				}
				}
				try {
					curNode = myPriorityQ.poll();								//pull the first element of the queue and make the curNode take it for re-check 
																				//weather the goal state or not, which will equal to current node.

				}catch (Exception err) {
					err.printStackTrace();
				}
				numNodePop = numNodePop + 1;										//this is the total of the node pop of the queue
			}

			else {
				checkEqual = false;
			}

		}
		//execute these values into the main func.
		myPuzzleMain.myMainExecute(curNode, topmostNode);							//send for summary
		myPuzzleMain.FindMaxSizeQ(maxQ, numNodePop);								//send to reveal the size of queue and number node pop.

		
	}	
	
	
	/*
	 *  UniformCost-Search: it will search the goal state by using compare function to compare the
	 *  low-cost node to be the high priority. 
	 */
	
	public void UCS(NodeLists topmostNode, String myGoal) {

		boolean checkEqual = true;
		int numNodePop = 0;
		char indexcount = '0';
		HashSet<String> tmpstate = new HashSet<String>(); //this step, it will provide the node that already passed 

		NodeLists curNode = new NodeLists(topmostNode.getCurState());									 //declare new nodelist to store current state
		curNode.setCost(0);
		PriorityQueue<NodeLists> myPriorityQ = new PriorityQueue<NodeLists>(NodeLists.comparenode);    //declare new priorrity queue and store comparator

		
		while(checkEqual) {
			if(!curNode.getCurState().contentEquals(myGoal)) {
				
				FindMaxSizeQ(myPriorityQ.size());

				tmpstate.add(curNode.getCurState());

				ArrayList<String> mySuccessor = new ArrayList<String>();
				mySuccessor = myExecuteSuccessor.MySearch(curNode.getCurState());			//find leave until the end of leave
				Myloop:	for(int i = 0; i < mySuccessor.size(); i++) {						//my iterator loop arraylist
					String tempNode = mySuccessor.get(i);									//loop through the index	

					
					if(!tmpstate.contains(tempNode)) {									//if tempState doesn't have a set then do, otherwise, get out of here
						tmpstate.add(tempNode);											//add state from successors

						NodeLists myChild = new NodeLists(tempNode);					//myChild nodelists	
						curNode.addState(myChild);										//add myChild to current node	
						myChild.setCurNode(curNode);									//updaye it

						//convert the character into the integer for calculate the approximate cost abd cost.
						int cost = curNode.getTotalCost();
						String countchar = myChild.getCurNode().getCurState();	
						char convertCha = (char) myChild.getCurState().codePointAt(countchar.indexOf(indexcount));  //first index of current state

						int approxCost = Integer.parseInt(String.valueOf(convertCha));
						int total = cost + approxCost;									//summing up the total cost
						myChild.setTotalCost(total);									//update the total cost 
						
						myPriorityQ.add(myChild);										//add adjacent node to the queue
					}
					
					
				}

				try {
					curNode = myPriorityQ.poll();	//using poll to drag the head which is less value.

				}catch (Exception err) {
					err.printStackTrace();
				}
				numNodePop = numNodePop + 1;										//this is the total of the node pop of the queue
			}

			else {
				checkEqual = false;
			}

		}
		//execute these values into the main func.
		myPuzzleMain.myMainExecute(curNode, topmostNode);  						//send for summary
		myPuzzleMain.FindMaxSizeQ(maxQ, numNodePop);     						//send to reveal the size of queue and number node pop.
		
	}	

}





