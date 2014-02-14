package jfitness.others;

import java.io.IOException;

public class CalorieCounter {
	
	String fileName ="";
	double met;
	double calories;
	
	int calculateAverageVelocity(int distance, int time){
		return (distance/time);
	}
	
	//All of this data was defined by the compedium of physical activities
	double defineMET(float velocity){
		if(velocity<3.2)
			return 2.0;
		if(velocity>=3.2 && velocity<4.5)
			return 3.0;
		if(velocity>=4.5 && velocity<5.2)
			return 3.5;
		if(velocity>=5.2 && velocity<6.4)
			return 5.0;
		if(velocity>=6.4 && velocity<7.2)
			return 7.0;
		if(velocity>=7.2 && velocity<8.1)
			return 8.3;
		if(velocity>=9.7 && velocity<8.1)
			return 8.3;
		if(velocity>=8.1 && velocity<9.7)
			return 9.8;
		if(velocity>=9.7 && velocity<11.3)
			return 11.0;
		if(velocity>=11.3 && velocity<12.9)
			return 11.8;
		if(velocity>=12.9 && velocity<14.5)
			return 12.8;
		if(velocity>=14.5 && velocity<16.1)
			return 14.5;
		if(velocity>=16.1 && velocity<17.8)
			return 16.0;
		if(velocity>=17.8 && velocity<19.3)
			return 19.0;
		if(velocity>=19.3 && velocity<20.9)
			return 19.8;
		if(velocity>=20.9 && velocity<22.5)
			return 23.0;
		if(velocity>=20.9)
			return 23.0;
		
		return 0;
	}
	
	double calculateCalories(User user, double met, int time){
		return (met*(user.getcurrentWeight())*time);
	}
	
	void updateCalorieFile(){
		try{
			FileManager data = new FileManager(fileName, true);
			data.writeToFile(Double.toString(this.calories));

		}
		catch(IOException e){
			System.out.println (e.getMessage());
		}
	}
	
}
