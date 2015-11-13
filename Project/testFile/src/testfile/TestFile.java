/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfile;

import com.sun.org.apache.xml.internal.security.encryption.Serializer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author Martin
 */
public class TestFile {
    public static void main(String[] args) throws IOException {
        String s = "in";
        int i = 0;
        File outputFile = new File("FlightData");
        while (!s.equals("exit")){
            System.out.println("Enter something here : "); 
            try{
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                s = bufferRead.readLine();    
            }
            catch(IOException e){
                    e.printStackTrace();
            }

            if (s.equals("in")){
                //TestObject data = new TestObject(i++, s + i);
//                System.out.println(data);
//                FileOutputStream  output = new FileOutputStream (outputFile);
//                ObjectOutputStream oos = new ObjectOutputStream(output); 
//                oos.writeObject(data);
//		oos.close();
            } else if (s.equals("out")){
                
            }
        }
    }    
}
