package ParentSelector;

import java.util.*;

import FitnessCalculator.AbsFitnessCalculator;
import Structure.Pair;
import Structure.Solution;

public abstract class AbsParentSelector {

	protected AbsFitnessCalculator fc;
	
	public AbsParentSelector ( AbsFitnessCalculator f){
		this.fc = f;
	}
	public abstract Solution generateOneParent(ArrayList<Solution> left);
	
	public ArrayList<Pair> pairMaker(ArrayList<Solution> solutions){
		
		ArrayList<Pair> result = new ArrayList<Pair>();
		this.fc.fitnessLoader(solutions);
		ArrayList<Solution> leftSolutions = new ArrayList<Solution>(solutions);
		while( result.size() < solutions.size()/2 ){
			//generate two random solutions
			Solution s1 = generateOneParent( leftSolutions);
			Solution s2 = generateOneParent( leftSolutions);
			Pair newPair = new Pair(s1,s2);
			result.add(newPair);
		}
		return result;
	}	
}
