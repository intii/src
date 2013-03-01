package FitnessCalculator;


import java.util.ArrayList;

import SolutionDecoder.AbsSolutionDecoder;
import Structure.Solution;


public class FitnessCalculatorMulti extends AbsFitnessCalculator{
	
	private AbsFitnessCalculator fc1;
	private AbsFitnessCalculator fc2;
	private int[] domgrade;
	
	
	public FitnessCalculatorMulti(AbsSolutionDecoder sd,AbsFitnessCalculator fc1,AbsFitnessCalculator fc2){
		super(sd);
		this.fc1 = fc1;
		this.fc2 = fc2;
	}

	@Override
	public boolean isBetter(Solution s1, Solution s2) {
		return (this.domgrade[this.population.indexOf(s1)] > this.domgrade[this.population.indexOf(s2)]);
	}
	
	public boolean isEqual(Solution s1, Solution s2) {
		return false ;
	}
	
	@Override
	public void fitnessLoader(ArrayList<Solution> solutions) {
		
		this.population = solutions;
		this.fc1.fitnessLoader(solutions);
		this.fc2.fitnessLoader(solutions);
		this.domgrade = new int[solutions.size()];
		for(int i = 0; i < this.domgrade.length; i++){
			this.domgrade[i]= 0;
			for (int j = 0; j < this.domgrade.length; j++ ){
				if 	(	(this.fc1.isBetter(this.population.get(i), this.population.get(j)) && (this.fc2.isBetter(this.population.get(i), this.population.get(j))) )||
						(this.fc1.isBetter(this.population.get(i), this.population.get(j)) && (this.fc2.isEqual(this.population.get(i), this.population.get(j)) )) ||
						(this.fc1.isEqual(this.population.get(i), this.population.get(j)) && (this.fc2.isBetter(this.population.get(i), this.population.get(j))) )
					)
								this.domgrade[i]++;
			}
		}
	}

	@Override
	public String toString(Solution s) {
		return new String("Domgrade: "+Integer.toString(this.domgrade[this.population.indexOf(s)])+"\n"
				+this.fc1.toString(s)+"\n"+this.fc2.toString(s));
	}

	@Override
	public Double getFitness(Solution s) {
		return new Double(this.domgrade[this.population.indexOf(s)]);
	}

	@Override
	public boolean isDominated(Solution s1, Solution s2) {
		
		return ((this.fc1.isBetter(s2, s1) && (this.fc2.isBetter(s2, s1)) )||
				(this.fc1.isBetter(s2, s1) && (this.fc2.isEqual(s2,s1) )) ||
				(this.fc1.isEqual(s2, s1) && (this.fc2.isBetter(s2, s1)) ));
	}
	
	
	
	
}

