import java.util.ArrayList;


public class CrosserA implements ICrosser {
    private int nActivity;
    private double discriminant;
    
    public CrosserA(int activities, double dis){
        this.nActivity = activities;
        this.discriminant = dis;
        
    }
    
    private void completeChild(ArrayList<Activity> child , ArrayList<Activity> p, int rest){
        int j = 0;
        while( rest < this.nActivity ){
            if( !child.contains( p.get(j) ) ){
                child.add( rest, p.get(j) );
                rest++;
            }
            j++;
        }        
    }
    
    public ArrayList<ArrayList<Activity>> getChildActivities(ArrayList<Activity> p1, ArrayList<Activity> p2){
        int point = (int) (Math.random()*this.nActivity);
        ArrayList<Activity> child1= new ArrayList<Activity>(this.nActivity);
        ArrayList<Activity> child2= new ArrayList<Activity>(this.nActivity);
        int i;
        for( i=0 ; i < point; i++){
            child1.add(i, p1.get(i));
            child2.add(i, p2.get(i));
        }
        completeChild(child1,p2,i);
        completeChild(child2,p1,i);
        ArrayList<ArrayList<Activity>> result= new ArrayList<ArrayList<Activity>>();
        result.add(child1);
        result.add(child2);
        return result;
    }
    
    
    
    public ArrayList<ArrayList<ArrayList<Resource>>> getChildResources(ArrayList<ArrayList<Resource>> r1, ArrayList<ArrayList<Resource>> r2){
    	ArrayList<ArrayList<Resource>> child1= new ArrayList<ArrayList<Resource>>(this.nActivity);
    	ArrayList<ArrayList<Resource>> child2= new ArrayList<ArrayList<Resource>>(this.nActivity);
    	//Inicializo las listas de recurso para cada actividad
		for(int i=0;i<this.nActivity;i++){
			ArrayList<Resource> aux1 = new ArrayList<Resource>();
			ArrayList<Resource> aux2 = new ArrayList<Resource>();
			child1.add(aux1);
			child2.add(aux2);
		}
        double v;
        for(int i=0 ; i < this.nActivity; i++){
            v = Math.random();
            if(v <= this.discriminant){
            	child1.get(i).addAll(r1.get(i));
            	child2.get(i).addAll(r2.get(i));
            }else{
            	child1.get(i).addAll(r2.get(i));
            	child2.get(i).addAll(r1.get(i));
            }
        }
        ArrayList<ArrayList<ArrayList<Resource>>> result= new ArrayList<ArrayList<ArrayList<Resource>>>();
        result.add(child1);
        result.add(child2);
        return result;
    }

	@Override
	public ArrayList<Solution> cross(Pair p) {
		//TODO probability of crossing, si no se cruzan se generan copias de los padres pero NO SE CUENTAN!!!
		Solution s1 = p.getS1();
		Solution s2 = p.getS2();
		ArrayList<Solution> children = new ArrayList<Solution>();
		ArrayList<ArrayList<ArrayList<Resource>>> childrenResources = this.getChildResources(s1.getAssignedRes(), s2.getAssignedRes());
		ArrayList<ArrayList<Activity>> childrenActivities = this.getChildActivities(s1.getActivities(), s2.getActivities());
		for(int i = 0; i < childrenActivities.size(); i++){
			Solution child = new Solution(childrenActivities.get(i), childrenResources.get(i));
			children.add(child);
		}
		
		return children;
	}


}
