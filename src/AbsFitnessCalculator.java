import java.util.ArrayList;


public abstract class AbsFitnessCalculator {
	
	protected ArrayList<Solution> population;
	protected SolutionHandler sh;
	
	public AbsFitnessCalculator(SolutionHandler sh){
		this.sh = sh;
	}
	
	public abstract boolean isBetter(Solution s1, Solution s2);
	
	public abstract void fitnessLoader(ArrayList<Solution> solutions);
	
	public abstract String getFitness(Solution s);
	
	public void updateFitness(ArrayList<Solution> offspring) {
		ArrayList<Solution> newpop = new ArrayList<Solution>(this.population);
		newpop.addAll(offspring);
		this.fitnessLoader(newpop);	
	}

}
