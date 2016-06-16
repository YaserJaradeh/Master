package programmingAssignmentB;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Life {

	/**
	 * the current grid
	 */
	static int [][] current;
	
	/**
	 * the next grid (next time step)
	 */
	static int [][] next;
	
	/**
	 * the writer object of the file
	 */
	static BufferedWriter writer;
	
	/**
	 * the size of the grid on the x axis
	 */
	static final int X_MAX = 101;
	
	/**
	 * the size of the grid on the y axis
	 */
	static final int Y_MAX = 82;
	
	/**
	 * the middle of the grid x axis point
	 */
	static final int X_MIDDLE_AXIS = X_MAX/2;
	
	/**
	 * the middle of the grid y axis point
	 */
	static final int Y_MIDDLE_AXIS = Y_MAX/2;

	/**
	 * the number of alive set at any given moment
	 */
	static int alive;
	
	/**
	 * the scanner object that reads from the console and handles the user's input
	 */
	static Scanner scanner;
	
	static boolean printFlag;

	public static void main(String[] args) {
		current = new int [Y_MAX][X_MAX];
		next = new int [Y_MAX][X_MAX];
		alive=0;
		scanner=new Scanner(System.in);
		try {
			handleUserInput(scanner);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		System.out.println("Game Ended Check File");
		scanner.close();
	}

	/**
	 * Handles the input of the user to start the game
	 * @throws IOException 
	 */
	private static void handleUserInput(Scanner s) throws IOException
	{
		System.out.println("Hello and Welcome ...");
		System.out.println("Please Choose what kind of pattern you want to start with");
		System.out.println("Please Enter the number of your choice");
		System.out.println("1- Blinker");
		System.out.println("2- Glider");
		System.out.println("3- R-pentomino");
		System.out.println("4- Glider Gun");
		System.out.println("5- B-heptimino");
		System.out.print("What is your Choice: ");
		int choice = s.nextInt();
		switch(choice)
		{
			case 1:
				putBlinker();
				break;
			case 2:
				putGlider();
				break;
			case 3:
				putR();
				break;
			case 4:
				putGun();
				break;
			case 5:
				putB();
				break;
			default:
				putGun(40,6);
				break;
		}
		System.out.println("make sure the program has sufficient permissions");
		System.out.println("to create the files: (the steps) & for (the number of alive cells)");
		System.out.println("Enter the number of time steps (example 200)");
		int num=s.nextInt();
		System.out.print("Do you want to print the steps file (Large file) (y,n): ");
		String print=s.next();
		if(print.equals("y")||print.equals("Y"))
			printFlag=true;
		else
			printFlag=false;
		System.out.println("Game Started");
		runGameOfLife(num);
	}
	
	/**
	 * Print the grid as a string to a file
	 * the file is state.txt
	 * @throws IOException
	 * throws IOException if the file is opened by another process
	 * or some other IO error
	 */
	public static void printGridToFile() throws IOException
	{
		try
		{
			writer = new BufferedWriter(new FileWriter("state.txt",true));
			writer.write(getGridAsStringForConsole());
			writer.newLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			writer.close();
		}
	}

	/**
	 * print the given count to the file
	 * @param count
	 * the variable to print
	 * @throws IOException
	 * if there is a problem with the file this exception get thrown
	 */
	public static void printCountToFile(int count) throws IOException
	{
		try
		{
			writer = new BufferedWriter(new FileWriter("count.txt",true));
			writer.write(Integer.toString(count));
			writer.newLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			writer.close();
		}
	}

	/**
	 * Convert the grid into grid of numbers and return it as a string
	 * @return
	 * the grid (current) as a String object
	 */
	private static String getGridAsString()
	{
		return Arrays.deepToString(current)
				.replaceAll("\\],", System.lineSeparator())
				.replaceAll(" \\[", "")
				.replaceAll("\\[", "")
				.replaceAll("\\]", "")
				.replaceAll(",", "");
	}
	
	private static String getGridAsStringForConsole()
	{
		return Arrays.deepToString(current)
				.replaceAll("\\],", System.lineSeparator())
				.replaceAll(" \\[", "")
				.replaceAll("\\[", "")
				.replaceAll("\\]", "")
				.replaceAll("0", " ")
				.replaceAll("1", "#")
				.replaceAll(",", "");
	}

	/**
	 * put on a blinker in the grid starting form the given indices
	 * @param xStart
	 * the x axis value to start from
	 * @param yStart
	 * the y axis value to start from
	 */
	private static void putBlinker(int xStart, int yStart)
	{
		if(xStart > X_MAX || yStart > Y_MAX)
			return;
		current[yStart-1][xStart]=1;
		current[yStart][xStart]=1;
		current[yStart+1][xStart]=1;
	}

	/**
	 * put on a blinker in the middle of the grid
	 */
	private static void putBlinker()
	{
		putBlinker(X_MIDDLE_AXIS,Y_MIDDLE_AXIS);
	}

	/**
	 * put on a glider in the grid at the given x and y
	 * @param xStart
	 * the x axis start point
	 * @param yStart
	 * the y axis point to start from
	 */
	private static void putGlider(int xStart, int yStart)
	{
		if(xStart > X_MAX || yStart > Y_MAX)
			return;
		current[yStart-1][xStart]=1;
		current[yStart+1][xStart]=1;
		current[yStart][xStart+1]=1;
		current[yStart-1][xStart+1]=1;
		current[yStart-1][xStart-1]=1;
	}

	/**
	 * put on a glider in the middle of the grid
	 */
	private static void putGlider()
	{
		putGlider(X_MIDDLE_AXIS,Y_MIDDLE_AXIS);
	}

	/**
	 * put on a r-pentomino in the given coordinates
	 * @param xStart
	 * the x axis start point
	 * @param yStart
	 * the y axis start point
	 */
	private static void putR(int xStart, int yStart)
	{
		if(xStart > X_MAX || yStart > Y_MAX)
			return;
		current[yStart][xStart]=1;
		current[yStart][xStart-1]=1;
		current[yStart+1][xStart]=1;
		current[yStart-1][xStart]=1;
		current[yStart-1][xStart+1]=1;
	}

	/**
	 * put on a r-pentomino in the middle of the grid
	 */
	private static void putR()
	{
		putR(X_MIDDLE_AXIS,Y_MIDDLE_AXIS);
	}

	/**
	 * put on a Glider gun on the grid at the x and y
	 * this should not be called with x and y randomly or will go beyond the boundary
	 * @param xStart
	 * the x to start the from
	 * @param yStart
	 * the y to start the from
	 */
	private static void putGun(int xStart, int yStart)
	{
		if(xStart > X_MAX || yStart > Y_MAX)
			return;
		current[yStart][xStart+2]=1;
		current[yStart][xStart+3]=1;
		current[yStart][xStart-2]=1;
		current[yStart][xStart-8]=1;
		current[yStart][xStart-17]=1;
		current[yStart][xStart-18]=1;
		current[yStart+1][xStart-17]=1;
		current[yStart+1][xStart-18]=1;
		current[yStart+1][xStart-8]=1;
		current[yStart+1][xStart-1]=1;
		current[yStart+1][xStart-2]=1;
		current[yStart+1][xStart-4]=1;
		current[yStart+1][xStart+4]=1;
		current[yStart+1][xStart+6]=1;
		current[yStart+2][xStart+6]=1;
		current[yStart+2][xStart-2]=1;
		current[yStart+2][xStart-8]=1;
		current[yStart+3][xStart-3]=1;
		current[yStart+3][xStart-7]=1;
		current[yStart+4][xStart-5]=1;
		current[yStart+4][xStart-6]=1;
		current[yStart-1][xStart-3]=1;
		current[yStart-1][xStart-7]=1;
		current[yStart-1][xStart+2]=1;
		current[yStart-1][xStart+3]=1;
		current[yStart-1][xStart+16]=1;
		current[yStart-1][xStart+17]=1;
		current[yStart-2][xStart+2]=1;
		current[yStart-2][xStart+3]=1;
		current[yStart-2][xStart-5]=1;
		current[yStart-2][xStart-6]=1;
		current[yStart-2][xStart+16]=1;
		current[yStart-2][xStart+17]=1;
		current[yStart-3][xStart+4]=1;
		current[yStart-3][xStart+6]=1;
		current[yStart-4][xStart+6]=1;
	}

	/**
	 * put on a Glider gun in the middle of the grid
	 */
	private static void putGun()
	{
		putGun(X_MIDDLE_AXIS,Y_MIDDLE_AXIS);
	}

	/**
	 * put on a B-heptomino in the given coordinates
	 * @param xStart
	 * the x start point
	 * @param yStart
	 * the y start point
	 */
	private static void putB(int xStart, int yStart)
	{
		if(xStart > X_MAX || yStart > Y_MAX)
			return;
		current[yStart][xStart]=1;
		current[yStart][xStart+1]=1;
		current[yStart][xStart-1]=1;
		current[yStart+1][xStart]=1;
		current[yStart-1][xStart-1]=1;
		current[yStart-1][xStart+1]=1;
		current[yStart-1][xStart+2]=1;
	}

	/**
	 * put on a B-heptomino in the middle of the grid
	 */
	private static void putB()
	{
		putB(X_MIDDLE_AXIS,Y_MIDDLE_AXIS);
	}

	/**
	 * Calculate the next state of the grid
	 */
	public static void calculateNextStep()
	{
		for(int row=0;row<Y_MAX;row++)
		{
			for(int col=0;col<X_MAX;col++)
			{
				int count=getNeighborsCount(row,col);
				if(current[row][col]==0 && count == 3)
				{
					next[row][col]=1;
					alive++;
				}
				else if(current[row][col]==1 && (count == 2 || count == 3))
				{
					next[row][col]=1;
					alive++;
				}
				else
					next[row][col]=0;
			}
		}
	}

	/**
	 * Get the count of alive cells in the neighborhood
	 * @param row
	 * the row that we are looking at
	 * @param col
	 * the column that we are looking at
	 * @return
	 * the count of the alive cells around the given cell
	 */
	private static int getNeighborsCount(int row, int col)
	{
		// *  *  *
		// *  X  *
		// *  *  *
		int sum=0;
		sum+=current[row][(col-1+X_MAX)%X_MAX];
		sum+=current[row][(col+1+X_MAX)%X_MAX];
		sum+=current[(row-1+Y_MAX)%Y_MAX][(col+1+X_MAX)%X_MAX];
		sum+=current[(row-1+Y_MAX)%Y_MAX][(col-1+X_MAX)%X_MAX];
		sum+=current[(row-1+Y_MAX)%Y_MAX][col];
		sum+=current[(row+1+Y_MAX)%Y_MAX][(col+1+X_MAX)%X_MAX];
		sum+=current[(row+1+Y_MAX)%Y_MAX][(col-1+X_MAX)%X_MAX];
		sum+=current[(row+1+Y_MAX)%Y_MAX][col];
		return sum;
	}

	/**
	 * Run the game of life for the given time steps
	 * @param steps
	 * the time steps to run the game
	 * @throws IOException
	 * if their is a problem with the file 
	 */
	public static void runGameOfLife(int steps) throws IOException
	{
		for(int i=0;i<steps;i++)
		{
			alive=0;
			calculateNextStep();
			printCountToFile(alive);
			if(printFlag==true)
				printGridToFile();
			current=next;
			next=new int[Y_MAX][X_MAX];
			if(i%10==0)
				System.out.println("at step " + i);
		}
	}

}
