/**
 * Do not import or declare any packages.
 * You are free to add fields/methods/interfaces/classes/etc., but do not alter the given code.
 * For your own safety, do not use any techniques not taught in this course so far.
 * 
 * @author <Dahyun Eom> 115943034
 */

public class GridGame{

	String[][] grid;
	int maxRow;
	int maxColumn;
	
	/*
	 * TODO: Fill in the constructor appropriately. 
	 * 'r' and 'c' are the numbers of rows and columns, respectively.
	 */
	public GridGame(int r, int c) {
		grid = new String[r][c];
		maxRow = r;
		maxColumn = c;
	}
	
	/*
	 * TODO: Introduce a new player with 'name' at location (r, c).
	 * Return false if a same player already exists in the game, or there is someone else at (r, c).
	 */
	/*
	 * The method firstly checks to make sure if the parameters r&c are appropriate row and column.
	 * if it is passing (r & c) that is outside the game, then it should return false since it cannot spawn player.
	 * And then I used nested loop(outside loop is iterating row and inside is iterating column)to find if the player is already exist in the game.
	 * if condition in the inner loop, I had to add grid[i][j] != null with grid[i][j].equals(name) statement.
	 * Because if I only write if(grid[i][j].equals(name)) then it occurs NullPointerException.
	 * When the forloop's iterating is finished, it will check directly the location(r, c) of the grid to see 
	 * if there is another player. At this point, if grid[r][c] is not null, it means there is another player.
	 * Lastly, if the parameters all passed the former dectecting code? which meaning grid[r][c] is empty and 
	 * there is no same player existing  in the game, then I will assign(introduce) the player into location(r, c) (grid[r][c])  and return true.
	 */
	public boolean spawnPlayer(String name, int r, int c) {
		//check if r & c is available row & column in the grid 
		if (r>maxRow-1 || c> maxRow-1 || r<0 || c<0){
			return false;
		}
		//returning false if a same player already in the game.
		for (int i=0; i<maxRow; i++){
			for(int j=0; j<maxColumn; j++){
				if(grid[i][j] != null && grid[i][j].equals(name)){
					return false;
				}
			}
		}
		//returning false if someone else at (r, c)		
		if (grid[r][c] != null){
			return false;
		}
		//introduce the player in location (r, c) and return true
		grid[r][c] = name;
		return true;
	
	}
	
	/*
	 * TODO: Remove player with 'name' from the game.
	 * Return false if there is no such player, and true otherwise.
	 */
	/*
	 * The method will go through all the locations inside the game using nested loop until it finds the player . 
	 * varaible i scopes row and j scopes column.
	 * starting with row 0, it will iterate all the columns that are in row 0 to find the player
	 * who the parameter told to remove. 
	 * and next row 1, it will iterate all the columns that are in row 1.......so on.
	 * Same as the method spawnPlayer(), if statement inside the inner for loop ,
	 * I had added grid[i][j] != null with grid[i][j].equals(name) statement.
	 * Because if I only write if(grid[i][j].equals(name)) then it occurs NullPointerException.
	 * if statement will search the player, and if the grid[i][j] which we obtained through iterating has the same name of parameter,
	 * then we will make that grid to null(removing) and return true.
	 * if it did not return true after iterating all the grids, it means there is no such player.
	 * So, return false.
	 * 
	 */
	public boolean removePlayer(String name) {
		//return true and remove the player if player is found inside the grid. 
		for (int i=0; i<maxRow; i++){
			for(int j=0; j<maxColumn; j++){
				if(grid[i][j] != null && grid[i][j].equals(name)){
					grid[i][j] = null;
					return true;
				}
			}
		}
		//return false if there is no such player
		return false;
	}
	
	/*
	 * TODO: Remove whatever player that's at location (r, c).
	 * Return false if nobody's there.
	 */
	/*
	 * We are taking row and column for the parameters because we want to remove whatever player that's at location (r,c) (grid[r][c])
	 * Firstly I check if the parameters that are passed are appropriate row and columns which means grid[r][c] is inside the game.
	 * And then, check if grid[r][c] is null meaning the location is empty, and if it is, I will return false since there is no thing to remove. 
	 * Finally, if location (r, c) is not null, then I will assign it to null (removing) and return true.
	 */
	public boolean removeAt(int r, int c) {
		//check if r & c is available row & column in the grid 
		if (r>maxRow-1 || c> maxRow-1 || r<0 || c<0){
			return false;
		}
		//returning false if nobody is in (r, c)
		if (grid[r][c] == null){
			return false;
		}
		//return true and remove the player.
		else{
			grid[r][c] = null;
			return true;
		}
	}
	
	/*
	 * TODO: Move the given player (goes by 'name') to location (r,c).
	 * Move is invalid if 'name' doesn't exist or (r,c) is not 1 step away from 'name''s location.
	 * Wrap-around movements are also allowed.
	 * Return null for invalid moves.
	 * For valid moves, the player already existing in that location will be removed from the game
	 * and its name should be returned. Return null if no player exists at (r,c).
	 */
	/*
	 * Since this passes parameter of row and column(r & c), I would like to check if r & c is appropriate in the first hand. 
	 * So it return null if r, c is inappropriate row and column to move.
	 * After this, it initialize string array allPlayers which is the return of method getAllPlayers().
	 * I declare variable 'checker' and set to false. so if the name is in the array allPlayers, checker will be true and if it's not, then checker will remain false.
	 * if checker is false, then it should return null since it means no such player names 'name' exist.
	 * I initiallize 'formerLocation' to know the location of the player who wants to move before any movement happens for later uses.
	 * 
	 * I divied to 2cases which player wants to move simply up, down, left, right or the wrap-around case.
	 * if it meets the former case(same row but move to single-step of other column or same column but move to single-step of other row), 
	 * it will initiallize String variable 'formerPlayer' to getPlayerAt(r, c) so that we can know and use the former player name that is at the location (r, c) before any movement happens.            -----------------------------
	 * by using removeAt(r,c), if it is false , which means there is no one at location (r, c), it will make the grid at formerLocation of player who wants to move to null.                                                          |
	 * And then assign player's name to the new location and returnning null since no one was in that location before.             																									  |---> also use this inside wrap-around case
	 * If there is someone where the player wants to move(else statement which the formerPlayer is already removed from the location (r,c) since it is removeAt(r, c)==true case which removes whatever at location(r,c) ),           |
	 * it will do the same step as when the location (r,c) was empty(removeAt(r, c) == false case) except returning the former player who was at location (r, c) before any movements happened.                             -----------
	 * 
	 * For the latter case(wrap-around case)
	 * We should check whether the location of the player wants to move (r, c) is the corner of the grid. 
	 * then it will again divide in to 2cases. 
	 * the first case is when the location of the player who wants to move (formerLocation) is at up&left most of the grid or down&right most of the grid. 
	 * the second case is when the location of the player who wants to move (formerLocation) is at up&right most of the grid or down&left most of the grid. 
	 * if it is the first case, then it can wrap around to up&right most of the grid or down&left most of the grid. So, again check the parameter r&c if it suits the case and if it is the case
	 * which wrap-around movement is available, then move the player just as we did at when was just simply moving up down left right.
	 * Also if it is the second case, it can wrap around to up&left most of the grid or down&right most of the grid. So, again check the parameter r&c if it suits the case and if it is the case
	 * which wrap-around movement is available, then move the player just as we did at when was just simply moving up down left right.
	 * 
	 * if it does not suits both wrap-around or simple up, down, left, righr move, then we should return null.
	 * 
	 * 
	 */
	public String moveTo(String name, int r, int c) {

		if (r>maxRow-1 || c> maxRow-1 || r<0 || c<0){
			return null;
		}

		String[] allPlayers = getAllPlayers();
		boolean checker = false;
		for (String searcher : allPlayers){
			if (searcher.equals(name)){
				checker = true;
			}
		}
		if (checker == false){
			return null;
		}

		Location formerLocation = whereIs(name); 
		      
		if((Math.abs(formerLocation.getRow() - r) <= 1 && formerLocation.getCol() - c ==0 )|| (formerLocation.getRow() - r == 0 && Math.abs(formerLocation.getCol() - c) <= 1)){  //determining same row but move to single-step other column or same column but move to single-step other row (preventing diagonal movement)

			String formerPlayer = getPlayerAt(r, c); 
			
			
			if (removeAt(r, c) == false){
				grid[formerLocation.getRow()][formerLocation.getCol()] = null;
				grid[r][c] = name;
				return null;
			}
			// String formalPlayer = getPlayerAt(r, c); 

			else{
				grid[formerLocation.getRow()][formerLocation.getCol()] = null;
				grid[r][c] = name;
				return formerPlayer;
			}
		}

		else if ((r==0 && c==0)||(r==0 && c==maxColumn-1)||(r==maxRow-1 && c==0)||(r==maxRow-1 && c==maxColumn-1)){

			if((formerLocation.getRow() == 0 && formerLocation.getCol() == 0)||(formerLocation.getRow() == maxRow-1 && formerLocation.getCol() == maxColumn-1)){
				if((r==0 && c==maxColumn-1) || (r==maxRow-1 && c==0)){
					String formalPlayer= getPlayerAt(r, c); 
					
					if(removeAt(r, c)==false){
						grid[formerLocation.getRow()][formerLocation.getCol()] = null;
						grid[r][c] = name;
						return null;
					}
					else{
						grid[formerLocation.getRow()][formerLocation.getCol()] = null;
						grid[r][c] = name;
						return formalPlayer;
					}
				}
				else{
					return null;
				}
			}

			else if((formerLocation.getRow() == 0 && formerLocation.getCol() == maxColumn-1)||(formerLocation.getRow() == maxRow-1 && formerLocation.getCol() == 0)){
				if((r==0 && c==0) || (r==maxRow-1 && c==maxColumn-1)){
					String formalPlayer = getPlayerAt(r, c); 
					
					if(removeAt(r, c)==false){
						grid[formerLocation.getRow()][formerLocation.getCol()] = null;
						grid[r][c] = name;
						return null;
					}
					else{
						grid[formerLocation.getRow()][formerLocation.getCol()] = null;
						grid[r][c] = name;
						return formalPlayer;
					}
				}
				else{
					return null;
				}
			}

			else{
				return null;
			}

		}

		else{
			return null;
		}

	}

	/*
	 * TODO: Return the name of the player at location (r, c).
	 * Return null if location is unoccupied.
	 */
	/*
	 * We are taking row and column for the parameters because we want to get whatever player that's at location(r,c) (grid[r][c])
	 * Firstly, I check if the parameters that are passed are appropriate row and columns which means grid[r][c] is inside the game.
	 * Secondly, I will see if the location(r, c) is null which means empty or not. return null if the location(r, c) is empty.
	 * lastly, if it all passsed the former if statements, then it means there is someone in the location (r, c). 
	 * So, the method will return the player who is inside the location(r, c).
	 */
	public String getPlayerAt(int r, int c) {
		//check if r & c is available row & column in the grid 
		if (r>maxRow-1 || c> maxRow-1 || r<0 || c<0){
			return "it is inappropriate row and column";
		}
		if (grid[r][c] == null){
			return null;
		}
		return grid[r][c];
	}
	
	/*
	 * TODO: Return the location of the player 'name'.
	 * Return null if no such player exists.
	 */
	/*
	 * This method is to return the location of the player whom player's name is passed as parameter.
	 * It starts with checking is all the grids of the game empty or not by using occupiedGridsNum().
	 * It will return null if there is no occupied grids.
	 * 
	 * Before it try to start iterating all the location and search for the name, it will check if such player exist or not in the game.
	 * For this, it initialize string array allPlayers which is the return of method getAllPlayers().
	 * I declare variable 'checker' and set to false. so if the name is in the array allPlayers, checker will be true and if it's not, then checker will remain false.
	 * if checker is false, then it should return null since it means no such player names 'name' exist.
	 * 
	 * After that, I will instantiate a  Locations class object playerLocation.(I cannot instantiate Location since it is a abstract class. So, I use subclass instead)
	 * By using nested forloop, I will search the location where isn't null and the player in that location has the same name as the parameter given.
	 * variable i I used for the outer loop scopes row and j for the inner loop scopes column. 
	 * During the iteration, if it meets the if condition statement in the inner for loop(current location is not empty and player name is equal to the name that parameter has given), 
	 * then it will set row of the object playerLocation to current iterating row i 
	 * and set column of the object playerLocation to current iterating column j.
	 * return playerLocation.
	 * 
	 */
	public Location whereIs(String name) {
		//if grid is empty, return null
		if (occupiedGridsNum()==0){
			return null;
		}
		//return null if no such player exist 
		String[] allPlayers = getAllPlayers();
		boolean checker = false;
		for (String searcher : allPlayers){
			if (searcher.equals(name)){
				checker = true;
			}
		}
		if (checker == false){
			return null;
		}---

		Locations playerLocation = new Locations();
		for(int i=0; i<maxRow; i++){
			for(int j=0; j<maxColumn; j++){
				if(grid[i][j]!= null && grid[i][j].equals(name)){
					playerLocation.setRow(i);
					playerLocation.setCol(j);
				}
			}
		}
		return playerLocation;
	}
	
	
	/*
	 * TODO: Return an array of locations for all players currently in the game.
	 * Return null if there aren't any.
	 */
	/*
	 * I want to return Location [] but since class Location is a abstract class which cannot instantiate
	 * I will use the subclass Locations that I created and also return Locations[] since it is possible to say the child class is parent class.
	 * I initiallize a Locations array name allLocations which size is as same as the number of occupied grids which I can get from method occupiedGridsNum().
	 * I start with checking is all the grids of the game empty or not by also using occupiedGridsNum().
	 * It will return null if there is no occupied grids.
	 * I will use nested for loops to get all the locations for all players in the game.
	 * I initiallized local variable k which will be the index of array (allLocations) .
	 * Also, variable i I used for the outer loop scopes row and j for the inner loop scopes column. 
	 * The nested loop will find the location where isn't null and put the player's location into the the array(allLocations).
	 * when it meets the if condition statement during for loop iteration, it will instantiate Locations class object in allLocations[k] since it is null before instantiating it
	 * and then set the object's row to i and column to j.
	 * I increased 1  to k whenever the location is added to the Locations array (allLocations) so that whenever it should add other player's location,
	 * it can added to the next index of the array allLocations.
	 * Also, in the 2nd statement of outer and inner for loop, I added k<occupiedGridsNum()condition with condition i<maxRow or j<maxColumn.
	 * Because if the array, allLocations is alreay full, then we don't have to iterate through leftover locations.
	 * After filling all the locations of the players in array allLocations, it will return the array.
	 */
	public Location[] getAllOccupiedGrids() {
		Locations[] allLocations = new Locations[occupiedGridsNum()];
		//return null if they aren't any occupied
		if (occupiedGridsNum() == 0){
			return null;
		}
		int k=0;
		for(int i=0; i<maxRow && k<occupiedGridsNum(); i++){
			for(int j=0; j<maxColumn && k<occupiedGridsNum(); j++){
				if(grid[i][j] != null){
					allLocations[k] = new Locations();
					allLocations[k].setRow(i);
					allLocations[k].setCol(j);
					k++;
				}
			}
		}	
		return allLocations;
	}
	
	/*Dahyun added method(helper method of getAllPlayes()) */
	/*
	 * I implemented this method for convenience as I want to know how many grids are occupied.
	 * since I need to use this information in the method getAllPlayer(), and getAllOccupiedGrids() when initiallizing array(have to know the size of array)
	 * So fisrtly, I initiallized local variable 'count' as 0.
	 * For the nested loop, I used variable i in outer for loop which will scope row and j in outer for loop which will scope column.
	 * It will go through the whole locations and search the location where it is not null, meaning there is someone in that location 
	 * whenever grid[i][j] meets the condition of if statement that is inside the inner for loop, 1 will be added to variable count.
	 * when iterating is finished, then 'count' is equal to the number of occupiedgrids.
	 * So, it returns count.
	 */
	public int occupiedGridsNum(){
		int count =0;
		for (int i=0; i<maxRow; i++){
			for(int j=0; j<maxColumn; j++){
				if(grid[i][j] != null ){
					count++;
				}
			}
		}		
		return count;
	}
	
	/*
	 * TODO: Return an array of all players' names.
	 * Null if there aren't any.
	 */
	/*
	 * This method is to return an array of all players name.
	 * Firstly, I will check if the game grid is perfectly empty or not by calling the method occupiedGidsNum() that I made.
	 * it will return null if there aren't any players in the game.
	 * if there are player(s), then it will initiallize a string array which length is the number of occupied grids which I can also get from the method occupiedGidsNum().
	 * I initiallized a local variable k which will be the index of array (allPlayers[]) 
	 * I used a nested for loop to find the location where isn't null and put the player's name into the the array(allPlayer[k]) who was in that location.
	 * variable i I used for the outer loop scopes row and j for the inner loop scopes column. 
	 * I increased 1  to k whenever the name is added to the String array (allPlayers) so that when it needs to add other player's name, it can added to the 
	 * next index of the array.
	 * Also, in the 2nd statement of outer and inner for loop, I added k<occupiedGridsNum()condition with condition i<maxRow or j<maxColumn.
	 * Because if the array, allPlayers is alreay full, then we don't have to iterate through leftover locations.
	 * After filling all the names of the playes in array allPlayers, the method will return the array allPlayers.
	 */
	public String[] getAllPlayers() {
		//return null if there are no players
		if (occupiedGridsNum()==0){
			return null;
		}
		String[] allPlayers = new String[occupiedGridsNum()]; 
		int k=0;
		for (int i=0; i<maxRow && k<occupiedGridsNum(); i++){
			for(int j=0; j<maxColumn && k<occupiedGridsNum(); j++){
				if(grid[i][j] != null ){
					allPlayers[k] = grid[i][j];
					k++;
				}
			}
		}
		return allPlayers;
	}

	/*
	 * The following main() method is entirely optional, and is given only as a reference. 
	 * These test cases give an example on how your code should work.
	 * You are free to do whatever you want to with this code. 
	 * In fact, you are encouraged to share test cases among your classmates. (But not the codes!)
	 * Notice that I am not testing all methods: try to come up with your own.
	 * This main() method will *not* be part of your grade.
	 */
	public static void main(String[] s) {

		GridGame g = new GridGame(5, 5);
		g.spawnPlayer("A", 0, 0);
		g.spawnPlayer("B", 1, 0);
		if(!"B".equals(g.moveTo("A", 1, 0))){
			System.out.println("-");
		}
		else{
			System.out.println("+");
		}
		g.spawnPlayer("C", 0, 4);
		g.spawnPlayer("D", 1, 1);
		if(g.moveTo("C", 0, 3) != null){
			System.out.println("-");
		}
		else{
			System.out.println("+");
		}
		if(g.moveTo("C", 1, 1) != null){
			System.out.println("-");
		}
		else{
			System.out.println("+");
		}
		if(!"F".equals(g.moveTo("E", 2, 4))){
			System.out.println("-");
		}

		



		
		
		/* 
		//Dahyun-made testcase
		GridGame h = new GridGame(3, 3);
		System.out.println(h.spawnPlayer("Dahyun", 0, 0));//true
		System.out.println(h.getPlayerAt(0, 0)); //Dahyun
		System.out.println(h.spawnPlayer("Hojun", 0, 1));//true
		System.out.println(h.spawnPlayer("Chaewon", 0, 0)); // false: there is already Dahyun in (0,0) 
		System.out.println(h.spawnPlayer("Dahyun", 1, 2)); //false: the same player already exists in the game.
		System.out.println(h.removePlayer("Dahyun")); //true
		System.out.println(h.removePlayer("Chaewon")); //false: there is no "Chaewon" inside the grid.
		System.out.println(h.getPlayerAt(0, 0)); //null
		System.out.println(h.removeAt(0, 0));//false: nonboy is in (0,0)
		System.out.println(h.removeAt(9,9)); //false: unavailable location inside 3*3 grid
		System.out.println(h.removeAt(0, 1)); //true: remove "Hojun"
		System.out.println(h.getPlayerAt(0, 0)); // null
		//System.out.println(h.whereIs("Hojun").getRow());

		System.out.println();////////////////////////////////////////////////////////////////////////////////

		GridGame k = new GridGame(5, 5);
		System.out.println(k.spawnPlayer("red", 0, 0)); //true
		System.out.println(k.spawnPlayer("orange", 4, 4)); //true
		System.out.println(k.spawnPlayer("black", 5, 5)); //false
		//System.out.println(k.getPlayerAt(5, 5)); //it is inappropriate row and column
		System.out.println(k.getPlayerAt(0, 1)); //null
		System.out.println(k.getPlayerAt(4, 4)); //orange
		//System.out.println(k.occupiedGridsNum()); //2
		String[] total = k.getAllPlayers();
		for(String str : total){
			System.out.println(str); //red  orange
		}
		Location loca1 = k.whereIs("orange");
		//System.out.println(loca1);
		System.out.println("(" + loca1.getRow() + ", " + loca1.getCol() + ")");//(4,4)
		System.out.println(k.whereIs("green"));//null
		System.out.println(k.spawnPlayer("yellow", 2, 2));//true
		Location[] allLoca1 = k.getAllOccupiedGrids();
		for(Location l: allLoca1){
			System.out.println("(" + l.getRow() + ", " + l.getCol() + ")"); //(0, 0) (2, 2) (4, 4)
		}
		k.spawnPlayer("green", 0, 4);
		System.out.println(k.moveTo("red", 0, 4)); //green
		System.out.println(k.whereIs("green")); //null
		System.out.println(k.getPlayerAt(0, 0)); //null
		System.out.println(k.getPlayerAt(0, 4)); //red
		k.spawnPlayer("blue", 0, 0);
		System.out.println(k.moveTo("orange", 3, 4));//null
		System.out.println(k.getPlayerAt(3, 4)); //orange
		System.out.println(k.getPlayerAt(4, 4)); //null
		System.out.println(k.moveTo("orange", 0, 4)); //null: since it is invalid move
		System.out.println(k.getPlayerAt(3, 4)); //orange
		System.out.println(k.moveTo("red", 1, 3)); //null
		System.out.println(k.getPlayerAt(1, 3)); //null 
		System.out.println(k.getPlayerAt(0, 4)); //red 
		System.out.println(k.moveTo("yellow", 1, 2)); //null
		System.out.println(k.moveTo("red", 0, 0)); //blue
		System.out.println(k.moveTo("red", 4, 4)); //null: invalid move
		

		System.out.println();/////////////////////////////////////////////////////////////////////////////////////

		GridGame t = new GridGame(2, 2);
		String[] total1 = t.getAllPlayers();
		System.out.println(total1); //null
		System.out.println(t.whereIs("Apple")); // null

	

		System.out.println();/////////////////////////////////professor's testcase////////////////////////////////////////////////////
		
		GridGame g = new GridGame(10, 10); // Build a 10x10 grid
		System.out.println(g.spawnPlayer("Homer Simpson", 0, 1)); // Should print 'true'
		System.out.println(g.spawnPlayer("Marjorie Bouvier", 0, 1)); // 'false'
		g.spawnPlayer("Gerald Smith", 0, 0);
		g.spawnPlayer("Richard Sanchez", 5, 2);
		g.spawnPlayer("Bender Rodriguez", 2, 3);
		System.out.println(g.spawnPlayer("Mortimer Smith", 10, 0)); // 'false'
		

		String[] all = g.getAllPlayers();
		for(String str : all) 
			System.out.println(str); // "Homer Simpson", "Gerald Smith", "Richard Sanchez", "Bender Rodriguez" get printed out
		
		Location[] locs = g.getAllOccupiedGrids();
		for(Location l : locs) 
			System.out.println("(" + l.getRow() + ", " + l.getCol() + ")");
		
		System.out.println(g.moveTo("Homer Simpson", 0, 0)); // "Gerald Smith"
		Location l = g.whereIs("Homer Simpson");
		System.out.println("(" + l.getRow() + ", " + l.getCol() + ")"); // "(0, 0)"
		l = g.whereIs("Gerald Smith");
		assert(l == null);

		/* 
///////////Dahyun's test /////////////////////
		

		System.out.println();

		Location[] locs1 = g.getAllOccupiedGrids();
		for(Location i : locs1) 
			System.out.println("(" + i.getRow() + ", " + i.getCol() + ")"); //(0, 0) (2,3) (5,2)
		
		System.out.println(g.moveTo("Homer Simpson", 9, 0)); //null

		System.out.println(g.getPlayerAt(9, 0)); //Homer Simpson
		System.out.println(g.getPlayerAt(0, 0)); //null
		g.spawnPlayer("Dahyun", 9, 9);
		System.out.println(g.moveTo("Homer Simpson", 9, 9)); //Dahyun
		System.out.println(g.getPlayerAt(9, 9)); //Homer Simpson

		System.out.println();

		String[] all1 = g.getAllPlayers();
		for(String ele: all1)
			System.out.println(ele); //Bender Rodriguez, Richard Sanchez, Homer Simpson
		
		System.out.println(g.occupiedGridsNum()); //3

		System.out.println(g.moveTo("Homer Simpson", 7, 0)); //null
		*/
	


	}
}

/*
 * Represents a location on a grid.
 */
abstract class Location {
	public abstract int getRow();
	public abstract int getCol();
}

class Locations extends Location{
	private int row;
	private int col;
	
	/*
	 * It is getter method which returns row of the object
	 */
	public int getRow(){
		return row;
	}
	/*
	 * It is getter method which returns col(column) of the object
	 */
	public int getCol(){
		return col;
	}
	/*
	 * It is setter method which sets object's row to parameter row
	 */
	public void setRow(int row){
		this.row = row;
	}
	/*
	 * It is setter method which sets object's column to parameter row
	 */
	public void setCol(int col){
		this.col = col;
	}
}