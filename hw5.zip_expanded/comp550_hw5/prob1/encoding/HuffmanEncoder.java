package encoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoder extends Encoder {

    private HashMap<Character, String> encodingTable;

    private CharacterNode treeRoot;

    public HuffmanEncoder(HashMap<Character, Float> frequencies) {
        this.encodingTable = new HashMap<Character, String>();

        this.buildEncodingTable(frequencies);
    }

    /**
     * Encodes the provided string using the stored encoding table.
     */
    @Override
    public String encodeString(String s) {
        // TODO: Implement me!
    	String result = "";
    	
    	// build result using encoding table
        for(char c : s.toCharArray()) {
        	result = result + this.encodingTable.get(c);
        }
        
        return result;
    }

    /**
     * Decodes the provided string using the stored encoding tree.
     */
    @Override
    public String decodeString(String s) {
        // TODO: Implement me!
        String result = "";
        CharacterNode node = this.treeRoot;
        
        // traverse the tree using the code
    	for (char c : s.toCharArray()) {
        	if (c == '0') {
        		node = node.getLeftChild();
        	} else if (c == '1') {
        		node = node.getRightChild();
        	}
        	
        	// reset to root if leaf reached
        	if (node.isLeaf()) {
        		result += node.getChar();
        		node = this.treeRoot;
        	}
        }
    	return result;
    }
    
    /**
     * Builds the encoding table using the Huffman algorithm to build
     * a binary tree corresponding to the per-character frequencies provided.
     * (Hint: call buildEncodingTableFromTree to kick off the recursion of actually
     * translating a tree into a table of character codes once you have built the tree.)
     * @param frequencies - a map from character to frequency (should sum to 1)
     */
    private void buildEncodingTable(HashMap<Character, Float> frequencies) {
        // TODO: Implement me!
    	
        // initiate queue
        PriorityQueue<CharacterNode> queue = new PriorityQueue<CharacterNode>();
        
        // create nodes and add to queue using list of chars from hashmap
        ArrayList<Character> list = new ArrayList<Character>(frequencies.keySet());
        for (Character c : list) {
        	queue.add(new CharacterNode(c.charValue(), frequencies.get(c)));
        }
        
        // build tree
    	int n = frequencies.size();
        for (int i = 1; i < n; i++) {
        	CharacterNode node = new CharacterNode(queue.remove(), queue.remove());
        	queue.add(node);
        }
        
        // set root node
        this.treeRoot = queue.remove();
        
        // build encoding table recursively
        buildEncodingTableFromTree(this.treeRoot.getLeftChild(), "0");
        buildEncodingTableFromTree(this.treeRoot.getRightChild(), "1");
    }

    /**
     * Builds the encoding table by traversing the encoding tree.  When a
     * leaf is reached, the current string s of 0s and 1s is stored in the
     * encoding table.  For an internal node, both the left (appending "0") to s
     * and right (appending "1") children are traversed.
     * @param node - the current node
     * @param s - the string of 0s and 1s so far to get to node
     */
    private void buildEncodingTableFromTree(CharacterNode node, String s) {
        // TODO: Implement me!
    	// add to table if leaf, otherwise call recursively
    	if (node.isLeaf()) {
    		this.encodingTable.put(new Character(node.getChar()), s);
    	} else {
    		buildEncodingTableFromTree(node.getLeftChild(), s+"0");
    		buildEncodingTableFromTree(node.getRightChild(), s+"1");
    	}
    }
}
