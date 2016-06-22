package programmingAssignmentE;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		EvolutionaryAlgorithm ev;
		Scanner scanner=new Scanner(System.in);
		System.out.println("Welcome >>>>");
		System.out.println("Please Enter the following parameters");
		System.out.print("Population Size (P): ");
		int p=scanner.nextInt();
		System.out.print("Muo: ");
		int muo=scanner.nextInt();
		System.out.print("Lamda: ");
		int lamda=scanner.nextInt();
		try {
			ev = new EvolutionaryAlgorithm(muo, lamda, p, "cities.txt");
			ev.run();
			ev.runPlotCommand();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scanner.close();
	}

}
