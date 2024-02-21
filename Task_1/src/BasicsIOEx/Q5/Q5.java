package BasicsIOEx.Q5;
import java.io.*;

public class Q5 {
    public static void main(String[] args) throws IOException {
         int count=0;
         String line;
    	 FileReader file = new FileReader("C:\\Users\\baps\\Desktop\\Java\\Task_1\\BasicsIO\\sample.txt");
         BufferedReader br = new BufferedReader(file);  
               
         //Gets each line till end of file is reached  
         while((line = br.readLine()) != null) {  
             //Splits each line into words  
             String[] words = line.split(" ");
             //Counts each word  
             count = count + words.length;  
         }  
           
         System.out.println("Number of words present in given file: " + count);  
         br.close();  
        
    	
    }
}
