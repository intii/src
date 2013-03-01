package InitialPopulator;
import java.util.ArrayList;

import DataHandler.SolutionHandler;
import Filter.EqualFilter;
import Filter.IFilter;
import Structure.Activity;
import Structure.Resource;
import Structure.Solution;


public class InitialPopulator {
	
	private SolutionHandler sh;
	
	public InitialPopulator(SolutionHandler sh){
		this.sh = sh;
	}
		
	private boolean isValid(int act, ArrayList<Activity> acts){
		
		for(int i = 0 ; i < this.sh.getPrecedenceMatrix().length ; i++ ){
			if( this.sh.getPrecedenceMatrix()[i][act]){
				Activity aux = new Activity(i+1);
				if(!acts.contains(aux))
					return false;
			}
		}
		return true;
	}
	
	private ArrayList<Activity> getInitialActivities(){
		ArrayList<Activity> acts = new ArrayList<Activity>();
		//the first activity has to be always the activity 0
		acts.add(this.sh.getActivities().get(0));
		while (this.sh.getActivities().size() != acts.size()){
			int newAct = 1+(int)(Math.random()*(this.sh.getActivities().size()-1));
			if(isValid(newAct, acts) && !acts.contains(this.sh.getActivities().get(newAct) )){
				acts.add(this.sh.getActivities().get(newAct));
			}	
		}
		return acts;
	}
	
	public ArrayList<Resource> getPossibleResources(Activity a, int h){
		IFilter ef = new EqualFilter(h+1);
		ArrayList<Resource> resListActivity = new ArrayList<Resource>();
		for(Resource re : this.sh.getResources()){
			if( ef.isValid(re))
				resListActivity.add(re);
		}
		return resListActivity;
	}
	
	public ArrayList<Resource> getResourcesForActivity( Activity a){
		
		int [] neededRes = new int[a.getResources().length];
		for(int n = 0; n < a.getResources().length; n++)
			neededRes[n]= a.getResources()[n];
		ArrayList<Resource> resList = new ArrayList<Resource>();
		for( int h = 0 ; h < neededRes.length ; h++){//for each skill needed
			ArrayList<Resource> resListActivity = this.getPossibleResources(a, h);
			while( neededRes[h] > 0){//while still needing that kind of resource
				int newResIndex = (int)(Math.random()*(resListActivity.size()-1));
				resList.add(resListActivity.get(newResIndex));
				resListActivity.remove(newResIndex);
				neededRes[h]--;
			}
		}
		return resList;
	}
		
	private ArrayList<ArrayList<Resource>> getInitialResources(){
		ArrayList<ArrayList<Resource>> resList = new ArrayList<ArrayList<Resource>>(this.sh.getActivities().size());
		for(int i = 0; i< this.sh.getActivities().size() ; i++){//for each activity
			Activity a = this.sh.getActivities().get(i);
			//get needed resources
			ArrayList<Resource> rl = this.getResourcesForActivity(a);
			resList.add(rl);
	
		}
		return resList;
	}
		
	public Solution getInitialSolution(){
		ArrayList<Activity> acts = this.getInitialActivities();
		ArrayList<ArrayList<Resource>> res = getInitialResources();
		return new Solution(acts,res);
	}

	public ArrayList<Solution> getNInitialSolutions( int n){
		ArrayList<Solution> result = new ArrayList<Solution>(n);
		for(int i = 0; i < n; i++){
			result.add(i,this.getInitialSolution());
		}
		return result;
	}
}
