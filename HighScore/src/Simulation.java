public class Simulation {
	static MersenneTwisterFast mersenneTwister = new MersenneTwisterFast();
	static int NUMBER_OF_CUTS = 4;
	
	Population population;
	
	public Simulation(GAParams params) {
			population = new Population(params);
	}
	
	Organism bestSeenSoFar = null;
	public Organism runAndReturnBest(int maxIterations) {
		for (int i = 0; i < maxIterations; i++) {
			
			int parent1Index = population.select();
			int parent2Index = population.select();
			Organism parent1 = population.population[parent1Index];
			Organism parent2 = population.population[parent2Index];
			Organism offspring1 = new Organism(population.orgLength, population.mutProb);
			Organism offspring2 = new Organism(population.orgLength, population.mutProb);
			population.crossOver(Math.min(population.orgLength-1, NUMBER_OF_CUTS), parent1, parent2, offspring1, offspring2);
			offspring1.setFitness();
			offspring2.setFitness();
			
			Organism better;
			if (offspring1.fitness - offspring2.fitness > 0) {
				better = offspring1;
			}
			else {
				better = offspring2;
			}
			
			int worseParent = 0;
			if (parent1.fitness - parent2.fitness > 0) {
				worseParent = parent2Index;
			}
			else {
				worseParent = parent1Index;
			}
			
			better.mutate();
			better.setFitness();
			
			//population.replaceWorst(better);
			population.replaceOrganismAtIndex(worseParent, better);
			
			if (bestSeenSoFar == null || bestSeenSoFar.fitness < population.population[0].fitness) {
				bestSeenSoFar = population.population[0];
				population.reportBestAndWorst();
			}
		}
		return bestSeenSoFar;
	}
}