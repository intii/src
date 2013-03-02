package Filter;
import Structure.Resource;


public class EqualFilter implements IFilter{
	
	private int comp;

	public EqualFilter(int comp) {
		this.comp = comp;
	}

	public boolean isValid( Resource r) {
		return (r.getSkill() == this.comp);
	}

}
