import java.util.*;
import java.io.*;
public class Scanner2point0 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO make sure we are actually converting the .scheme file to a string through 
		File inputFile = new File("input.scheme");
		Scanner sc = new Scanner(inputFile);
		ArrayList<String> linedArray = new ArrayList<String>(); 
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			linedArray.add(line);
		}

		
	
	}
	
	public static ArrayList <ArrayList<String>> makeTokens(ArrayList<String> input){
		ArrayList <ArrayList<String>> tokens = new ArrayList <ArrayList <String>>();
		for(int line = 0; line < input.size(); line++){
			ArrayList<String> tempToken = new ArrayList <String>();
			char[] toke = new char[100] ;
			int tokeCount = 0;
			int posiCharacter = 1;
			for(int currCharacter = 0; currCharacter < input.get(line).length(); posiCharacter++, currCharacter++){
				if( input.get(line).charAt(currCharacter) ==  '(' ||
					input.get(line).charAt(currCharacter) ==  ')' ||
					input.get(line).charAt(currCharacter) ==  '[' ||
					input.get(line).charAt(currCharacter) ==  ']' ||
					input.get(line).charAt(currCharacter) ==  '{' ||
					input.get(line).charAt(currCharacter) ==  '}') {
					
					if (! (toke[0] == '\0')) {
						String token = new String(toke).trim().replaceAll(" ",  "");
						Arrays.fill(toke, '\0');
						
						tempToken.add(identToken(token));
						tempToken.add(Integer.toString(line));
						tempToken.add(Integer.toString(posiCharacter));
						tempToken.clear();
					}
					
					tempToken.add(parenthCall(input.get(line).charAt(currCharacter)));
					tempToken.add(Integer.toString(line));
					tempToken.add(Integer.toString(posiCharacter));
					tokens.add(tempToken);
					tempToken.clear();
				}
				else if(input.get(line).charAt(currCharacter) == '"'){
					//TODO 
				}
				else if(input.get(line).charAt(currCharacter) == ' ' ){
					if (toke[0] == '\0')
						continue;
					else{
						String token = new String(toke).trim().replaceAll(" ",  "");
						Arrays.fill(toke, '\0');
						
						tempToken.add(identToken(token));
						tempToken.add(Integer.toString(line));
						tempToken.add(Integer.toString(posiCharacter));
						tempToken.clear();
					}
						
						
				}else if(input.get(line).charAt(currCharacter) == '\t'){
					
				}else{
					String token = new String(toke).trim().replaceAll(" ",  "");
					Arrays.fill(toke, '\0');
					
					tempToken.add(identToken(token));
					tempToken.add(Integer.toString(line));
					tempToken.add(Integer.toString(posiCharacter));
					tempToken.clear();
				}
			}
		}	
		return tokens; 
	}
	
	public static String identToken(String s){
		switch(s){
		case "lambda" :
			return "lambda";
		case "define" :
			return "define";
		case "let" :
			return "let";
		case "cond" :
			return "cond";
		case "if" :
			return "if";
		case "begin" :
			return "begin";
		case "quote" :
			return "quote";
		default :
			break;
		}
		
		if((s.charAt(0) != ('[')) &&
				(s.charAt(0) != (']')) &&
				(s.charAt(0) != ('(')) &&
				(s.charAt(0) != (')')) &&
				(s.charAt(0) != ('{')) &&
				(s.charAt(0) != ('}')) &&
				(s.charAt(0) != ('0')) &&
				(s.charAt(0) != ('1')) &&
				(s.charAt(0) != ('2')) &&
				(s.charAt(0) != ('3')) &&
				(s.charAt(0) != ('4')) &&
				(s.charAt(0) != ('5')) &&
				(s.charAt(0) != ('6')) &&
				(s.charAt(0) != ('7')) &&
				(s.charAt(0) != ('8')) &&
				(s.charAt(0) != ('9')) &&
				(s.charAt(0) != ('+')) &&
				(s.charAt(0) != ('-')) &&
				(s.charAt(0) != (';')) &&
				(s.charAt(0) != ('\''))){
			for(int i = 1; i < s.length(); i++){
				if((s.charAt(i) == ('[')) ||
						(s.charAt(i) != (']')) ||
						(s.charAt(i) != ('(')) ||
						(s.charAt(i) != (')')) ||
						(s.charAt(i) != ('{')) ||
						(s.charAt(i) != ('}')) ||
						(s.charAt(i) != (';')) ||
						(s.charAt(i) != ('\''))){
					return "ERROR";
				}
			}
			return "IDENTIFIER";
		}
		else if((s.length() == 1) && (s.charAt(0) == '\''))
			return "QUOTMK";
		
		switch(s.charAt(0)){
		case '+' :
			return numberToken();
		case '-' :
			return numberToken();
		case '0' :
			return numberToken();
		case '1' :
			return numberToken();
		case '2' :
			return numberToken();
		case '3' :
			return numberToken();
		case '4' :
			return numberToken();
		case '5' :
			return numberToken();
		case '6' :
			return numberToken();
		case '7' :
			return numberToken();
		case '8' :
			return numberToken();

		case '9' :
			return numberToken();
		case '#' :
			if(s.charAt(1) =='t' || s.charAt(1) =='f')
				return boolToken(s);
			else
				return charToken(s);
		default :
			return "ERROR";
		}
	}
	
	private static String charToken(String s) {
		
		switch(s) {
		case "#\\\\newline" :
			return "CHAR";
		case "#\\\\space" :
			return "CHAR";
		case "#\\\\tab" : 
			return "CHAR";
		case " " :
			return "CHAR";
		case "  " :
			return "CHAR";
		default :
			return "ERROR";
		}
		
	}

	private static String boolToken(String s) {
		
		switch(s.charAt(1)) {
		case 't' :
			if(s.length() == 2)
				return "BOOL";
			else
				return "ERROR";
		case 'f' :
			if(s.length() == 2)
				return "BOOL";
			else
				return "ERROR";
		default :
			return "ERROR";
		}
	}

	public static String numberToken() {
		
		return null;
	}

	public static String parenthCall(char c){
		if( c ==  '(')
			return "OPENRD";
		else if(c ==  ')' )
			return "CLOSERD";
		else if(c ==  '[')
			return "OPENSQ";
		else if(c ==  ']')
			return "CLOSESQ";
		else if(c ==  '{')
			return "OPENCU";
		else if(c ==  '}')
			return "CLOSECU";
		else
			return "ERROR";
	}
	
}
