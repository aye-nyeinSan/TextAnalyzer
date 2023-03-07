 import java.io.*;
import java.lang.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Processer for counting words,characters,palindrome words,token,emoticon(:P,:O,:)),newline,longest and average token size
 */
public class Analyzer {

    private int charCount,palindromeCount,tokenCount,emoticonCount,newlineCount,longestToken,wordCount;
    private double averageTokenSize;
    //private  String string; 
    private ArrayList<String> stringline;
     private ArrayList<Integer> tokenSizes ;
 
    public Analyzer(){
      
    } 
    public Analyzer( ArrayList<String> stringline){
      this.stringline= stringline;
      this.tokenSizes = new ArrayList<Integer>();
    } 

    public String[] processText(){
        String [] words={};
        for (String string : stringline) {
            String [] stringsWords= string.split(" ");
           // System.out.println(stringsWords[0]);//output: the front words
           String[] newWords = new String[words.length + stringsWords.length];
           System.arraycopy(words, 0, newWords, 0, words.length);
           System.arraycopy(stringsWords, 0, newWords, words.length, stringsWords.length);
             words = newWords;
        }
        return words;
    }

    public boolean checkIfWord(String string) {
        
        Pattern regex=Pattern.compile("\\w+");

           Matcher match=regex.matcher(string);
            if (match.find()) {
               // System.out.println(match);
             return true;
            }
        
        return false;
       
    
     
    }

    public int checkWordCount(){
        String []words=processText();
        for (int i = 0; i < words.length; i++) {
          
          if(checkIfWord(words[i])){
            this.wordCount++;
          }
         }
         return wordCount;
       
    
    }

    public void CharacterCount(){
        String []words=processText();
       for (int i = 0; i < words.length; i++) {
             String string=words[i];
             //System.out.println(string);
             for (int j = 0; j < string.length(); j++) {
                int character = string.charAt(j);
               // System.out.print((char)character+" ");
               
                if (Character.isAlphabetic(character) || !Character.isWhitespace(0)) {
                    charCount++;
                }
             }
       }
      
  
    }

    public void checkEmoticon(){
        String []words=processText();
        String s=":[oOD3)()]|\\^\\.\\^";
        Pattern regex=Pattern.compile(s);
        for (int i = 0; i < words.length; i++) {
            //System.out.println(words[i]);

           Matcher match=regex.matcher(words[i]);
            if (match.find()) {
               // System.out.println(match);
               this.emoticonCount++;
            }
        }
        
    }
    public int checkPalindromeCount(){
        String []words=processText();
        for (int i = 0; i < words.length; i++) {
            if(checkIfWord(words[i])){
                   
             if(checkIfPalindrome(words[i])){
                this.palindromeCount++;
                }
               
           }
        }
         return palindromeCount;
       
    }
    public boolean checkIfPalindrome(String word){

        //System.out.println(word);
        String rev="";
        if (word.length() <= 1) {
            return false;
        }

        for (int i = word.length()-1; i >= 0; i--) {
            rev+=word.charAt(i);
        }
        if (word.equals(rev)) {
            
            return true;
        }
      
        return false;
    }
    public int checkNewLineCount(){
    
         return this.newlineCount=stringline.size();
    } 
    public void calculateAverageAndLongestTokenSizes()   {
        this.longestToken=Integer.MIN_VALUE;
        int total = 0;
        String []words=processText();
        for (int i = 0; i < words.length; i++) {
            
                  tokenSizes.add(words[i].length());
                 // System.out.println(tokenSizes.get(i));

            
        }
          for (Integer tokenSize : tokenSizes) {
            if (tokenSize > this.longestToken) {
                this.longestToken = tokenSize;
            }
            total += tokenSize;
        }
        this.averageTokenSize = total / tokenSizes.size();
    
    }
    
    
    private boolean checkIfToken(String string) {
        Pattern regex=Pattern.compile("\\S+");// \\S matches any non-whitespace character
        Matcher match=regex.matcher(string);
        if (match.find()) {
           // System.out.println(match);
         return true;
        }
    
        return false;
    }
    public void checkTokenCount(){
        String words[]={};
        Pattern regex=Pattern.compile("\\S+");// \\S matches any non-whitespace character
    
          
        for (String string : stringline) {
            String [] stringsWords= string.split(" ");
           // System.out.println(stringsWords[0]);//output: the front words
           String[] newWords = new String[words.length + stringsWords.length];
           System.arraycopy(words, 0, newWords, 0, words.length);
           System.arraycopy(stringsWords, 0, newWords, words.length, stringsWords.length);
             words = newWords;
        }
       // System.out.println(words.length);
       for (int i = 0; i < words.length; i++) {
            //System.out.println(words[i]);

           if (checkIfToken(words[i])) {
              this.tokenCount++;
         }
             
        }

           
         
    
    }
    public int getCharCount() {
        CharacterCount();
        return charCount;
    }
    public int getPalindromeCount() {
        checkPalindromeCount();
        return palindromeCount;
    }
    public int getTokenCount() {
        checkTokenCount();
        return tokenCount;
    }
    public int getEmoticonCount() {
        checkEmoticon();
        return this.emoticonCount;
    }
    public int getNewlineCount() {
        checkNewLineCount();
        return newlineCount;
    }
    public int getLongestToken() {
        calculateAverageAndLongestTokenSizes();
        return longestToken;
    }
    public int getWordCount() {
        checkWordCount();
        return wordCount;
    }
    public double getAverageTokenSize() {
        calculateAverageAndLongestTokenSizes();
        return averageTokenSize;
    }
}

