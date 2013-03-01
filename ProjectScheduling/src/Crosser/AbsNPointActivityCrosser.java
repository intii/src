package Crosser;

import java.util.ArrayList;

import Structure.Activity;

public abstract class AbsNPointActivityCrosser implements AbsActivityCrosser {

	public AbsNPointActivityCrosser() {
	}

	protected void completeChild(ArrayList<Activity> child, ArrayList<Activity> p, int begin, int end) {
		int j = 0;
		while (begin < end) {
			if (!child.contains(p.get(j))) {
				child.add(begin, p.get(j));
				begin++;
			}
			j++;
		}
	}

	public abstract ArrayList<ArrayList<Activity>> getChildActivities(
			ArrayList<Activity> p1, ArrayList<Activity> p2);

}
