// Name:Youhao Wang
// USC loginid:youhaowa
// CS 455 PA4
// FLL 2016
/**
	RandomTextGenerator class:
		this class achieve main function of text generate. 
*/
import java.io.PrintWriter;
import java.util.*;

public class RandomTextGenerator {

	public static final int FIXED_SEED = 1;
	public static final int MAX_LENGTH = 80;

	private static int prefixLength;
	private static int numWords;
	private  Scanner in;
	private  PrintWriter out;
	private static boolean isDebug;
	private HashMap<Prefix, ArrayList<String>> map;
	private ArrayList<Prefix> allPrefix;
	ArrayList<String> result;
	private Random random;
	

	public RandomTextGenerator(int prefixLength,int numWords, Scanner in,PrintWriter out,boolean isDebug){
		this.prefixLength = prefixLength;
		this.numWords = numWords;
		this.in = in;
		this.out = out;
		this.isDebug = isDebug;
		if(isDebug)
			random = new Random(FIXED_SEED);
		else
			random = new Random();
		map = new HashMap<Prefix, ArrayList<String>>();
		allPrefix = new ArrayList<Prefix>();
		result = new ArrayList<String>();
	}


	/**
		read the data from soucefile and store them in the Hashmap.
	*/
	public void readSource(){
		LinkedList<String> inial_list = new LinkedList<String> ();
		for(int i = 0; i < prefixLength; i++){
			if(in.hasNext()){
				String str = in.next();
				inial_list.add(str);
			}
		}
		Prefix prefix = new Prefix(inial_list);
		allPrefix.add(prefix);
		ArrayList<String> successor;
		
		while(in.hasNext()){
			String next = in.next();
			if(map.containsKey(prefix)){
				successor = map.get(prefix);
			}
			else{
				successor = new ArrayList<String>();
			}
			successor.add(next);
			map.put(prefix,successor);
			prefix = prefix.shiftIn(next);
			allPrefix.add(prefix);
		}

		if(map.containsKey(prefix)){
			successor = map.get(prefix);
		}
		else{
			successor = new ArrayList<String>();
		}
		map.put(prefix,successor);

	}

	/**
		Generate the words in the text. 
	*/
	public void generateText(){
		Prefix currentlPrefix = inialPrefix();
		for(int i = 0; i < numWords; i++){
			if(isDebug)	
				System.out.println("DEBUG: prefix :"+ currentlPrefix);
			ArrayList<String> successor = map.get(currentlPrefix);
			if(successor.isEmpty()){
				if(isDebug)	
					System.out.println("DEBUG: successors: <END OF FILE> ");
				currentlPrefix = inialPrefix();
				i--;
			}
			else{
				if(isDebug)
					System.out.println("DEBUG: successors: "+ successor);
				int len = successor.size();
				int index = random.nextInt(len);
				String str = successor.get(index);
				result.add(str);
				if(isDebug)
					System.out.println("DEBUG: word generated: "+ str);
				currentlPrefix = currentlPrefix.shiftIn(str);
			}
		}
	}


	/**
		Write the text to the out file
	*/
	public void outToFile(){
		ListIterator<String> iter = result.listIterator();
		int countLine = 0;
		String line = "";
		while(iter.hasNext()){
			String str = iter.next();
			if( (line.length()+ str.length()) <= MAX_LENGTH){
				line = line + str + " ";
			}
			else{
				line = line.substring(0, line.length() -1);
				out.println(line);
				line = str + " ";
			}
		}
		line = line.substring(0, line.length() -1);
		line = line + ".";
		out.println(line);
	}


	/**
		Choose at random to become the initial prefix.
	*/
	private Prefix inialPrefix(){
		int len = allPrefix.size();
		int index = random.nextInt(len);
		Prefix inial_prefix = allPrefix.get(index);
		if(isDebug){
			System.out.println("DEBUG: choose a new initial prefix :"+ inial_prefix);
		}
		return inial_prefix;
	}

}
