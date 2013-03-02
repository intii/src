package PopulationReplacer;

import java.util.ArrayList;

import FitnessCalculator.AbsFitnessCalculator;
import Structure.Pair;
import Structure.Solution;


public class PopulationReplacerA  extends AbsPopulationReplacer{

	public PopulationReplacerA(AbsFitnessCalculator fc){
		super(fc);
	}
	private void initMatrix(double[][] mat){
		for(int h = 0;h<mat.length;h++)
			for(int m = 0;m<mat.length;m++)
				mat[h][m] = 0;
	}
	private void standardCaseReplace(int h1, int h2, int p1, int p2, ArrayList<Solution> result,ArrayList<Solution> o, ArrayList<Solution> p){
		if(this.fc.isBetter(o.get(h1), p.get(p1)))
			result.add(o.get(h1));
		else
			result.add(p.get(p1));
		if(this.fc.isBetter(o.get(h2), p.get(p2)))
			result.add(o.get(h2));
		else
			result.add(p.get(p2));
	}
	private void ExeptionalCaseReplace(double[][] distance,int h1, int h2, int p1, int p2, ArrayList<Solution> result,ArrayList<Solution> o, ArrayList<Solution> p){
		result.add(p.get(p2));
		if(this.fc.isBetter(o.get(h1), p.get(p1)) && this.fc.isBetter(p.get(p1), o.get(h2)) )
			result.add(o.get(h1));
		else 
			if (this.fc.isBetter(o.get(h2), p.get(p1)) && this.fc.isBetter(p.get(p1), o.get(h1)))
				result.add(o.get(h2));
			else
				if(this.fc.isBetter(o.get(h2), p.get(p1)) && this.fc.isBetter(o.get(h1), p.get(p1))){
					if(distance[h2][p1] < distance[h1][p1])
						result.add(o.get(h2));
					else
						if(distance[h2][p1] > distance[h1][p1])
							result.add(o.get(h1));
						else
							if(Math.random() <= 0.5)
								result.add(o.get(h1));
							else
								result.add(o.get(h2));
				}else
					result.add(p.get(p1));
	}
	public ArrayList<Solution> replace(ArrayList<Pair> parents, ArrayList<Solution> offspring){
		ArrayList<Solution> result = new ArrayList<Solution>();
		/* closest matrix and distance matrix
		 * ____________
		 * __|_P1_|_P2|
		 * H1|____|___|	
		 * H2|____|___|	
		 */	
//		int [][] closest = new int[2][2];
//		int [][] distance = new int[2][2];
		double [][] distance = new double[2][2];
		double [][] closest = new double[2][2];
		int j = 0;
//		fc.updateFitness(offspring);
		for(int i = 0; i < parents.size() ; i++){
			this.initMatrix(closest);
			this.initMatrix(distance);
			Solution P1 = parents.get(i).getS1();
			Solution P2 = parents.get(i).getS2();
			Solution H1 = offspring.get(j++);
			Solution H2 = offspring.get(j++);
			ArrayList<Solution> p = new ArrayList<Solution>();
			p.add(P1);
			p.add(P2);
			ArrayList<Solution> o = new ArrayList<Solution>();
			o.add(H1);
			o.add(H2);
			//get the distance between each child with their parents
			for(int h = 0;h<closest.length;h++)
				for(int m = 0;m<closest.length;m++)
					distance[h][m] = Math.abs(this.fc.getFitness(o.get(h))-  this.fc.getFitness(p.get(m)));
			//get the closest parent for each child
			for(int h = 0;h<closest.length;h++){
				if(distance[h][0] <= distance [h][1])
					closest[h][0] = 1;
				else
					closest[h][1] = 1;
			}	
			//select the next population based on closeness and fitness
			//normal cases
			if( closest[0][0] == 1 && closest[1][1] == 1 ){
				this.standardCaseReplace(0, 1, 0, 1, result, o, p);
			}else
				if( closest[0][1] == 1 && closest[1][0] == 1 ){
					this.standardCaseReplace(0, 1, 1, 0, result, o, p);
				}else
				
					//exceptional cases
					if(closest[0][1] == 1 && closest[1][1] == 1){
						this.ExeptionalCaseReplace(distance, 0, 1, 1, 0, result, o, p);
					}else
						if(closest[0][0] == 1 && closest[1][0] == 1){
							this.ExeptionalCaseReplace(distance, 0, 1, 0, 1, result, o, p);
						}
		}
		return result;
	}
}
