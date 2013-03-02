package Crosser;

import java.util.ArrayList;

import Structure.Activity;

public class ActivityCrosserA extends AbsNPointActivityCrosser {

	private int nActivity;

	public ActivityCrosserA(int act) {
		super();
		this.nActivity = act;
	}

	public ArrayList<ArrayList<Activity>> getChildActivities(
			ArrayList<Activity> p1, ArrayList<Activity> p2) {
		int point = (int) (Math.random() * this.nActivity);
		ArrayList<Activity> child1 = new ArrayList<Activity>(this.nActivity);
		ArrayList<Activity> child2 = new ArrayList<Activity>(this.nActivity);
		int i;
		for (i = 0; i < point; i++) {
			child1.add(i, p1.get(i));
			child2.add(i, p2.get(i));
		}
		this.completeChild(child1, p2, i, this.nActivity);
		this.completeChild(child2, p1, i, this.nActivity);
		ArrayList<ArrayList<Activity>> result = new ArrayList<ArrayList<Activity>>();
		result.add(child1);
		result.add(child2);
		return result;
	}

}
