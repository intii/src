import java.util.ArrayList;


public abstract class AbsPopulationReplacer {

	protected AbsFitnessCalculator fc;
	
	public AbsPopulationReplacer(AbsFitnessCalculator fc){
		this.fc = fc;
	}
	public AbsFitnessCalculator getFc(){
		return this.fc;
	}
	public abstract ArrayList<Solution> replace(ArrayList<Pair> parents, ArrayList<Solution> offspring);
}
