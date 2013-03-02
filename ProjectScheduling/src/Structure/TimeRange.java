package Structure;


public class TimeRange {

	private int start;
	private int end;
	private Activity a;
	
	public TimeRange(int s, int e,Activity a){
		this.start = s;
		this.end = e;
		this.a=a;
	}

	public int getStart() {
		return start;
	}
	
	public Activity getA(){
		return this.a;
	}
	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	public boolean isBetween(int s, int e){
			if( s > this.start && s < this.end) return true;
			if( e > this.start && e < this.end) return true;
			if( s < this.start && e > this.end) return true;
		return false;
	}
}
