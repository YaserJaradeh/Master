package programmingAssignmentC;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents the core equations and structure
 * of a modified prey-predator system as purposed in the assignment
 */
public class PPSystem {
	
	/**
	 * this is the old X point X(i-1)
	 */
	double oldX;
	
	/**
	 * this is the old Y point Y(i-1)
	 */
	double oldY;
	
	/**
	 * this is the new X point X(i)
	 */
	double newX;
	
	/**
	 * this is the new Y point Y(i)
	 */
	double newY;
	
	/**
	 * the parameter a
	 */
	double a;
	
	/**
	 * the parameter b
	 */
	double b;
	
	/**
	 * the parameter c
	 */
	double c;
	
	/**
	 * the parameter d
	 */
	double d;
	
	/**
	 * the parameter e
	 */
	double e;
	
	/**
	 * the parameter f
	 */
	double f;
	
	/**
	 * the parameter g
	 */
	double g;
	
	/**
	 * the parameter h
	 */
	double h;
	
	/**
	 * the mean of X values
	 */
	double meanX;
	
	/**
	 * the mean of Y values
	 */
	double meanY;
	
	/**
	 * list of values containing the x(i) over the time steps
	 */
	ArrayList<Double> xs;
	
	/**
	 * list of values containing the y(i) over the time steps
	 */
	ArrayList<Double> ys;
	
	/**
	 * Writer object to write data to the files
	 */
	BufferedWriter writer;
	
	/**
	 * the current time step number
	 */
	int step;
	
	/**
	 * initialize the variables with values for the first set of equations
	 * set the new x and y to zeros
	 * put the values for x0 and y0 and the parameters
	 * a, b, c, d, e, f to make the system oscillate in a stable manor
	 */
	private void initVariablesFirstCase()
	{
		newX=newY=0;
		xs=new ArrayList<Double>();
		ys=new ArrayList<Double>();
		step=0;
		//oldX=20;
		//oldY=10;
		//a=1.3;
		//b=-0.54;
		//c=-0.6;
		//d=0.01;
		//e=-0.06;
		//f=0.07;
		a=2;
		b=-0.1;
		c=0.6;
		d=0.1;
		e=-0.005;f=-0.005;oldX=300;oldY=100;
		
	}
	
	/**
	 * initialize the variables with values for the second set of equations
	 * set the new x and y to zeros
	 * put the values for x0 and y0 and the parameters
	 * a, b, c, d, e, f, g, h to make the system oscillate in a stable manor 
	 */
	private void initVariablesSecondCase()
	{
		newX=newY=0;
		xs=new ArrayList<Double>();
		ys=new ArrayList<Double>();
		step=0;
		oldX=10;
		oldY=10;
		a=0.8;
		b=-0.54;
		c=-0.46;
		d=-0.01;
		e=-0.06;
		f=0.007;
		g=-0.0014;
		h=0.0014;
	}
	
	/**
	 * calculate the new values for the system in the first set of Equations
	 * calculate the new x and y for the system in every step
	 */
	private void calculateNewValueFirstCase()
	{
		//x(i +1) = x(i) + a * x(i) + b * y(i) + e * x(i) * x(i)
		//y(i +1) = y(i) + c * x(i) + d * y(i) + f * y(i) * y(i)
		newX = oldX + (a * oldX) + (b * oldY) + (e * oldX * oldX) ;
		newY = oldY + (c * oldX) + (d * oldY) + (f * oldY * oldY) ;
	}
	
	/**
	 * calculate the new values for the system in the second set of Equations
	 * calculate the new x and y for the system in every step
	 */
	private void calculateNewValueSecondCase()
	{
		//x(i +1) = x(i) + a * x(i) +b * y(i) +e * x(i) * x(i) +g * x(i) * y(i)
		//y(i +1) = y(i) + c * x(i) +d * y(i) +f * y(i) * y(i) +h * x(i) * y(i)
		newX = oldX + (a * oldX) + (b * oldY) + (e * oldX * oldX) + (g * oldX * oldY) ;
		newY = oldY + (c * oldX) + (d * oldY) + (f * oldY * oldY) + (h * oldX * oldY) ;
	}
	
	/**
	 * print the new data to the console
	 * prints the new x and y and the parameters a,b,c,d,e,f
	 */
	public void printData()
	{
		System.out.println(
				String.format("Prey (X): %s, Predator (Y): %s",
						Double.toString(newX),Double.toString(newY)));
		/*System.out.println(
				String.format("a: %f, b: %f, c: %f, d: %f, e: %f, f: %f",
						a,b,c,d,e,f));*/
	}
	
	/**
	 * replace the old variable with the new variable
	 * to calculate the next time step
	 * this is done for both x and y (oldX <- newX)
	 * and (oldY <- newY)
	 */
	private void exchangeOldAndNewData()
	{
		oldX=newX;
		oldY=newY;
	}
	
	/**
	 * run the first prey-predator system for the given time steps
	 * @param steps
	 * the number of time steps to run the system
	 */
	public void runSystem(int steps)
	{
		writeHeaderToFile(1);
		initVariablesFirstCase();
		for(int i=0;i<steps;i++)
		{
			calculateNewValueFirstCase();
			printData();
			writeToFileFirstCase();
			exchangeOldAndNewData();
			if(steps>25)//this is when the system stabalizes
				addStepToList();
		}
		calculateMeans();
		runGnuPlot(true,1);
		runGnuPlot(false,1);
	}
	
	/**
	 * run the altered prey-predator system for the given time steps
	 * @param steps
	 * the number of time steps to run the system
	 */
	public void runNewSystem(int steps)
	{
		writeHeaderToFile(2);
		initVariablesSecondCase();
		for(int i=0;i<steps;i++)
		{
			calculateNewValueSecondCase();
			printData();
			writeToFileSecondCase();
			exchangeOldAndNewData();
			if(steps>25)//this is when the system stabalizes
				addStepToList();
		}
		calculateMeans();
		runGnuPlot(true,2);
		runGnuPlot(false,2);
	}
	
	/**
	 * writes the results to a file called state1.txt
	 */
	private void writeToFileFirstCase()
	{
		writeToFile("state1.txt");
	}
	
	/**
	 * writes the results to a file called state2.txt
	 */
	private void writeToFileSecondCase()
	{
		writeToFile("state2.txt");
	}
	
	/**
	 * writes the results to a file
	 * to plot them using the gnuplot program
	 * @param file
	 * the name of the file
	 */
	private void writeToFile(String file)
	{
		try
		{
			writer = new BufferedWriter(new FileWriter(file,true));
			writer.write(String.format("%s\t%s\t%s",
						Integer.toString(++step),Double.toString(newX),Double.toString(newY)));
			writer.newLine();
			writer.flush();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Writes the header of the data file to the file
	 * just to put the names of each column in the file in a comment
	 * not required for the program to run correctly but gives a nice touch
	 */
	private void writeHeaderToFile(int file)
	{
		try
		{
			writer = new BufferedWriter(new FileWriter("state"+Integer.toString(file)+".txt",false));
			writer.write("#Step\tPrey\tPredator");
			writer.newLine();
			writer.flush();
			writer.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	
	}
	
	/**
	 * run the GNUPlot program on the script file to plot the data
	 * @param isTemporal
	 * boolean if true plots the temporal development
	 * otherwise draws the phase diagram. 
	 */
	private void runGnuPlot(boolean isTemporal,int file)
	{
		String command="";
		if(isTemporal)
			command="gnuplot -p \"temporal-script"+Integer.toString(file)+".txt\"";
		else
			command="gnuplot -p \"phase-script"+Integer.toString(file)+".txt\"";
		Runtime commandPrompt = Runtime.getRuntime();
		//synchronized (commandPrompt)
		//{
			try {
				commandPrompt.exec(command);
				//commandPrompt.wait();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		//}
	}
	
	/**
	 * adds the x and y of this step to the list to be able
	 * to track the data and calculate the mean of each variable
	 */
	private void addStepToList()
	{
		xs.add(oldX);
		ys.add(oldY);
	}
	
	/**
	 * calculate the means of x (prey) and y (predator) of the system
	 */
	private void calculateMeans()
	{
		meanX=0;
		for(double d:xs)
			meanX+=d;
		meanX/=xs.size();
		meanY=0;
		for(double d:ys)
			meanY+=d;
		meanY/=ys.size();
		System.out.println(
				String.format("mean X: %f, mean Y: %f",
						meanX,meanY));
	}
}
