package programmingAssignmentD;

/**
 * The main Class that runs the entire system
 */
public class Assignment {

	/**
	 * The main function of the assignment
	 * @param args
	 * the command line arguments of the program
	 * this program takes no argument
	 */
	public static void main(String[] args) {
		EvolutionaryAlgorithm alg= new EvolutionaryAlgorithm(2,10,0.03); //
		alg.runAlgorithm(); // run the system
	}

}
