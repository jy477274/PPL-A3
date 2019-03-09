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
						indentToken(toke);
						Arrays.fill(toke, '\0');
					}
						
						
				}else if(input.get(line).charAt(currCharacter) == '\t'){
					
				}else{
					identToken(toke);
				}
			}
		}	
		return tokens; 
	}
	
	public static String identToken(char [] c){
		String tokenType = null;
		switch(c[0]){
		case '+':
			tokenType = numberToken();
		case '-':
			tokenType = numberToken();
		case '0':
			tokenType = numberToken();
		case '1':
			tokenType = numberToken();
		case '2':
			tokenType = numberToken();
		case '3':
			tokenType = numberToken();
		case '4':
			tokenType = numberToken();
		case '5':
			tokenType = numberToken();
		case '6':
			tokenType = numberToken();
		case '7':
			tokenType = numberToken();
		case '8':
			tokenType = numberToken();
		case '9':
			tokenType = numberToken();
		case '#':
			if(c[1] =='t' || c[1] =='f')
				tokenType = boolToken(c);
			else
				tokenType = charToken(c);
		}
		
		
		return tokenType;
	}
	
	private static String charToken(char[] c) {
		if(Arrays.equals(c, new char [] {'#','\\','\\','n','e','w','l','i','n','e'})){
			
		}else if(Arrays.equals(c, new char [] {'#','\\','\\','s','p','a','c','e'})){
			
		}else if(Arrays.equals(c, new char [] {'#','\\','\\','t','a','b'})){
		}else if(ANY CHAR){
			
		}
		else if(ANY CHAR ident by 3 dig octal){
			
		}
		return null;
	}

	private static String boolToken(char [] c) {
		if(c[1] =='t')
			return "BOOL";
		else if(c[1] =='f')
			return "BOOL";
		else
			return "ERROR";
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
