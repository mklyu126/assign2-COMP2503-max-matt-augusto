package mru;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class A2 {

    private final String FILE_PATH = "res/input3.txt"; // Change if file name changes

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

    //public A2() {//no need for this once you can call the method in the main
    //    readFile();
    //}

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
            int wordCount = 0;
            int stopWordCount = 0;
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
            SLL<Token> uniqueWords = getUniqueWords(tokenList);

            System.out.println("Total Words: " + wordCount);
            System.out.println("Unique Words: " + uniqueWords.size());
            System.out.println("Stop Words: " + stopWordCount);
            System.out.println("\n");
            
            //tokenList.print();
            
            

            // updateTokenFrequencies(tokenList); //Finds token frequencies in list

            // Collections.sort(tokenList, new MostFrequentWordComparator()); //Sorts
            // tokenList to have most frequent words first

            // System.out.println("10 Most Frequent: ");
            // printTopCommonWords(tokenList);

            // Collections.sort(tokenList, new LeastFrequentWordComparator()); //Sorts
            // tokenList to have least frequent words first

            // System.out.println("\n10 Least Frequent: ");
            // printTopCommonWords(tokenList);
            SLL<Token> orderedTkList = orderTheList(tokenList);// use this list to make the most and least
            System.out.println("\n All: ");
            printAllWords(orderedTkList);

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
        SLL<Token> orderedTkList = new SLL<Token>();
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
    
    private void printAllWords(SLL<Token> orderedTkList) {
    	for (int i = 0; i < orderedTkList.size(); i++) {
    		System.out.println(orderedTkList.get(i).getData().format());
    	}
    }
    // private void printTopCommonWords(SLL<Token> tokenList){
    // /**
    // * Iterates from start of list until end
    // * Math.min makes sure loop does not go past size of the list - avoiding an
    // out of bounds exception
    // * Retrieves word of token object at index i, prints it to the console
    // */
    // for (int i = 0; i < Math.min(10, tokenList.size()); i++){
    // System.out.println(tokenList.get(i).getWord() + " : " +
    // tokenList.get(i).getCount());
    // }
    // }

    // private void updateTokenFrequencies(SLL<Token> tokenList){
    // /**
    // * Nested for loop
    // * Outer loop, i, iterates thru tokenList from beginning to end
    // * Inner loop, k , starts from i + 1 and goes thru remaining items in list
    // * Each pair of tokens (at i and k) are compared, if equal than the count is
    // increased by one
    // * The matched token is removed from the tokenList
    // */
    // for (int i = 0; i < tokenList.size(); i++) {
    // for (int k = i + 1; k < tokenList.size(); k++) {
    // if (tokenList.get(i).getWord().equals(tokenList.get(k).getWord())){
    // tokenList.get(i).increaseCount();
    // tokenList.remove(k);
    // k--;
    // }
    // }
    // }
    // }
    public static void main(String[] args) throws Exception {
        A2 a2 = new A2();
        a2.readFile();
    }
}