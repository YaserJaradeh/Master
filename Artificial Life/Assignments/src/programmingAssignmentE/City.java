package programmingAssignmentE;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that represents the cities and have method to manipulate them
 * the city is represented by a 2d point (x,y) and it has an id
 */
public class City {
	
	private int x;
	private int y;
	private int id;
	
	/**
	 * get the x value of the city
	 * @return
	 * the x value as integer
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * set the value of x in the city
	 * @param x
	 * the x value to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * get the y value of the city
	 * @return
	 * the y value as integer
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * set the value of y in the city
	 * @param y
	 * the y value to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	
	/**
	 * get the id value of the city
	 * @return
	 * the id value as integer
	 */
	public int getId() {
		return id;
	}

	/**
	 * set the value of id in the city
	 * @param id
	 * the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Create an instance of the city
	 * @param x
	 * the x coordinate value of the city's location
	 * @param y
	 * the y coordinate value of the city's location
	 * @param id
	 * the id of the city
	 */
	public City(int x, int y, int id) {
		super();
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	/**
	 * Calculate the euclidean distance between this point and another one
	 * @param anotherCity
	 * the other city to measure the distance with
	 * @return
	 * A double value represent the distance between the two cities
	 */
	public double calculateDistance(City anotherCity)
	{
		return Math.sqrt(Math.pow(this.x-anotherCity.x, 2) + Math.pow(this.y-anotherCity.y, 2));
		
	}
	
	/**
	 * print the city object in a readable way
	 */
	@Override
	public String toString() {
		return id+"[" + x + ", " + y + "]";
	}
	
	/**
	 * Reads a file that contains the cities positions and ids in the following format
	 * ID	X	Y	#
	 * @param filePath
	 * the file to read the data from
	 * @return
	 * an Array list of cities that we read from the file
	 * @throws FileNotFoundException
	 * Exception if the file given to the method is not found
	 */
	public static ArrayList<City> readCitiesFromFile(String filePath) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File(filePath));
		scanner.nextLine();
		ArrayList<City> cities=new ArrayList<City>();
		int counter=1;
		while(scanner.hasNextLine() && counter++<=150)
		{
			int id=scanner.nextInt();
			int x=scanner.nextInt();
			int y=scanner.nextInt();
			scanner.nextLine(); //Discard the # symbol
			cities.add(new City(x,y,id));
		}
		scanner.close();
		return cities;
	}

}
