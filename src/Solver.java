import java.util.ArrayList;


public class Solver {

	private ICrosser crosser;
	private IMutator mutator;
	private ParentSelector parentSelector;
	private InitialPopulator initialPopulator;
	private FitnessCalculator fitnessCalculator;
	private PopulationReplacer populationReplacer;
	
	private SolutionHandler sh;
	private int numberInitialSolutions;
	private int cutCondition=500;
	
	public int getCutCondition() {
		return cutCondition;
	}

	public void setCutCondition(int cutCondition) {
		this.cutCondition = cutCondition;
	}

	public Solver (SolutionHandler sh){
		this.sh = sh;
	}

	public void setCrosser(ICrosser crosser) {
		this.crosser = crosser;
	}

	public void setMutator(IMutator mutator) {
		this.mutator = mutator;
	}

	public void setParentSelector(ParentSelector parentSelector) {
		this.parentSelector = parentSelector;
	}

	public void setInitialPopulator(InitialPopulator initialPopulator) {
		this.initialPopulator = initialPopulator;
	}

	public void setFitnessCalculator(FitnessCalculator fitnessCalculator) {
		this.fitnessCalculator = fitnessCalculator;
	}
	
	public void setPopulationReplacer(PopulationReplacer populationReplacer) {
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
			System.out.println("Ciclo "+j);
						
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
			j++;
		}
		return current;
	}
}
