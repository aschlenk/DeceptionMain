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
import solvers.BBMarginalSearch;
import solvers.BBSearch;
import solvers.BBSigmaSearch;
import solvers.BisectionAlgorithm;
import solvers.GameSolver;
import solvers.GameSolverCuts;
import solvers.GreedyMaxMinSolver;
import solvers.HeuristicSolver;
import solvers.LinearGameSolver;
import solvers.MarginalSolver;
import solvers.PureStrategySolver;
import solvers.UpperBoundMILP;

public class SimpleDeceptionAllSolvers {
	
	private static double maxUtil;

	public static void main(String[] args) throws Exception {
		
		// g.generateGame(3, 2, 3);
		int numConfigs = 15;
		int numObs = 5;
		int numSystems = 15;
		//seed = 101 has some issues in returning the right strategy for numSystems = 5, numObs = 2, and numConfigs = 5
		//seed = 103 creates a perfect case for when heuristic doesn't run well, numS = 4, numO = 3, numC = 3
		//seed = 113 creates issues for objective MILP value, numS = 20, numO = 10, numC = 20
		
		//whenever all machines can be assigned to same observable, heuristic doesn't work, otherwise it seems to work
		
		//seed = 104, seed = 109, both give possible cases where greedy could give less than 1/2 approximation
		long seed = 112; 
		for(int i=1; i<=10; i++){
			DeceptionGame g = new DeceptionGame();
			seed++;
			g.generateGame(numConfigs, numObs, numSystems, seed);
			g.printGame();

			System.out.println();
			System.out.println();

			//g.exportGame(1, 1);

			String dir = "C:/Users/Aaron Schlenker/workspace/CyberDeception/";

			// DeceptionGame game2 = new DeceptionGame();
			// game2.readInGame(dir, 1, 1);

			// game2.printGame();

//			 runSampleLinearGame(g, numConfigs, numObs, numSystems, seed);
			
//			runSampleGame(g, numConfigs, numObs, numSystems, seed);
			
			System.out.println();
			System.out.println();
			System.out.println();
			
			runSampleGameCuts(g, numConfigs, numObs, numSystems, seed);
			
			
			//This might work, but the code is not correct right now!
			//I think the issue is with solving for the MILP w bounds whenever we have a marginal w/ all N_o that are integers
//			runBBMarginalSearch(g, numConfigs, numObs, numSystems, seed);
			
			// runMarginalSolver(g, numConfigs, numObs, numSystems, seed);
			runBisectionAlgorithm(g, numConfigs, numObs, numSystems, seed);

			// runBBSearch(g, numConfigs, numObs, numSystems, seed);
			// There is an issue with returning a defender strategy when the
			// optimal utility is equal to the lower bound!
//			runGreedyMaxMinSolver(g);
			
//			runGreedyMaxMinSolver(g, 2000);
			
//			runBBSigmaSearch(g, numConfigs, numObs, numSystems, seed);

			System.out.println();
			System.out.println();
			// runHeuristicSolver(g);

			
			// Should write a greedymaxmin solver that works with a general set
			// of constraints
			//Also should incorporate a partial strategy then greedy max min assigning the rest
		}
		
	}
	
	private static void runBBMarginalSearch(DeceptionGame g, int numConfigs, int numObservables, int numSystems, long seed) throws Exception {

		System.out.println("Running BB Marginal Search");
		System.out.println();
		// Need to load cplex libraries
		String cplexInputFile = "CplexConfig";

		DeceptionGameHelper.loadLibrariesCplex(cplexInputFile);
		
		BBMarginalSearch search = new BBMarginalSearch(g);
		
		search.solve();

		printCompactStrategy2(search.getDefenderStrategy(), g);
		
//		printCompactStrategy(search.getDefenderStrategy());
		
		System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+search.getPayoff()+", "+search.getRuntime());

		System.out.println();
	}
	
	private static void runBBSigmaSearch(DeceptionGame g, int numConfigs, int numObservables, int numSystems, long seed) throws Exception {

		System.out.println("Running BB Sigma Search");
		System.out.println();
		// Need to load cplex libraries
		String cplexInputFile = "CplexConfig";

		DeceptionGameHelper.loadLibrariesCplex(cplexInputFile);
		
		BBSigmaSearch search = new BBSigmaSearch(g);
		
		search.solve();

		printCompactStrategy2(search.getDefenderStrategy(), g);
		
//		printCompactStrategy(search.getDefenderStrategy());
		
		System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+search.getGlobalLB()+", "+search.getRuntime());

		System.out.println();
	}
	
	private static void runBBSearch(DeceptionGame g, int numConfigs, int numObservables, int numSystems, long seed) throws Exception {

		System.out.println("Running BB Search");
		System.out.println();
		// Need to load cplex libraries
		String cplexInputFile = "CplexConfig";

		DeceptionGameHelper.loadLibrariesCplex(cplexInputFile);
		
		BBSearch search = new BBSearch(g);
		
		search.solve();
		
		printCompactStrategy(search.getDefenderStrategy());
		
		System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+search.getGlobalLB()+", "+search.getRuntime());

		System.out.println();
	}
	
	private static void runBisectionAlgorithm(DeceptionGame g, int numConfigs, int numObservables, int numSystems, long seed) throws Exception {

		System.out.println();
		System.out.println("Running Bisection Algorithm");
		System.out.println();
		// Need to load cplex libraries
		String cplexInputFile = "CplexConfig";

		DeceptionGameHelper.loadLibrariesCplex(cplexInputFile);
		
		Map<ObservableConfiguration, Integer> bounds = new HashMap<ObservableConfiguration, Integer>();
		
		for(ObservableConfiguration o : g.obs){
			//if(o.id == 2)
				//bounds.put(o, 8);
			//else if(o.id == 7)
				//bounds.put(o, 6);
			/*else if(o.id == 9)
				bounds.put(o, 5);
			else 
				bounds.put(o, 0);*/
		}
		
		//System.out.println(bounds.toString());
		//System.out.println();
		
		BisectionAlgorithm alg = new BisectionAlgorithm(g, bounds);
		
		alg.solve();
		
//		printStrategy(alg.getDefenderStrategy());
		printCompactObsStrategy(alg.getDefenderStrategy(), g);
		
		System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+alg.getLB()+", "+alg.getUB()+", "+alg.getRuntime()+", "+alg.getIterations());
		
	}

	public static void runGreedyMaxMinSolver(DeceptionGame g){
		System.out.println("Runnning Greedy Max Min Solver");
		
		double highUtil = -100;
		
		for(int i=1; i<=1; i++){
			GreedyMaxMinSolver solver = new GreedyMaxMinSolver(g);
			
			solver.setShuffle(false);
			
			solver.setDescending(true);
			
			solver.solve();
			
			if(solver.getDefenderUtility() > highUtil)
				highUtil = solver.getDefenderUtility();
			
//			System.out.println();
////			
//			System.out.println(g.configs.size()+", "+g.obs.size()+", "+g.machines.size()+", "+solver.getDefenderUtility()+","+
//							solver.calculateMaxMinUtility(solver.getGreedyStrategy()).eu+", "+solver.getRuntime());
//			System.out.println();
//			
			/*if(solver.getDefenderUtility() < maxUtil*2){
				System.out.println("We have a problem!");
				System.out.println("Greedy: "+solver.getDefenderUtility());
			}*/
			
			highUtil = solver.getDefenderUtility();
			
//			printCompactStrategy(solver.getGreedyStrategy(), g);
			
//			printStrategy2(solver.getGreedyStrategy());
			
			GreedyMaxMinSolver solver1 = new GreedyMaxMinSolver(g);
			
			solver1.setShuffle(false);
			
			solver1.setDescending(false);
			
			solver1.solve();
			
			if(solver1.getDefenderUtility() > highUtil)
				highUtil = solver1.getDefenderUtility();
			
			if(solver1.getDefenderUtility() < maxUtil*2){
				System.out.println("We have a problem!");
				System.out.println("Greedy: "+solver.getDefenderUtility());
			}
			
			if(solver1.getDefenderUtility() > highUtil)
				highUtil = solver1.getDefenderUtility();
//			printCompactStrategy(solver.getGreedyStrategy(), g);
			
//			printStrategy2(solver1.getGreedyStrategy());
		}
		
		System.out.println();
//		
		System.out.println(g.configs.size()+", "+g.obs.size()+", "+g.machines.size()+", "+highUtil);
		System.out.println();
		
		
//		System.out.println("Util: "+highUtil);
		
	}
	
	public static void runGreedyMaxMinSolver(DeceptionGame g, int numShuffles){
		System.out.println("Runnning Greedy Max Min Solver");
		
		double highUtil = -100;
		
		for(int i=1; i<=numShuffles; i++){
			GreedyMaxMinSolver solver = new GreedyMaxMinSolver(g);
			
			solver.setShuffle(true);
			
			solver.solve();
			
			if(solver.getDefenderUtility() > highUtil)
				highUtil = solver.getDefenderUtility();
			
//			System.out.println();
//			
//			System.out.println(g.configs.size()+", "+g.obs.size()+", "+g.machines.size()+", "+solver.getDefenderUtility()+","+
//							solver.calculateMaxMinUtility(solver.getGreedyStrategy()).eu+", "+solver.getRuntime());
//			System.out.println();
			
			if(solver.getDefenderUtility() < maxUtil*2){
				System.out.println("We have a problem!");
				System.out.println("Greedy: "+solver.getDefenderUtility());
			}
			
//			printCompactStrategy(solver.getGreedyStrategy(), g);
			
//			printStrategy2(solver.getGreedyStrategy());
		}
		
		System.out.println("Util: "+highUtil);
		
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
		
		//GameSolver solver = new GameSolver(g);
		//UpperBoundMILP solver3 = new UpperBoundMILP(g);

		//solver3.solve();
		
		//System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+solver3.getUtility()+", "+solver3.getRuntime()+", "+tUB);
		
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
	
	public static void runSampleGameCuts(DeceptionGame g, int numConfigs, int numObservables, int numSystems, long seed) throws Exception {
		double highUtil = -100;
		
		for(int i=1; i<=100; i++){
			GreedyMaxMinSolver solver = new GreedyMaxMinSolver(g);
			
			solver.setShuffle(true);
			
			solver.solve();
			
			if(solver.getDefenderUtility() > highUtil)
				highUtil = solver.getDefenderUtility();
			
		}
		
		System.out.println("Running MILP w Cuts");
		System.out.println();
		
		boolean verbose = false;

		// Need to load cplex libraries
		String cplexInputFile = "CplexConfig";

		DeceptionGameHelper.loadLibrariesCplex(cplexInputFile);

		for(int i=1; i<=1; i++){
			// Solve the MILP
			GameSolverCuts solver = new GameSolverCuts(g);
	
			solver.setGlobalLB(highUtil);
			
			solver.setMaxSubsetSize(i);
			
			solver.solve();
			
			double tUB = calculateUB(g);
			
			maxUtil = solver.getUtility();
			
			System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+solver.getUtility()+", "+solver.getRuntime()+", "+tUB);
			
			printCompactStrategy(solver.getDefenderStrategy(), g);
			
	//		printStrategy2(solver.getDefenderStrategy());
	
			solver.deleteVars();
			
			//System.out.println();
			System.out.println();
		}
	}

	public static void runSampleGame(DeceptionGame g, int numConfigs, int numObservables, int numSystems, long seed) throws Exception {
		System.out.println("Running MILP");
		System.out.println();
		
		boolean verbose = false;

		// Need to load cplex libraries
		String cplexInputFile = "CplexConfig";

		DeceptionGameHelper.loadLibrariesCplex(cplexInputFile);

		// Solve the MILP
		GameSolver solver = new GameSolver(g);

		solver.solve();

//		String output = "experiments/CDG_"+numConfigs+"_"+numObservables+"_"+numSystems+".csv";
		
//		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));
		
		double tUB = calculateUB(g);
		
		maxUtil = solver.getUtility();
		
		System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+solver.getUtility()+", "+solver.getRuntime()+", "+tUB);
		
//		w.println(numConfigs+", "+numObservables+", "+numSystems+", "+solver.getUtility()+", "+solver.getRuntime());
		
//		w.close();
		
		printCompactStrategy(solver.getDefenderStrategy(), g);
		
//		printStrategy2(solver.getDefenderStrategy());

		solver.deleteVars();
		
		//System.out.println();
		System.out.println();
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
	
	public static void printCompactStrategy(Map<Systems, Map<ObservableConfiguration, Double>> strat){
		for(Systems k : strat.keySet()){
			System.out.print("K"+k.id+": ");
			for(ObservableConfiguration o : strat.get(k).keySet()){
				if(strat.get(k).get(o)>0)
					System.out.print("TF"+o.id+" : "+strat.get(k).get(o)+" ");
			}
			System.out.println();
		}
	}
	
	public static void printCompactObsStrategy(Map<Systems, Map<ObservableConfiguration, Double>> strat, DeceptionGame g){

		for(ObservableConfiguration o : g.obs){
			double sum =0;
			for(Systems k : strat.keySet()){
				sum += strat.get(k).get(o);
			}
			System.out.println("O"+o.id+": "+sum);
		}
		
	}
	
	public static void printCompactStrategy(Map<Systems, Map<ObservableConfiguration, Integer>> strat, DeceptionGame g){

		for(ObservableConfiguration o : g.obs){
			double sum =0;
			for(Systems k : strat.keySet()){
				sum += strat.get(k).get(o);
			}
			System.out.println("O"+o.id+": "+sum);
//			System.out.println();
			for(Systems k : strat.keySet()){
				if(strat.get(k).get(o) > .999)
					System.out.print("k"+k.id+" ");
			}
			System.out.println();
		}
		
	}
	
	public static void printCompactStrategy2(Map<Systems, Map<ObservableConfiguration, Double>> strat, DeceptionGame g){

		for(ObservableConfiguration o : g.obs){
			double sum =0;
			for(Systems k : strat.keySet()){
				sum += strat.get(k).get(o);
			}
			System.out.println("O"+o.id+": "+sum);
		}
		
	}
	
	public static void printStrategy(Map<Systems, Map<ObservableConfiguration, Double>> strat){
		for(Systems k : strat.keySet()){
			System.out.print("K"+k.id+": ");
			for(ObservableConfiguration o : strat.get(k).keySet()){
				System.out.print("TF"+o.id+" : "+strat.get(k).get(o)+" ");
			}
			System.out.println();
		}
	}
	
	public static void printStrategy2(Map<Systems, Map<ObservableConfiguration, Integer>> strat){
		for(Systems k : strat.keySet()){
			System.out.print("K"+k.id+": ");
			for(ObservableConfiguration o : strat.get(k).keySet()){
				System.out.print("TF"+o.id+" : "+strat.get(k).get(o)+" ");
			}
			System.out.println();
		}
	}
}
