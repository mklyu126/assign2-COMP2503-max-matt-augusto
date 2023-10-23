import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * This class is the Driver class it sorts words and sorts them alphabetically and numerically
 * @author Matthew
 *
 */


public class A1 {

	public static void main(String[] args) {
		new A1();
	}
	
	/**
	 * Loads in Data from txt 
	 * add Most frequent and Least frequent words to array
	 * Save data then prints all the private array to an output txt file
	 */
	public A1() {
		loadData();
		AddFrequentWords();
		AddLessFrequentWords();
		saveData();
		System.out.println("this qwaedjskldf");
	}
    
	//get count of  uniquewords
    private int numberOfUniqueWords = 0;
  //get count of stop words
    private int numberOfStopWords = 0;
    //get count of total words
    private int totalWords = 0;
    
    //Main word list
    private ArrayList<Token> wordsList = new ArrayList<Token>();
    
    //Use to store 10 Frequent words
    private ArrayList<Token>mostFrequentWords = new ArrayList<Token>();
    
    //use to store 10 least frequent words
    private ArrayList<Token>leastFrequentWords = new ArrayList<Token>();
	
    File INPUT_FILE = new File("res/input.txt");
	
	
	/**
	 * Load data takes in txt file and then filters stop words and add the words to wordsList
	 */
	public void loadData() {
		Scanner inputFile;
		
        String[] stopWordsList  = {"a", "about", "all", "am", "an", "and", "any", "are", "as", "at", "be",
        		"been", "but", "by", "can", "cannot", "could", "did", "do", "does",
        		"else", "for", "from", "get", "got", "had", "has", "have", "he", "her",
        		"hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", "its", "like",
        		"more", "me", "my", "no", "now", "not", "of", "on", "one", "or", "our", "out",
        		"said", "say", "says", "she", "so", "some", "than", "that", "the", "their",
        		"them", "then", "there", "these", "they", "this", "to", "too", "us", "upon",
        		"was", "we", "were", "what", "with", "when", "where", "which", "while", "who",
        		"whom", "why", "will", "you", "your"};
        
		try {
			inputFile = new Scanner(INPUT_FILE);
	        File file = new File("res/input.txt");
	        
	        //checks to see if input file has any lines
	        while(inputFile.hasNextLine()) {
	        	
	        	
	        	String currentLine = inputFile.nextLine();
	        	String[] splittedLine = currentLine.split(" ");
	        	
	        	
	        	//This loops adds and filter words
	        	for(String line : splittedLine) {
	        		String word = (line.toLowerCase().trim().replaceAll("[^a-zA-Z]+", ""));
	        		if(word.equals("")) continue;
	        		totalWords++;
	        		Token token = new Token(word);
	        		
	        		int index = wordsList.indexOf(token);
	        		
	        		if(index == -1) {
	        			if(!checkStopWords(word, stopWordsList)) {
	        				wordsList.add(token);
	        				numberOfUniqueWords ++;
	        			}
	        		}
	        		else 
	        		{
	        			wordsList.get(index).addCount();
	        			
	        		}	
	        	}


	        }

	        inputFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Check if current word in input txt file is a stopword
	 */
    public boolean checkStopWords(String words, String[] stopwordsList) {
    	for(String stopwords : stopwordsList) {
    		if (words.equals(stopwords)) {
    			numberOfStopWords ++;
    			return true;
    		}
    	} 
    		return false;
    }
    
    /**
     * Get the lowest count of a word. It is used for Least frequent array list
     */
	public int getMinimumWords(ArrayList<Token> wordsList) {
		int min = 0;
		min = wordsList.get(0).getCount();
		for (int i = 0;  i < wordsList.size() ;i++) {
			if(wordsList.get(i).getCount() < min) {
				min = wordsList.get(i).getCount();
			}
		}
		return min;
	}
	
    /**
     * Adds the most frequent words to array
     */
	public void AddFrequentWords() {
		Collections.sort(wordsList, new TokenCountCompare());
		for(int i = 0; i < wordsList.size(); i++) {
			
				if(mostFrequentWords.size() < 10) {
					mostFrequentWords.add(wordsList.get(i));

			}

		}
	}
    /**
     * Adds least frequent words to array
     */
	public void AddLessFrequentWords() {
		Collections.sort(wordsList, new TokenLettersCompare());
		for (int i = 0;  i < wordsList.size(); i++) {
			
			if(wordsList.get(i).getCount() == getMinimumWords(wordsList)) {
				if(leastFrequentWords.size() < 10)
				leastFrequentWords.add(wordsList.get(i));
			}
		}
	}
	
    /**
     * Output file txt gets created. This is where formatting occurs
     */
	public void saveData() {
		
		try {
			
			PrintWriter outputFile = new PrintWriter("res/output.txt");
			outputFile.println("Total Words: " + totalWords);
			outputFile.println("Unique Words: " + numberOfUniqueWords);
			outputFile.println("Stop Words: " + numberOfStopWords);
			
			outputFile.println("");
			outputFile.println("10 Most Frequent");
			for(Token words : mostFrequentWords) {
				outputFile.println(words.format());

			}
			outputFile.println("");
			outputFile.println("10 Least Frequent");
			for(Token words : leastFrequentWords) {
				outputFile.println(words.format());

			}
			outputFile.println("");
			outputFile.println("All");
			for(Token words : wordsList) {
				outputFile.println(words.format());

			}
			outputFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
	}
}
