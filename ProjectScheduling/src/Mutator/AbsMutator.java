package Mutator;
import DataHandler.SolutionHandler;
import InitialPopulator.InitialPopulator;
import Structure.Solution;


public abstract class AbsMutator {

	protected AbsActivityMutator am;
	protected AbsResourceMutator rm;
	
	public AbsMutator( AbsActivityMutator am, AbsResourceMutator rm ){
		this.am = am;
		this.rm = rm;
	}
	
	public abstract void mutate(Solution s);

	
}
