import java.io.*;
import java.util.*;

import Crosser.AbsActivityCrosser;
import Crosser.AbsCrosser;
import Crosser.AbsResourceCrosser;
import Crosser.ActivityCrosserA;
import Crosser.ActivityCrosserB;
import Crosser.CrosserA;
import Crosser.OnePointResourceCrosser;
import Crosser.ResourceCrosserA;
import DataHandler.SolutionHandler;
import FitnessCalculator.AbsFitnessCalculator;
import FitnessCalculator.FitnessCalculatorEf;
import FitnessCalculator.FitnessCalculatorMS;
import FitnessCalculator.FitnessCalculatorMulti;
import InitialPopulator.InitialPopulator;
import Measures.AccuracyMeasure;
import Measures.DistributionMeasure;
import Measures.IMeasure;
import Measures.SizeMeasure;
import Mutator.AbsActivityMutator;
import Mutator.AbsMutator;
import Mutator.AbsResourceMutator;
import Mutator.ActivityMutatorA;
import Mutator.ActivityMutatorAdj;
import Mutator.MutatorA;
import Mutator.ResourceMutatorA;
import ParentSelector.*;
import PopulationReplacer.AbsPopulationReplacer;
import PopulationReplacer.PopulationReplacerB;
import SolutionDecoder.AbsSolutionDecoder;
import SolutionDecoder.SolutionDecoderA;
import Structure.Solution;
public class Main {

	public static void main(String[] args) throws IOException {
		
		/*System.out.println("Iniciando...");
		int nInitialSolutions = 50;
		int cutCondition = 100;
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
		AbsFitnessCalculator fc1 = new FitnessCalculatorMS(sd);
		AbsFitnessCalculator fc2 = new FitnessCalculatorEf(sd);
		AbsFitnessCalculator fc = new FitnessCalculatorMulti(sd,fc1,fc2);
		AbsParentSelector ps = new ParentSelectorB(fc);
		AbsActivityMutator am = new ActivityMutatorAdj(sh, pm);
		AbsResourceMutator rm = new ResourceMutatorA(pm, sh, ip);
		AbsMutator m = new MutatorA(am,rm);
		AbsActivityCrosser ac = new ActivityCrosserB(nActivities);
		AbsResourceCrosser ar = new OnePointResourceCrosser(nActivities);
		AbsCrosser cr = new CrosserA(ac, ar, pc);
		AbsPopulationReplacer pr = new PopulationReplacerB(fc);
		Solver solv = new Solver();
		solv.setFitnessCalculator(fc);
		solv.setCrosser(cr);
		solv.setInitialPopulator(ip);
		solv.setMutator(m);
		solv.setNumberInitialSolutions(nInitialSolutions);
		solv.setParentSelector(ps);
		solv.setPopulationReplacer(pr);
		solv.setCutCondition(cutCondition);
		ArrayList<Solution> result = solv.solve();
		ParetoSet pareto = new ParetoSet(fc,result);
		ArrayList<Solution> resultPareto = pareto.getSet();
		for(Solution s : result){
			SolutionsPrinter.printOneSolution(s, fc);
		}
		System.out.println("====================================================");
		System.out.println("====================PARETO SET =====================");
		System.out.println("====================================================");
		
		//fc.fitnessLoader(resultPareto);
		
		for(Solution s : resultPareto){
			SolutionsPrinter.printOneSolution(s, fc);
		}
		System.out.println("====================================================");
		System.out.println("==================== MEASURES ======================");
		System.out.println("====================================================");
		
		System.out.println("ACCURACY MEASURE : ");
		IMeasure measure = new AccuracyMeasure(44.0, 30.0, fc1, fc2);
		System.out.println(measure.measure(result));
		
		System.out.println("SIZE MEASURE : ");
		IMeasure measure2 = new SizeMeasure();
		System.out.println(measure2.measure(result));
		
		System.out.println("DISTRIBUTION MEASURE : ");
		IMeasure measure3 = new DistributionMeasure( fc1, fc2);
		System.out.println(measure3.measure(result));*/
	}
}