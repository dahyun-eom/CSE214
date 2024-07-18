public class Test{




/* 

    public static int minimumElement(int[] sortedArr){
        return sortedArr[0];
    }

    public Location whereIs(String name) {
		if (occupiedGridsNum()==0){              //1
			return null;                         //2
		}
		String[] allPlayers = getAllPlayers();   //3
		boolean checker = false;                 //4
		for (String searcher : allPlayers){      //5
			if (searcher.equals(name)){          //6
				checker = true;                  //7
			}
		}
		if (checker == false){                   //8
			return null;                         //9
		}

		Locations playerLocation = new Locations();                 //10
		for(int i=0; i<maxRow; i++){                                //11
			for(int j=0; j<maxColumn; j++){                         //12
				if(grid[i][j]!= null && grid[i][j].equals(name)){   //13
					playerLocation.setRow(i);                       //14
					playerLocation.setCol(j);                       //15
				}
			}
		}
		return playerLocation;                                      //16
	}

    public int occupiedGridsNum(){
		int count =0;                        //1
		for (int i=0; i<maxRow; i++){        //2
			for(int j=0; j<maxColumn; j++){  //3
				if(grid[i][j] != null ){     //4
					count++;
				}     
			}
		}		
		return count;                       //5
	}

	public String[] getAllPlayers() {
		if (occupiedGridsNum()==0){                                   //1
			return null;                                              //2
		}
		String[] allPlayers = new String[occupiedGridsNum()];         //3
		int k=0;                                                      //4
		for (int i=0; i<maxRow && k<occupiedGridsNum(); i++){         //5
			for(int j=0; j<maxColumn && k<occupiedGridsNum(); j++){   //6
				if(grid[i][j] != null ){                              //7
					allPlayers[k] = grid[i][j];                       //8
					k++;                                              //9
				}
			}
		}
		return allPlayers;                                            //10
	}
	
*/
	

	public static int count(int[] arr, int item){		
		if(arr[0]<arr[arr.length-1]){ //ascending     //1
			if(item<arr[0]){                          //2
				return 0;                             //3
			}
			else if(item == arr[0]){				 //4
				int count =0;						 //5
				int i=0; 						     //6
				while(arr[i] == item){               //7
					count++;						 //8
					i++;							 //9
					if(i == arr.length){			//10
						break;						//11
					}
				}
				return count;						//12
			}
			else if (item > arr[0]){				//13
				int count=0;						//14
				int i=0;							//15
				while(arr[i] <= item){				//16
					count++;						//17
					i++;							//18
					if(i == arr.length){			//19
						break;						//20
					}
				}
				return count;						//21
			}
		}

		else if(arr[0]>arr[arr.length-1]){ //descending  //22

			if(item>=arr[0]){							//23
				return arr.length;						//24
			}
			else{										//25
				int countBig=0;							//26
				int i=0;								//27
				while (arr[i]>item){					//28
					i++;								//29
					countBig++;							//30
					if(i == arr.length){				//31
						break;							//32
					}
				}
				return arr.length-countBig;				//33
			}
		}

		else if(arr[0] == arr[arr.length-1]){			//34
			if(item>=arr[0]){							//35
				return arr.length;						//36
			}
			else{										//37
				return 0;								//38
			}
		}

		return 0;										//39
	}





		


    public static void main(String[] args) {
		

		
		int[] arr4 = {4, 4, 4, 6, 6, 8, 9, 10};
		int[] arr5 = {7, 7, 7, 7, 7, 7, 7, 7};
		int[] arr6 = {7, 7, 5, 4, 3, 2};
		int[] arr7 = {5};
		

		System.out.println(count(arr4, 2)); //0
		System.out.println(count(arr4, 4)); //3
		System.out.println(count(arr4, 5)); //3
		System.out.println(count(arr4, 7));//5
		System.out.println(count(arr4,6)); //5
		System.out.println();

		System.out.println(count(arr5, 9)); //8
		System.out.println(count(arr5, 2)); //0
		System.out.println(count(arr5, 7)); //8
		System.out.println();

		System.out.println(count(arr6,9)); //6
		System.out.println(count(arr6, 7)); //6
		System.out.println(count(arr6, 1)); //0
		System.out.println();


		System.out.println(count(arr7,5)); //1
		System.out.println(count(arr7,6)); //1
		System.out.println(count(arr7,4)); //0







        
    }
}

