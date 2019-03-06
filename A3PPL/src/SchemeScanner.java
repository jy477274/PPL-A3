import java.util.ArrayList;

public class SchemeScanner {
	public static int currChar = 0;
	public static void main(String[] args) {

		String input = "(define (fibonacci n)\n\t(let fib ([prev 0]\n\t\t\t[cur 1]\n\t\t\t[i 0])\n\t\t(if (= i n)\n\t\t\tcur\n\t\t\t(fib cur (+ prev cur) (+ i 1)))))";
		printTokenStream(schemeScanner(input));
		
		for(int i = 0; i < input.length(); i++){
			System.out.print(input.charAt(i));
			
		}
		
		/*skip any init white space
		 * if cur_char is {}()[] 
		 * 		return the single char token
		 * if cur_char is # lookat next_char look at next letter
		 * 		if f or t is BOOL
		 * 		if \, look at next_char if \
		 * 			if . is CHAR
		 * 			if newline is CHAR
		 * 			if space is CHAR
		 * 			if tab is CHAR
		 * 			if [0-3][0-7]{2} is CHAR
		 * 			if f or t is BOOL
		 * 
		 * if cur_char - or + or a num 
		 * 		if next_chars [0-9*\.[0-9]+([wW][-+]?[0-9]+)? or [0-9]+[eE][-+]?[0-9]+
		 * 			NUMBER
		 * 		if next chars all numbers
		 * 			NUMBER
		 * 
		 * if cur_char 0
		 * 		if next_char x look at rest of the string for [0-9a-fA-F]+
		 * 			NUMBER
		 * 		if next_char b look at rest of the string for [01]
		 * 			NUMBER
		 * 
		 * if cur_char "
		 * 		if next_chars ([^\”]|\\([tn]|[0-3][0-7]{2}))*”
		 * 			STRING
		 * if cur _char '
		 * 		IDENTIFIER
		 * if curr_char anything but (){};0-9’
		 * 		if next_char andthing but [(){};’]*
		 * 			IDENTIFIER 
		 * 
		 * */
		
		// TODO Auto-generated method stub

	}
	
	
	public static ArrayList <ArrayList<String>> schemeScanner(String input){
		ArrayList <ArrayList<String>> tokens = new ArrayList <ArrayList <String>>();
		boolean moreChars = true;
		int currChar = 0;
		while(moreChars){
			String currentInput = new String(input.substring(currChar));
			String workableInput = new String(rmInitWhiteSpace(currentInput));
			if(currChar != workableInput.length()-1){moreChars = false;}
			
			else{
				tokens.add(findNextToken(workableInput));
				if(tokens.get(tokens.size()-1).get(0)=="ERROR"){
					System.out.println("ERROR AT LINE : "+tokens.get(tokens.size()-1).get(1));
					for(int k = 0; k< tokens.size()-2; k++){
						tokens.remove(k);
					}
					break;
				}
				
			}
			
		}
		
		return tokens; 
	}
	
	public static ArrayList<String> findNextToken(String s){
		ArrayList <String> token;
		boolean seenToken = false;
		if(s.charAt(currChar)=='('){token.add("OPENRD"); 	token.add(Integer.toString(currLine)+": "+Integer.toString(currPos));}
		else if(s.charAt(currChar)==')'){token.add("CLOSEDRD");	token.add(Integer.toString(currLine)+": "+Integer.toString(currPos));}
		else if(s.charAt(currChar)=='['){token.add("OPENSQ");	token.add(Integer.toString(currLine)+": "+Integer.toString(currPos));}
		else if(s.charAt(currChar)==']'){token.add("CLOSEDSQ");	token.add(Integer.toString(currLine)+": "+Integer.toString(currPos));}
		else if(s.charAt(currChar)=='{'){token.add("OPENCU");	token.add(Integer.toString(currLine)+": "+Integer.toString(currPos));}
		else if(s.charAt(currChar)=='}'){token.add("CLOSEDSQ");	token.add(Integer.toString(currLine)+": "+Integer.toString(currPos));}
		
		else if(s.charAt(currChar)=='#'){
			if(s.charAt(currChar+1)=='t' || s.charAt(currChar+1)=='f'){token.add("BOOL");	token.add(Integer.toString(currLine)+": "+Integer.toString(currPos));}
			else if(s.charAt(currChar+1)=='#' && s.charAt(currChar+2)=='\\' && s.charAt(currChar+2)=='\\'){
				
			}
		}
		
		
		
		
		return token;
	}
	
	public static String rmInitWhiteSpace(String stringWithInitWhiteSpace){
		String stringWithNoInitWhiteSpace = stringWithInitWhiteSpace.trim();
		return stringWithNoInitWhiteSpace;
	}
	
	public static void printTokenStream(ArrayList <ArrayList <String>> tokenStream){
		for(int i = 0; i < tokenStream.size(); i++){
			System.out.println(tokenStream.get(i).get(0)+" "+tokenStream.get(i).get(1));
		}
	}
}
