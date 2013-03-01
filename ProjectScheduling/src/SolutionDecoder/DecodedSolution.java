package SolutionDecoder;

import java.util.ArrayList;

import Structure.Resource;
import Structure.ScheduledActivity;
import Structure.TimeRange;


public class DecodedSolution {
	
	private ArrayList<ScheduledActivity> schedule;
	private ArrayList<Resource> resources;
	private ArrayList<ArrayList<TimeRange>> resourcesUsed;
	private boolean[][] assigner;
	
	
	public DecodedSolution(ArrayList<ScheduledActivity> s, ArrayList<Resource> r, boolean[][] a){
		this.schedule = s;
		this.resources = r;
		this.assigner = a;
		this.resourcesUsed = new ArrayList<ArrayList<TimeRange>>(r.size());
	}


	public ArrayList<ScheduledActivity> getSchedule() {
		return schedule;
	}

	public ScheduledActivity getScheduledActivity(int id){
		ScheduledActivity aux = new ScheduledActivity(id);
		for( int i = 0 ; i < this.schedule.size(); i++){
			if(this.schedule.get(i).equals(aux))
				return this.schedule.get(i);
		}
		return null;
	}
	public ArrayList<Resource> getResources() {
		return resources;
	}


	public boolean[][] getAssigner() {
		return assigner;
	}
	public String toString(){
		String str = new String();
		for(ScheduledActivity sa : this.schedule){
			str += sa.toString()+ " || ";
		}
		return str;
	}
	
	public void delayActivities(boolean[][] succ, int id ){
		ScheduledActivity currentAct = this.getScheduledActivity(id);
		currentAct.setStartTime(currentAct.getStartTime()+1);
		currentAct.setEndTime(currentAct.getEndTime()+1);
		for(int i=0;i<succ[id-1].length;i++){
			if( succ[id-1][i])
				delayActivities(succ, i);
		}
	}
	/*Es necesario recorrer el arreglo por si hay varias actividades finales 
	 * que comienzan en el mismo tiempo y terminan en tiempos diferentes
	 * 
	 */
	public int getFinalTime(){
		int time = 0;
		for(ScheduledActivity sa : this.schedule){
			if(time < sa.getEndTime())
				time = sa.getEndTime();
		}
		return time;
	}

}
