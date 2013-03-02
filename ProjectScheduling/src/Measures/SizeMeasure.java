package Measures;

import java.util.ArrayList;

import Structure.Solution;

public class SizeMeasure implements  IMeasure{

	@Override
	public Double measure(ArrayList<Solution> solutions) {
		return new Double(solutions.size());
	}

}
