import java.util.Comparator;
/**
 * This class sorts highest count of a word 
 * @author Matthew
 *
 */


public class TokenCountCompare implements Comparator<Token>{
    /**
     * Sorts numbers
     */
	public int compare(Token word1, Token word2) {
		
		int wordCountCompare = Integer.compare(word2.getCount(), word1.getCount());
		int letterCompare = word1.getWord().compareTo(word2.getWord());
		

		
		if(wordCountCompare != 0) {
			return wordCountCompare;
		}
		else 
			return letterCompare;
	}
}
