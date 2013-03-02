package Crosser;

import java.util.ArrayList;

import Structure.Resource;

public class ResourceCrosserA implements AbsResourceCrosser{
	
	private int nActivity;
	private double discriminant;
	
	public ResourceCrosserA(int nAct, double dis){
		this.nActivity = nAct;
		this.discriminant = dis;
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

}
