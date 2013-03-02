package Filter;

import java.util.*;

import Structure.Resource;
import Structure.TimeRange;
public class BetweenFilter implements IFilter{
	
	private int min;
	private int max;
	private ArrayList<ArrayList<TimeRange>> usedRes;

	public BetweenFilter(int min, int max, ArrayList<ArrayList<TimeRange>> ur ) {
		this.min = min;
		this.max = max;
		this.usedRes = ur;
	}



	@Override
//	public boolean isValid( Resource r) {
//		ArrayList<ScheduledActivity>  sch = this.ds.getSchedule();
//		boolean[] assigned = this.ds.getAssigner()[r.getId()-1];
//		for( int i = 0; i< assigned.length ; i++){
//			if( i!= (a.getId()-1) && assigned[i] ){
//				//look for the activity with id = i+1
//				ScheduledActivity aux = new ScheduledActivity(i+1);
////				System.out.println("Activity" + i);
//				if(sch.contains(aux)){
//					ScheduledActivity sa = sch.get(sch.indexOf(aux));
//					for(int j = min ; j <= max ; j++){
//						if( j <= sa.getEndTime() && j >= sa.getStartTime() ){
//							return false;
//						}	
//					}
//				}	
//			}
//		}
//		return true;
//	}
	
	public boolean isValid(Resource r){
		for(TimeRange t : this.usedRes.get(r.getId()-1)){
			if(t.isBetween(min, max)){
				//System.out.println(r+ " lo tiene "+t.getA().getId());
				return false;
			}	
		}
		return true;
	}

}
