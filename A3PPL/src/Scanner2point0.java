import java.util.*;
import java.io.*;
public class Scanner2point0 {
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO make sure we are actually converting the .scheme file to a string through 
		File inputFile = new File("input.scheme");
		Scanner sc = new Scanner(inputFile);
		
		//READ THE OUTPUT INTO AN ARRAYLIST OF STRINGS WHERE EACH STRING IS A LINE
		ArrayList<String> linedArray = new ArrayList<String>(); 
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			linedArray.add(line);
		}
		
		//PASS THE ARRAY OF LINES INTO THE SCANNER TO BE SCANNED
		makeTokens(linedArray);
		
		

	}
	
	public static ArrayList <ArrayList<String>> makeTokens(ArrayList<String> input){
		ArrayList <ArrayList<String>> tokens = new ArrayList <ArrayList <String>>();
		
		// FOR EVERY LINE IN THE ARRAY OF STRINGS, CHECK THE INITIAL ELEMENTS TO SEE WHICH TYPE OF
		// TOKEN THAT THE SUBSTRINGS MIGHT REPRESENT
		for(int line = 0; line < input.size(); line++){
			ArrayList<String> tempToken = new ArrayList <String>();
			
			// INITIALIZES A CHAR ARRAY TO STORE PIECES OF TOKENS IN,
			// A COUNTER TO TRACK THE SIZE OF THE TOKEN, AND A POSICHARACTER
			// THAT TRACKS THE CHARACTER'S POSITION ON THE LINE, WHICH DIFFERES
			// POSITION OF THE CHARACTER RELATIVE TO OTHERS
			char[] toke = new char[100] ;
			int tokeCount = 0;
			int posiCharacter = 1;
			for(int currCharacter = 0; currCharacter < input.get(line).length(); posiCharacter++, currCharacter++){
				
				// IF THE FIRST ELEMENT OF THE POTENTIAL TOKEN IS A PARENTHESIS
				if( input.get(line).charAt(currCharacter) ==  '(' ||
					input.get(line).charAt(currCharacter) ==  ')' ||
					input.get(line).charAt(currCharacter) ==  '[' ||
					input.get(line).charAt(currCharacter) ==  ']' ||
					input.get(line).charAt(currCharacter) ==  '{' ||
					input.get(line).charAt(currCharacter) ==  '}') {
					
					// IF THE TOKEN WE'VE BEEN BUILDING IS NOT CURRENTLY EMPTY
					if (toke[0] != '\0') {
						
						
						// CREATE A STRING OUT OF THE TOKEN PIECES THAT REPRESENTS THE TOKEN,
						// THEN OUTPUT THIS TOKEN ALONG WITH THE LINE AND SPACE THAT THE TOKEN STARTS ON
						String token = new String(toke).trim().replaceAll(" ",  "");
						Arrays.fill(toke, '\0');
						tokeCount = 0;
						
						tempToken.add(identToken(token));
						tempToken.add(Integer.toString(line + 1));
						tempToken.add(Integer.toString(posiCharacter - token.length()));
						tokens.add(tempToken);
						System.out.println(tempToken);
						tempToken.clear();
					}
					
					// AFTERWARDS, CREAT A PARENTHESIS TOKEN BY CALLING THE METHOD THAT DETERMINES
					// THE TYPE OF PARENTHESIS USED, THEN PRINT IT ALONG WITH ITS LINE AND LOCATION
					tempToken.add(parenthCall(input.get(line).charAt(currCharacter)));
					tempToken.add(Integer.toString(line + 1));
					tempToken.add(Integer.toString(posiCharacter));
					tokens.add(tempToken);
					System.out.println(tempToken);
					tempToken.clear();
				}
				
				// IF THE NEXT TOKEN IS BEGINNING AS A STRING
				else if(input.get(line).charAt(currCharacter) == '"'){
					
					// KEEP TRACK OF WHERE THE STRING STARTED AND THEN PASS IT TO THE STRING
					// DECIPHERING METHOD TO DETERMINE WHERE THE STRING STOPS SO WE CAN RESUME
					// SCANNING AT THAT POINT
					int[] stringEnd = stringToken(line, currCharacter, posiCharacter, input);
					for(int k = 0; k < 4; k++)
						System.out.println(stringEnd[k]);
					
					// IF THE STRING WAS FREE OF ERRORS, DISPLAY THE TOKEN TYPE
					// AS WELL AS ITS STARTING LINE AND LOCATION
					if (stringEnd[0] == 1) {
						tempToken.add("STRING");
						tempToken.add(Integer.toString(line + 1));
						tempToken.add(Integer.toString(posiCharacter));
						tokens.add(tempToken);
						System.out.println(tempToken);
						tempToken.clear();
						
						
						// RESUME WHERE THE STRING ENDED
						line = stringEnd[1];
						currCharacter = stringEnd[2];
						posiCharacter = stringEnd[3];
						
						continue;
					}
					else {
						
						// IF THE STRING WAS FOUND TO BE AN ERROR, PRINT THIS AND ITS LOCATION, ETC.
						tempToken.add("ERROR");
						tempToken.add(Integer.toString(line + 1));
						tempToken.add(Integer.toString(posiCharacter));
						tokens.add(tempToken);
						System.out.println("LEXICAL ERROR [" + (line + 1) + ":" + posiCharacter + "]: Invalid token");
						tempToken.clear();
					}
				}
				
				// IF THE NEXT CHARACTER IS A SPACE
				else if(input.get(line).charAt(currCharacter) == ' '){
					
					if (toke[0] == '\0')
						continue;
					else{
						
						// BUILD ANY TOKEN THAT WE'VE BEEN GATHERING AND DISPLAY IT WITH
						// ITS INFO
						String token = new String(toke).trim().replaceAll(" ",  "");
						Arrays.fill(toke, '\0');
						tokeCount = 0;
            
						tempToken.add(identToken(token));
						tempToken.add(Integer.toString(line + 1));
						tempToken.add(Integer.toString(posiCharacter - token.length()));
						tokens.add(tempToken);
						System.out.println(tempToken);
						tempToken.clear();
					}	
				}
				
				// IF THE NEXT CHARACTER IS A TAB
				else if(input.get(line).charAt(currCharacter) == '\t'){
					
					//INCREMENT POSICHARACTER TO ACCOUNT FOR THE 4 SPACES OF THE TAB
					if (toke[0] == '\0')
					{
						posiCharacter += 3;
						continue;
					}
					else{
						
						// BUILD ANY TOKEN THAT WE'VE BEEN WORKING ON AND DISPLAY ITS INFO
						String token = new String(toke).trim().replaceAll(" ",  "");
						Arrays.fill(toke, '\0');
						tokeCount = 0;
						
						tempToken.add(identToken(token));
						tempToken.add(Integer.toString(line + 1));
						tempToken.add(Integer.toString(posiCharacter - token.length()));
						tokens.add(tempToken);
						System.out.println(tempToken);
						tempToken.clear();
						posiCharacter += 3;
					}
				}
				
				// IF THE LINE BEGINS A COMMENT, BREAK TO THE NEXT LINE
				else if(input.get(line).charAt(currCharacter) == ';') {
					break;	
				}
				else{
					
					// OTHERWISE SIMPLY BUILD THE TOKEN WE'VE BEEN WORKING ON AND DISPLAY IT ALONG WITH ITS INFO
					toke[tokeCount] = input.get(line).charAt(currCharacter);
					tokeCount++;
					if (tokeCount == input.get(line).length() - 2)
					{
						String token = new String(toke).trim().replaceAll(" ",  "");
						Arrays.fill(toke, '\0');
						tokeCount = 0;
						
						tempToken.add(identToken(token));
						tempToken.add(Integer.toString(line + 1));
						tempToken.add(Integer.toString(posiCharacter - token.length() + 1));
						tokens.add(tempToken);
						System.out.println(tempToken);
						tempToken.clear();
					}
				}
			}
		}	
		return tokens; 
	}
	
	public static String identToken(String s){//ident token first checks for all keywords
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
		
		if((s.charAt(0) != ('[')) &&// then we check for identifiers, stating all the chars it cannot start with and then all the chars it cannot be later in the string
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
				(s.charAt(0) != ('\'')) &&
				(s.charAt(0) != ('\"')) &&
				(s.charAt(0) != ('.')) &&
				(s.charAt(0) != ('#'))){
			for(int i = 1; i < s.length(); i++){
				if((s.charAt(i) == ('[')) ||
						(s.charAt(i) == (']')) ||
						(s.charAt(i) == ('(')) ||
						(s.charAt(i) == (')')) ||
						(s.charAt(i) == ('{')) ||
						(s.charAt(i) == ('}')) ||
						(s.charAt(i) == (';')) ||
						(s.charAt(i) == ('\'')) ||
						(s.charAt(i) == ('\"')) ||
						(s.charAt(i) == ('#'))){
					return "ERROR";
				}
			}
			return "IDENTIFIER";
		}
		else if((s.length() == 1) && (s.charAt(0) == '\''))//hard coded value for the quote mark 
			return "QUOTMK";
		
		switch(s.charAt(0)){//we chec all possible starter values for ints, and if they are met we send it into a numberToken method that checks all the different types of NUMBERS
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
		case '.' :
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
  
	private static String boolToken(String s) {// very simple method checkcing if the 2ed char is t or false we return BOOL
		
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

	public static String numberToken(String startingString) {
		String s = startingString.toLowerCase();
		if(s.startsWith("0b")){//just calls the bi method
			if(isBi(s.substring(2, s.length())))
				return "NUMBER";
		
		}else if(s.startsWith("0x")){//calls the hex method since know the first two digits conferm it is a hex  (or should be)
			if(isHex(s.substring(2, s.length())))
				return "NUMBER";
		
		}else if(s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '.' || isNum(s.substring(0,1))){
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
		else if(isNum(s.substring(0, eValue)) && isExponent(s.substring((eValue)))){
			return true;
		}
		
		return false;
	}

	private static boolean isExponent(String s) {//is exponent makes sure that the first char is an 'e', we have tolowercase for 'E' before here, 
		if(s.charAt(0) == 'e'){
			if((s.charAt(1) == '+' || s.charAt(1) == '-')){//two options if there is a = or - we go from an incremented pos to check if the rest is a num
				if(isNum(s.substring(2)));
					return true;
			}else{
				if(isNum(s.substring(1)))
					return true;
			}
		}
		return false;
	}

	public static boolean isFloatPoint(String s) {//we mark the vals of . and e, since we know this type of float has to have a '.' we we check that the first part is either just a . or from 0 to the . is a number
		int pValue = s.indexOf('.');// then from the . to the end of the string we check if it is an exponent using another helper funct
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

	public static Boolean isBi(String s){//checks that the string only contains 1s and 0s
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != '0' && s.charAt(i) != '1'){
				return false;
			}
		}
		return true;
	}
	
	public static Boolean isHex(String s){//checks that the string only contains all possible hex vals
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
	
	public static Boolean isNum(String s){//nice little method that was used a lot, checks that all chars in a string are numbers
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

	private static String charToken(String s) {// we know that the first 5 chars are #\\\\ if the length is four and the last char is not \5 \n \0 we know it is any char, we also look at strings length 6 and check all possible ansrs
		
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
		
		switch(s) {//hard coded values
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
		
		System.out.println(line);
		System.out.println(currCharacter);
		System.out.println(posiCharacter);
		System.out.println("-------------");
		
		for(; line < input.size(); line++) {	
			for(;currCharacter < input.get(line).length(); currCharacter++, posiCharacter++)
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


	public static String parenthCall(char c){// parenthCall checks what type of parenth it is and then returns a string based on the char
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
