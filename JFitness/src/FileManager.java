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
		FileReader fileToRead = new FileReader(path);
		BufferedReader bf = new BufferedReader(fileToRead);
		
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
		PrintWriter printLine = new PrintWriter(write);
		
		printLine.printf("%s" + "%n", textLine);
		printLine.close();
	}
	
	public void clearFile() throws IOException{
		FileWriter write = new FileWriter(path, false);
		PrintWriter printLine = new PrintWriter(write);
		
		printLine.print("");
		printLine.close();
	}
	

}
