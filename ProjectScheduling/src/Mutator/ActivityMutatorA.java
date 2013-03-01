package Mutator;

import java.util.ArrayList;

import DataHandler.SolutionHandler;
import Structure.Activity;

public class ActivityMutatorA extends AbsActivityMutator{
	
	public ActivityMutatorA(SolutionHandler s, double p){
		super(s,p);		
	}
	
	public boolean mutateActivities(ArrayList<Activity> acts){
		
		boolean mutated = false;
		ArrayList<Activity> toBeMutated = new ArrayList<Activity>(acts);
		for(int i = 0; i < acts.size() ; i++){
			double r = Math.random();
			if( r <= this.pm ){
				mutated = true;
				Activity a = toBeMutated.get(i);
				int actId = a.getId();
				int closestPred = 0;
				int closestSucc = acts.size()-1;
				//find closest predecessor
				for(int j = 0; j < i ; j++){
					int auxActId = acts.get(j).getId();
					if(sh.isPredecessor(auxActId-1, actId-1)){
						closestPred = j+1;
					}
				}
				//find closest successor
				for(int j = acts.size()-1; j > i ; j--){
					int auxActId = acts.get(j).getId();
					if(sh.isSuccessor(auxActId-1, actId-1)){
						closestSucc = j-1;
					}
				}
				//generate a new random position in a possible range
				int newPos = closestPred + (int)(Math.random()*(closestSucc-closestPred ));
				//insert in the new position with a possibility of pm
				acts.remove(a);
				acts.add(newPos,a);
			}	
		}
		return mutated;
	}

}
