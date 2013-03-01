package Mutator;

import java.util.ArrayList;

import DataHandler.SolutionHandler;
import Structure.Activity;
import Structure.Solution;

public abstract class AbsActivityMutator{
	
	protected double pm = 0.2;
	protected SolutionHandler sh;

	public AbsActivityMutator(SolutionHandler s, double p){
		this.pm = p;
		this.sh = s;
	}
	
	public abstract boolean mutateActivities(ArrayList<Activity> acts);

}
