package jfitness;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class User {

	
	public class Objectives{
		public static final String WALK = "WALK";
		public static final String CALORIES = "CALORIES";
		public static final String DISTANCE_RUN = "DISTANCE_RUN";
		public static final String VELOCITY_RUN = "VELOCITY_RUN";
		public static final String MARATHON_RUN = "MARATHON_RUN";
	}
	
	public class Sex{
		public static final String FEMALE = "FEMALE";
		public static final String MALE = "MALE";
	}
	
	private int currentWeight;
	private int initialWeight;
	private int age;
	private double walkingSpeed;
	private int calorieIntake;
	private String sex;
	private List <String> objectives = new ArrayList<String>();
	
	
	
	public User(int currentWeight, int initialWeight, int age, List<String> objectives){
		this.currentWeight = currentWeight;
		this.initialWeight = initialWeight;
		this.objectives = objectives;
	}
	
	//constructer for testing purposes
	public User(){
		this.currentWeight = 63;
		this.initialWeight = 63;
		this.age = 23;
		this.sex = Sex.FEMALE;
		this.setWalkingSpeed();
		objectives.add(Objectives.CALORIES);
		objectives.add(Objectives.WALK);
	}
	
	public void updateUserFile(String fileName){
		try{
			FileManager data = new FileManager(fileName, true);
			
			//since we can only have one user for this file, we must clear it always 
			//before updating it
			data.clearFile();
			
			data.writeToFile(Integer.toString(currentWeight));
			data.writeToFile(Integer.toString(initialWeight));
			data.writeToFile(Integer.toString(age));
			data.writeToFile(sex);
			data.writeToFile(Double.toString(walkingSpeed));
			
			for(int i=0; i<objectives.size(); i++){
				data.writeToFile(objectives.get(i));
			}
		}
		catch(IOException e){
			System.out.println (e.getMessage());
		}
	}
	
	
	public int getcurrentWeight() {
		return currentWeight;
	}
	public void setcurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}
	public int getinitialWeight() {
		return initialWeight;
	}
	public void setinitialWeight(int initialWeight) {
		this.initialWeight = initialWeight;
	}
	public int getage() {
		return age;
	}
	public void setage(int age) {
		this.age = age;
	}
	public List<String> getobjectives() {
		return objectives;
	}
	public void setobjectives(List<String> objectives) {
		this.objectives = objectives;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getCalorieIntake() {
		return calorieIntake;
	}

	//based on: https://www.weightlossresources.co.uk/logout/calorie_intake.htm
	public void setCalorieIntake(int calorieIntake) {
		if(sex.equals(Sex.MALE))
			calorieIntake = 2550;
		else
			calorieIntake = 1940;
	}

	public double getWalkingSpeed() {
		return walkingSpeed;
	}

	public void setWalkingSpeed() {
		//initially everything is in cm.
		if(sex.equals(Sex.FEMALE)){
			if(age<=20)
				walkingSpeed=140.7;
			if(age>20 && age<=30)
				walkingSpeed=141.5;
			if(age>30 && age<=40)
				walkingSpeed=139.1;
			if(age>40 && age<=50)
				walkingSpeed=139.5;
			if(age>50 && age<=60)
				walkingSpeed=129.6;
			if(age>60)
				walkingSpeed = 127.2;
		}
		else{
			if(age<=20)
				walkingSpeed=139.3;
			if(age>20 && age<=30)
				walkingSpeed=145.8;
			if(age>30 && age<=40)
				walkingSpeed=146.2;
			if(age>40 && age<=50)
				walkingSpeed=139.3;
			if(age>50 && age<=60)
				walkingSpeed=135.9;
			if(age>60)
				walkingSpeed = 133.0;
		}
		walkingSpeed = walkingSpeed/100; //we convert it to meters
	}
	

	
	

}
