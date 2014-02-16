package jfitness;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Runner implements Strategy {

	public class Evaluation{
		public static final String VERY_EASY = "VERY_EASY";
		public static final String EASY = "EASY";
		public static final String NORMAL = "NORMAL";
		public static final String HARD = "HARD";
		public static final String VERY_HARD = "VERY HARD";
	}
	
	String recommendFileName = ".\\Files\\runnerRecommend"; //file where all the runner recommendations will be stored
	String analyseFileName = ".\\Files\\runnerAnalyse";
	
	int runRecommendation, walkRecommendation;
	String evaluation; //the user needs to evaluate how hard the recommendation was
	
	public Runner(){

	}
	
	public void getRecommendation()  throws IOException{
	
		FileManager file = new FileManager(recommendFileName);
		//if the file is empty, it considers that the user has never run before
		if(file.isEmpty()){
			runRecommendation = 1;
			walkRecommendation=2;
			file.writeToFile(Integer.toString(runRecommendation));
			file.writeToFile(Integer.toString(walkRecommendation));
		}
		else{
			String []data;
			data = file.OpenFile();
			
			//the recommendation made are the two last lines of the file.
			runRecommendation = Integer.parseInt(data[data.length-2]);
			walkRecommendation = Integer.parseInt(data[data.length-1]);
			
			System.out.println(runRecommendation);
			System.out.println(walkRecommendation);
		}
		
	}
	
	//this will store the results of the values that the user actually ran
	//the user may also evaluate the exercise he just did
	public void storeResults(int ran, int walked, Evaluation evaluation) throws IOException{
		FileManager file = new FileManager(analyseFileName);
		
		file.writeToFile(Integer.toString(ran));
		file.writeToFile(Integer.toString(walked));
		file.writeToFile(evaluation.toString());
	}

	//the same function but without an evaluation
	//in this case, it is considered default, which will be interpreted as normal 
	public void storeResults(int ran, int walked) throws IOException{
		FileManager file = new FileManager(analyseFileName);
		
		file.writeToFile(Integer.toString(ran));
		file.writeToFile(Integer.toString(walked));
		file.writeToFile(Evaluation.NORMAL);
	}

	
	public void analyse() throws IOException{
		FileManager file = new FileManager(analyseFileName);
		String []data;
		data = file.OpenFile();
		
		int lastRun = Integer.parseInt(data[data.length-3]);
		int lastWalk = Integer.parseInt(data[data.length-2]);
		String lastEvaluation = data[data.length-1];
		
		if(lastRun >= this.runRecommendation && lastEvaluation == Evaluation.NORMAL){
			
		}
		
	}

	@Override
	public double recommend() {
		// TODO Auto-generated method stub
		return 0.0; //just added until the method properly is implemented
		
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
