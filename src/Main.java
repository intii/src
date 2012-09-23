import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Iniciando...");
		int nInitialSolutions = 50;
		int cutCondition = 800;
		int nActivities = 32;
		int nResources = 103;
		int nSkills = 4;
		double discriminant = 0.5;
		double pc = 0.9;
		double pm = 0.1;
		System.out.println("*********************************************************");
		System.out.println("*********************CONDITIONS**************************");
		System.out.println("** Initial Solutions = "+nInitialSolutions);
		System.out.println("** Number of cycles = "+cutCondition);
		System.out.println("** Number of Activities = "+nActivities);
		System.out.println("** Number of Resources = "+nResources);
		System.out.println("** Number of Skills = "+nSkills);
		System.out.println("** Cross discriminant = "+discriminant);
		System.out.println("** Probability of cross = "+pc);
		System.out.println("** Probability of mutation = "+pm);
		System.out.println("*********************************************************");
		SolutionHandler sh = new SolutionHandler(nActivities, nResources, nSkills);
		AbsSolutionDecoder sd = new SolutionDecoderA(sh);
		InitialPopulator ip = new InitialPopulator(sh);
		AbsFitnessCalculator fc = new FitnessCalculatorMS(sd);
		AbsParentSelector ps = new ParentSelectorB(fc);
		MutatorA m = new MutatorA(sh,ip);
		m.setPm(pm);
		CrosserA cr = new CrosserA(nActivities, discriminant,pc);
		AbsPopulationReplacer pr = new PopulationReplacerA(fc);
		Solver solv = new Solver();
		solv.setCrosser(cr);
		solv.setInitialPopulator(ip);
		solv.setMutator(m);
		solv.setNumberInitialSolutions(nInitialSolutions);
		solv.setParentSelector(ps);
		solv.setPopulationReplacer(pr);
		solv.setCutCondition(cutCondition);
		ArrayList<Solution> result = solv.solve();
		for(Solution s : result){
			System.out.println(s);
			System.out.println("Duration Fitness = " + fc.getFitness(s));
			System.out.println();
		}
	}
}