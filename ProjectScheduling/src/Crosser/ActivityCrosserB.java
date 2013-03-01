package Crosser;

import java.util.ArrayList;

import Structure.Activity;

public class ActivityCrosserB extends AbsNPointActivityCrosser{
	
private int nActivity;
    
    public ActivityCrosserB(int act){
    	super();
    	this.nActivity = act;
    }

	
	@Override
	public ArrayList<ArrayList<Activity>> getChildActivities(ArrayList<Activity> p1, ArrayList<Activity> p2) {
		
		int point1, point2, pointAux;
		point1 = (int) (Math.random()*this.nActivity);
		point2 = (int) (Math.random()*this.nActivity);
		if( point1 > point2){
			pointAux = point2;
			point2 = point1;
			point1 = pointAux;
		}
        ArrayList<Activity> child1= new ArrayList<Activity>(this.nActivity);
        ArrayList<Activity> child2= new ArrayList<Activity>(this.nActivity);
        int i;
        for( i=0 ; i < point1; i++){
            child1.add(i, p1.get(i));
            child2.add(i, p2.get(i));
        }
        this.completeChild(child1, p2, i, point2);
        this.completeChild(child1, p2, point2, this.nActivity);
        this.completeChild(child2, p1, i, point2);
        this.completeChild(child2, p1, point2, this.nActivity);
        ArrayList<ArrayList<Activity>> result= new ArrayList<ArrayList<Activity>>();
        result.add(child1);
        result.add(child2);
        return result;
	}
	
	

}
