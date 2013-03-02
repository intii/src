package Mutator;

import java.util.ArrayList;

import DataHandler.SolutionHandler;
import InitialPopulator.InitialPopulator;
import Structure.Activity;
import Structure.Resource;

public abstract class AbsResourceMutator {
	
	protected double pm = 0.2;
	protected SolutionHandler sh;
	protected InitialPopulator ip;
	
	public AbsResourceMutator(double p, SolutionHandler s, InitialPopulator ip){
		this.pm = p;
		this.sh = s;
		this.ip = ip;
	}
	
	public abstract boolean mutateResources( ArrayList<ArrayList<Resource>> res, ArrayList<Activity> acts);
	
	

}
