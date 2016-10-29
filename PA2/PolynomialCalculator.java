// Name: Youhao Wang
// USC loginid:  youhaowa
// CS 455 PA2 
// Fall 2016

/**
This program is a user interface program, which is an interactive program that  
builds and do computations on several Polynomial objects.
*/

import java.util.Scanner;

public class PolynomialCalculator {
	public static void main( String[] args){
		Polynomial[ ] test = new Polynomial[POLY_LENGTH];
		for(int i =0; i< POLY_LENGTH; i++){
			test[i] = new Polynomial( );
		}
		while (true){
			System.out.print("cmd> ");
			Scanner in = new Scanner(System.in);
			String line = in.nextLine();
			Scanner lineScanner = new Scanner(line);
			String cmd = lineScanner.next();   // Read the first word
			cmd = cmd.toLowerCase();     // transform to the lower case 

			if ( cmd.equals("create")){    //Create the new ploy
				if (! lineScanner.hasNextInt( ) )
					System.out.println("ERROR: illegal command. Type help for command options.");
				else{
					int num_pol = lineScanner.nextInt();
					if(num_pol>=0 && num_pol<= POLY_LENGTH - 1)
						test[num_pol] = doCreate();
					else
						System.out.println("ERROR: illegal index for a ploy. must be between 0 and 9, inclusive");
					}
			}

			else if(cmd.equals("add")){    // add two poly to the new one 
				int add1=0;
				int add2=0;
				int result =0;
				if (! lineScanner.hasNextInt( ) )
					System.out.println("ERROR: illegal command. Type help for command options.");
				else{
					result =lineScanner.nextInt();
					add1 = lineScanner.nextInt();
					add2 =lineScanner.nextInt();
				}
				test[result] = test[add1].add(test[add2]);
			}

			else if(cmd.equals("print")){    //print the ploy
				if (! lineScanner.hasNextInt( ) )
					System.out.println("ERROR: illegal command. Type help for command options.");
				else{
					int num_pol = lineScanner.nextInt();
					if(num_pol>=0 && num_pol<= POLY_LENGTH-1)
						doPrint(test[num_pol ]);
					else
						System.out.println("ERROR: illegal index for a ploy. must be between 0 and 9, inclusive");
				}
			}
			
			else if(cmd.equals("eval")){
				if (! lineScanner.hasNextInt( ) )
					System.out.println("ERROR: illegal command. Type help for command options.");
				else{
					int num_pol = lineScanner.nextInt();
					if(num_pol>=0 && num_pol<= POLY_LENGTH -1 )
						doEval(test[num_pol ]);
					else
						System.out.println("ERROR: illegal index for a ploy. must be between 0 and 9, inclusive");
				}
			}

			else if(cmd.equals("quit")){
				return;
			}

			else if(cmd.equals("help")){
				doHelp();
			}

			else{
				System.out.println("ERROR: illegal command. Type help for command options.");
			}
		}
		
		//System.out.println(cmd);
	}
	

	/**
	create the poly. 
	-- Zero coefficients. Invoking create with one or more terms term with a zero coefficient is fine. 
	  The Polynomial class itself handles simplifying the polynomial. 
	-- Multiple coefficients that have the same exponent. Again, the polynomial takes care of simplification. 
	-- Multiple spaces (' ') and/or tabs ('\t') are allowed before, between or after the numbers given. 
	-- Warning: missing the last exponent (i.e., odd number of values). For this will ignore the last value entered 
		(i.e., the term that had a coefficient given, but no corresponding exponent).
	-- Warning: negative exponent. For this you should use the absolute value.
	*/
	private static Polynomial doCreate()
	{
		Polynomial result = new Polynomial();
		System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by <n1>");
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		Scanner lineScanner = new Scanner(line);
		while ( lineScanner.hasNextDouble() )
		{
			double coeff = lineScanner.nextDouble();
			int expo =0;
			if (lineScanner.hasNextInt( ) )
				   expo = lineScanner.nextInt( );
			else{
				System.out.println("WARNING: Miss the last exponent!");
			}

			if (expo < 0){
				expo = - expo;
				System.out.println("WARNING: Negative exponent!");
			}

			Term term = new Term(coeff, expo);
			result = result.add(new Polynomial(term));
			
		}

		return result;		
	}



	/**
	Print the given poly
	*/
	private static void doPrint(Polynomial poly)
	{
		System.out.println(poly.toFormattedString());
	}



	/**
	Returns the value of the poly at a given value of x.
	*/
	private static void doEval(Polynomial poly)
	{
		double result = 0;
		System.out.print("Enter a floating point value for x :");
		Scanner in = new Scanner(System.in);
		double x = in.nextInt();
		result = poly.eval(x);
		System.out.println(result);
	}


	/**
	Print all the instrution of this program.
	*/
	private static void doHelp()
	{
		System.out.println("the command summary of this program:");
		System.out.println("-- create: you should then enter the int between 0 - 9 that the arrary you want to create");
		System.out.println("   then Enter a space-separated sequence of coeff-power pairs terminated by <nl>");
		System.out.println("   please pay much attention for follow:");
		System.out.println("    -- Zero coefficients. Invoking create with one or more terms term with a zero coefficient is fine. ");
	  	System.out.println("	   The Polynomial class itself handles simplifying the polynomial. ");
		System.out.println("	-- Multiple coefficients that have the same exponent. Again, the polynomial takes care of simplification. ");
		System.out.println("	-- Multiple spaces (' ') and/or tabs ('\t') are allowed before, between or after the numbers given. ");
		System.out.println("	-- Warning: missing the last exponent (i.e., odd number of values). For this will ignore the last value entered ");
		System.out.println("		(i.e., the term that had a coefficient given, but no corresponding exponent).");
		System.out.println("	-- Warning: negative exponent. For this you should use the absolute value.-- ");
		System.out.println();
		System.out.println("-- print: you should then enter the int between 0 - 9 that the ploy you want to print");
		System.out.println();
		System.out.println("-- add: you should then enter three int between 0 - 9 int1, int2, int3. That means int1 = int2 + int3");
		System.out.println();
		System.out.println("-- eval: you should then enter the int between 0 - 9 that the returns the value of the poly at a given value of x.");
		System.out.println("   then Enter a floating point value for x:");
		System.out.println();
		System.out.println("-- quit: you will quit this test program.");
		System.out.println();
		System.out.println("Attention: if the input is illegal, the according error will show you!");
		System.out.println("		   The command can be upper or lower case!");
	}

	private final static int POLY_LENGTH = 10;   // we create 10 polynomials here

}
