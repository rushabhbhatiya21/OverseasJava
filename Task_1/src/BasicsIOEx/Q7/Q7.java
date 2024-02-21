package BasicsIOEx.Q7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Q7 {
	 public static void main(String[] args) throws IOException {
         int count=0;
         String data;
    	 FileReader file = new FileReader("C:\\Users\\baps\\Desktop\\Java\\Task_1\\BasicsIO\\sample.txt");
         BufferedReader br = new BufferedReader(file);  
         while ((data=br.readLine())!=null) {
       
             System.out.println(data);
           }      
         //Gets each line till end of file is reached  
         br.read();
         br.close();  
        
    	
    }
}
