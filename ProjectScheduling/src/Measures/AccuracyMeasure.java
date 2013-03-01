package Measures;

import java.util.ArrayList;
import java.util.Collections;

import FitnessCalculator.AbsFitnessCalculator;
import FitnessCalculator.FitnessCalculatorEf;
import FitnessCalculator.FitnessCalculatorMS;
import Structure.Solution;

public class AccuracyMeasure implements IMeasure{

	private Double optimalMakespan;
	private Double optimalEffectiveness;
	private AbsFitnessCalculator fcMs;
	private AbsFitnessCalculator fcEf;
	
	public AccuracyMeasure( Double om, Double oe, AbsFitnessCalculator fcms, AbsFitnessCalculator fcef ){
		this.fcMs = fcms;
		this.fcEf = fcef;
		this.optimalEffectiveness = oe;
		this.optimalMakespan = om;
	}
	
	@Override
	public Double measure(ArrayList<Solution> solutions) {
		return this.getEffectivenessAccuracy(solutions) + this.getMakespanAccuracy(solutions);
	}
	private Double getMakespanAccuracy( ArrayList<Solution> solutions ){
		Collections.sort(solutions, this.fcMs);
		Solution minorSolution = solutions.get(0);
		return (this.fcMs.getFitness(minorSolution) - this.optimalMakespan)/this.optimalMakespan*100;
		
	}
	private Double getEffectivenessAccuracy( ArrayList<Solution> solutions ){
		Collections.sort(solutions, this.fcEf);
		Solution minorSolution = solutions.get(solutions.size()-1);
		return (this.optimalEffectiveness - this.fcEf.getFitness(minorSolution) )/this.optimalEffectiveness*100;
	}
}
