import java.util.ArrayList;


public class InitialPopulator {
	
	private SolutionHandler sh;
	private Solution s;
	
	public InitialPopulator(SolutionHandler sh){
		this.sh = sh;
		this.s  = new Solution();
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
		acts.add(this.sh.getActivities().get(0));
		while (this.sh.getActivities().size() != acts.size()){
			int newAct = 1+(int)(Math.random()*(this.sh.getActivities().size()-1));
			if(isValid(newAct, acts) && !acts.contains(this.sh.getActivities().get(newAct) )){
				acts.add(this.sh.getActivities().get(newAct));
			}	
		}
		return acts;
	}
	
	public void getRandomResources(Activity a, int h, DecodedSolution ds, ArrayList<Resource> resListActivity){
		IFilter ef = new EqualFilter(h+1);
		ScheduledActivity sa = ds.getScheduledActivity(a.getId());
		IFilter bf = new BetweenFilter(a,sa.getStartTime(), sa.getEndTime());
		IFilter af = new AndFilter(ef, bf);
		for(Resource re : this.sh.getResources()){
			if( af.isValid(ds, re))
				resListActivity.add(re);
		}
	}
		
	private ArrayList<ArrayList<Resource>> getInitialResources( ArrayList<Activity> acts){
		ArrayList<ArrayList<Resource>> resList = new ArrayList<ArrayList<Resource>>(acts.size());
		//initialize the vector of resources with the correct amount of empty vectors 
		for(int i=0;i<acts.size();i++){
			ArrayList<Resource> rl = new ArrayList<Resource>();
			resList.add(rl);
		}
		this.s.setActivities(acts);
		this.s.setAssignedRes(resList);
		DecodedSolution ds = this.sh.getSchedule(s);
//		System.out.println("tempo final antes de los recursos: "+ds.getFinalTime());
		for(int i = 0; i< acts.size() ; i++){//for each activity
			boolean delayed = false;
			Activity a = acts.get(i);
			//get needed resources
			int [] neededRes = new int[a.getResources().length];
			for(int n = 0; n < a.getResources().length; n++)
				neededRes[n]= a.getResources()[n];
			ArrayList<Resource> resListActivity = new ArrayList<Resource>();
			for( int h = 0 ; h < neededRes.length ; h++){//for each skill needed
				while( neededRes[h] > 0 && !delayed){
					resListActivity.clear();
					getRandomResources(a,h,ds,resListActivity);
					if(resListActivity.size() > 0){
						int newRandomRes = (int)(Math.random()*(resListActivity.size()-1 ));
						resList.get(i).add(resListActivity.get(newRandomRes));
						this.sh.getAssigner(resList, ds.getAssigner());
						neededRes[h]--;
					}else{
						ds.delayActivities(this.sh.getPrecedenceMatrix(), a.getId());
						delayed=true;
						resList.get(i).clear();
						this.sh.initMatrix(ds.getAssigner());
						this.sh.getAssigner(resList, ds.getAssigner());
						i--;
					}
				}
			}
		}
		return resList;
	}
		
	public Solution getInitialSolution(){
		ArrayList<Activity> acts = this.getInitialActivities();
		ArrayList<ArrayList<Resource>> res = getInitialResources(acts);
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
