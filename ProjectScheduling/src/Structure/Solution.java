package Structure;
import java.util.*;
public class Solution {
	
	private static int counter = 0;
	
	private int id;
	private ArrayList<Activity> activities;
	private ArrayList<ArrayList<Resource>> assignedRes;
	
	public Solution(){
		this.id = counter++;
		this.activities = new ArrayList<Activity>();
		this.assignedRes = new ArrayList<ArrayList<Resource>>();
	}
	
	public Solution(Solution s){
		this.id  = s.getId();
		this.activities = new ArrayList<Activity>(s.getActivities());
		this.assignedRes = new ArrayList<ArrayList<Resource>>(s.getAssignedRes());
	}
	
	public static int getCounter(){
		return counter;
	}
	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public void setAssignedRes(ArrayList<ArrayList<Resource>> assignedRes) {
		this.assignedRes = assignedRes;
	}

	public Solution(ArrayList<Activity> act, ArrayList<ArrayList<Resource>> res){
		this.activities = act;
		this.assignedRes = res;
		this.id = counter++;
	}
	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public ArrayList<ArrayList<Resource>> getAssignedRes() {
		return assignedRes;
	}
	
	public void addResource(Resource r, int actIndex){
		this.assignedRes.get(actIndex).add(r);
	}
	public void addActivity(Activity a){
		this.activities.add(a);
	}
	public void popActivity(){
		this.activities.remove(this.activities.size()-1);
	}
	public String toString(){
		String str = new String();
		str += "Solution "+this.id+"\n";
		str += "Activities : ";
		for(Activity a : this.activities ){
			str += a.getId() +" ";
		}
		str += "\n";
		for(int i= 0;i< this.assignedRes.size();i++){
			str += "Resource for "+i+": "+ this.assignedRes.get(i);
		}
		return str;
	}


	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {

		Solution other = (Solution) obj;
		if (other == this) return true;
		if (id != other.id)
			return false;
		return true;
	}
	
}
