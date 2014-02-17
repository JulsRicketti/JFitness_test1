package jfitness;

import java.io.IOException;

public interface Strategy {
	
	void receiveResults() throws IOException;
	
	abstract void analyse() throws IOException;
	abstract double recommend() throws IOException;
	abstract void report() throws IOException;
	

}
