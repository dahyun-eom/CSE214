import java.util.ArrayList;

public class SpellCheckTest {
	public static void main(String[] args) {
		SpellCheck sc = new SpellCheck();
		String[] sentences = {"the og is my fal", "bois and gurls", "banan ist frut", "juge", "aks me nicely", "he sells sea shells by the sea shore", "contemporary arts and craft", "ung beetle"};
		String[][][] ans = {{null, {"g","o","bog","cog","dog","fog","hog","jog","log","mg","ob","of","oh","on","or","ox","go"}, 
					{"i","his","irs","as","ss","us","id","if","ii","il","in","io","iq","it","iv","si"}, null, {"al","foal","fail","fall","gal","pal","fad","fag","fan","far","fat","fax"}},
				{{"bris", "boss", "boil"}, null, {}}, 
				{{"banyan", "banana"}, {"st","it","gist","list","mist","inst","sit"}, {"rut", "fruit", "fret"}},
				{{"jug","judge","huge","luge","jude","june","jute"}},
				{{"as", "ass", "ask"}, null, null},
				{null, {"sell", "wells"}, null, {"shell"}, null, null, null, null},
				{null, null, null, null},
				{{"dung","jung","lung","rung","sung","tung"}, null}};
		
		int wrongs = 0;
		int tot = 0;
        int cntEx = 0;
		for(String[][] x : ans) {
			for(String[] y : x) {
				if(y == null) { tot++; continue; }
				for(String z : y) tot++;
			}
		}
		
		for(int j = 0; j < sentences.length; j++) {
            try{
                String sent = sentences[j];
                String[][] answers = ans[j];
                //String[] words = sent.split("\\s");
                ArrayList<String>[] ret = sc.spellCheck(sent);
                for(int i = 0; i < ret.length; i++) {
                    if(ret[i] == null && answers[i] == null) continue;
                    if((ret[i] == null && answers[i] != null) || (ret[i] != null && answers[i] == null))
                    { wrongs++; System.err.println("False positive at test case " + i); continue; }
                    for(String s : answers[i]) 
                        if(!ret[i].contains(s)){
                            System.err.println("Wrong return value " + s);
                            wrongs++;
                        }
                }
            } catch(Exception e) {
                System.err.println("Exception " + cntEx);
                cntEx++;
                if(cntEx == 2) {
                    System.err.println("Strike two");
                    wrongs = tot;
                    break;
                }
            }
		}
		
		System.out.println("Final score: " + (60 - (60.0 * wrongs / tot)));
	}
}
