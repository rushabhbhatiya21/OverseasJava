package BasicsIOEx.Q8;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Q8 {
	public static void main(String[] args) throws IOException {
     
        String data;
        String add_data="\nThis is a string";
        
   	    FileReader file = new FileReader("C:\\Users\\baps\\Desktop\\Java\\Task_1\\BasicsIO\\sample.txt");
   	    FileWriter fw=new FileWriter("C:\\Users\\baps\\Desktop\\Java\\Task_1\\BasicsIO\\sample.txt",true);
        fw.write(add_data);
        fw.close();
        BufferedReader br = new BufferedReader(file);  
        while ((data=br.readLine())!=null) {
      
            System.out.println(data);
          }     
       
        
        br.read();
        br.close();  
       
   	
   }
}
