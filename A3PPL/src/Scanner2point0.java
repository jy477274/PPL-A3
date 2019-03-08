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
						toke
						
					}
						
						
				}else if(input.get(line).charAt(currCharacter) == '\t'){
					
				}else{
					
				}
			}
		}	
		return tokens; 
	}
	
	public static String identToken(char [] c){
		String tokenType = null;
		
		
		
		return tokenType;
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
