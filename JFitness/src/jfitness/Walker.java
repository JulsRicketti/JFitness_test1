package jfitness;

import java.io.IOException;

public class Walker implements Strategy{
	
	public class Evaluation{
		public static final String VERY_GOOD = "VERY_GOOD";
		public static final String GOOD = "GOOD";
		public static final String NORMAL = "NORMAL";
		public static final String BAD = "BAD";
		public static final String INCONCLUSIVE = "INCONCLUSIVE";
	}
	
	final String recommendFileName = ".\\Files\\walkerRecommend"; //file where all the walker recommendations will be stored
	final String analyseFileName = ".\\Files\\walkerAnalyse";
	final int DAYS_OF_WEEK = 7;
	final int DEFAULT_MINIMUM_DISTANCE_WEEK= 5500;
	
	int DEFAULT_MINUMUM_DAILY_WALK_TIME = 30; //30 minutes
	
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
		
	
	public double recommend() throws IOException{
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
		System.out.println("Distance walked: "+time*60*(user.getWalkingSpeed())+" meters");
		return (time*60*(user.getWalkingSpeed()));
		
	}
	
	public void receiveResults(User user, int time) throws IOException{
		FileManager analyseFile = new FileManager(analyseFileName, true);
		analyseFile.writeToFile(Integer.toString(time));
		analyse(user, time);
	}
	
	//the objective is to try and make people walk at the very least 5.5km every 5 days
	//when using time as the parameter, we will take into consideration the average walking speed of a human
	/*How it works for now:
	 * If the user has already walked the needed distance in the passed 7 days, just divided the weekly distance by 7
	 * Perhaps I still need to find a better way to do this.*/
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
			if(((DAYS_OF_WEEK-1)*averageOfDistance) > minimumDistanceWeek){
				distanceLeft = minimumDistanceWeek/DAYS_OF_WEEK; //we restart in order to motivate the user to keep doing it
			}
			else
				distanceLeft = minimumDistanceWeek - ((DAYS_OF_WEEK-1)*averageOfDistance);
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

	@Override
	public void analyse() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveResults() {
		// TODO Auto-generated method stub
		
	}
	
	//when the person wants a daily mode to evaluate their walk
	String dailyWalkerEvaluator(int time){
		if (time<badInterval){
			return Evaluation.BAD;
		}
		if(time>normalInterval[0] && time<normalInterval[1]){
			return Evaluation.NORMAL;
		}
		if(time>goodInterval[0] && time<goodInterval[1]){
			return Evaluation.GOOD;
		}
		if(time>excellentInterval){
			return Evaluation.VERY_GOOD;
		}
		return Evaluation.INCONCLUSIVE;
	}
	
	String weeklyWalkerEvaluator () throws IOException{
		FileManager analyseFile = new FileManager(analyseFileName);
		String[] data = analyseFile.OpenFile();
		double weekDistance =0;
		
		if(data.length>=DAYS_OF_WEEK){
			for (int i=1; i<DAYS_OF_WEEK; i++){ //we start at 1 because we are going backwards in the arrays
				weekDistance += Double.parseDouble(data[data.length-i]);
			}
		}
		else{
			return Evaluation.INCONCLUSIVE;
		}
		
		if(weekDistance<DEFAULT_MINIMUM_DISTANCE_WEEK)
			return Evaluation.BAD;
		if(weekDistance>=DEFAULT_MINIMUM_DISTANCE_WEEK && weekDistance<DEFAULT_MINIMUM_DISTANCE_WEEK*1.5)
			return Evaluation.NORMAL;
		if(weekDistance>DEFAULT_MINIMUM_DISTANCE_WEEK*1.5 && weekDistance<DEFAULT_MINIMUM_DISTANCE_WEEK*2)
			return Evaluation.GOOD;
		if(weekDistance>DEFAULT_MINIMUM_DISTANCE_WEEK*2)
			return Evaluation.VERY_GOOD;
		return Evaluation.INCONCLUSIVE;
	}

	
}
