package myEightPuzzle;

import java.util.ArrayList;
import java.util.Comparator;

public class NodeLists {
	
	/*
	 * my getter and setter class for read the variable which is get and update those variable which is set
	 * In this function also has comparator which is use to compare the cost of the node 
	 * Once I declare variable and use function getter and setter in java to generate these variables 
	 */
	
	public NodeLists curNode;
	public int totalCost;
	public int cost;
	public int TotalaproxCost;
	public ArrayList<NodeLists> adjac;
	public boolean visit;
	public String curState;
	
	//compare function, it will be use in the search algorithm
	public static  Comparator<NodeLists> comparenode = new Comparator<NodeLists>() {
		
		@Override
		public int compare(NodeLists o1, NodeLists o2) {
			return Integer.compare(o1.getTotalCost(), o2.getTotalCost());
		}
	};
	
	public final NodeLists getCurNode() {					//for read current node
		return curNode;
	}
	public final void setCurNode(NodeLists curNode) {		//for update current node
		this.curNode = curNode;
	}
	
	
	public void setTotalaprox(int cost, int approxCost) {	//for update approximately cost

		this.totalCost = cost + approxCost;
	}
	
	
	public NodeLists(String states) {						//nodeLists struct
		this.curState = states;
		adjac= new ArrayList<NodeLists>();
	}
	
	
	public void addState(NodeLists node) {					//for adjacent node
		adjac.add(node);
	}
	
	
	public final int getTotalCost() {						//for read total cost
		return totalCost;
	}
	public final void setTotalCost(int totalCost) {			//for update total cost
		this.totalCost = totalCost;
	}

	public final int getCost() {							//for read the cost node
		return cost;
	}
	public final void setCost(int cost) {					//for update cost
		this.cost = cost;
	}

	
	public final ArrayList<NodeLists> getAdjac() {			//array for adjacent node
		return adjac;
	}
	
	
	public final void setAdjac(ArrayList<NodeLists> adjac) {	//for update adjacent node
		this.adjac = adjac;
	}

	public final String getCurState() {						//for read current string state
		return curState;
	}
	
	public final void setCurState(String curState) {	   //read update current string state
		this.curState = curState;
	}
	

}
