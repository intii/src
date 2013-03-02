package Representation;
import java.util.ArrayList;

import FitnessCalculator.AbsFitnessCalculator;
import Structure.Solution;


public class SolutionsPrinter {

	public static String printSolutionSet(ArrayList<Solution> current, AbsFitnessCalculator fc, int j){

		double minFitness = 200;
		double maxFitness = -1;
		double avgFitness = 0;
		Solution maxS = null;
		for(Solution s: current){
			double currentFitness = fc.getFitness(s);
//			int currentFitness = Integer.parseInt(this.populationReplacer.getFc().getFitness(s));
			if( currentFitness > maxFitness){
				maxFitness = currentFitness;
				maxS = s; 
			}
			if (currentFitness < minFitness)
				minFitness = currentFitness;
			avgFitness += currentFitness;
		}
//		HashSet<Solution> set = new HashSet<Solution>(current);
//		System.out.println("Number of different solutions in the population = "+set.size());
		String out = "";
		avgFitness = avgFitness/current.size();
		out += "Ciclo "+j+"\n";
		out += "Number of generated Solutions = "+Solution.getCounter() +"\n";
		out += "Maximum Fitness = "+maxFitness +"\n";
		out += SolutionsPrinter.printOneSolution(maxS, fc) + "\n";
		out += "Minimum Fitness = "+minFitness +"\n";
		out += "Average Fitness = "+avgFitness +"\n";
		out += "\n";

		return out;
	}
	
	public static String printOneSolution(Solution s, AbsFitnessCalculator fc){
		String out = new String();
		out = s.toString();
		out += "\n";
		out += fc.toString(s);
		return out;
	}
}
