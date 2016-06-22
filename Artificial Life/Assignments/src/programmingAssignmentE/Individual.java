package programmingAssignmentE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Individual {
	
	int[] genome;
	double fitness;
	private static final Random randomizer = new Random();
	
	/**
	 * get the fitness of the 
	 * @return
	 * the fitness of the individual
	 */
	public Double getFitness()
	{
		return this.fitness;
	}
	
	/**
	 * set the fitness of the individual
	 * @param fitness
	 * the new fitness to set
	 */
	public void setFitness(double fitness)
	{
		this.fitness = fitness;
	}
	
	/**
	 * get the randomizer
	 * @return
	 * a random object initialized statically in this class to be used in any other location
	 */
	public static Random getRandomizer() {
		return randomizer;
	}

	/**
	 * creates an instance of the individual class
	 * @param genome
	 * the genome of the individual
	 */
	public Individual(int[] genome) {
		super();
		this.genome = genome;
	}

	/**
	 * Create a random individual
	 * @param numberOfCities
	 * the number if cities
	 * @return
	 * an object of the individual created randomly w.r.t. the conditions of the problem
	 */
	public static Individual createRandom(int numberOfCities)
	{
		int[] temp=new int[numberOfCities*2];
		HashSet<Integer> set=new HashSet<Integer>();
		for(int i=0;i<numberOfCities;i++)
		{
			for(int j=0;j<2;j++)
			{
				Integer index = randomizer.nextInt(temp.length);
				while(!set.add(index))
				{
					index = randomizer.nextInt(temp.length);
				}
				temp[index]=i;
			}
		}
		return new Individual(temp);
	}
	
	/**
	 * Perform the mutation process on the current individual
	 */
	public void mutate()
	{
		int max = (this.genome.length/4);
		int count = randomizer.nextInt(max)+1;
		for(int i=0;i<count;i++)
		{
			int index1 = randomizer.nextInt(this.genome.length);
			int index2 = randomizer.nextInt(this.genome.length);
			while(index1==index2)
				index2 = randomizer.nextInt(this.genome.length);
			swapTwoValues(index1, index2);
		}
	}
	
	/**
	 * helper method to swap two values in the genome array
	 * @param index1
	 * teh first index
	 * @param index2
	 * the second index
	 */
	private void swapTwoValues(int index1, int index2)
	{
		int temp=this.genome[index1];
		this.genome[index1] = this.genome[index2];
		this.genome[index2] = temp;
	}
	
	/**
	 * Clones the current Individual to a new one
	 */
	public Individual clone()
	{
		return new Individual(this.genome.clone());
	}
	
	/**
	 * print in a formatted way
	 * this is used for the final result
	 * @param cities
	 * the list of cities to take details from
	 */
	public void printFormated(City[] cities)
	{
		for(int index:this.genome)
			System.out.println(cities[index]);
	}

	/**
	 * print out the Individual
	 */
	@Override
	public String toString() {
		return Arrays.toString(this.genome) + ", f=" + fitness;
	}
}
