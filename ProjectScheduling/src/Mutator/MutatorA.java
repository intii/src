package Mutator;

import java.util.*;

import Structure.Activity;
import Structure.Resource;
import Structure.Solution;
import DataHandler.SolutionHandler;
import InitialPopulator.InitialPopulator;


public class MutatorA extends AbsMutator{
	
	public MutatorA( AbsActivityMutator am, AbsResourceMutator rm ){
		super(am, rm);
	}
	
	

	public void mutate(Solution s){
		ArrayList<Activity> newAct = new ArrayList<Activity>(s.getActivities());
		ArrayList<ArrayList<Resource>> newRes = new ArrayList<ArrayList<Resource>>(s.getAssignedRes());
		if(this.am.mutateActivities(newAct)|| this.rm.mutateResources(newRes, newAct)){
			Solution newSol = new Solution(newAct,newRes);
			s = newSol;
		}
		
	}
	
}
