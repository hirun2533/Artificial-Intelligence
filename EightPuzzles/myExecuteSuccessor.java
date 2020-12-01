package myEightPuzzle;

import java.util.ArrayList;


public class myExecuteSuccessor {
	
	
	public NodeLists topmostNode;
	
/*
 * my successor class will generate the index of the tiles of the puzzle which are index zero until index 8
 * it will swap the tiles index with the possible tiles that surrounding of the particular index
 */
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
			//it will swap index 0 with the possible surrounding tiles which are index 1 and index 3
			if (tmpTiles == 0){

	            	String begin = myTiles.replace(tile0, tmp);
	            	String tmp0 = myTiles.replace(tile0, tmp).
	            			replace(tile1, tile0).
	            			replace(tmp, tile1);// swap possible index one 
	            	
	            	
	            	String tmp3 = begin.replace(tile3, tile0).
	            			replace(tmp, tile3);// swap possible index three
	            			

	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);

	                
	            }
		//it will swap index 1 with the possible surrounding tiles which are index 0 and index 4 and index 2

	          else if(tmpTiles == 1){
	            	String begin = myTiles.replace(tile1, tmp);
	            	String tmp0 = myTiles.replace(tile1, tmp).
	            			replace(tile0, tile1).
	            			replace(tmp, tile0);// swap possible index zero
	            	String tmp3 = begin.replace(tile2, tile1).
	            			replace(tmp, tile2);// swap possible index two
	            	String tmp5 = begin.replace(tile4, tile1).
	            	replace(tmp, tile4);// swap possible index four
	            	
	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	mySwapPuzzle.add(tmp5);
	            	
	                
	            }
			//it will swap index 2 with the possible surrounding tiles which are index 1 and index 5
	
	          else if(tmpTiles == 2) {

	            	String begin = myTiles.replace(tile2, tmp);
	            	String tmp0 = myTiles.replace(tile2, tmp).
	            	replace(tile1, tile2).
	            	replace(tmp, tile1);// swap possible index one
	            	String tmp3 = begin.replace(tile5, tile2).
	            	replace(tmp, tile5);// swap possible index five

	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	
	    	      
	            }
			//it will swap index 3 with the possible surrounding tiles which are index 0 and index 4 and index 6

	          else if(tmpTiles == 3) {
	            	
	      	        String begin = myTiles.replace(tile3, tmp);
	            	String tmp0 = myTiles.replace(tile3, tmp)
	            	.replace(tile0, tile3)
	            	.replace(tmp, tile0); // swap possible index zero
	            	String tmp3 = begin.replace(tile4, tile3)
	            	.replace(tmp, tile4);// swap possible index four
	            	String tmp5 = begin.replace(tile6, tile3)
	            	.replace(tmp, tile6); // swap possible index six
	            	
	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	mySwapPuzzle.add(tmp5);
	            	
	            }
			
		//it will swap index 4 with the possible surrounding tiles which are index 1 and index 3 and index 5 and index 6

	          else if(tmpTiles == 4){

	            	String begin = myTiles.replace(tile4, tmp);
	            	String tmp0 = myTiles.replace(tile4, tmp)
	            	.replace(tile1, tile4)
	            	.replace(tmp, tile1);// swap possible index one
	            	String tmp3 = begin.replace(tile3, tile4)
	            	.replace(tmp, tile3);// swap possible index three
	            	String tmp5 = begin.replace(tile5, tile4)
	            	.replace(tmp, tile5);// swap possible index five
	            		
	            	String tmp7 = begin.replace(tile7, tile4)
	            	.replace(tmp, tile7);// swap possible index seven
	            	
	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	mySwapPuzzle.add(tmp5);
	            	mySwapPuzzle.add(tmp7);
	            	
	            }
	//it will swap index 5 with the possible surrounding tiles which are index 2 and index 4 and index 8

	          else if(tmpTiles == 5) {
	
	            	String begin = myTiles.replace(tile5, tmp);
	            	String tmp0 = myTiles.replace(tile5, tmp)
	            	.replace(tile2, tile5)
	            	.replace(tmp, tile2);// swap possible index two
	            	String tmp3 = begin.replace(tile4, tile5)
	            	.replace(tmp, tile4);// swap possible index four
	            	String tmp5 = begin.replace(tile8, tile5)
	            	.replace(tmp, tile8);// swap possible index eight
	            	
	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	mySwapPuzzle.add(tmp5);
	            	
	            	
	            }
	//it will swap index 6 with the possible surrounding tiles which are index 3 and index 7

	          else if(tmpTiles == 6) {
	
	            	String begin = myTiles.replace(tile6, tmp);
	            	String tmp0 = myTiles.replace(tile6, tmp)
	            	.replace(myTiles.charAt(3), tile6)
	            	.replace(tmp, tile3);// swap possible index three

	            	String tmp3 = begin.replace(tile7, tile6)
	            	.replace(tmp, tile7);// swap possible index seven

	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	                

	            }
	//it will swap index 7 with the possible surrounding tiles which are index 4 and index 6 and index 8		
	          else if(tmpTiles == 7) {

	            	String begin = myTiles.replace(tile7, tmp);
	            	
	            	String tmp0 = myTiles.replace(tile7, tmp)
	            	.replace(tile4, tile7)
	            	.replace(tmp, tile4);// swap possible index four
	            	

	            	String tmp3 = begin.replace(tile6, tile7)

	            	.replace(tmp, myTiles.charAt(6));// swap possible index six
	            	

	            	String tmp5 = begin.replace(tile8, tile7)
	            	.replace(tmp, tile8);// swap possible index right
	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            	mySwapPuzzle.add(tmp5);
	                
	            }
	//it will swap index 8 with the possible surrounding tiles which are index 5 and index 7	

	          else if(tmpTiles == 8) {
	            	
	            	String begin = myTiles.replace(tile8, tmp);
	            	String tmp0 = myTiles.replace(tile8, tmp)
	            	.replace(tile5, tile8)
	            	.replace(tmp, myTiles.charAt(5));// swap possible index seven

	            	String tmp3 = begin.replace(tile7, tile8)
	            	.replace(tmp, tile7);// swap possible index seven

	            	mySwapPuzzle.add(tmp0);
	            	mySwapPuzzle.add(tmp3);
	            }
	        

	        return mySwapPuzzle;


	    }
	 /*
	  * my arraylist function for return the cuurrent node to the particular algorithm search
	  * which will take the successor when its execute
	  */

	 public static ArrayList<String> MySearch(String node) {
		
		 NodeLists topmostNode = new NodeLists(node);
		 ArrayList<String> tempNode = new ArrayList<String>();
		 NodeLists mynode = new NodeLists(topmostNode.getCurState());
		 NodeLists curNode = mynode;
		 
		 tempNode = myExecuteSuccessor.mySuccessorPuzzle(curNode.getCurState());
		 	
		 	return tempNode;
	 
	 }
	 
	}

