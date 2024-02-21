package BasicsIOEx.Q9;
import java.util.*;
import java.io.*;
public class Q9 {
        public static void main(String[] args)throws Exception {
            File file = new File("C:\\Users\\baps\\Desktop\\Java\\Task_1\\BasicsIO\\username.csv");

        	Scanner sc = new Scanner(file);  
        	sc.useDelimiter(",");
        	while (sc.hasNext()) {
        	System.out.print(sc.next());
        	}   
        	sc.close();
		}
       
}
