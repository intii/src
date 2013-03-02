package ParentSelector;

import java.util.ArrayList;

import FitnessCalculator.AbsFitnessCalculator;
import Structure.Solution;


public class ParentSelectorB extends AbsParentSelector{
	
	
	public ParentSelectorB(AbsFitnessCalculator fc){
		super(fc);
	}
	
	
	public Solution generateOneParent(ArrayList<Solution> leftSolutions){
		int r1 = (int)(Math.random()*(leftSolutions.size()-1));
		int r2 = (int)(Math.random()*(leftSolutions.size()-1));
		if( fc.isBetter(leftSolutions.get(r1), leftSolutions.get(r2)) ){ 
			Solution s = leftSolutions.get(r1);
			leftSolutions.remove(leftSolutions.get(r1));
			return s;
		}else{
			Solution s = leftSolutions.get(r2);
			leftSolutions.remove(leftSolutions.get(r2));
			return s;
		}
	}
}
