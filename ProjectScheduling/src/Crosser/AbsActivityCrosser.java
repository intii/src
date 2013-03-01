package Crosser;

import java.util.ArrayList;

import Structure.Activity;

public interface AbsActivityCrosser {
	
	public abstract ArrayList<ArrayList<Activity>> getChildActivities(ArrayList<Activity> p1, 
			ArrayList<Activity> p2);

	
}
