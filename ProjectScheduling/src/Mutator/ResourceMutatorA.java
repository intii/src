package Mutator;

import java.util.ArrayList;

import DataHandler.SolutionHandler;
import InitialPopulator.InitialPopulator;
import Structure.Activity;
import Structure.Resource;

public class ResourceMutatorA extends AbsResourceMutator{
	
	public ResourceMutatorA(double p, SolutionHandler s, InitialPopulator ip) {
		super(p, s, ip);
	}

	@Override
	public boolean mutateResources(ArrayList<ArrayList<Resource>> res, ArrayList<Activity> acts) {
		
		boolean mutated = false;
		for(int i = 0; i < this.sh.getActivities().size(); i++){//for each activity
			double r = Math.random();
			if( r <= this.pm ){
				mutated = true;
				res.set(i, ip.getResourcesForActivity(this.sh.getActivities().get(i)));
		}
	}

		return mutated;
	}

}
