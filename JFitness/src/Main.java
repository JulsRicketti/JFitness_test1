import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import javax.print.DocFlavor.URL;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String file_name = ".\\src\\test"; //relative file path 
		
		try{
			ReadFile file = new ReadFile(file_name);
			String[] aryLines = file.OpenFile();
			
			for(int i=0; i<aryLines.length; i++){
				System.out.println(aryLines[i]);
			}
		}
		catch (IOException e){
			System.out.println (e.getMessage());
		}
	}
	
}
