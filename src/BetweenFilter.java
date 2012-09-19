import java.util.*;
public class BetweenFilter implements IFilter{
	
	private int min;
	private int max;
	private Activity a;
	

	public BetweenFilter(Activity a,int min, int max ) {
		this.min = min;
		this.max = max;
		this.a = a;
	}



	@Override
	public boolean isValid(DecodedSolution ds, Resource r) {
		ArrayList<ScheduledActivity>  sch = ds.getSchedule();
		boolean[] assigned = ds.getAssigner()[r.getId()-1];
		for( int i = 0; i< assigned.length ; i++){
			if( i!= (a.getId()-1) && assigned[i] ){
				//look for the activity with id = i+1
				ScheduledActivity aux = new ScheduledActivity(i+1);
//				System.out.println("Activity" + i);
				if(sch.contains(aux)){
					ScheduledActivity sa = sch.get(sch.indexOf(aux));
					for(int j = min ; j <= max ; j++){
						if( j <= sa.getEndTime() && j >= sa.getStartTime() ){
							return false;
						}	
					}
				}	
			}
		}
		return true;
	}
	

}
