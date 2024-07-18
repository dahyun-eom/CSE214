
public class ParseTreeTest {

	ParseTree hw; 
	final double EPSILON = 1e-5; 
			
	public ParseTreeTest() {
		hw = new ParseTree();
		String[] exps = {"(5-2)/5/8/1", "8*0-1*(5-6)", "((((4-5)/(5-3)+1)*6-1)/5+1)*1", "1+1+1+1+1*2", 
				"(2-1)*(4-3)*(5-2)*(6-4)/2", "(1-1-2-6)/2+2*1*2", "1+1", "(1*2-3-5)/2-5"};
		double[] ans = {0.075, 1.0, 1.4, 6.0, 3.0, 0.0, 2.0, -8.0};
		String[] ans2 = {"52-5/8/1/", "80*156-*-", "45-53-/1+6*1-5/1+1*", "11+1+1+12*+", "21-43-*52-*64-*2/",
				"11-2-6-2/21*2*+", "11+", "12*3-5-2/5-"};
		double score = 80, denom = score / (exps.length * 3);
		int wrongs = 0;
		for(int i = 0; i < exps.length; i++) {
			System.out.println("Testing case #" + i);
			try {
				hw.buildTree(exps[i]);
				if(Math.abs(hw.eval() - ans[i]) > EPSILON) {
					System.err.println("\tWrong recursive eval() answer: " + hw.eval() + " vs. " + ans[i]);
					wrongs++; //score -= denom;
				}
				/*
				 * if(Math.abs(hw.iterativeEval() - ans[i]) > EPSILON) {
				 * System.err.println("\tWrong iterative evaluation answer: " +
				 * hw.iterativeEval() + " vs. " + ans[i]); wrongs++; //score -= denom; }
				 */
                String infix = hw.toString().replaceAll("\\s", "");
				if(!infix.equals(exps[i])) { 
					wrongs++; //score -= denom;
					System.err.println("\tWrong infix");
				}
				String postfix = hw.toPostfixString().replaceAll("\\s", "");
				if(!postfix.equals(ans2[i])) {
					System.err.println("\tWrong postfix");
					wrongs++; //score -= denom;
				}
				
			} catch(Exception e) {
				System.err.println("\tException at sample " + i);
				wrongs+=4;
			}
		}
		score -= (wrongs * denom);
		System.out.println("-------------------------------------------\n"
				+ "Score: " + Math.max(0, ((int)(score * 10))) / 10.0 + "/80");
	}
	
	public static void main(String[] args) {
		new ParseTreeTest();
	}

}
