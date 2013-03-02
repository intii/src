package Structure;


public class ScheduledActivity {

	private Activity activity;
	private int id;
	private int startTime;
	private int endTime;
	
	public ScheduledActivity( Activity a){
		this.activity = a;
		this.id = a.getId();
	}
	
	public ScheduledActivity(int id){
		this.id = id;
		
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return " id=" + id ;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public boolean equals(Object o){
		ScheduledActivity sa = (ScheduledActivity)o;
		return ( sa.id == this.id);
	}
}
