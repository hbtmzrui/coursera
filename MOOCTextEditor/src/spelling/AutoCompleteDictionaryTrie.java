package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size = 0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		TrieNode curr = root;
		TrieNode parent = null;
		String lowerString = word.toLowerCase();
		char[] chars = lowerString.toCharArray();
		int i = 0;
		
		for (; i < lowerString.length(); i++) {
			parent = curr;
			curr = curr.getChild(chars[i]);
			if (curr == null) {
				break;
			}
		}
		
		if (curr != null && curr.getText().equals(lowerString)) {
			//already in Tries
			if (curr.endsWord()) {
				return false;
			} else {
				curr.setEndsWord(true);
				size++;
				return true;
			}
		} else {
			//not found in Tries,insert this word
			for (; i < lowerString.length(); i++) {
				parent.insert(chars[i]);
				parent = parent.getChild(chars[i]);
				parent.setEndsWord(false);
			}
			
			parent.setEndsWord(true);
			size++;
			
			return true;
		}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		TrieNode curr = root;
		String lowerString = s.toLowerCase();
		char[] chars = lowerString.toCharArray();
		
		for (int i = 0; i < lowerString.length(); i++) {
			curr = curr.getChild(chars[i]);
			if (curr == null) {
				return false;
			}
		}
		
		if (curr.endsWord() && curr.getText().equals(lowerString)) {
			return true;
		} else {
			return false;
		}
}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	TrieNode curr = root;
 		String lowerString = prefix.toLowerCase();
 		char[] chars = lowerString.toCharArray();
 		LinkedList<String> rList = new LinkedList<String>();
 		LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
 		int found = 0;
 		
 		//get the position of stem in Trie
 		for (int i = 0; i < lowerString.length(); i++) {
 			curr = curr.getChild(chars[i]);
 			if (curr == null) {
 				return rList;
 			}
 		}
 		
 		//BFS on node of this stem
 		while (found < numCompletions) {
 			if (curr.endsWord()) {
 				rList.add(curr.getText());
 				found++;
 			}
 			
 			for (Character c : curr.getValidNextCharacters()) {
 	 			queue.add(curr.getChild(c));
 	 		}
 			
 			if (queue.size() > 0) {
 				curr = queue.remove();
 			} else {
 				break;
 			}
 		}
 		
 		return rList;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		if (curr.endsWord())
 			System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}