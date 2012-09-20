import java.util.ArrayList;


public class FitnessCalculatorMS extends AbsFitnessCalculator{
	
	private int[] durationSolution;
	
	public FitnessCalculatorMS(SolutionHandler sh){
		super( sh);
	}
	
	
	
	public int getDuration(Solution s,SolutionHandler sh ){
		 return(sh.getSchedule(s).getFinalTime());
	}
	

	@Override
	public boolean isBetter(Solution s1, Solution s2) {
		return (this.durationSolution[this.population.indexOf(s1)] < this.durationSolution[this.population.indexOf(s2)]);
	}

	@Override
	public void fitnessLoader(ArrayList<Solution> solutions) {
		this.population = solutions;
		this.durationSolution= new int[solutions.size()];
		int i;
		for(i = 0;i<solutions.size();i++){
			this.durationSolution[i] = this.getDuration(solutions.get(i), sh);
		}
		
	}



	@Override
	public String getFitness(Solution s) {
		return new String(Integer.toString(this.durationSolution[this.population.indexOf(s)]));
	}

}
