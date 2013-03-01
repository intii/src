package FitnessCalculator;


import java.util.ArrayList;
import java.util.Comparator;

import SolutionDecoder.AbsSolutionDecoder;
import Structure.Solution;


public abstract class AbsFitnessCalculator implements Comparator{
	
	protected ArrayList<Solution> population;
	protected AbsSolutionDecoder sd;
	
	public AbsFitnessCalculator(AbsSolutionDecoder sd){
		this.sd = sd;
		this.population = new ArrayList<Solution>();
	}
	
	public abstract boolean isBetter(Solution s1, Solution s2);
	
	public abstract boolean isEqual(Solution s1, Solution s2);
	
	public abstract void fitnessLoader(ArrayList<Solution> solutions);
	
	public abstract Double getFitness(Solution s);
	
	public void updateFitness(ArrayList<Solution> offspring) {
		ArrayList<Solution> newpop = new ArrayList<Solution>(this.population);
		newpop.addAll(offspring);
		this.fitnessLoader(newpop);	
	}
	
	@Override 
	public int compare(Object o1, Object o2){
		Solution s1 = (Solution)o1;
		Solution s2 = (Solution)o2;
		if(this.isBetter(s1,s2))
			return 1;
		if(this.isBetter(s2,s1))
			return -1;
		return 0;
	}
	
	public void mostrarSoluciones(){
		for(Solution s : this.population ){
			System.out.println("Solucion : " + s.getId()+ " || Fitness : " + getFitness(s));
		}
	}
	
	public abstract boolean isDominated(Solution s1, Solution s2);

	public abstract String toString(Solution s);

}
