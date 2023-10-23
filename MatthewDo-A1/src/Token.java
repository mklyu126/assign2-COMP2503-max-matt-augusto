
public class Token implements Comparable<Token> {
	
	private String word;
	private int count;
	
	public Token(String word) {
		this.word = word;
		this.count = 1;
	}
	
	public String getWord() {
		return word;
	}
	
	
	
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(o instanceof Token) {
			Token other = (Token) o;
			return this.getWord().equals(other.getWord());
		}else return false;
		
	}
	
	
	@Override
	public int compareTo(Token word) { //Comparable
		return this.getWord().compareTo(word.getWord());
	}
	
	
	public int getCount() {
		return count;
	}
	
	//Used to keep track of repeated words
	public void addCount() {
		count ++;
	}
	
	@Override
	public String toString() {
		return this.word;
	}
	public String format() {
		return word + " : " + count;
	}
}
