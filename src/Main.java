import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Iniciando...");
//		StructMaker sm= new StructMaker(32, 103, 4);
//		ArrayList<Activity> a=sm.getListAct();
//		System.out.println("Lista de Actividades");
//		System.out.println("tamaño:" + a.size());
//		for(int i=0;i< a.size();i++){
//			System.out.println(a.get(i));
//		}
//		boolean[][] precedence=sm.getPrecedences();
//		System.out.println("/n/n Matriz de precedenia");
//		for(int i=0;i < precedence.length; i++){
//			for(int j=0;j < precedence.length; j++){
//				System.out.print(" " + precedence[i][j]+ " ");
//			}
//			System.out.println();
//		}
//		ArrayList<Resource> r=sm.getResources();
//		System.out.println("/n/n Lista de Recursos");
//		System.out.println("tamaño:" + a.size());
//		for(int i=0;i< r.size();i++){
//			System.out.println(r.get(i));
//		}
		SolutionHandler sh = new SolutionHandler(32, 103, 4);
		InitialPopulator ip = new InitialPopulator(sh);
		FitnessCalculator fc = new FitnessCalculator(sh);
		ParentSelector ps = new ParentSelector(fc);
		MutatorA m = new MutatorA(sh,ip);
		CrosserA cr = new CrosserA(32, 0.5);
		PopulationReplacer pr = new PopulationReplacer(fc);
		Solver solv = new Solver(sh);
		solv.setCrosser(cr);
		solv.setFitnessCalculator(fc);
		solv.setInitialPopulator(ip);
		solv.setMutator(m);
		solv.setNumberInitialSolutions(50);
		solv.setParentSelector(ps);
		solv.setPopulationReplacer(pr);
		solv.setCutCondition(500);
		ArrayList<Solution> result = solv.solve();
		for(Solution s : result){
			System.out.println(s);
			System.out.println("Duration Fitness" + fc.getDuration(s, sh));
			System.out.println("Effectiveness Fitness" + fc.getEffectiveness(s));
		}
//		System.out.println("---------------------------INITIAL SOLUTION------------------------");
		
//		Solution sm = m.mutate(s1);
//		System.out.println("-------------------------MUTATED SOLUTION------------------------");
//		System.out.println(sm);
		
//		System.out.println("-------------------------CROSSED SOLUTION------------------------");
		
//		ArrayList<Solution> sc = cr.cross(s1, s2);
//		for(int i=0;i< sc.size();i++){
//			System.out.println("Solucion hijo" +i + " : "+sc.get(i));
//	}
//		ArrayList<Solution> solutions = ip.getNInitialSolutions(6);
//		System.out.println("-------------------------INITIAL POPULATION------------------------");
//		for(int i=0;i< solutions.size();i++){
//			System.out.println(solutions.get(i));
//			System.out.println("Duration Fitness: "+fc.getDuration(solutions.get(i), sh));
//			System.out.println("Effectiveness Fitness: "+fc.getEffectiveness(solutions.get(i)));
//		}
//		ArrayList<Pair> parents = ps.pairMaker(solutions);
//		ArrayList<Solution> offspring = new ArrayList<Solution>();
//		//Cross parent couples
//		for(int i = 0; i < parents.size(); i++ ){
//			offspring.addAll(cr.cross(parents.get(i)));
//			System.out.println("papa "+parents.get(i).getS1().getId());
//			System.out.println("mama "+parents.get(i).getS2().getId());
//		}
//		System.out.println("-------------------------CROSSED SOLUTION------------------------");
//		for(int i=0;i< offspring.size();i++){
//			System.out.println(offspring.get(i));
//			System.out.println("Duration Fitness: "+fc.getDuration(offspring.get(i), sh));
//			System.out.println("Effectiveness Fitness: "+fc.getEffectiveness(offspring.get(i)));
//		}
//		//mutate offspring solutions
//		System.out.println("-------------------------MUTATED SOLUTION------------------------");
//		for(int i = 0; i< offspring.size(); i++){
//			m.mutate(offspring.get(i));
//		}
//		for(int i=0;i< offspring.size();i++){
//			System.out.println(offspring.get(i));
//			System.out.println("Duration Fitness: "+fc.getDuration(offspring.get(i), sh));
//			System.out.println("Effectiveness Fitness: "+fc.getEffectiveness(offspring.get(i)));
//		}
		
//		solutions = pr.replace(parents, offspring);
//		System.out.println("-------------------------NEXT GENERATION------------------------");
//		for(int i =0 ; i < solutions.size() ; i++){
//			System.out.println(solutions.get(i));
//			System.out.println("Duration Fitness: "+fc.getDuration(solutions.get(i), sh));
//			System.out.println("Effectiveness Fitness: "+fc.getEffectiveness(solutions.get(i)));
//		}
	}
}