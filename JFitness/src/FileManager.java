import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class FileManager {

	private String path;
	private boolean appendToFile = false;
	
	public FileManager(String path){
		this.path = path;
	}
	
	public FileManager(String path, boolean appendToFile){
		this.path = path;
		this.appendToFile = appendToFile;
	}
	
	public String[] OpenFile() throws IOException{
		FileReader read = new FileReader(path);
		BufferedReader textReader = new BufferedReader(read);
		
		int numberOfLines = this.readLines();
		String[] textData = new String[numberOfLines];
		
		for(int i=0; i<numberOfLines; i++){
			textData[i]=textReader.readLine();
		}
		
		textReader.close();
		return textData;
	}
	
	int readLines() throws IOException{
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);
		
		String aLine;
		int numberOfLines=0;
		
		while((aLine=bf.readLine())!=null){
			numberOfLines ++;
		
		}
		
		bf.close();
		return numberOfLines;
	}
	
	//To write on the file:
	public void writeToFile (String textLine) throws IOException{
		FileWriter write = new FileWriter(path, appendToFile);
		PrintWriter print_line = new PrintWriter(write);
		
		print_line.printf("%s" + "%n", textLine);
		print_line.close();
	}
	

}
