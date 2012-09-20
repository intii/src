import java.util.ArrayList;


public class FitnessCalculatorEf extends AbsFitnessCalculator{

	private double[] effectivnessSolution;
	
	public FitnessCalculatorEf(SolutionHandler sh){
		super(sh);
	}
	
	
	private double getEffectivenessAct(ArrayList<Resource> res){
		if( res.size() == 0 )
			return 0.0;
		double result = 0.0;
		for(Resource r : res){
			result+=r.getEffectivness();
		}
		return(result/res.size());
	}
	
	public double getEffectiveness(Solution s){
		ArrayList<ArrayList<Resource>> r = s.getAssignedRes();
		double result = 0.0;
		for(int i = 0; i < r.size(); i++){
			result += this.getEffectivenessAct(r.get(i));
		}
		return result;
	}

	
	@Override
	public boolean isBetter(Solution s1, Solution s2) {
		return (this.effectivnessSolution[this.population.indexOf(s1)] >= this.effectivnessSolution[this.population.indexOf(s2)]);	
	}

	@Override
	public void fitnessLoader(ArrayList<Solution> solutions) {
		this.population = solutions;
		this.effectivnessSolution = new double[solutions.size()];
		int i;
		for(i = 0;i<solutions.size();i++){
			this.effectivnessSolution[i] = this.getEffectiveness(solutions.get(i));
		}

		
	}


	@Override
	public String getFitness(Solution s) {
		return new String(Double.toString(this.effectivnessSolution[this.population.indexOf(s)]));
	}

}
