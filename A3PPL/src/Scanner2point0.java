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
		String tokenType = null;
		switch(s.charAt(0)){
		case '+':
			tokenType = numberToken();
			break;
		case '-':
			tokenType = numberToken();
			break;
		case '0':
			tokenType = numberToken();
			break;
		case '1':
			tokenType = numberToken();
			break;
		case '2':
			tokenType = numberToken();
			break;
		case '3':
			tokenType = numberToken();
			break;
		case '4':
			tokenType = numberToken();
			break;
		case '5':
			tokenType = numberToken();
			break;
		case '6':
			tokenType = numberToken();
			break;
		case '7':
			tokenType = numberToken();
			break;
		case '8':
			tokenType = numberToken();
			break;
		case '9':
			tokenType = numberToken();
			break;
		case '#':
			if(s.charAt(1) =='t' || s.charAt(1) =='f')
				tokenType = boolToken(s);
			else
				tokenType = charToken(s);
			break;
		default :
			break;
		}
		
		
		return tokenType;
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
			return "BOOL";
		case 'f' :
			return "BOOL";
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
