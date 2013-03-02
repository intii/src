package AlgorithmExecution;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import Crosser.AbsCrosser;
import FitnessCalculator.AbsFitnessCalculator;
import InitialPopulator.InitialPopulator;
import Mutator.AbsMutator;
import ParentSelector.AbsParentSelector;
import PopulationReplacer.AbsPopulationReplacer;
import Representation.SolutionsPrinter;
import Structure.Pair;
import Structure.Solution;


public class Solver {

	private AbsCrosser crosser;
	private AbsMutator mutator;
	private AbsParentSelector parentSelector;
	private InitialPopulator initialPopulator;
	private AbsPopulationReplacer populationReplacer;
	private AbsFitnessCalculator fitnessCalculator;
	
	private int numberInitialSolutions;
	private int cutCondition;
	
	public int getCutCondition() {
		return cutCondition;
	}

	public void setCutCondition(int cutCondition) {
		this.cutCondition = cutCondition;
	}

	public void setCrosser(AbsCrosser crosser) {
		this.crosser = crosser;
	}

	public void setMutator(AbsMutator mutator) {
		this.mutator = mutator;
	}

	public void setParentSelector(AbsParentSelector parentSelector) {
		this.parentSelector = parentSelector;
	}

	public void setInitialPopulator(InitialPopulator initialPopulator) {
		this.initialPopulator = initialPopulator;
	}
	
	public void setPopulationReplacer(AbsPopulationReplacer populationReplacer) {
		this.populationReplacer = populationReplacer;
	}

	public void setNumberInitialSolutions(int numberInitialSolutions) {
		this.numberInitialSolutions = numberInitialSolutions;
	}

	public ArrayList<Solution> solve(Document doc){
		//Generate a given number of initial solutions
		ArrayList<Solution> current = new ArrayList<Solution>(this.numberInitialSolutions);
		int j=0;
		System.out.println("initial solutions : "+ this.numberInitialSolutions);
		current = this.initialPopulator.getNInitialSolutions(this.numberInitialSolutions);
		//System.out.println(current);
		while(j < this.cutCondition ){
			//make parent couples
//			System.out.println("==============================================");
//			System.out.println("Parent Selection");
//			System.out.println("==============================================");
			ArrayList<Pair> parents = this.parentSelector.pairMaker(current);
			ArrayList<Solution> offspring = new ArrayList<Solution>();
			//Cross parent couples
			for(int i = 0; i < parents.size(); i++ ){
				offspring.addAll(this.crosser.cross(parents.get(i)));
			}
			//mutate offspring solutions
			for(int i = 0; i< offspring.size(); i++){
				this.mutator.mutate(offspring.get(i));
			}
			this.fitnessCalculator.updateFitness(offspring);
			current = this.populationReplacer.replace(parents, offspring);
			/*
			 * Output current solutions to a Document element
			 */
			String output = SolutionsPrinter.printSolutionSet(current, fitnessCalculator, j);
			System.out.println(output);
			try {
				doc.insertString(doc.getLength(), output, null);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			
			j++;
		}
		return current;
	}

	public void setFitnessCalculator(AbsFitnessCalculator fitnessCalculator) {
		this.fitnessCalculator = fitnessCalculator;
	}
}
