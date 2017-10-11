package examples;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import Utilities.DeceptionGameHelper;
import models.DeceptionGame;
import models.ObservableConfiguration;
import models.Systems;
import solvers.GameSolver;
import solvers.GreedyMaxMinSolver;
import solvers.HeuristicSolver;
import solvers.LinearGameSolver;
import solvers.MarginalSolver;
import solvers.PureStrategySolver;

public class SimpleDeceptionAllSolvers {

	public static void main(String[] args) throws Exception {
		DeceptionGame g = new DeceptionGame();
		// g.generateGame(3, 2, 3);
		int numConfigs = 20;
		int numObs = 10;
		int numSystems = 20;
		//seed = 101 has some issues in returning the right strategy for numSystems = 5, numObs = 2, and numConfigs = 5
		//seed = 103 creates a perfect case for when heuristic doesn't run well, numS = 4, numO = 3, numC = 3
		//seed = 113 creates issues for objective MILP value, numS = 20, numO = 10, numC = 20
		
		//whenever all machines can be assigned to same observable, heuristic doesn't work, otherwise it seems to work
		long seed = 113; 
		g.generateGame(numConfigs, numObs, numSystems, seed);
		if (false)
			g.printGame();
		

		//runSampleLinearGame(g, numConfigs, numObs, numSystems, seed);
		//runSampleGame(g, numConfigs, numObs, numSystems, seed);
		runMarginalSolver(g, numConfigs, numObs, numSystems, seed);
		
		System.out.println();
		System.out.println();
		//runHeuristicSolver(g);
		
		//runGreedyMaxMinSolver(g);
		//Should write a greedymaxmin solver that works with a general set of constraints
		//Also should incorporate a partial strategy then greedy max min assigning the rest
		
	}
	
	public static void runGreedyMaxMinSolver(DeceptionGame g){
		System.out.println("Runnning Greedy Max Min Solver");
		
		GreedyMaxMinSolver solver = new GreedyMaxMinSolver(g);
		
		solver.solve();
		
		
	}
	
	public static void runHeuristicSolver(DeceptionGame g){
		
		System.out.println("Running Heuristic Solver");
		
		HeuristicSolver solver = new HeuristicSolver(g);
		
		solver.solve();
		
	}
	
	public static void runMarginalSolver(DeceptionGame g, int numConfigs, int numObservables, int numSystems, long seed) throws Exception {
		boolean verbose = false;

		// Need to load cplex libraries
		String cplexInputFile = "CplexConfig";

		DeceptionGameHelper.loadLibrariesCplex(cplexInputFile);

		//Need to create bounds!
		// Solve the MILP
		//GameSolver solver1 = new GameSolver(g);

		//solver1.solve();
		
		double tUB = calculateUB(g);

		//System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+solver1.getUtility()+", "+solver1.getRuntime()+", "+tUB);
		
		//Map<ObservableConfiguration, Integer> bounds = getBounds(g, solver1.getDefenderStrategy());

		Map<ObservableConfiguration, Integer> bounds = new HashMap<ObservableConfiguration, Integer>();
		
		for(ObservableConfiguration o : g.obs){
			if(o.id == 6)
				bounds.put(o, 5);
			else if(o.id == 7)
				bounds.put(o, 10);
			else if(o.id == 9)
				bounds.put(o, 5);
			else 
				bounds.put(o, 0);
		}
		
		//Solve for optimal pure, given bounds
		//PureStrategySolver solver2 = new PureStrategySolver(g, bounds);
		GameSolver solver2 = new GameSolver(g, bounds);
		
		solver2.solve();

		System.out.println(numConfigs + ", " + numObservables + ", " + numSystems + ", " + solver2.getUtility() + ", "
				+ solver2.getRuntime() + ", " + tUB);
		
		// Solve for the marginal, given bounds
		//GameSolver solver = new GameSolver(g);
		MarginalSolver solver = new MarginalSolver(g, bounds);

		solver.solve();

		System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+solver.getUtility()+", "+solver.getRuntime()+", "+tUB);
		
	}
	
	public static void runSampleLinearGame(DeceptionGame g, int numConfigs, int numObservables, int numSystems, long seed) throws Exception {
		boolean verbose = false;

		// Need to load cplex libraries
		String cplexInputFile = "CplexConfig";

		DeceptionGameHelper.loadLibrariesCplex(cplexInputFile);

		// Solve the MILP
		//GameSolver solver = new GameSolver(g);
		LinearGameSolver solver = new LinearGameSolver(g);

		solver.solve();

		//String output = "experiments/CDG_"+numConfigs+"_"+numObservables+"_"+numSystems+".csv";
		
		//PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));
		
		double tUB = calculateUB(g);
		
		System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+solver.getUtility()+", "+solver.getRuntime()+", "+tUB);
		
		//w.println(numConfigs+", "+numObservables+", "+numSystems+", "+solver.getUtility()+", "+solver.getRuntime());
		
		//w.close();
	}

	public static void runSampleGame(DeceptionGame g, int numConfigs, int numObservables, int numSystems, long seed) throws Exception {
		boolean verbose = false;

		// Need to load cplex libraries
		String cplexInputFile = "CplexConfig";

		DeceptionGameHelper.loadLibrariesCplex(cplexInputFile);

		// Solve the MILP
		GameSolver solver = new GameSolver(g);

		solver.solve();

		String output = "experiments/CDG_"+numConfigs+"_"+numObservables+"_"+numSystems+".csv";
		
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));
		
		double tUB = calculateUB(g);
		
		System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+solver.getUtility()+", "+solver.getRuntime()+", "+tUB);
		
		w.println(numConfigs+", "+numObservables+", "+numSystems+", "+solver.getUtility()+", "+solver.getRuntime());
		
		w.close();
	}
	
	public static Map<ObservableConfiguration, Integer> getBounds(DeceptionGame g, Map<Systems, Map<ObservableConfiguration, Integer>> strategy){
		Map<ObservableConfiguration, Integer> bounds = new HashMap<ObservableConfiguration, Integer>();
		
		for(ObservableConfiguration o : g.obs){
			int sum = 0;
			for(Systems k : g.machines){
				sum += strategy.get(k).get(o);
			}
			bounds.put(o, sum);
		}
		
		return bounds;
	}

	public static double calculateUB(DeceptionGame g){
		int totalU = 0;
		for(Systems k : g.machines){
			totalU += k.f.utility;
		}
		return ((double)(totalU)/(double)g.machines.size());
	}
}
