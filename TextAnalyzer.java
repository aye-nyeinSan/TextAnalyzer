
 import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;



public class TextAnalyzer {
    public static void main(String[] args) {
        if(args.length!=1)
        {
            System.out.println("Useage: java TextAnalyzer <input file path>");
            System.exit(1);
        }
        System.out.println("Program start: ");
        long startTime=System.currentTimeMillis();
      
        try { 
            //// Read the file into a string
            //StringBuilder stringbuilder=new StringBuilder();
            File file = new File(args[0]);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
           
            ArrayList<String>s=new ArrayList<>();
            String line=bufferedReader.readLine();
            while (line!= null) {
                s.add(line);
                 line=bufferedReader.readLine();
                
            }
            bufferedReader.close();
            Analyzer analyze=new Analyzer(s);
           long endTime=System.currentTimeMillis();
          
           
         System.out.println("Total token count is "+ analyze.getTokenCount()); 
          System.out.println("Total newline count is "+ analyze.getNewlineCount()); 
         System.out.println("Total emoticon count is "+ analyze.getEmoticonCount()); 
         System.out.println("Total word count is "+analyze.getWordCount());
            System.out.println("Total character count is "+analyze.getCharCount());
         System.out.println("Total palidrome word count is "+analyze.getPalindromeCount());
         System.out.println("Total largest token count is "+analyze.getLongestToken());
          System.out.println("Total average token word count is "+analyze.getAverageTokenSize());
          System.out.println("Total time to execute this program: " + (endTime - startTime) + " ms.");
            
        }   
        catch (FileNotFoundException exception) {
            System.out.println("File not found.");
            return;}
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }  
       
    
    }
}


