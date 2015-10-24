public class Organism {
	double mutationProb = 0.0;
	double fitness = 0;
	StringBuffer[] chromosome;

	public Organism(int length, double mutationProb) {
		this.mutationProb = mutationProb;
		chromosome = new StringBuffer[length];
		createRandomChromosome();
	}
	
	public void setFitness() {
		fitness = Fitness.calc(chromosome);
	}
	
	public void createRandomChromosome() {
		for (int i = 0; i < chromosome.length; i++) {
			chromosome[i] = new StringBuffer();
			for (int j = 0; j < 10; j++) {
				
				char c = generateRandomChar();
			    chromosome[i].append(c);
			}
		}
	}
	
	public void mutate() {
		for (int i = 0; i < chromosome.length; i++) {
			for (int j = 0; j < 10; j++) {
				double toss = Simulation.mersenneTwister.nextdouble(true, true);
				if (toss < this.mutationProb) {
					chromosome[i].setCharAt(j, generateRandomChar());
				}
			}
		}
	}
	
	// TODO: Make sure its not whitespace and it printable
	public char generateRandomChar() {
		//return Simulation.mersenneTwister.nextChar();
		int a =  33 + Simulation.mersenneTwister.nextInt(127-33);
		return  (char) a;

	}
}