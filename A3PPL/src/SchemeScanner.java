import java.util.ArrayList;

public class SchemeScanner {

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
	
	
	public static ArrayList<String> schemeScanner(String input){
		ArrayList <String> tokens;
		
		
		return tokens; 
	}

	
	public static void printTokenStream(ArrayList <ArrayList <String>> tokenStream){
		for(int i = 0; i < tokenStream.size(); i++){
			System.out.println(tokenStream.get(i).get(0)+" "+tokenStream.get(i).get(1));
		}
	}
}
