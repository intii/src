
public abstract class AbsSolutionDecoder {

	protected SolutionHandler sh;
	
	public AbsSolutionDecoder(SolutionHandler sh){
		this.sh =  sh;
	}
	public abstract DecodedSolution getSchedule(Solution s);
}
