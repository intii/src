package Structure;

public class Activity {
	
	private int duration;
	private int id;
	private int[] resources;
	
	public Activity(int i){
		this.id= i;
	}
	
	public Activity(int i,int d, int[] r){
		this.id= i;
		this.duration=d;
		this.resources=r;
	}

	public int getDuration() {
		return duration;
	}

	public int getId() {
		return id;
	}

	public int[] getResources() {
		return resources;
	}
	
	public String toString(){
		String str=new String();
		str+="Activity "+this.id+ " || Duration= "+ this.duration +" R= "+ this.resources[0] +this.resources[1] +this.resources[2] +this.resources[3] ;
		return str;
	}
	
	public boolean equals(Object o){
		Activity a = (Activity)o;
		return ( a.id == this.id);
	}
	
}
