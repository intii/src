package Structure;

public class Resource {
	private int id;
	//TODO arrays of skills and effectivness
	private int skill;
	private double effectivness;

	public Resource(int id, int skill, double effectivness) {
		this.id = id;
		this.skill = skill;
		this.effectivness = effectivness;
	}

	public int getId() {
		return id;
	}

	public int getSkill() {
		return skill;
	}

	public double getEffectivness() {
		return effectivness;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", skill=" + skill + ", effectivness="
				+ effectivness + "]";
	}
	
	
}


