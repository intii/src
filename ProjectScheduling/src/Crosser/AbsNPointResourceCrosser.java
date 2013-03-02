package Crosser;

import java.util.ArrayList;

import Structure.Activity;
import Structure.Resource;

public abstract class AbsNPointResourceCrosser implements AbsResourceCrosser{

	public AbsNPointResourceCrosser(){}
	
	protected void completeChild(ArrayList<ArrayList<Resource>> child, ArrayList<ArrayList<Resource>> p, int begin, int end) {
		for(int i = begin; i < end; i++)
			child.add(i, p.get(i));
	}

	@Override
	public abstract ArrayList<ArrayList<ArrayList<Resource>>> getChildResources(
		ArrayList<ArrayList<Resource>> r1, ArrayList<ArrayList<Resource>> r2);
}
