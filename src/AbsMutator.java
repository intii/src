
public abstract class AbsMutator {

	protected double pm = 0.2;
	
	public abstract void mutate(Solution s);

	public void setPm(double pm) {
		this.pm = pm;
	} 
	
}
