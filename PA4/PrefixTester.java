import java.util.LinkedList;

public class PrefixTester {

		public static void main(String[] args){
			LinkedList<String> prefixLinkedList1 = new LinkedList<>();
	        prefixLinkedList1.add("the ");
	        prefixLinkedList1.add("big ");
	        prefixLinkedList1.add("dog");
	        Prefix prefix1 = new Prefix(prefixLinkedList1);
	        //prefix1 = prefix1.shiftIn("D");
	        System.out.println("prefix1: " + prefix1.toString());

	        System.out.println("");

	        LinkedList<String> prefixLinkedList2 = new LinkedList<>();
	        prefixLinkedList2.add("dog");
	        prefixLinkedList2.add("the ");
	        prefixLinkedList2.add("big ");
	        Prefix prefix2 = new Prefix(prefixLinkedList2);
	        prefix2 = prefix2.shiftIn("dog");
	        System.out.println("prefix2: " + prefix2.toString());
	        
	        
	        System.out.println("equlas: " + prefix1.equals(prefix2)); 
	        System.out.println("prefix1 hashcode: " + prefix1.hashCode());
	        System.out.println("prefix2 hashcode: " + prefix2.hashCode());
		}
}
