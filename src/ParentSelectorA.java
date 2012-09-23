import java.util.ArrayList;


public class ParentSelectorA extends AbsParentSelector {

	public ParentSelectorA(AbsFitnessCalculator fc){
		super(fc);
	}
	
	
	public Solution generateOneParent(ArrayList<Solution> leftSolutions){
		int r1 = (int)(Math.random()*(leftSolutions.size()-1));
		int r2 = (int)(Math.random()*(leftSolutions.size()-1));
		if( fc.isBetter(leftSolutions.get(r1), leftSolutions.get(r2)) ){ 
			Solution s = leftSolutions.get(r1);
			return s;
		}else{
			Solution s = leftSolutions.get(r2);
			return s;
		}
	}
}
