import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

class GAParams {
		int numOfIterations;
		int cutPoints;
		double mutationProb;
		int popSize;
		int organismLength;
		
		public GAParams(int numOfIterations, int cutPoints, double mutationProb, int popSize, int organismLength) {
			this.numOfIterations = numOfIterations;
			this.cutPoints = cutPoints;
			this.mutationProb = mutationProb;
			this.popSize = popSize;
			this.organismLength = organismLength;
		}
	}
public class Main {

	public static void main(String[] args) throws IOException {
		int[] inputSizes = {1, 2, 5, 10, 30, 75, 150, 300};
		
		GAParams params = new GAParams(1000000, 4, .01, 10000, 1);
		String curDir = System.getProperty("user.dir");
		System.out.print(curDir + "bestInputs");
		BufferedWriter bw = new BufferedWriter(new PrintWriter(new File(curDir + "/bestInputs.txt")));
		for (int i = 0; i < inputSizes.length; i++) {
			params.organismLength = inputSizes[i];
			Simulation s = new Simulation(params);
			Organism best = s.runAndReturnBest(params.numOfIterations);
			
			bw.write("Fitness: " + best.fitness + "\n");
			bw.write("------------Input---------------\n");
			bw.write(Integer.toString(best.chromosome.length) + "\n");
			for (int j = 0; j < best.chromosome.length; j++) {
				bw.write(best.chromosome[j] + "\n");
			}
			bw.write("\n");
			bw.flush();
		}
		bw.close();
	}
}
