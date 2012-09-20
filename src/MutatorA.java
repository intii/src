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
		ArrayList<Activity> toBeMutated = new ArrayList<Activity>(acts);
		for(int i = 0; i < acts.size() ; i++){
			double r = Math.random();
			if( r <= this.pm ){
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
	}
	
	private void mutateResources( ArrayList<ArrayList<Resource>> res, ArrayList<Activity> acts){
		for(int i = 0; i < acts.size(); i++){//for each activity
			double r = Math.random();
			if( r <= this.pm ){
				res.remove(i);
				res.add(i, ip.getResourcesForActivity(acts.get(i)));
			}
		}
	}
	public void mutate(Solution s){
		ArrayList<Activity> newAct = new ArrayList<Activity>(s.getActivities());
		ArrayList<ArrayList<Resource>> newRes = new ArrayList<ArrayList<Resource>>(s.getAssignedRes());
		this.mutateActivities(newAct);
		this.mutateResources(newRes,newAct);
		Solution newSol = new Solution(newAct,newRes);
		s = newSol;
	}
	


}
