import java.io.IOException;
import java.util.*;
public class SolutionHandler {

	private boolean[][] precedenceMatrix;
	private ArrayList<Resource> resources;
	private ArrayList<Activity> activities;
	
	public SolutionHandler(int activities, int resources, int skills){
		StructMaker sm = new StructMaker(activities, resources, skills);
		try {
			this.precedenceMatrix = sm.getPrecedences();
			this.resources = sm.getResources();
			this.activities = sm.getListAct();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initMatrix(boolean[][] precedence){
		
		for(int i=0;i < precedence.length; i++){
			for(int j=0;j < precedence[i].length; j++){
				precedence[i][j]=false;
			}
		}
	}
	
	public void getAssigner(ArrayList<ArrayList<Resource>> res, boolean[][] a){
		
		
		//res.size = cantidad de actividades 
		for(int i =0; i < res.size(); i++){
			ArrayList<Resource> aux = res.get(i);
			for(int j =0; j < aux.size(); j++){
				a[aux.get(j).getId()-1][i] = true;
			}
		}
	}
	
	public boolean isPredecessor(int i, int j){
	//returns true if i is a predecessor of j
		return this.precedenceMatrix[i][j];
	}
	
	public boolean isSuccessor(int i, int j){
		//returns true if i is a successor of j
			return this.precedenceMatrix[j][i];
	}
	
	private boolean noFreeResource(Activity a, DecodedSolution ds, ArrayList<Resource> resListActivity){
		//Returns true if at least one of the assigned resource for an activity is not available
		if( resListActivity.size()==0)
			return false;
		ScheduledActivity sa = ds.getScheduledActivity(a.getId());
		IFilter bf = new BetweenFilter(ds,a,sa.getStartTime(), sa.getEndTime());
		for (Resource re : resListActivity){
			if( !bf.isValid(re) )
				return true;
		}
		return false;
	}
	
	public DecodedSolution getSchedule(Solution s){
		
		boolean[][] assigner = new boolean [this.resources.size()][this.activities.size()];
		this.initMatrix(assigner);
		ArrayList<ScheduledActivity> scheduledList = new ArrayList<ScheduledActivity>();
		ArrayList<Activity> acts = s.getActivities();
		ArrayList<ArrayList<Resource>> res = s.getAssignedRes();
		this.getAssigner(res, assigner);
		for( Activity a : acts){
			int minStart = 0;
			ScheduledActivity newSch = new ScheduledActivity(a);
			for( ScheduledActivity sch : scheduledList){
				int predId = sch.getActivity().getId();
				if( isPredecessor(predId-1, a.getId()-1))
					if( minStart < sch.getEndTime() )
						minStart = sch.getEndTime() ;
			}
			newSch.setStartTime(minStart);
			newSch.setEndTime(minStart + a.getDuration());
			scheduledList.add(newSch);

			DecodedSolution aux = new DecodedSolution(scheduledList, this.resources ,assigner );
			int i =1;
			while(this.noFreeResource(a, aux, s.getAssignedRes().get(a.getId()-1))){
				newSch.setStartTime(minStart+i);
				newSch.setEndTime(minStart+i + a.getDuration());
				i++;
			}
		}
//		System.out.println("End time for this schedule : "+scheduledList.get(scheduledList.size()-1).getEndTime() );
		DecodedSolution d = new DecodedSolution(scheduledList, this.resources ,assigner );
		return d;
	}
	
	public ArrayList<Resource> getResources(){
		return this.resources ;
	}
		
	public boolean[][] getPrecedenceMatrix() {
		return this.precedenceMatrix;
	}

	public ArrayList<Activity> getActivities() {
		return this.activities;
	}



}
