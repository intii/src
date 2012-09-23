import java.util.ArrayList;


public class PopulationReplacerA  extends AbsPopulationReplacer{

	public PopulationReplacerA(AbsFitnessCalculator fc){
		super(fc);
	}
	private void initMatrix(int[][] mat){
		for(int h = 0;h<mat.length;h++)
			for(int m = 0;m<mat.length;m++)
				mat[h][m] = 0;
	}
	private void standardCaseReplace(int a, int b, int c, int d, ArrayList<Solution> result,ArrayList<Solution> o, ArrayList<Solution> p){
		if(this.fc.isBetter(o.get(a), p.get(b)))
			result.add(o.get(a));
		else
			result.add(p.get(b));
		if(this.fc.isBetter(o.get(c), p.get(d)))
			result.add(o.get(c));
		else
			result.add(p.get(d));
	}
	private void ExeptionalCaseReplace(int[][] distance,int a, int b, int c, int d, ArrayList<Solution> result,ArrayList<Solution> o, ArrayList<Solution> p){
		result.add(p.get(d));
		if(this.fc.isBetter(o.get(a), p.get(c)) && this.fc.isBetter(p.get(c), o.get(b)) )
			result.add(o.get(a));
		else 
			if (this.fc.isBetter(o.get(b), p.get(c)) && this.fc.isBetter(p.get(c), o.get(a)))
				result.add(o.get(b));
			else
				if(this.fc.isBetter(o.get(b), p.get(c)) && this.fc.isBetter(o.get(a), p.get(c))){
					if(distance[b][c] < distance[a][c])
						result.add(o.get(b));
					else
						if(distance[b][c] > distance[a][c])
							result.add(o.get(a));
						else
							if(Math.random() <= 0.5)
								result.add(o.get(a));
							else
								result.add(o.get(b));
				}else
					result.add(p.get(d));
	}
	public ArrayList<Solution> replace(ArrayList<Pair> parents, ArrayList<Solution> offspring){
		ArrayList<Solution> result = new ArrayList<Solution>();
		/* closest matrix and distance matrix
		 * ____________
		 * __|_P1_|_P2|
		 * H1|____|___|	
		 * H2|____|___|	
		 */	
		int [][] closest = new int[2][2];
		int [][] distance = new int[2][2];
		int j = 0;
		fc.updateFitness(offspring);
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
					distance[h][m] = Math.abs(Integer.parseInt(this.fc.getFitness(o.get(h)))- Integer.parseInt( this.fc.getFitness(p.get(m))));
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
				this.standardCaseReplace(0, 0, 1, 1, result, o, p);
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
