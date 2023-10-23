package mru;

public class Token implements Comparable<Token> {
    private String word;
    private int count;

    public Token(String word){ 
        this.word = word;
        this.count = 1;
    }

    public String getWord(){
        return word;
    }
    public int getCount(){
        return count;
    }

    public void increaseCount(){
        count++;
    }

    @Override
    public int compareTo(Token anotherToken){
        /**
         * Compares two words 
         */

        return (this.getWord().compareTo(anotherToken.getWord()));
    }

    @Override
    public boolean equals(Object obj){
     
    if (obj == null){
        return false;
     }   

     if (obj instanceof Token){
        Token otherToken = (Token) obj;
        return this.getWord().equals(otherToken.getWord());
     }
     else return false;
    }
    @Override
    public String toString(){
        return this.word;
    }
}
