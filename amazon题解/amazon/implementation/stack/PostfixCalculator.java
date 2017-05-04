
import java.util.Scanner;

/**
 * An implementation of a postfix calculator
 * Jing Lu
 * jinglu@brandeis.edu
 */
public class PostfixCalculator {
	
	/**
	 * The implementation of a postfix calculator
	 * @param args
	 */
	public static void main(String[] args){
	    //read from user input
		Scanner scan = new Scanner(System.in);
		String line;
		
		//Initialize a stack of size 20 used for calculation
		Stack s = new Stack(20);
		
		//give instructions to the user
        introCalculator();
		
		//push postfix input to stack if user does not quit
		while (!(line = scan.nextLine()).equalsIgnoreCase("quit")){
			//check user input and call corresponding method
			if (line.equals("+")){
		        sumOperation(s);
			}else if(line.equals("-")){
				subOperation(s);
			}else if(line.equals("*")){
				multiOperation(s);
			}else if(line.equals("/")){
				divisionOperation(s);
			}else if(line.equals("clear")) {
				//if user enters "clear", then clear the stack and start a new equation
				while(s.isEmpty() == false){
					s.pop();
				}
				System.out.println("Starting a new equation...");
			}else {
				//if user enters a number, convert it into double and push onto stack
				try {
				    double addNumber = Double.parseDouble(line);
				    s.push(addNumber);
				}catch (NumberFormatException e){
					System.out.println("Invalid input, please enter correct operands, operator and commands.");
				}
			}
		}
	}
	
	
	/**
	 * check if the operand of operation is missing
	 * print error message if missing and exit program
	 * @param s the stack storing operands
	 */
	public static void checkMissing(Stack s){
		if(s.isEmpty() == true){
	        System.out.println("ERROR: Missing Value... Quiting Calculator...");
			System.exit(0);
		}
	}
	
	/**
	 * print instruction to postfix calculator
	 */
	public static void introCalculator(){
		System.out.println("Welcome to Postfix Calculator...");
		System.out.println("This calculator supports operator +, -, *, and /.");
		System.out.println("Please type clear to start a new equation and type quit if you want to exit.");
	}
	
	/**
	 * handle "+" operation and print current value
	 * @param s stack storing operands
	 */
	public static void sumOperation(Stack s){
		//when user enters an operator,first check if the operand of this operation is missing
		//if missing, print error message
		//if not, process the operation, put result onto stack and display current value of equation
		checkMissing(s);
		double b = ((Double) s.pop()).doubleValue();			   
        checkMissing(s);
		double a = ((Double) s.pop()).doubleValue();
		s.push(a+b);
		System.out.println("Current result: "+ (a+b));
	}
	
	/**
	 * handle "-" operation and print current value
	 * @param s stack storing operands
	 */
	public static void subOperation(Stack s){
		checkMissing(s);
		double b = ((Double) s.pop()).doubleValue();			   
        checkMissing(s);
	    double a = ((Double) s.pop()).doubleValue();		   
		s.push(a-b);
		System.out.println("Current result: "+ (a-b));
	}
	
	/**
	 * handle "*" operation and print current value
	 * @param s stack storing operands
	 */
	public static void multiOperation(Stack s){
		checkMissing(s);
		double b = ((Double) s.pop()).doubleValue();			   
        checkMissing(s);
		double a = ((Double) s.pop()).doubleValue();		   
		s.push(a*b);
		System.out.println("Current result: "+ (a*b));
	}
	
	/**
	 * handle "/" operation and print current value
	 * @param s stack storing operands
	 */
	public static void divisionOperation(Stack s){
		checkMissing(s);
		double b = ((Double) s.pop()).doubleValue();			   
        checkMissing(s);
		double a = ((Double) s.pop()).doubleValue();		   
		s.push(a/b);
		System.out.println("Current result: "+ (a/b));
	}
	
}
