
public class AndFilter implements IFilter{
	
	private IFilter f1;
	private IFilter f2;
		
	public AndFilter(IFilter f1, IFilter f2) {
		this.f1 = f1;
		this.f2 = f2;
	}

	@Override
	public boolean isValid(DecodedSolution ds, Resource r) {
		return ( f1.isValid(ds, r) && f2.isValid(ds, r) );
	}

}
