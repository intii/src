import java.util.ArrayList;


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
			int i =1;
			while(this.noFreeResource(a, aux, s.getAssignedRes().get(a.getId()-1))){
				newSch.setStartTime(minStart+i);
				newSch.setEndTime(minStart+i + a.getDuration());
				i++;
			}
		}
//		System.out.println("End time for this schedule : "+scheduledList.get(scheduledList.size()-1).getEndTime() );
		DecodedSolution d = new DecodedSolution(scheduledList, sh.getResources() ,assigner );
		return d;
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
}
