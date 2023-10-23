package mru;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class A1{
    
    private final String FILE_PATH = "res/input.txt"; //Change if file name changes
    
    List<String> stopWordList = new ArrayList<>(List.of(
        "a", "about", "all", "am", "an", "and", "any", "are", "as", "at", "be",
            "been", "but", "by", "can", "cannot", "could", "did", "do", "does", "else",
            "for", "from", "get", "got", "had", "has", "have", "he", "her", "hers", "him",
            "his", "how", "i", "if", "in", "into", "is", "it", "its", "like", "more", "me",
            "my", "no", "now", "not", "of", "on", "one", "or", "our", "out", "said", "say",
            "says", "she", "so", "some", "than", "that", "the", "their", "them", "then",
            "there", "these", "they", "this", "to", "too", "us", "upon", "was", "we", "were",
            "what", "with", "when", "where", "which", "while", "who", "whom", "why", "will",
            "you", "your"
    ));
    
    

    public A1(){
        readFile();
    }
    private void readFile() {
        /**
         * Reads file from txt word by word
         * Trims word of trailing and leading blanks, converts it to lowercase, and removes any punctuation/digits
         * If word is not in stopWordList, add it to tokenList
         * 
         */
        try{
            Scanner scanner1 = new Scanner(new File(FILE_PATH));
            int wordCount = 0;
            int stopWordCount = 0;
            ArrayList<Token> tokenList = new ArrayList<Token>();
            while(scanner1.hasNext()){

                String word = scanner1.next();
                word = word.trim().toLowerCase().replaceAll("[^a-z\\s]", "");
                
                

                if (stopWordList.contains(word)){
                    wordCount++;
                    stopWordCount++;
                }
                else if (! word.isEmpty()){
                    wordCount++;
                    Token newToken = new Token(word);
                
                    tokenList.add(newToken);
                }
               
                    
                
                
                
                
            }
            int uniqueWordCount = countUniqueWords(tokenList);
            
            
            
            System.out.println("Total Words: " + wordCount);
            System.out.println("Unique Words: " + uniqueWordCount);
            System.out.println("Stop Words: " + stopWordCount);
            System.out.println("\n");

            
    
            
            
            updateTokenFrequencies(tokenList); //Finds token frequencies in list
            
            Collections.sort(tokenList, new MostFrequentWordComparator()); //Sorts tokenList to have most frequent words first
            
            System.out.println("10 Most Frequent: ");
            printTopCommonWords(tokenList);
            
            Collections.sort(tokenList, new LeastFrequentWordComparator()); //Sorts tokenList to have least frequent words first 
            
            System.out.println("\n10 Least Frequent: ");
            printTopCommonWords(tokenList);
            
            System.out.println("\n All: ");
            printAllWords(tokenList);
            
            
            scanner1.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public int countUniqueWords(List<Token> tokenList) {
        /**
         * Used to store unique words
         * Uses .contains method to check if word is already in uniqueWords list
         * If it isn't, word is added to the list
         */
        List<String> uniqueWords = new ArrayList<>();
        
        for (Token token : tokenList) {
            if (!uniqueWords.contains(token.getWord())) {
                uniqueWords.add(token.getWord());
            }
        }

        return uniqueWords.size();
    }

    private void printAllWords(List<Token> tokenList){
        for (int i = 0; i<tokenList.size(); i++){
            System.out.println(tokenList.get(i).getWord() + " : " + tokenList.get(i).getCount());
        }
    }
    
    private void printTopCommonWords(List<Token> tokenList){
        /**
         * Iterates from start of list until end
         * Math.min makes sure loop does not go past size of the list - avoiding an out of bounds exception
         * Retrieves word of token object at index i, prints it to the console
         */
        for (int i = 0; i < Math.min(10, tokenList.size()); i++){
            System.out.println(tokenList.get(i).getWord() + " : " + tokenList.get(i).getCount());
        }
    }
    
    private void updateTokenFrequencies(List<Token> tokenList){
        /**
         * Nested for loop 
         * Outer loop, i, iterates thru tokenList from beginning to end
         * Inner loop, k , starts from i + 1 and goes thru remaining items in list
         * Each pair of tokens (at i and k) are compared, if equal than the count is increased by one
         * The matched token is removed from the tokenList
         */
        for (int i = 0; i < tokenList.size(); i++) {
                for (int k = i + 1; k < tokenList.size(); k++) {
                    if (tokenList.get(i).getWord().equals(tokenList.get(k).getWord())){
                        tokenList.get(i).increaseCount();
                        tokenList.remove(k);
                        k--; 
                    }
                }
            }
    }
    public static void main(String[] args) throws Exception {
        A1 a1 = new A1();
        
        
        
        
        

    }}

