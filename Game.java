import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

class Game {

	public static void main(String[] args) throws Exception {
		//Calls genetic algorithym to find a winning genome
		double[] weights = evolveWeights();
		//Print Winning Genome
		printWeights(weights);
		//Puts best NeuralAgent in real battle with GUI
		//Controller.doBattle(new ReflexAgent(), new NeuralAgent(weights));
	}
	
	static double[] evolveWeights() throws Exception {
		// Create a random initial population
		Random r = new Random();
		Matrix population = new Matrix(100, 291);
		for(int i = 0; i < 100; i++) {
			double[] chromosome = population.row(i);
			for(int j = 0; j < chromosome.length; j++)  // chromosome.length = 291
				chromosome[j] = .3 * r.nextGaussian();
		}
		
		
		ArrayList<IAgent> candidates = new ArrayList<>();
		for(int i = 0; i < population.rows(); i++)
			candidates.add(new NeuralAgent(population.row(i)));

		Long startTime = Instant.now().toEpochMilli();
		Controller.doTournament(candidates);
		System.out.println(Instant.now().toEpochMilli() - startTime);
		
		// Evolve them. For tournament selection, call Controller.doBattleNoGui(agent1, agent2).
		
		
		// Return an arbitrary member from the population
		double[] weights = population.row(r.nextInt(population.rows()));
		return weights;
	}

	static void printWeights(double[] w) {
		for(double d: w)
			System.out.println(d);
	}
	
	static double round(double d) {
		return (double)((int)(1000*d))/100.0;
	}
}