package Structure;

public class Pair {

	private Solution s1;
	private Solution s2;
	
	public Pair(Solution s1, Solution s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	
	public Solution getS1() {
		return s1;
	}
	public Solution getS2() {
		return s2;
	}
	
	@Override
	public String toString(){
		return this.s1.getId()+" || "+this.s2.getId();
	}
}
