package programmingAssignmentD;

import java.util.ArrayList;

public class EvolutionaryAlgorithm 
{	
	/**
	 * the population count
	 */
	private int P;
	
	/**
	 * the number of components of each genome
	 */
	private int L;
	
	/**
	 * the population of individuals
	 */
	private ArrayList<Individual> population;
	
	/**
	 * the bound of the mutation distribution
	 */
	private double bound;

	
	/**
	 * Create an instance of the evolutionary algorithm
	 * @param populationSize
	 * the population size (in our task P=2)
	 * @param genomeLenght
	 * the length of the genome components
	 * @param bound
	 * the upper and lower bound of the random distribution
	 */
	public EvolutionaryAlgorithm(int populationSize, int genomeLenght, double bound) {
		super();
		this.P = populationSize;
		this.L = genomeLenght;
		this.bound = bound;
		this.population=new ArrayList<Individual>(populationSize);
	}
	
	/**
	 * Creates the initial population using random generation
	 */
	private void createInitialPopulation()
	{
		for(int i=0;i<this.P;i++)
		{
			this.population.add(Individual.createRandom(this.L));
		}
	}
	
	/**
	 * select the best one of the two individuals and discards the other
	 */
	public void externalSelection()
	{
		Individual i1 = this.population.get(0);
		Individual i2 = this.population.get(1);
		if(i1.getFitness()<i2.getFitness())
			this.population.remove(1);
		else
			this.population.remove(0);
	}
	
	/**
	 * the parent selection process
	 * not required for this part of the assignment
	 * @return
	 * return a list of two individual that are the parents.
	 */
	public ArrayList<Individual> parentSelection()
	{
		return null;
	}
	
	/**
	 * the inheritance process
	 * copy the surviving genome to the other one
	 */
	public void inheritance()
	{
		this.population.add(this.population.get(0).clone());
	}
	
	/**
	 * Applies the mutation process on the new member of the population
	 */
	public void applyMutation()
	{
		this.population.get(1).mutate(this.bound);
	}
	
	/**
	 * Evaluate the fitness of each individual of the population
	 * @return
	 * the best 
	 */
	public double fitnessEvaluation()
	{
		double max=Double.MIN_VALUE;
		for(Individual ind:this.population)
		{
			double temp=ind.measureFitness();
			ind.setFitness(temp);
			max=Math.max(max, temp);
		}
		return max;
	}
	
	/**
	 * run the evolutionary algorithm on the given system
	 */
	public void runAlgorithm()
	{
		int rounds=0;
		double maxFitness;
		this.createInitialPopulation();
		maxFitness=this.fitnessEvaluation();
		while(rounds++<500||maxFitness<=-2)
		{
			this.externalSelection();
			//this.parentSelection();
			this.inheritance();
			this.applyMutation();
			maxFitness=this.fitnessEvaluation();
			System.out.println("*************************");
			printResults(false);			
		}
		System.out.println("==========================");
		printResults(true);
		System.out.println("==========================");
	}
	
	/**
	 * prints the population to the console
	 */
	public void printResults(boolean flag)
	{
		for(Individual ind:this.population)
			if(flag)
				System.out.println(ind.toString());
			else
				System.out.println(ind.getFitness());
	}
}
