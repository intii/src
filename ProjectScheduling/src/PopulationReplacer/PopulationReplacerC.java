package PopulationReplacer;

import java.util.*;

import FitnessCalculator.AbsFitnessCalculator;
import Structure.Pair;
import Structure.Solution;


public class PopulationReplacerC extends AbsPopulationReplacer{

	public PopulationReplacerC(AbsFitnessCalculator fc){
		super(fc);
	}
	
	@Override
	public ArrayList<Solution> replace(ArrayList<Pair> parents,ArrayList<Solution> offspring) {
//		this.fc.updateFitness(offspring);
		ArrayList<Solution> result = new ArrayList<Solution>();
		//get the list of solutions from the list of pairs
		ArrayList<Solution> resolvedParents = new ArrayList<Solution>();
		for(Pair p : parents){
			resolvedParents.add(p.getS1());
			resolvedParents.add(p.getS2());
		}
		Collections.sort(resolvedParents,this.fc);
		Collections.sort(offspring,this.fc);
		result.addAll(resolvedParents.subList(resolvedParents.size()/2,resolvedParents.size() ));
		result.addAll(offspring.subList(offspring.size()/2,offspring.size() ));
		return result;
	}

}
