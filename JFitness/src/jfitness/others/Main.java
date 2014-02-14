package jfitness.others;
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

		Runner runner = new Runner();
		runner.getRecommendation();
		
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
