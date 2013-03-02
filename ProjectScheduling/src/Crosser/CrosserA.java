package Crosser;

import java.util.ArrayList;

import Structure.Activity;
import Structure.Pair;
import Structure.Resource;
import Structure.Solution;


public class CrosserA extends AbsCrosser {
	
    
    public CrosserA(AbsActivityCrosser ac, AbsResourceCrosser rc, double pc){
		super(ac, rc, pc);
	}
    
    
	@Override
	public ArrayList<Solution> cross(Pair p) {
		Solution s1 = p.getS1();
		Solution s2 = p.getS2();
		ArrayList<Solution> children = new ArrayList<Solution>();
		double v = Math.random();
		if(v > this.pc){
			//Copy the parents
			Solution child1 = new Solution(s1);
			Solution child2 = new Solution(s2);
			children.add(child1);
			children.add(child2);
			return children; 
		}
		ArrayList<ArrayList<ArrayList<Resource>>> childrenResources = this.rc.getChildResources(s1.getAssignedRes(), s2.getAssignedRes());
		ArrayList<ArrayList<Activity>> childrenActivities = this.ac.getChildActivities(s1.getActivities(), s2.getActivities());
		for(int i = 0; i < childrenActivities.size(); i++){
			Solution child = new Solution(childrenActivities.get(i), childrenResources.get(i));
			children.add(child);
		}
		
		return children;
	}


}
