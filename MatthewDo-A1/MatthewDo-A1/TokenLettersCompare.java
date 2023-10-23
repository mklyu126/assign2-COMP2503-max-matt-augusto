import java.util.Comparator;

/**
 * This class sorts letters 
 * @author Matthew
 *
 */

public class TokenLettersCompare implements Comparator<Token> {
	
    /**
     * Sorts Letters
     */
		public int compare(Token word1, Token word2) {
			
			int wordCountCompare = Integer.compare(word2.getCount(), word1.getCount());
			int letterCompare = word1.getWord().compareTo(word2.getWord());
			

			
			if(letterCompare != 0) {
				return letterCompare;
			}
			else 
				return wordCountCompare;
		}

}

