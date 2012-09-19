
public class EqualFilter implements IFilter{
	
	private int comp;

	public EqualFilter(int comp) {
		this.comp = comp;
	}

	public boolean isValid(DecodedSolution ds, Resource r) {
		return (r.getSkill() == this.comp);
	}

}
