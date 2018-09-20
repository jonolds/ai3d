import java.util.Random;

class Game {

	static double[] evolveWeights() {
		// Create a random initial population
		Random r = new Random();
		Matrix population = new Matrix(100, 291);
		for(int i = 0; i < 100; i++) {
			double[] chromosome = population.row(i);
			for(int j = 0; j < chromosome.length; j++)
				chromosome[j] = 0.03 * r.nextGaussian();
		}


		// Evolve the population
		// todo: YOUR CODE WILL START HERE.
		//       Please write some code to evolve this population.
		//       (For tournament selection, you will need to call Controller.doBattleNoGui(agent1, agent2).)


		// Return an arbitrary member from the population
		return population.row(0);
	}


	public static void main(String[] args) throws Exception {
		double[] w = evolveWeights();
		Controller.doBattle(new ReflexAgent(), new NeuralAgent(w));
	}
}

interface IAgent {
	void reset();
	void update(Model m);
}