import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

/**
 * 
 * Name: Dahyun Eom , 115943034
 * Your implementation should be as efficient as possible in terms of the time complexity. You're free to import and use any of the data structures we learned in class so far.
 */
public class SpellCheck {
	final String path = "dictionary.txt"; // Don't change this path in your final submission.
	Set<String> dictionary;

	/*
	 * O(L*m) where L is the number of Lines in the file and m is the average length of each line.
	 * This constructor is to initialize the 'dictionary' set by reading eacch line in the file "dictionary.txt".
	 * reading each line O(m) need to be done number of the line times O(n). 
	 */
	public SpellCheck() {
		dictionary = new HashSet<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null) {
				line = line.trim();
				// 'line' is a valid word
				dictionary.add(line);
			}
			br.close();
		} catch(Exception e) {
			System.err.println("File error: " + e.getMessage());
			System.exit(-1);
		}		
	}
	/*
	 * The length of the return array should be the same as the number of words in 'sentence'.
	 * The i-th element of the return array is the substitute candidate for the i-th word in the sentence.
	 * If the i-th word is a valid word (i.e., not a typo), then the array should be empty.
	 */
	/*
	 * O(m + w*k) 
	 * where m is the length of 'sentence' that is passed through the parameter.
	 * where w is the number of words(not characters) in the 'sentence' that is given through the parameter,
	 * and where k is the size of an arraylist 'candidates' that passed as parameter into checkSwap method in the method candidates.
	 * 
	 * the split method require linear time complexity. Also, since every word in the 'sentence' need to be check if it is a valid word or not.
	 * So, the for loop will loop amount of the number of words in the 'sentence' times.
	 * If the word is an invalid word, then the method will call the method candidates which requires O(k) time complexity 
	 * where k is the size of an arraylist 'candidates' that passed as parameter into checkSwap method which is inside the method candidates.
	 * In the worst case, it is when the words in the 'sentence' are all invalid words. 
	 * 
	 */
	public ArrayList<String>[] spellCheck(String sentence) {
		String[] words = sentence.split("\\s"); // 'words' is the list of words in 'sentence'. --> O(m) where m is the length of 'sentence' that is passed through the parameter.
	
		@SuppressWarnings("unchecked")
		ArrayList<String>[] res = new ArrayList[words.length];
		for(int i=0; i<words.length; i++){  // it will loop w times where w is is the number of words in the 'sentence'
			if(dictionary.contains(words[i].toLowerCase())){
				res[i] = null;
			}
			else{ 
				res[i] = candidates(words[i].toLowerCase()); //O(k) where k is the size of an arraylist 'candidates' that passed as parameter into checkSwap method.
			}
		}
		return res;
	}
	/*
	 * O(k) where k is the size of an arraylist 'candidates' that passed as parameter into checkSwap method.
	 * Since each method( checkRemoval, checkAddition, checkReplace, and checkSwap)'s time complexity
	 * depends on the size of the ArrayList 'candidates' that is passed into the parameter. 
	 * 
	 * This method is to return the candidate word that exist in the dictionary.
	 * So, it will call checkRemoval, checkAddition, checkReplace, and checkSwap methods to find the candidates.
	 */
	private ArrayList<String> candidates(String word){
		ArrayList<String> candidates = new ArrayList<>();
		checkRemoval(candidates, word);  //O(1) since parameter 'candidates' was an empty parameter.
		checkAddition(candidates, word);    //O(a) where a is size of an ArrayList 'candidates' that is passed through the parameter.
		checkReplace(candidates, word);  //O(b)  where b is the size of an ArrayList 'candidates' that is passed through the parameter.
		checkSwap(candidates,word);     //O(k)   where k is the size of an ArrayList 'candidates' that is passed through the parameter.   k>= a, k>=b
		return candidates;
	}
	
	////////////////////////////////Removal///////////////////////////////
	/*
	 * O(k) where k is the size of an ArrayList 'res' that is passed through the parameter.
	 * 
	 * The longest word in English is a word with 45 characters. 
	 * So, firstly, if the given word 'rawWord' that is passed through the parameter is longer than 46 characters, 
	 * It means that there is no such candidates in the dictionary. 
	 * So, it will return the ArrayList 'res' that is passed through the parameter
	 * which the candidates should be added but in result adding nothing.
	 * 
	 * If the given word 'rawWord' that is passed through the parameter is less than or equal to 46 characters, 
	 * it will go through a for loop.
	 * However, it is guranteed that the length of the 'rawWord' is less than or equal to 46 characters.
	 * So, under the constraint of the length of 'rawWord' <= 46, the time complexity only matters with the contains method
	 * which the time complexity is O(k) where k is the size of an ArrayList 'res' that is passed through the parameter.
	 * 
	 */
	private ArrayList<String> checkRemoval(ArrayList<String> res, String rawWord){
		if(rawWord.length() > 46){
			return res;
		}
		for(int i=0; i<rawWord.length(); i++){
			String charDeleted = rawWord.substring(0, i) + rawWord.substring(i+1);      //O(n)
			if(dictionary.contains(charDeleted) && res.contains(charDeleted)!= true){
				res.add(charDeleted);
			}
		}
		return res;
	}
	////////////////////////////////Addition////////////////////////////////
	/*
	 * O(k) where k is the size of an ArrayList 'res' that is passed through the parameter.
	 * 
	 * The longest word in English is a word with 45 characters. 
	 * So, firstly, if the given word 'rawWord' that is passed through the parameter is longer than or equal to 45 characters, 
	 * It means that there is no such candidates in the dictionary. 
	 * So, it will return the ArrayList 'res' that is passed through the parameter
	 * which the candidates should be added but in result adding nothing.
	 * 
	 * If the given word 'rawWord' that is passed through the parameter is less than 45 characters, 
	 * it will go through the nested for loops.
	 * However, it is guranteed that the length of the 'rawWord' is less than 45 characters.
	 * So, under the constraint of the length of 'rawWord' < 45, the time complexity only matters with the contains method
	 * which the time complexity is O(k) where k is the size of an ArrayList 'res' that is passed through the parameter.
	 * 
	 */
	private ArrayList<String> checkAddition(ArrayList<String> res, String rawWord){
		char [] alphabets = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		if(rawWord.length() >= 45){
			return res;
		}
		for (int i=0; i<alphabets.length; i++){ //26 iteration
			for(int j=0; j<rawWord.length()+1; j++){
				String charAdded = rawWord.substring(0, j) + alphabets[i] +rawWord.substring(j);
				if(dictionary.contains(charAdded) && res.contains(charAdded)!= true){
					res.add(charAdded);
				}
			}
		}
		return res;
	} 
	//////////////////////////////Replacement//////////////////////////////////////
	/*
	 * Replacement of a single character: “abple” → “apple”, “bbple”, “abpke”, …
	 * can't just do with iteration.... ---> time complexity will be so bad......
	 * need to use data structure.
	 */
	/*
	 * O(k) where k is the size of an ArrayList 'res' that is passed through the parameter.
	 * 
	 * The longest word in English is a word with 45 characters. 
	 * So, firstly, if the given word 'rawWord' that is passed through the parameter is longer than 45 characters, 
	 * It means that there is no such candidates in the dictionary. 
	 * So, it will return the ArrayList 'res' that is passed through the parameter
	 * which the candidates should be added but in result adding nothing.
	 * 
	 * If the given word 'rawWord' that is passed through the parameter is less than or equal to 45 characters, 
	 * it will convert 'rawWord' to char array and go through the nested for loops.
	 * However, it is guranteed that the length of the 'rawWord' is less than or equal to 45 characters.
	 * So, under the constraint of the length of 'rawWord' <= 45, the time complexity only matters with the contains method
	 * which the time complexity is O(k) where k is the size of an ArrayList 'res' that is passed through the parameter.
	 * 
	 */
	private ArrayList<String> checkReplace(ArrayList<String> res, String rawWord){
		char [] alphabets = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		if(rawWord.length() > 45){
			return res;
		}
		char[] wordArr = rawWord.toCharArray();
		for(int i=0; i<wordArr.length; i++){
			char originalChar = wordArr[i];
			for(char c : alphabets){
				wordArr[i] = c;
				String replacedWord = new String(wordArr);  //O(n)
				if(dictionary.contains(replacedWord) && res.contains(replacedWord)!= true){
					res.add(replacedWord);
				}
			}
			wordArr[i] = originalChar;
		}
		return res;
	}

	//////////////////////////////Swapping//////////////////////////////////////
	/*
	 * O(k) where k is the size of an ArrayList 'res' that is passed through the parameter.
	 * 
	 * The longest word in English is a word with 45 characters. 
	 * So, firstly, if the given word 'rawWord' that is passed through the parameter is longer than 45 characters, 
	 * It means that there is no such candidates in the dictionary. 
	 * So, it will return the ArrayList 'res' that is passed through the parameter
	 * which the candidates should be added but in result adding nothing.
	 * 
	 * If the given word 'rawWord' that is passed through the parameter is less than or equal to 45 characters, 
	 * it will convert 'rawWord' to char array and go through the nested for loops.
	 * However, it is guranteed that the length of the 'rawWord' is less than or equal to 45 characters.
	 * So, under the constraint of the length of 'rawWord' <= 45, the time complexity only matters with the contains method
	 * which the time complexity is O(k) where k is the size of an ArrayList 'res' that is passed through the parameter.
	 * 
	 */
	private ArrayList<String> checkSwap(ArrayList<String> res, String rawWord){ 
		// char [] charArr = rawWord.toCharArray(); 
		if(rawWord.length() > 45){
			return res;
		}
		char [] charArr = rawWord.toCharArray(); //O(n)
		for(int i=0; i<rawWord.length()-1; i++){
			for(int j=i+1; j<rawWord.length(); j++){
				char temp = charArr[i];
				charArr[i] = charArr[j];
				charArr[j] = temp;
				String swappedString = new String(charArr);
				if(dictionary.contains(swappedString) && res.contains(swappedString)!= true){
					res.add(swappedString); 
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		SpellCheck sc = new SpellCheck();
		String[] sentences = {"I ate an x", "paint the banel", "shee is a riend", "kangaru"};
		// Feel free to change the following printout routine
		for(String sent : sentences) {
			String[] words = sent.split("\\s");
			ArrayList<String>[] ret = sc.spellCheck(sent);
			if(ret == null) continue;
			// String cand = "";   --> not here
			for(int i = 0; i < ret.length; i++) {
				String cand = "";   //--> suppose to be here
				if(ret[i] == null) {
					System.out.print(words[i] + " ");
					continue;
				}
				Iterator<String> it = ret[i].iterator();
				while(it.hasNext())
					cand += (it.next() + ",");
				System.out.print("(" + cand + ") ");
			}
			System.out.println();
		} 
	}
//듀플리케이트처리하기
}
