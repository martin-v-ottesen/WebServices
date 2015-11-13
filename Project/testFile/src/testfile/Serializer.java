package testfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializer {
    static File outputFile;
    
    public static void main (String args[]) {
           outputFile = new File("FlightData");
           
           String s = "in";
        int i = 0;
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
                Serializer serializer = new Serializer();
                serializer.serializeAddress("wall street" + i++, "united state"+ 1);
            }

	   
        }
   }

   public void serializeAddress(String street, String country){
	   
	   Address address = new Address();
	   address.setStreet(street);
	   address.setCountry(country);
	   
	   try{
		   
		FileOutputStream fout = new FileOutputStream(outputFile);
		ObjectOutputStream oos = new ObjectOutputStream(fout);   
		oos.writeObject(address);
		oos.close();
		System.out.println("Done");
		   
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
   }
}