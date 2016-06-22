package programmingAssignmentE;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class EvolutionaryAlgorithm {
	
	private City[] cities;
	private ArrayList<Individual> population;
	private int muo;
	private int lamda;
	private int p;
	private double[][] distances;
	
	/**
	 * create an instance of the algorithm to solve this custom Traveling Sales man 
	 * @param muo
	 * the number of the parents to keep
	 * @param lamda
	 * the number of the offsprings to discard
	 * @param p
	 * the max size of the population
	 * @throws FileNotFoundException 
	 */
	public EvolutionaryAlgorithm(int muo, int lamda, int p, String citiesFile) throws FileNotFoundException {
		super();
		this.muo = muo;
		this.lamda = lamda;
		this.p = p;
		this.population = new ArrayList<Individual>(p);
		this.readCitiesFromFile(citiesFile);
		createInitialPopulation(this.muo + this.lamda);
	}
	
	/**
	 * Reads the cities from the file, using the City static helper method
	 * @param citiesFile
	 * the file containing the data
	 * @throws FileNotFoundException
	 * throws if the file is not found
	 */
	public void readCitiesFromFile(String citiesFile) throws FileNotFoundException
	{
		this.cities = City.readCitiesFromFile(citiesFile).toArray(new City[0]);
		this.distances = new double[cities.length][cities.length];
		calculateDistanceMatrix();
	}
	
	/**
	 * Calculate the distance matrix based on the euclidean distance between the cities
	 */
	private void calculateDistanceMatrix()
	{
		for(int i=0;i<this.cities.length;i++)
			for(int j=i;j<this.cities.length;j++)
				if(i==j)
					this.distances[i][j]=0;
				else
				{
					double dist=cities[i].calculateDistance(cities[j]);
					this.distances[i][j]=dist;
					this.distances[j][i]=dist;
				}
	}
	
	/**
	 * Creates the initial population of the algorithm
	 * @param count
	 * the count of the individuals of the initial population 
	 */
	public void createInitialPopulation(int count)
	{
		if(count>this.p)
			count=this.p;
		for(int i=0;i<count;i++)
			this.population.add(Individual.createRandom(this.cities.length));
	}
	
	/**
	 * the external Selection process and the parent selection
	 */
	public void selectionProcess()
	{
		// sort the list 
		Collections.sort(this.population, Collections.reverseOrder(new Comparator<Individual>()
	    {
	        public int compare(Individual i1, Individual i2)
	        {
	            return (i1.getFitness()).compareTo(i2.getFitness());
	        }
	    }));
		this.printSummary();
		try{
		for(int i=0;i<this.lamda;i++)
			this.population.remove(this.muo-1);
		}catch(java.lang.IndexOutOfBoundsException ex){}
	}
	
	/**
	 * apply the inheritance operators on the population
	 * k=1 only copy and let the mutation do the rest.
	 */
	public void inheritance()
	{
		int offsprings=(int) Math.round((double)this.lamda/(double)this.muo);
		for(int i=0;i<this.muo;i++)
		{
			for(int j=0;j<offsprings && this.population.size() < this.p;j++)
				this.population.add(this.population.get(i).clone());
		}
	}
	
	/**
	 * evaluate the fitness for each individual in the population
	 */
	public void evaluateFitness()
	{
		for(Individual ind:this.population)
		{
			double fitness=0;
			for(int i=0;i<ind.genome.length-1;i++)
				fitness+=this.cities[ind.genome[i]].calculateDistance(this.cities[ind.genome[i+1]]);
			ind.setFitness(fitness);
		}
	}
	
	/**
	 * Apply the mutation on each offspring of the population
	 */
	public void applyMutation()
	{
		int i=this.population.size()-1;
		for(int j=0;j<this.lamda;j++)
			this.population.get(i--).mutate();
	}
	
	/**
	 * print the summary of the population
	 */
	public void printSummary()
	{
		double mean=0;
		for(Individual ind:this.population)
			mean+=ind.fitness;
		mean /= this.population.size();
		double max = this.population.get(0).getFitness();
		double min = this.population.get(this.population.size()-1).getFitness();
		System.out.println("best: " + max +
				" ,mean: " + mean + " ,least: " + min);
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter("plotthis.txt",true));
			writer.write(String.format("%s\t%s\t%s",
						Double.toString(max),Double.toString(mean),Double.toString(min)));
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
	 * run the algorithm
	 * @throws IOException 
	 * IO exception if another processing is controlling the file of the results
	 */
	public void run() throws IOException
	{
		Files.deleteIfExists(Paths.get("plotthis.txt"));
		Random r=new Random();
		int limit = r.nextInt(300)+100;
		int i=0;
		while(i++<limit)
		{
			this.evaluateFitness();
			this.selectionProcess();
			this.inheritance();
			this.applyMutation();
		}
		this.population.get(this.population.size()-1).printFormated(this.cities);
	}
	
	/**
	 * RUN THE GNU PLOT COMMAND TO SEE THE LEST,MEAN, AND BEST INDIVIDUALS EACH GENERATION
	 * @throws IOException
	 * in the execution of the command line (in case the gnuplot is not in the environment variables)
	 */
	public void runPlotCommand() throws IOException
	{
		String command="gnuplot -p \"plot-population.txt\"";
		Runtime commandPrompt = Runtime.getRuntime();
		commandPrompt.exec(command);
	}
}
