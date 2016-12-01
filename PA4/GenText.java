// Name:Youhao Wang
// USC loginid:youhaowa
// CS 455 PA4
// FLL 2016
/**
	GenText class:
		This class contain the main method of this priject.
*/

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class GenText {
	private static int prefixLength ;
	private static int numWords ;
	private static String sourceFile;
	private static String outFile;
	private static boolean isDebug;

	public static void main(String[] args){

		try{
			
			readArgument(args);
			File inFile = new File(sourceFile);
      		Scanner in = new Scanner(inFile);
			checkValid(in);
			in = new Scanner(inFile);
			PrintWriter out = new PrintWriter(outFile);
			RandomTextGenerator g = new RandomTextGenerator(prefixLength, numWords, in, out, isDebug);
			long time1=System.currentTimeMillis();  
			if(numWords > 0){ 
				g.readSource();
				g.generateText();
				g.outToFile();
			}
			long time2=System.currentTimeMillis();  
			long interval=time2-time1; 
			System.out.println("GENERATION SUCCESS!");
			System.out.println("The time it consumes to generate " + numWords +" words is "+ interval +" ms.");
			
			in.close();
			out.close();
			
		}
		catch (BadDataException e1){
            System.out.println("ERROR: " + e1.getMessage());
            printUsage();
			System.exit(1);
	    }
		catch(NumberFormatException e2){
			System.out.println("ERROR: numWords or prefixLength argument must be Integer!");
			printUsage();
			System.exit(1);
		}
		catch (FileNotFoundException e3){
	        if (e3.getMessage().equals("new (Permission denied)")) {
                System.out.println("ERROR: Cannot write output file: " + outFile);
            } 
            else {
                System.out.println("ERROR: Input file does not exist: " + sourceFile);
            }
            printUsage();
			System.exit(1);
	    } 

	}


	/**
		read the input argument 
		@parm args: the input argument array
	*/
	private static void readArgument(String[] args)throws BadDataException{
		if(args.length < 4 )
			throw new BadDataException("Missing command-line arguments!");

		String prefixLength_string = "";
		String numWords_string = "";
		if(args[0].equals("-d")){
			if(args.length < 5 )
				throw new BadDataException("Missing command-line arguments!");
			isDebug = true;
			prefixLength_string = args[1];
			numWords_string = args[2];
			sourceFile = args[3];
			outFile = args[4];
		}
		else{
			isDebug = false;
			prefixLength_string = args[0];
			numWords_string = args[1];
			sourceFile = args[2];
			outFile = args[3];
		}

		prefixLength = Integer.parseInt(prefixLength_string);
		numWords = Integer.parseInt(numWords_string);
	}



	/**
		check is the input argument valid
	*/
	private static void checkValid(Scanner in) throws BadDataException{
		if(prefixLength < 1)
			throw new BadDataException("prefixLength must be larger than 0!");
		if(numWords < 0)
			throw new BadDataException("numWords must be larger than -1!");

		int count = 0;
		while(in.hasNext()){
			in.next();
			count++;
		}
		//System.out.println(count);

		if(prefixLength >= count)
			throw new BadDataException("prefixLength is equal or larger than words in sourceFile!");
	}


	/**
		print the usage of this program
	*/
	private static void printUsage(){
		System.out.println("");
		System.out.println("            ****************************************                             ");
		System.out.println("USAGE: java GenText [-d] prefixLength numWords sourceFile outFile");
        System.out.println("       [-d]           - enter debugging mode");
        System.out.println("       prefixLength   - number of words used as a prefix");
        System.out.println("       numWords       - number of words to generate");
        System.out.println("       sourceFile     - the source file name");
        System.out.println("       outFile        - the output file name");
        System.out.println("");
	}
}
