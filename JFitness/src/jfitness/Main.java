package jfitness;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import javax.imageio.spi.RegisterableService;
import javax.print.DocFlavor.URL;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String fileName = ".\\Files\\user"; //relative file path 
		
		User user = new User();
		user.updateUserFile(fileName);

		/*Issue: there is only one line that is being written both on the walk and recommend files!*/
		//Where to start checking the error from:
		//analyse function and calculateDistanceWalked functions!
		Walker walker = new Walker();
		double recommendation = walker.recommend();
		System.out.println("Please walk "+recommendation+ " meters today");
		walker.registerWalk(user, 30);
		recommendation = walker.recommend();
		System.out.println("Please walk "+recommendation+ " meters today");
		
		
//		try{
//			FileManager file = new FileManager(fileName);
//			String[] aryLines = file.OpenFile();
//			
//			for(int i=0; i<aryLines.length; i++){
//				System.out.println(aryLines[i]);
//			}
//		}
//		catch (IOException e){
//			System.out.println (e.getMessage());
//		}
//		
//		//testing to write a file:
//		try{
//			FileManager data = new FileManager(fileName, true);
//			data.writeToFile("Lets try this again, yes?");
//		}
//		catch(IOException e){
//			System.out.println (e.getMessage());
//		}
	}
	
	void createUser(){

	}
	
}
