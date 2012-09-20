import java.util.ArrayList;


public class ParentSelector {
	
	private AbsFitnessCalculator fc;
	
	public ParentSelector(AbsFitnessCalculator fc){
		this.fc = fc;
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
