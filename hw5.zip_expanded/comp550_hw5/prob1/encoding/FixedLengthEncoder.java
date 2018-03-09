package encoding;

import java.util.HashMap;

public class FixedLengthEncoder extends Encoder {

    private final int CHAR_LENGTH = 8;

    private HashMap<Character, String> encodingTable;
    private HashMap<String, Character> decodingTable;

    public FixedLengthEncoder() {
        this.encodingTable = new HashMap<Character, String>();
        this.decodingTable = new HashMap<String, Character>();

        this.buildEncodingTable();
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
     * Decodes the provided string using the stored decoding table.
     */
    @Override
    public String decodeString(String s) {
        // TODO: Implement me!
    	String result = "";
    	String code;
    	
    	// build result by taking sections of 8 chars and comparing to table
    	while (!s.isEmpty()) {
    		code = s.substring(0, 8);
    		s = s.substring(8);
    		result += decodingTable.get(code);
    	}
    	
        return result;
    }

    private void buildEncodingTable() {
        for (char c : Encoder.characters.toCharArray()) {
            int i = Integer.valueOf(c);
            String s = Integer.toBinaryString(i);
            s = String.format("%" + CHAR_LENGTH + "s", s).replace(' ', '0');

            this.encodingTable.put(c, s);
            this.decodingTable.put(s, c);
        }
    }
}
