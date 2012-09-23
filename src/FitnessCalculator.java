import java.util.ArrayList;


public class FitnessCalculator {
	
	private int[] durationSolution;
	private double[] effectivnessSolution;
	private int[] domgrade;
	private ArrayList<Solution> population;
	private AbsSolutionDecoder sd;
	
	public FitnessCalculator(AbsSolutionDecoder sd){
		this.sd = sd;
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
	
	public int getDuration(Solution s){
		 return(sd.getSchedule(s).getFinalTime());
	}
	
	public void fitnessLoader(ArrayList<Solution> solutions){
		
		this.population = solutions;
		this.durationSolution= new int[solutions.size()];
		this.effectivnessSolution = new double[solutions.size()];
		this.domgrade = new int[solutions.size()];
		int i;
		for(i = 0;i<solutions.size();i++){
			this.durationSolution[i] = this.getDuration(solutions.get(i));
			this.effectivnessSolution[i] = this.getEffectiveness(solutions.get(i));
		}
		this.caculateDomdgrade();
//		for(int j = 0;j < durationSolution.length;j++){
//			System.out.println("Solution "+this.population.get(j).getId());
//			System.out.println("effectivness : "+this.effectivnessSolution[j]);
//			System.out.println("Duration : "+this.durationSolution[j]);
//			System.out.println("Domgrade: "+this.domgrade[j]);
//		}
	}
	
	public void caculateDomdgrade(){
		for(int i = 0; i < this.domgrade.length; i++){
			this.domgrade[i]= 0;
			int d = this.durationSolution[i];
			double e = this.effectivnessSolution[i];
			for (int j = 0; j < this.domgrade.length; j++ ){
				if((d < this.durationSolution[j]) && (e > this.effectivnessSolution[j]))
//				if((d < this.durationSolution[j]) )
					this.domgrade[i]++;
			}
		}
	}
	
	public boolean isBetter(Solution s1, Solution s2){
		
		return (this.domgrade[this.population.indexOf(s1)] >= this.domgrade[this.population.indexOf(s2)]);
	}
	
	public void updateDomgrade(ArrayList<Solution> offspring){
		ArrayList<Solution> newpop = new ArrayList<Solution>(this.population);
		newpop.addAll(offspring);
		this.fitnessLoader(newpop);
		
	}
	
	public int getDomgrade(Solution s){
		return (this.domgrade[this.population.indexOf(s)]);
	}

}
