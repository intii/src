import java.util.*;
public class MutatorA implements IMutator{
	
	private double pm = 0.2;
	private SolutionHandler sh;
	private InitialPopulator ip;
	
	
	public MutatorA(SolutionHandler s, InitialPopulator ip){
		this.sh=s;
		this.ip = ip;
	}
	
	public MutatorA(double p, SolutionHandler s, InitialPopulator ip){
		this.pm = p;
		this.sh = s;
		this.ip = ip;
	}
	private void mutateActivities(ArrayList<Activity> acts){
		//TODO for each activity, not position man!
		for(int i = 0; i < acts.size() ; i++){
			double r = Math.random();
			if( r <= this.pm ){
				Activity a = acts.get(i);
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
				acts.remove(i);
				acts.add(newPos,a);
			}	
		}
	}
	
	private void mutateResources(Solution s, ArrayList<ArrayList<Resource>> res, ArrayList<Activity> acts){
		//TODO no tener en cuenta el schedule
		DecodedSolution ds = this.sh.getSchedule(s);
		for(int i = 0; i < acts.size(); i++){//for each activity
			double r = Math.random();
			if( r <= this.pm ){
				Activity a = acts.get(i);
				ArrayList<Resource> copy = new ArrayList<Resource>(res.get(i));
				res.get(i).clear();
				//get needed resources
				int [] neededRes = new int[a.getResources().length];
				for(int n = 0; n < a.getResources().length; n++)
					neededRes[n]= a.getResources()[n];
				ArrayList<Resource> resListActivity = new ArrayList<Resource>();
				for( int h = 0 ; h < neededRes.length ; h++){//for each skill needed
					while( neededRes[h] > 0 ){
						resListActivity.clear();
						this.ip.getRandomResources(a,h,ds,resListActivity);
						if(resListActivity.size() > 0){
							int newRandomRes = (int)(Math.random()*(resListActivity.size()-1 ));
							res.get(i).add(resListActivity.get(newRandomRes));
							this.sh.getAssigner(res, ds.getAssigner());
							neededRes[h]--;
						}else{
							res.get(i).clear();
							res.get(i).addAll(copy);
							for(int j = 0; j<neededRes.length;j++){
								neededRes[j]=0;
							}
						}
					}
				}
			}
		}
	}
	public void mutate(Solution s){
		ArrayList<Activity> newAct = new ArrayList<Activity>(s.getActivities());
		ArrayList<ArrayList<Resource>> newRes = new ArrayList<ArrayList<Resource>>(s.getAssignedRes());
		this.mutateActivities(newAct);
		this.mutateResources(s,newRes,newAct);
		Solution newSol = new Solution(newAct,newRes);
		s = newSol;
	}
	


}
