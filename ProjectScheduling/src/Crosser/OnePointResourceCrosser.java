package Crosser;

import java.util.ArrayList;

import Structure.Resource;

public class OnePointResourceCrosser extends AbsNPointResourceCrosser{
	
	private int nActivity;
	
	public OnePointResourceCrosser(int act){
		super();
		this.nActivity = act;
	}

	@Override
	public ArrayList<ArrayList<ArrayList<Resource>>> getChildResources(
			ArrayList<ArrayList<Resource>> r1, ArrayList<ArrayList<Resource>> r2) {
		
		ArrayList<ArrayList<Resource>> child1= new ArrayList<ArrayList<Resource>>(this.nActivity);
    	ArrayList<ArrayList<Resource>> child2= new ArrayList<ArrayList<Resource>>(this.nActivity);
    	int point = (int) (Math.random() * this.nActivity);
    	this.completeChild(child1, r1, 0, point);
    	this.completeChild(child1, r2, point, nActivity);
    	this.completeChild(child2, r2, 0, point);
    	this.completeChild(child2, r1, point, nActivity);
    	ArrayList<ArrayList<ArrayList<Resource>>> result= new ArrayList<ArrayList<ArrayList<Resource>>>();
        result.add(child1);
        result.add(child2);
        return result;
	}

}
