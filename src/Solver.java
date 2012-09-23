import java.util.ArrayList;


public class Solver {

	private ICrosser crosser;
	private AbsMutator mutator;
	private AbsParentSelector parentSelector;
	private InitialPopulator initialPopulator;
	private AbsPopulationReplacer populationReplacer;
	
	private int numberInitialSolutions;
	private int cutCondition=500;
	
	public int getCutCondition() {
		return cutCondition;
	}

	public void setCutCondition(int cutCondition) {
		this.cutCondition = cutCondition;
	}

	public void setCrosser(ICrosser crosser) {
		this.crosser = crosser;
	}

	public void setMutator(AbsMutator mutator) {
		this.mutator = mutator;
	}

	public void setParentSelector(AbsParentSelector parentSelector) {
		this.parentSelector = parentSelector;
	}

	public void setInitialPopulator(InitialPopulator initialPopulator) {
		this.initialPopulator = initialPopulator;
	}
	
	public void setPopulationReplacer(AbsPopulationReplacer populationReplacer) {
		this.populationReplacer = populationReplacer;
	}

	public void setNumberInitialSolutions(int numberInitialSolutions) {
		this.numberInitialSolutions = numberInitialSolutions;
	}

	public ArrayList<Solution> solve(){
		//Generate a given number of initial solutions
		ArrayList<Solution> current = new ArrayList<Solution>(this.numberInitialSolutions);
		int j=0;
		current = this.initialPopulator.getNInitialSolutions(this.numberInitialSolutions);
		while(j < this.cutCondition ){//some condition
			//make parent couples
			ArrayList<Pair> parents = this.parentSelector.pairMaker(current);
			ArrayList<Solution> offspring = new ArrayList<Solution>();
			//Cross parent couples
			for(int i = 0; i < parents.size(); i++ ){
				offspring.addAll(this.crosser.cross(parents.get(i)));
			}
			//mutate offspring solutions
			for(int i = 0; i< offspring.size(); i++){
				this.mutator.mutate(offspring.get(i));
			}
			current = this.populationReplacer.replace(parents, offspring);
			int minFitness = 200;
			int maxFitness = 0;
			double avgFitness = 0;
			for(Solution s: current){
				int currentFitness = Integer.parseInt(this.populationReplacer.getFc().getFitness(s));
				if( currentFitness > maxFitness)
					maxFitness = currentFitness;
				if (currentFitness < minFitness)
					minFitness = currentFitness;
				avgFitness += currentFitness;
			}
			avgFitness = avgFitness/current.size();
			System.out.println("Ciclo "+j);
			System.out.println("Number of generated Solutions = "+Solution.getCounter());
			System.out.println("Maximum Fitness = "+maxFitness);
			System.out.println("Minimum Fitness = "+minFitness);
			System.out.println("Average Fitness = "+avgFitness);
			System.out.println();
			j++;
		}
		return current;
	}
}
