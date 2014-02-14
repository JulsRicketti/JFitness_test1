package jfitness.others;

public class Walker {
	
	String recommendFileName = ".\\Files\\walkerRecommend"; //file where all the walker recommendations will be stored
	String analyseFileName = ".\\Files\\walkerAnalyse";

	
	int time;
	String result;
	//these are the intervals used to define what is a good walk
	int badInterval =  30;
	int[] okInterval = {30, 60};
	int[] goodInterval = {60, 120};
	int excellentInterval = 120;
		
	void walkerEvaluator(){
		if (time<badInterval){
			result = "bad";
		}
		if(time>okInterval[0] && time<okInterval[1]){
			result = "ok";
		}
		if(time>goodInterval[0] && time<goodInterval[1]){
			result = "good";
		}
		if(time>excellentInterval){
			result = "excellent";
		}
	}
}
