package jfitness;

import java.io.IOException;

public interface Strategy {
	
	void analyse() throws IOException;
	double recommend() throws IOException;
	void report() throws IOException;
	

}
