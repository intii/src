package PopulationReplacer;

import java.util.*;

import FitnessCalculator.AbsFitnessCalculator;
import Structure.Pair;
import Structure.Solution;

public class PopulationReplacerB extends AbsPopulationReplacer{

	
	public PopulationReplacerB(AbsFitnessCalculator fc){
		super(fc);
	}
	
	public ArrayList<Solution> replace(ArrayList<Pair> parents, ArrayList<Solution> offspring){
		ArrayList<Solution> newPopulation = new ArrayList<Solution>();
		int j = 0;
//		fc.updateFitness(offspring);
		for(int i = 0; i < offspring.size();i++){
			if( i % 2 == 0){
				if(fc.isBetter(parents.get(j).getS1(), offspring.get(i)))
					newPopulation.add(parents.get(j).getS1());
				else
					newPopulation.add(offspring.get(i));
			}else{
				if(fc.isBetter(parents.get(j).getS2(), offspring.get(i)))
					newPopulation.add(parents.get(j).getS2());
				else
					newPopulation.add(offspring.get(i));
			}
			j+= (i % 2);

		}
		return newPopulation;
	}
}
