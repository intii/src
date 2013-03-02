package Measures;

import java.util.ArrayList;
import java.util.Collections;

import FitnessCalculator.AbsFitnessCalculator;
import Structure.Solution;

public class DistributionMeasure implements IMeasure{

	private AbsFitnessCalculator fcMs;
	private AbsFitnessCalculator fcEf;
	
	
	public DistributionMeasure( AbsFitnessCalculator fcms,  AbsFitnessCalculator fcef){
		this.fcMs = fcms;
		this.fcEf = fcef;
	}
	
	private Double getDistance(Solution s1, Solution s2 ){
		Double result = 0.0;
		Double leg1 = this.fcMs.getFitness(s1) - this.fcMs.getFitness(s2);
		Double leg2 = this.fcEf.getFitness(s1) - this.fcEf.getFitness(s2);
		result = leg1*leg1 + leg2*leg2;
		return Math.sqrt(result);
	}
	
	@Override
	public Double measure(ArrayList<Solution> solutions) {
		Double result = 0.0;
		Collections.sort(solutions, this.fcMs);
		int length = solutions.size();
		Double[] consecutiveDistance = new Double[length-1];
		Double averageDistance = new Double(0.0);
		
		for( int i = 0; i < length-1 ; i++ ){
			consecutiveDistance[i] = this.getDistance(solutions.get(i), solutions.get(i+1));
			averageDistance += consecutiveDistance[i] ;
		}
		
		averageDistance /= length -1;
		
		for( int i = 0; i < length-1 ; i++) {
			result += Math.pow(consecutiveDistance[i]/averageDistance -1,2);
		}
		return Math.sqrt(result/length-1); 
	}

}
