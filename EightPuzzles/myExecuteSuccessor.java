package myEightPuzzle;

import java.util.ArrayList;


public class myExecuteSuccessor {
	
	
	public NodeLists topmostNode;

	 public static ArrayList<String> mySuccessorPuzzle(String myTiles) {

		 
		 ArrayList<String> mySwapPuzzle = new ArrayList<String>();
		 
		 char tile0 = myTiles.charAt(0);
		 char tile1 = myTiles.charAt(1);
		 char tile2 = myTiles.charAt(2);
		 char tile3 = myTiles.charAt(3);
		 char tile4 = myTiles.charAt(4);
		 char tile5 = myTiles.charAt(5);
		 char tile6 = myTiles.charAt(6);
		 char tile7 = myTiles.charAt(7);
		 char tile8 = myTiles.charAt(8);
	
		 
			int tmpTiles = Integer.parseInt(String.valueOf(myTiles.indexOf('0')));
			char tmp = 't';
			
			if (tmpTiles == 0){

	            	String begin = myTiles.replace(tile0, tmp);
	            	String tmp0 = myTiles.replace(tile0, tmp).
	            			replace(tile1, tile0).
	            			replace(tmp, tile1);
	            	
	            	
	            	String tmp3 = begin.replace(tile3, tile0).
	            			replace(tmp, tile3);
	            			

	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);

	                
	            }
		

	          else if(tmpTiles == 1){
	            	String begin = myTiles.replace(tile1, tmp);
	            	String tmp0 = myTiles.replace(tile1, tmp).
	            			replace(tile0, tile1).
	            			replace(tmp, tile0);
	            	String tmp3 = begin.replace(tile2, tile1).
	            			replace(tmp, tile2);
	            	String tmp5 = begin.replace(tile4, tile1).
	            	replace(tmp, tile4);
	            	
	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	mySwapPuzzle.add(tmp5);
	            	
	                
	            }
			
	
	          else if(tmpTiles == 2) {

	            	String begin = myTiles.replace(tile2, tmp);
	            	String tmp0 = myTiles.replace(tile2, tmp).
	            	replace(tile1, tile2).
	            	replace(tmp, tile1);
	            	String tmp3 = begin.replace(tile5, tile2).
	            	replace(tmp, tile5);

	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	
	    	      
	            }
		

	          else if(tmpTiles == 3) {
	            	
	      	        String begin = myTiles.replace(tile3, tmp);
	            	String tmp0 = myTiles.replace(tile3, tmp)
	            	.replace(tile0, tile3)
	            	.replace(tmp, tile0); 
	            	String tmp3 = begin.replace(tile4, tile3)
	            	.replace(tmp, tile4);
	            	String tmp5 = begin.replace(tile6, tile3)
	            	.replace(tmp, tile6); 
	            	
	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	mySwapPuzzle.add(tmp5);
	            	
	            }
			
	

	          else if(tmpTiles == 4){

	            	String begin = myTiles.replace(tile4, tmp);
	            	String tmp0 = myTiles.replace(tile4, tmp)
	            	.replace(tile1, tile4)
	            	.replace(tmp, tile1);
	            	String tmp3 = begin.replace(tile3, tile4)
	            	.replace(tmp, tile3);
	            	String tmp5 = begin.replace(tile5, tile4)
	            	.replace(tmp, tile5);
	            		
	            	String tmp7 = begin.replace(tile7, tile4)
	            	.replace(tmp, tile7);
	            	
	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	mySwapPuzzle.add(tmp5);
	            	mySwapPuzzle.add(tmp7);
	            	
	            }


	          else if(tmpTiles == 5) {
	
	            	String begin = myTiles.replace(tile5, tmp);
	            	String tmp0 = myTiles.replace(tile5, tmp)
	            	.replace(tile2, tile5)
	            	.replace(tmp, tile2);
	            	String tmp3 = begin.replace(tile4, tile5)
	            	.replace(tmp, tile4);
	            	String tmp5 = begin.replace(tile8, tile5)
	            	.replace(tmp, tile8);
	            	
	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	mySwapPuzzle.add(tmp5);
	            	
	            	
	            }
	

	          else if(tmpTiles == 6) {
	
	            	String begin = myTiles.replace(tile6, tmp);
	            	String tmp0 = myTiles.replace(tile6, tmp)
	            	.replace(myTiles.charAt(3), tile6)
	            	.replace(tmp, tile3);

	            	String tmp3 = begin.replace(tile7, tile6)
	            	.replace(tmp, tile7);

	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	                

	            }
		
	          else if(tmpTiles == 7) {

	            	String begin = myTiles.replace(tile7, tmp);
	            	
	            	String tmp0 = myTiles.replace(tile7, tmp)
	            	.replace(tile4, tile7)
	            	.replace(tmp, tile4);
	            	

	            	String tmp3 = begin.replace(tile6, tile7)

	            	.replace(tmp, myTiles.charAt(6));
	            	

	            	String tmp5 = begin.replace(tile8, tile7)
	            	.replace(tmp, tile8);
	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	mySwapPuzzle.add(tmp5);
	                
	            }
	

	          else if(tmpTiles == 8) {
	            	
	            	String begin = myTiles.replace(tile8, tmp);
	            	String tmp0 = myTiles.replace(tile8, tmp)
	            	.replace(tile5, tile8)
	            	.replace(tmp, myTiles.charAt(5));

	            	String tmp3 = begin.replace(tile7, tile8)
	            	.replace(tmp, tile7);

	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            }
	        

	        return mySwapPuzzle;


	    }
	
	 public static ArrayList<String> MySearch(String node) {
		
		 NodeLists topmostNode = new NodeLists(node);
		 ArrayList<String> tempNode = new ArrayList<String>();
		 NodeLists mynode = new NodeLists(topmostNode.getCurState());
		 NodeLists curNode = mynode;
		 
		 tempNode = myExecuteSuccessor.mySuccessorPuzzle(curNode.getCurState());
		 	
		 	return tempNode;
	 
	 }
	 
	}

