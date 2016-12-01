// Name:Youhao Wang
// USC loginid:youhaowa
// CS 455 PA4
// FALL 2016

/** 
	Prefix class:
		Storing the words in the prefix and achieve the shiftin the new word. 
*/
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Prefix {

	private LinkedList<String> prefixlist;
	private int prefixlength;


	/**
		the constructor of Prefix class. 
		@param list:  it is the linkedlist of word you want to make as prefix. 
	*/ 
	public Prefix(LinkedList<String> list){
		prefixlist = list;
		prefixlength = list.size();
	}


	/**
		This method achieve the function to put the new word in the prefix and delete the front one from prefix. 
		@parm newword: the new word that you want to put in the prefix. 
		@return the prefix after shiftin. 
	*/
	public Prefix shiftIn(String newword){
		LinkedList<String> newList = new LinkedList<String> (prefixlist);
		newList.addLast(newword);
		newList.removeFirst();
		return new Prefix(newList);
	}


	/**
       Return string version of object for debugging purposes.
    */
	public String toString(){
		String result = "";
		ListIterator<String> iter = prefixlist.listIterator();
		while(iter.hasNext()){
			result += iter.next() + " ";
		}
		return result;
	}

	/**
       Return the linkedlist that achieve prefix.
    */
	private LinkedList<String> getList(){
		return prefixlist;
	}


	/**
      Override the equlas method in order to use for the key of HashMap.
    */
	@Override
	public boolean equals(Object obj){
		Prefix other = (Prefix) obj;
		ListIterator<String> iter1 = this.getList().listIterator();
		ListIterator<String> iter2 = other.getList().listIterator();
		while(iter1.hasNext() && iter2.hasNext()){
			if( !iter1.next().equals(iter2.next()))
				return false;
		}
		if(iter1.hasNext() || iter2.hasNext())
			return false;
		return true;

	}


	/**
      Override the hashCode method in order to use for the key of HashMap.
    */
	@Override
	public int hashCode(){
		return this.toString().hashCode();
	}



}
