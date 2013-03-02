package FitnessCalculator;


import java.util.ArrayList;

import SolutionDecoder.AbsSolutionDecoder;
import Structure.Solution;


public class FitnessCalculatorMS extends AbsFitnessCalculator{
	
	private int[] durationSolution;
	
	public FitnessCalculatorMS(AbsSolutionDecoder sd){
		super( sd );
	}
	
	
	
	public int getDuration(Solution s){
		 return(sd.getSchedule(s).getFinalTime());
	}
	

	@Override
	public boolean isBetter(Solution s1, Solution s2) {
		return (this.durationSolution[this.population.indexOf(s1)] < this.durationSolution[this.population.indexOf(s2)]);
	}

	@Override
	public boolean isEqual(Solution s1, Solution s2) {
		return (this.durationSolution[this.population.indexOf(s1)] == this.durationSolution[this.population.indexOf(s2)]);
	}
	
	@Override
	public void fitnessLoader(ArrayList<Solution> solutions) {
		this.population = solutions;
		this.durationSolution= new int[solutions.size()];
		int i;
		for(i = 0;i<solutions.size();i++){
			this.durationSolution[i] = this.getDuration(solutions.get(i));
		}
		
	}



	@Override
	public String toString(Solution s) {
		return new String("Makespan: "+Integer.toString(this.durationSolution[this.population.indexOf(s)]));
	}



	@Override
	public Double getFitness(Solution s) {
		return new Double(this.durationSolution[this.population.indexOf(s)]);
	}



	@Override
	public boolean isDominated(Solution s1, Solution s2) {
		return this.isBetter(s2, s1);
	}

}
