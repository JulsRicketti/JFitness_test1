import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class User {

	private int currentWeight;
	private int initialWeight;
	private int age;
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
		objectives.add("Calories");
		objectives.add("Walk");
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

	
	

}
