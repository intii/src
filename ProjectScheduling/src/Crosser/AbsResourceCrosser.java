package Crosser;

import java.util.ArrayList;

import Structure.Resource;

public interface AbsResourceCrosser {
	
	public abstract ArrayList<ArrayList<ArrayList<Resource>>> getChildResources(ArrayList<ArrayList<Resource>> r1,
			ArrayList<ArrayList<Resource>> r2);
	
}
