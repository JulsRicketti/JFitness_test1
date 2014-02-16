package jfitness;

import java.io.IOException;

public class Walker {
	
	public class Evaluation{
		public static final String VERY_GOOD = "VERY_GOOD";
		public static final String GOOD = "GOOD";
		public static final String NORMAL = "NORMAL";
		public static final String BAD = "BAD";
	}
	
	final String recommendFileName = ".\\Files\\walkerRecommend"; //file where all the walker recommendations will be stored
	final String analyseFileName = ".\\Files\\walkerAnalyse";
	final int DAYS_OF_WEEK = 7;
	final int DEFAULT_MINIMUM_DISTANCE_WEEK= 5500;
	
	//the minimum distance a week is the default! The user may be able to change it if he wants too!
	double minimumDistanceWeek = DEFAULT_MINIMUM_DISTANCE_WEEK; //we make the distance in meters
	double recommendation;
	String result;
	
	//these are the intervals used to define what is a good walk
	int badInterval =  30;
	int[] normalInterval = {30, 60};
	int[] goodInterval = {60, 120};
	int excellentInterval = 120;
	
	String message = " "; //variable to send messages to the user
		
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
	
	double recommend() throws IOException{
		FileManager file = new FileManager(recommendFileName, true);
		if(file.isEmpty()){
			recommendation = minimumDistanceWeek/DAYS_OF_WEEK;
			file.writeToFile(Double.toString(recommendation));
			return recommendation;
		}
		else{
			String []data = file.OpenFile();
			recommendation = Double.parseDouble(data[data.length-1]);
			return recommendation;
		}
	}
	
	public double calculateDistanceWalked(int time, User user){
		//Remember:
		//The time is in minutes
		//The original speed is in m/s
		//I dont think the 100 is correct...
		return (time*60*(user.getWalkingSpeed())/100);
		
	}
	
	public void registerWalk(User user, int time) throws IOException{
		FileManager analyseFile = new FileManager(analyseFileName, true);
		analyseFile.writeToFile(Integer.toString(time));
		analyse(user, time);
	}
	
	//the objective is to try and make people walk at the very least 5.5km every 5 days
	//when using time as the parameter, we will take into consideration the average walking speed of a human
	void analyse(User user, int time) throws IOException{
		FileManager analyseFile = new FileManager(analyseFileName, true);
		FileManager recommendFile = new FileManager(recommendFileName, true);
		String[] data = analyseFile.OpenFile();
		
		double distanceLeft=0;
		double totalDistanceWalked=0;
		double averageOfDistance=0;

		if(data.length<DAYS_OF_WEEK){
			for(int i=0; i<data.length; i++)
				totalDistanceWalked += calculateDistanceWalked(Integer.parseInt(data[i]), user);
			averageOfDistance = totalDistanceWalked/data.length;
			
			//we need to try to complete 5.5 km or 5500 meters every 7 days AT LEAST!!
			int daysLeft = DAYS_OF_WEEK-data.length;
			distanceLeft = minimumDistanceWeek - (data.length*averageOfDistance);
			recommendFile.writeToFile(Double.toString(distanceLeft/daysLeft));
		}
		else{
			for (int i=0; i<DAYS_OF_WEEK-1; i++){
				System.out.println(Integer.parseInt(data[(data.length-1)-(i)]));
				totalDistanceWalked += calculateDistanceWalked(Integer.parseInt(data[(data.length-1)-(i)]), user); //we get the last distances registered in the file
			}
			averageOfDistance = totalDistanceWalked/(DAYS_OF_WEEK-1);
			distanceLeft = minimumDistanceWeek - ((DAYS_OF_WEEK-1)*averageOfDistance);
			System.out.println("minimum distance week: "+minimumDistanceWeek);
			System.out.println("Average of distance: "+averageOfDistance);
			System.out.println("distance left: "+distanceLeft);
			recommendFile.writeToFile(Double.toString(distanceLeft));
		}
	}

	public double getMinimumDistanceWeek() {
		return minimumDistanceWeek;
	}

	public void setMinimumDistanceWeek(double minimumDistanceWeek) {
		if(minimumDistanceWeek<DEFAULT_MINIMUM_DISTANCE_WEEK){
			message = "The minimum distance a week must be more than 5500meters, in order to maintain a healthy lifestyle";
		}
		else
			this.minimumDistanceWeek = minimumDistanceWeek;
	}
	
	
}
