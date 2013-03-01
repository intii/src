package Crosser;

import java.util.*;

import Structure.Pair;
import Structure.Solution;
public abstract class AbsCrosser {

	protected AbsActivityCrosser ac;
	protected AbsResourceCrosser rc;
	protected double pc = 0.9;
	
	public AbsCrosser(AbsActivityCrosser ac, AbsResourceCrosser rc, double pc){
		this.ac = ac;
		this.rc = rc;
		this.pc = pc;
	}
	
	public abstract ArrayList<Solution> cross(Pair p);
}
