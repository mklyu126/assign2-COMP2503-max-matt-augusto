
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class A2 {

    private final String FILE_PATH = "res/input2.txt"; // Change if file name changes

    List<String> stopWordList = new ArrayList<>(List.of(
            "a", "about", "all", "am", "an", "and", "any", "are", "as", "at", "be",
            "been", "but", "by", "can", "cannot", "could", "did", "do", "does", "else",
            "for", "from", "get", "got", "had", "has", "have", "he", "her", "hers", "him",
            "his", "how", "i", "if", "in", "into", "is", "it", "its", "like", "more", "me",
            "my", "no", "now", "not", "of", "on", "one", "or", "our", "out", "said", "say",
            "says", "she", "so", "some", "than", "that", "the", "their", "them", "then",
            "there", "these", "they", "this", "to", "too", "us", "upon", "was", "we", "were",
            "what", "with", "when", "where", "which", "while", "who", "whom", "why", "will",
            "you", "your"));
    
    private MostFrequentWordComparator mFcomparator= new MostFrequentWordComparator();
    private LeastFrequentWordComparator lFcomparator = new LeastFrequentWordComparator();
    
    SLL<Token> orderedTkList = new SLL<Token>();
    SLL<Token> mostFrequent = new SLL<Token>(mFcomparator);
    SLL<Token> leastFrequent = new SLL<Token>(lFcomparator);
    SLL<Token> uniqueWords;
    
    int wordCount = 0;
    int stopWordCount = 0;

    private void readFile() {
        /**
         * Reads file from txt word by word
         * Trims word of trailing and leading blanks, converts it to lowercase, and
         * removes any punctuation/digits
         * If word is not in stopWordList, add it to tokenList
         * 
         */
        try {
            Scanner scanner = new Scanner(new File(FILE_PATH));
            
            SLL<Token> tokenList = new SLL<Token>();
            while (scanner.hasNext()) {

                String word = scanner.next();
                word = word.trim().toLowerCase().replaceAll("[^a-z\\s]", "");

                if (stopWordList.contains(word)) {
                    wordCount++;
                    stopWordCount++;
                } else if (!word.isEmpty()) {
                    wordCount++;
                    Token newToken = new Token(word);

                    tokenList.addAt(wordCount, newToken);
                }

            }
            uniqueWords = getUniqueWords(tokenList);

//            System.out.println("Total Words: " + wordCount);
//            System.out.println("Unique Words: " + uniqueWords.size());
//            System.out.println("Stop Words: " + stopWordCount);
//            System.out.println("\n");
//            
//            
            SLL<Token> orderedTkList = orderTheList(tokenList);// use this list to make the most and least
//            System.out.println("All: ");
//            printAllWords();
//
//            
            orderLeastFreq();
            orderMostFreq();
//            
//            System.out.println();
//            System.out.println("Least frequent");
//            printLeastFreq();
//            
//            System.out.println();
//            System.out.println("Most frequent");
//            printMostFreq();
//            
            
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SLL<Token> getUniqueWords(SLL<Token> tokenList) {
        /**
         * Used to store unique words
         * Uses SLL.contains method to check if word is already in uniqueWords list
         * If it isn't, word is added to the list
         */
        SLL<Token> uniqueWords = new SLL<>();

        Node<Token> curr = tokenList.get(0);
        while (curr != null) {
            Token token = curr.getData();
            

            if (!uniqueWords.contains(token)) {
                uniqueWords.addAt(0, token);
            }

            curr = curr.getNext();
        }
        return uniqueWords;
    }

    private SLL<Token> orderTheList(SLL<Token> tokenList) {
        
        SLL<Token> uniqueWords = getUniqueWords(tokenList);
    	for (int i = 0; i < uniqueWords.size(); i++) {
            orderedTkList.addInOrder(uniqueWords.get(i).getData());   
        }
    	for (int i = 0; i < orderedTkList.size(); i++) {
    		countRepetition(tokenList, orderedTkList.get(i).getData());
    	}
    	return orderedTkList;
    }
    
    private void countRepetition(SLL<Token> tokenList,Token word){
		for (int i = 0; i < tokenList.size(); i++) {
			if(word.compareTo(tokenList.get(i).getData()) == 0) {
				word.increaseCount();
			}
		}
    }
    
    public void orderMostFreq(){
    	for(int i = 0; i < orderedTkList.size() -1 ; i++) {
    		mostFrequent.addInOrder(orderedTkList.get(i).getData());
    	}
    }
    public void orderLeastFreq() {
    	for (int i = 0; i < orderedTkList.size() -1 ; i++) {
    		leastFrequent.addInOrder(orderedTkList.get(i).getData());
    	}
    }
    
    public void printMostFreq() {
    	
    	for(int i = 0; i < 10; i++) {
    		System.out.println(mostFrequent.get(i).getData().format());
    	}
    	
    }
    
    public void printLeastFreq() {
    	for(int i = 0; i < 10; i++) {
    		System.out.println(leastFrequent.get(i).getData().format());
    	}
    }
    
    private void printAllWords() {
    	for (int i = 0; i < orderedTkList.size(); i++) {
    		System.out.println(orderedTkList.get(i).getData().format());
    	}
    }
    
public void saveData() {
		
		try {
			
			PrintWriter outputFile = new PrintWriter("res/output.txt");
			outputFile.println("Total Words: " + wordCount);
			outputFile.println("Unique Words: " + uniqueWords.size());
			outputFile.println("Stop Words: " + stopWordCount);
			
			outputFile.println("");
			outputFile.println("10 Most Frequent");

			
			//add in most frequent lines in output txt
			for(int i = 0; i < 10; i++) {
				outputFile.println(mostFrequent.get(i).getData().format());
	    		
	    	}
			
			
			outputFile.println("");
			outputFile.println("10 Least Frequent");
			
	    	for(int i = 0; i < 10; i++) {
	    		outputFile.println(leastFrequent.get(i).getData().format());
	    		
	    	}


			outputFile.println("");
			outputFile.println("All");
			
			for (int i = 0; i < orderedTkList.size(); i++) {
				outputFile.println(orderedTkList.get(i).getData().format());
	    		
	    	}

			outputFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
	}
    
    
    public static void main(String[] args) throws Exception {
        A2 a2 = new A2();
        a2.readFile();
        a2.saveData();
    }
}