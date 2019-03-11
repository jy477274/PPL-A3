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

		ArrayList <ArrayList<String>> results = makeTokens(linedArray);
		results.toString();

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
					
					if (toke[0] != '\0') {
						String token = new String(toke).trim().replaceAll(" ",  "");
						Arrays.fill(toke, '\0');
						tokeCount = 0;
						
						tempToken.add(identToken(token));
						tempToken.add(Integer.toString(line));
						tempToken.add(Integer.toString(posiCharacter - token.length()));
						tokens.add(tempToken);
						tempToken.clear();
					}
					
					tempToken.add(parenthCall(input.get(line).charAt(currCharacter)));
					tempToken.add(Integer.toString(line));
					tempToken.add(Integer.toString(posiCharacter));
					tokens.add(tempToken);
					tempToken.clear();
				}
				else if(input.get(line).charAt(currCharacter) == '"'){
					int[] stringEnd = stringToken(line, currCharacter, posiCharacter, input);
					
					if (stringEnd[0] == 1) {
						tempToken.add("STRING");
						tempToken.add(Integer.toString(line));
						tempToken.add(Integer.toString(posiCharacter));
						tokens.add(tempToken);
						tempToken.clear();
						
						line = stringEnd[1];
						currCharacter = stringEnd[2];
						posiCharacter = stringEnd[3];
						
						continue;
					}
					else {
						tempToken.add("ERROR");
						tempToken.add(Integer.toString(line));
						tempToken.add(Integer.toString(posiCharacter));
						tokens.add(tempToken);
						tempToken.clear();
					}
				}
				else if(input.get(line).charAt(currCharacter) == ' ' ){
					if (toke[0] == '\0')
						continue;
					else{
						String token = new String(toke).trim().replaceAll(" ",  "");
						Arrays.fill(toke, '\0');
						tokeCount = 0;
            
						tempToken.add(identToken(token));
						tempToken.add(Integer.toString(line));
						tempToken.add(Integer.toString(posiCharacter - token.length()));
						tokens.add(tempToken);
						tempToken.clear();
					}	
				}
				else if(input.get(line).charAt(currCharacter) == '\t'){
					if (toke[0] == '\0')
						continue;
					else{
						String token = new String(toke).trim().replaceAll(" ",  "");
						Arrays.fill(toke, '\0');
						tokeCount = 0;
						
						tempToken.add(identToken(token));
						tempToken.add(Integer.toString(line));
						tempToken.add(Integer.toString(posiCharacter - token.length()));
						tokens.add(tempToken);
						tempToken.clear();
						posiCharacter += 4;
					}
				}
				else if(input.get(line).charAt(currCharacter) == ';') {
					break;
					
						
				}
				else{
					toke[tokeCount] = input.get(line).charAt(currCharacter);
					tokeCount++;
				}
			}
		}	
		return tokens; 
	}
	
	public static String identToken(String s){
		switch(s){
		case "lambda" :
			return "LAMBDA";
		case "define" :
			return "DEFINE";
		case "let" :
			return "LET";
		case "cond" :
			return "COND";
		case "if" :
			return "IF";
		case "begin" :
			return "BEGIN";
		case "quote" :
			return "QUOTE";
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
			return numberToken(s);
		case '-' :
			return numberToken(s);
		case '0' :
			return numberToken(s);
		case '1' :
			return numberToken(s);
		case '2' :
			return numberToken(s);
		case '3' :
			return numberToken(s);
		case '4' :
			return numberToken(s);
		case '5' :
			return numberToken(s);
		case '6' :
			return numberToken(s);
		case '7' :
			return numberToken(s);
		case '8' :
			return numberToken(s);

		case '9' :
			return numberToken(s);
		case '#' :
			if(s.charAt(1) =='t' || s.charAt(1) =='f')
				return boolToken(s);
			else
				return charToken(s);
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

	public static String numberToken(String s) {
		if(s.startsWith("0b")){
			if(isBi(s.substring(2, s.length())))
				return "NUMBER";
		
		}else if(s.startsWith("0x")){
			if(isHex(s.substring(2, s.length())))
				return "NUMBER";
		
		}else if(s.charAt(0) == '+' || s.charAt(0) == '-' || isNum(s.substring(0,1))){
			s.toLowerCase();
			if(s.charAt(0) != '+' && s.charAt(0) != '-'){
				if(isNum(s))// if s is a decimal signed integer we know it is a number
					return "NUMBER";
				else if(s.contains(".") && isFloatPoint(s)){//if s contains a '.' we know that it must fit the regular expression that contains a .
					return "NUMBER";
				}else if(!s.contains(".") && isFloatNoPoint(s)){//if s does not contain a '.' then it has to satisfy the reg expression that only contains eE 
					return "NUMBER";
				}
				
			}else if(s.charAt(0) == '+' || s.charAt(0) == '-'){//if s has a + or minus sign we can use the same methods if we start at char@ 1 on instead of 0 on
				if(isNum(s.substring(1)))
					return "NUMBER";
				else if(s.contains(".") && isFloatPoint(s.substring(1))){
					return "NUMBER";
				}else if(!s.contains(".") && isFloatNoPoint(s.substring(1))){
					return "NUMBER";
				}	
			}
		}
				
		return "ERROR";
	}
	
	private static boolean isFloatNoPoint(String s) {//we know that s must have [0-9]+[e(we use tolowercase)][+-]?[0-9}
		int eValue = s.indexOf('e');
		if(eValue == -1)
			return false;
		if(isNum(s.substring(0, s.charAt(eValue))) && isExponent(s.substring((eValue), s.length()))){
			return true;
		}
		
		return false;
	}

	private static boolean isExponent(String s) {
		if(s.charAt(0) == 'e'){
			if((s.charAt(1) == '+' || s.charAt(1) == '-')){
				if(isNum(s.substring(2)));
					return true;
			}else{
				if(isNum(s.substring(1)))
					return true;
			}
		}
		return false;
	}

	public static boolean isFloatPoint(String s) {
		int pValue = s.indexOf('.');
		int eValue = s.indexOf('e');
		if(pValue == -1)
			return false;
		if(pValue == 0 || isNum(s.substring(0, pValue))){
			if(eValue != -1){
				if(isNum(s.substring(pValue + 1, eValue)) && isExponent(s.substring(eValue))){
					return true;
				}
			}else if(isNum(s.substring(pValue + 1))){
				return true;
			}
		}
			
			
		return false;
	}

	public static Boolean isBi(String s){
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != '0' && s.charAt(i) != '1'){
				return false;
			}
		}
		return true;
	}
	
	public static Boolean isHex(String s){
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != '0' &&
				s.charAt(i) != '1' &&
				s.charAt(i) != '2' &&
				s.charAt(i) != '3' &&
				s.charAt(i) != '4' &&
				s.charAt(i) != '5' &&
				s.charAt(i) != '6' &&
				s.charAt(i) != '7' &&
				s.charAt(i) != '8' &&
				s.charAt(i) != '9' &&
				s.charAt(i) != 'A' &&
				s.charAt(i) != 'B' &&
				s.charAt(i) != 'C' &&
				s.charAt(i) != 'D' &&
				s.charAt(i) != 'E' &&
				s.charAt(i) != 'F' &&
				s.charAt(i) != 'a' &&
				s.charAt(i) != 'b' &&
				s.charAt(i) != 'c' &&
				s.charAt(i) != 'e' &&
				s.charAt(i) != 'f' &&
				s.charAt(i) != 'd'){
				return false;
			}
		}		
		return true;		
	}
	
	public static Boolean isNum(String s){
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != '0' &&
				s.charAt(i) != '1' &&
				s.charAt(i) != '2' &&
				s.charAt(i) != '3' &&
				s.charAt(i) != '4' &&
				s.charAt(i) != '5' &&
				s.charAt(i) != '6' &&
				s.charAt(i) != '7' &&
				s.charAt(i) != '8' &&
				s.charAt(i) != '9')
				return false;
		}		
		return true;	
	}

	private static String charToken(String s) {
		
		if(s.charAt(1) == '\\' && s.charAt(2) == '\\')
			if (((s.length() == 4) &&
					(s.charAt(3) != '\t') &&
					(s.charAt(3) != '\n') &&
					(s.charAt(3) != '\0')) ||
					
					((s.length() == 6) &&
						((s.charAt(3) == '0') ||
								(s.charAt(3) == '1') ||
								(s.charAt(3) == '2') ||
								(s.charAt(3) == '3')) &&
						((s.charAt(4) == '0') ||
								(s.charAt(4) == '1') ||
								(s.charAt(4) == '2') ||
								(s.charAt(4) == '3') ||
								(s.charAt(4) == '4') ||
								(s.charAt(4) == '5') ||
								(s.charAt(4) == '6') ||
								(s.charAt(4) == '7')) &&
						((s.charAt(5) == '0') ||
								(s.charAt(5) == '1') ||
								(s.charAt(5) == '2') ||
								(s.charAt(5) == '3') ||
								(s.charAt(5) == '4') ||
								(s.charAt(5) == '5') ||
								(s.charAt(5) == '6') || 
								(s.charAt(5) == '7'))))
				return "CHAR";
		
		switch(s) {
		case "#\\\\newline" :
			return "CHAR";
		case "#\\\\space" :
			return "CHAR";
		case "#\\\\tab" : 
			return "CHAR";
		default :
			return "ERROR";
		}
		
	}
	
	public static int[] stringToken(int line, int currCharacter, int posiCharacter, ArrayList<String> input) {
		
		int[] stringEnd = new int[4];
		currCharacter++;
		posiCharacter++;
		
		for(; line < input.size(); line++) {	
			for(;currCharacter < input.get(line).length(); currCharacter++)
			{
				if (input.get(line).charAt(currCharacter) == '"')
				{
					stringEnd[0] = 1;
					stringEnd[1] = line;
					stringEnd[2] = currCharacter;
					stringEnd[3] = posiCharacter;
					return stringEnd;
				}
			
				if (input.get(line).charAt(currCharacter) == '\"')
				{
					stringEnd[0] = 0; // ERROR CODE
					stringEnd[1] = 0; // LINE
					stringEnd[2] = 0; // currCharacter
					stringEnd[3] = 0; // posiCharacter
					return stringEnd;
				}
				else if (input.get(line).charAt(currCharacter) == '\\')
					if (! (input.get(line).length() < (currCharacter + 1)))
					{
						stringEnd[0] = 0;
						stringEnd[1] = 0;
						stringEnd[2] = 0;
						stringEnd[3] = 0;
						return stringEnd;
					}
					else if (!(input.get(line).charAt(currCharacter + 1) == 't') ||
							!(input.get(line).charAt(currCharacter + 1) == 'n'))
						if (! (input.get(line).length() < (currCharacter + 3)))
						{
							stringEnd[0] = 0;
							stringEnd[1] = 0;
							stringEnd[2] = 0;
							stringEnd[3] = 0;
							return stringEnd;
						}
						else if (! (((input.get(line).charAt(currCharacter + 1) == '0') ||
								(input.get(line).charAt(currCharacter + 1) == '1') ||
								(input.get(line).charAt(currCharacter + 1) == '2') ||
								(input.get(line).charAt(currCharacter + 1) == '3')) &&
								((input.get(line).charAt(currCharacter + 2) == 't') ||
										(input.get(line).charAt(currCharacter + 2) == 't') ||
										(input.get(line).charAt(currCharacter + 2) == 't') ||
										(input.get(line).charAt(currCharacter + 2) == 't') ||
										(input.get(line).charAt(currCharacter + 2) == 't') ||
										(input.get(line).charAt(currCharacter + 2) == 't') ||
										(input.get(line).charAt(currCharacter + 2) == 't') ||
										(input.get(line).charAt(currCharacter + 2) == 't')) &&
								((input.get(line).charAt(currCharacter + 3) == 't') ||
										(input.get(line).charAt(currCharacter + 3) == 't') ||
										(input.get(line).charAt(currCharacter + 3) == 't') ||
										(input.get(line).charAt(currCharacter + 3) == 't') ||
										(input.get(line).charAt(currCharacter + 3) == 't') ||
										(input.get(line).charAt(currCharacter + 3) == 't') ||
										(input.get(line).charAt(currCharacter + 3) == 't') ||
										(input.get(line).charAt(currCharacter + 3) == 't') ||
										(input.get(line).charAt(currCharacter + 3) == 't'))))
						{
							stringEnd[0] = 0;
							stringEnd[1] = 0;
							stringEnd[2] = 0;
							stringEnd[3] = 0;
							return stringEnd;
						}
			}
		}
		
		stringEnd[0] = 0;
		stringEnd[1] = 0;
		stringEnd[2] = 0;
		stringEnd[3] = 0;
		return stringEnd;
		
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
