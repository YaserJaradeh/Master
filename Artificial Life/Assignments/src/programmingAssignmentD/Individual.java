package programmingAssignmentD;

import java.util.Arrays;
import java.util.Random;

/**
 * a class that represents the individual of a population
 *  in an evolutionary algorithm system
 */
public class Individual {
	
	private static Random randomizer = new Random();
	private double[] genome;
	private double fitness;

	private Individual(double[] genome) {
		super();
		this.genome = genome;
		this.fitness = this.measureFitness();
	}

	/**
	 * Getter of the fitness
	 * @return
	 * the fitness value of the individual
	 */
	public double getFitness() {
		return fitness;
	}
	
	/**
	 * set the value of the fitness to the given value
	 * @param fitness
	 * the calculated fitness value to store
	 */
	public void setFitness(double fitness)
	{
		this.fitness=fitness;
	}

	/**
	 * Creates a random individual with the specified length
	 * random numbers are generated between the range [0,10]
	 * @param length
	 * the length of the genome components
	 * @return
	 * a new object of the individual class
	 */
	public static Individual createRandom(int length)
	{
		double rangeMin=0;
		double rangeMax=10;
		double[] temp=new double[length];
		for(int i=0;i<length;i++)
		{
			temp[i]= rangeMin + (rangeMax - rangeMin) * randomizer.nextDouble();
		}
		return new Individual(temp);
	}
	
	/**
	 * Apply the mutation process on the current individual
	 * and take the given values for the random equally distributed mutation vector
	 * @param bound
	 * the bound epsilon to get the value between it's positive and negative value 
	 */
	public void mutate(double bound)
	{
		double[] r = new double[this.genome.length];
		double step= (2*bound)/(double)this.genome.length;
		r[0] = -1 * bound;
		for(int i=1;i<this.genome.length;i++)
			r[i] = r[i-1] + step;
		for(int i=0;i<this.genome.length;i++)
			this.genome[i] += r[i];
	}
	
	/**
	 * measure the fitness by applying the Hosaki function
	 * @return
	 * double value representing the fitness of the genome
	 */
	public double measureFitness()
	{
//		double x1=0,x2=0;
//		for(double d:this.genome)
//			if(d<=0)
//				x1+=d;
//			else
//				x2+=d;
		double x1=Double.MAX_VALUE,x2=Double.MIN_VALUE; // take the min and max as x1 and x2
		for(double d:this.genome)
		{
			x1=Math.min(x1, d);
			x2=Math.max(x2, d);
		}
		return (1-(8*x1)+(7*Math.pow(x1, 2))-((7/3)*Math.pow(x1, 3))+((1/4)*Math.pow(x1, 4))*Math.pow(x2, 2)*Math.exp(-1*x1));
	}
	
	
	/**
	 * print the individual as a human friendly text
	 */
	@Override
	public String toString() {
		return Arrays.toString(genome) + " f= "+ fitness ;
	}

	/**
	 * Clones the current object into a new one
	 */
	@Override
	protected Individual clone()
	{
		return new Individual(this.genome.clone());
	}
}
