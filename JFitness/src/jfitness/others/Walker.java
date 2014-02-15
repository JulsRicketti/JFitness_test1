package jfitness.others;

import java.io.IOException;

public class Walker {
	
	public class Evaluation{
		public static final String VERY_GOOD = "VERY_GOOD";
		public static final String GOOD = "GOOD";
		public static final String NORMAL = "NORMAL";
		public static final String BAD = "BAD";
	}
	
	String recommendFileName = ".\\Files\\walkerRecommend"; //file where all the walker recommendations will be stored
	String analyseFileName = ".\\Files\\walkerAnalyse";

	
	int time;
	int recommendation;
	String result;
	//these are the intervals used to define what is a good walk
	int badInterval =  30;
	int[] normalInterval = {30, 60};
	int[] goodInterval = {60, 120};
	int excellentInterval = 120;
		
	void walkerEvaluator(int time){
		if (time<badInterval){
			result = Evaluation.BAD;
		}
		if(time>normalInterval[0] && time<normalInterval[1]){
			result = Evaluation.NORMAL;
		}
		if(time>goodInterval[0] && time<goodInterval[1]){
			result = Evaluation.GOOD;
		}
		if(time>excellentInterval){
			result = Evaluation.VERY_GOOD;
		}
	}
	
	void recommend() throws IOException{
		FileManager file = new FileManager(recommendFileName);
		String []data = file.OpenFile();
		
		recommendation = Integer.parseInt(data[data.length-1]);
	}
	
	public void storeResults(int timeWalked) throws IOException{
		FileManager file = new FileManager(analyseFileName);
		file.writeToFile(Integer.toString(timeWalked));
	}
	
	//the objective is to try and make people walk at the very least 5.5km every 5 days
	//when using time as the parameter, we will take into consideration the average walking speed of a human
	public void analyse() throws IOException{
		FileManager file = new FileManager(analyseFileName);
	}
}
