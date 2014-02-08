import java.util.ArrayList;
import java.util.List;


public class User {

	private int currentWeight;
	private int initialWeight;
	private int age;
	private List <String> objectives = new ArrayList<String>();
	
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
