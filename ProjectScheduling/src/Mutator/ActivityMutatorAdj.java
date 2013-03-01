package Mutator;

import java.util.ArrayList;

import DataHandler.SolutionHandler;
import Structure.Activity;

public class ActivityMutatorAdj extends AbsActivityMutator {

	public ActivityMutatorAdj(SolutionHandler s, double p) {
		super(s, p);
	}

	@Override
	public boolean mutateActivities(ArrayList<Activity> acts) {
		boolean mutated = false;
		for ( int i = 0 ; i < acts.size() - 1 ; i++ ){
			if ( ! this.sh.isPredecessor(acts.get(i).getId()-1, acts.get(i+1).getId()-1 )){
				if ( Math.random() <= this.pm ){
					mutated = true;
					Activity a = acts.get(i+1);
					acts.remove(i+1);
					acts.add(i, a);
				}
			}
		}
		return mutated;
	}

}
