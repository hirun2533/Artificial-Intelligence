package myEightPuzzle;

import java.util.ArrayList;
import java.util.Comparator;

public class NodeLists {
	
	
	public NodeLists curNode;
	public int totalCost;
	public int cost;
	public int TotalaproxCost;
	public ArrayList<NodeLists> adjac;
	public boolean visit;
	public String curState;
	
	
	public static  Comparator<NodeLists> comparenode = new Comparator<NodeLists>() {
		
		@Override
		public int compare(NodeLists o1, NodeLists o2) {
			return Integer.compare(o1.getTotalCost(), o2.getTotalCost());
		}
	};
	
	public final NodeLists getCurNode() {					
		return curNode;
	}
	public final void setCurNode(NodeLists curNode) {	
		this.curNode = curNode;
	}
	
	
	public void setTotalaprox(int cost, int approxCost) {	

		this.totalCost = cost + approxCost;
	}
	
	
	public NodeLists(String states) {					
		this.curState = states;
		adjac= new ArrayList<NodeLists>();
	}
	
	
	public void addState(NodeLists node) {					
		adjac.add(node);
	}
	
	
	public final int getTotalCost() {						
		return totalCost;
	}
	public final void setTotalCost(int totalCost) {			
		this.totalCost = totalCost;
	}

	public final int getCost() {							
		return cost;
	}
	public final void setCost(int cost) {					
		this.cost = cost;
	}

	
	public final ArrayList<NodeLists> getAdjac() {			
		return adjac;
	}
	
	
	public final void setAdjac(ArrayList<NodeLists> adjac) {	
		this.adjac = adjac;
	}

	public final String getCurState() {						
		return curState;
	}
	
	public final void setCurState(String curState) {	   
		this.curState = curState;
	}
	

}
