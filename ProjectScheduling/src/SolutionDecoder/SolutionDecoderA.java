package SolutionDecoder;

import java.util.ArrayList;

import DataHandler.SolutionHandler;
import Filter.BetweenFilter;
import Filter.IFilter;
import Structure.Activity;
import Structure.Resource;
import Structure.ScheduledActivity;
import Structure.Solution;
import Structure.TimeRange;


public class SolutionDecoderA extends AbsSolutionDecoder {

	public SolutionDecoderA(SolutionHandler sh){
		super(sh);
	}
	
	public DecodedSolution getSchedule(Solution s){
		
		boolean[][] assigner = new boolean [sh.getResources().size()][sh.getActivities().size()];
		sh.initMatrix(assigner);
		ArrayList<ScheduledActivity> scheduledList = new ArrayList<ScheduledActivity>();
		ArrayList<Activity> acts = s.getActivities();
		ArrayList<ArrayList<Resource>> res = s.getAssignedRes();
		sh.getAssignedResources(res, assigner);
		//assigner indicates which resource is attached to which activity
		
		ArrayList<ArrayList<TimeRange>> usedResources = new ArrayList<ArrayList<TimeRange>>(sh.getResources().size());
		for(int h = 0;h<sh.getResources().size();h++){
			ArrayList<TimeRange> times = new ArrayList<TimeRange>();
			usedResources.add(times);
		}
		
		for( Activity a : acts){
			int minStart = 0;
			ScheduledActivity newSch = new ScheduledActivity(a);
			for( ScheduledActivity sch : scheduledList){
				int predId = sch.getActivity().getId();
				if( sh.isPredecessor(predId-1, a.getId()-1))
					if( minStart < sch.getEndTime() )
						minStart = sch.getEndTime() ;
			}
			newSch.setStartTime(minStart);
			newSch.setEndTime(minStart + a.getDuration());
			scheduledList.add(newSch);

			DecodedSolution aux = new DecodedSolution(scheduledList, sh.getResources() ,assigner );
			int i =0;
			while(this.noFreeResource(a,aux, s.getAssignedRes().get(a.getId()-1),usedResources)){
				i++;
				newSch.setStartTime(minStart+i);
				newSch.setEndTime(minStart+i + a.getDuration());
			}
//			System.out.println(a+" se retrasó "+i+" semanas.");
//			System.out.println("Start time : " + newSch.getStartTime() + "End time :" + newSch.getEndTime());
			TimeRange t = new TimeRange(newSch.getStartTime(),newSch.getEndTime()-1,a);
			for(Resource r : s.getAssignedRes().get(a.getId()-1)){
				usedResources.get(r.getId()-1).add(t);
			}
		}
		DecodedSolution d = new DecodedSolution(scheduledList, sh.getResources() ,assigner );
		return d;
	}
	
	private boolean noFreeResource(Activity a,DecodedSolution ds,ArrayList<Resource> resListActivity, ArrayList<ArrayList<TimeRange>> usedRes){
		//Returns true if at least one of the assigned resource for an activity is not available
		if( resListActivity.size()==0)
			return false;
		ScheduledActivity sa = ds.getScheduledActivity(a.getId());
		IFilter bf = new BetweenFilter(sa.getStartTime(), sa.getEndTime(),usedRes);
		for (Resource re : resListActivity){
			if( !bf.isValid(re) ){
//				System.out.println("Conflicto con el recurso "+re);
				return true;
			}	
		}
		return false;
	}
}
