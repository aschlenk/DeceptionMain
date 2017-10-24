package examples;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import Utilities.DeceptionGameHelper;
import models.DeceptionGame;
import models.ObservableConfiguration;
import models.Systems;
import solvers.GameSolver;
import solvers.GreedyMaxMinSolver;

public class Experiments {

	public static void main(String[] args) throws Exception {
		
		//numConfigs, numSystems, numObs, numGames, experimentNum
		runExperiments(50, 30, 2, 30, 2);
		
		}

	public static void runExperiments(int numConfigs, int numSystems, int numObs, int numgames, int experimentnum)
			throws Exception {

		createGames(numConfigs, numSystems, numObs, numgames, experimentnum);
		
		solveMILP(numConfigs, numSystems, numObs, numgames, experimentnum);
		
		solveGreedyMaxMin(numConfigs, numSystems, numObs, numgames, experimentnum);

		// String dir = "C:/Users/Aaron Schlenker/workspace/CyberDeception/";

		// DeceptionGame game2 = new DeceptionGame();
		// game2.readInGame(dir, 1, 1);

		// game2.printGame();

		// runSampleLinearGame(g, numConfigs, numObs, numSystems, seed);
		// runSampleGame(g, numConfigs, numObs, numSystems, seed,
		// experimentnum);
		// runMarginalSolver(g, numConfigs, numObs, numSystems, seed);
		// runBisectionAlgorithm(g, numConfigs, numObs, numSystems, seed);

		// runBBSearch(g, numConfigs, numObs, numSystems, seed);
		// There is an issue with returning a defender strategy when the optimal
		// utility is equal to the lower bound!
		// runBBSigmaSearch(g, numConfigs, numObs, numSystems, seed);

		System.out.println();
		System.out.println();
		// runHeuristicSolver(g);

	}

	public static void createGames(int numConfigs, int numSystems, int numObs, int numgames, int experimentnum)
			throws Exception {

		for (int i = 1; i <= numgames; i++) {
			DeceptionGame g = new DeceptionGame();

			long seed = System.currentTimeMillis();// 113;
			g.generateGame(numConfigs, numObs, numSystems, seed);
			// g.printGame();

			g.exportGame(i, experimentnum);
			
			System.gc();
			
		}
	}

	public static void solveGreedyMaxMin(int numConfigs, int numSystems, int numObs, int numGames,  int experimentnum) throws IOException {
		System.out.println("Runnning Greedy Max Min Solver");

		String dir = "C:/Users/Aaron Schlenker/workspace/CyberDeception/";

		for (int i = 1; i <= numGames; i++) {
			DeceptionGame game = new DeceptionGame();
			game.readInGame(dir, numConfigs, numObs, numSystems, i, experimentnum);

			// game.printGame();
			
			runGreedyMaxMin(game, numConfigs, numObs, numSystems, experimentnum, 100);

//			System.out.println(numConfigs+", "+numObs+", "+numSystems+", "+solver.getDefenderUtility()+", "+solver.getRuntime());
			
			System.gc();
		}
	}
	
	private static void runGreedyMaxMin(DeceptionGame game, int numConfigs, int numObservables, int numSystems, int experimentnum, int numShuffles) throws IOException{

		String output = "experiments/GMM_" + experimentnum + "_" + numConfigs + "_" + numObservables + "_" + numSystems
				+ ".csv";

		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));

		double tUB = calculateUB(game);
		
		double bestUtil = -100;
		
		double start = System.currentTimeMillis();
		
		for(int i=1; i<=numShuffles; i++){

			GreedyMaxMinSolver solver = new GreedyMaxMinSolver(game);

			solver.setShuffle(true);
			
			solver.solve();
			
			if(solver.getDefenderUtility() > bestUtil)
				bestUtil = solver.getDefenderUtility();
			
		}
		
		double runtime = (System.currentTimeMillis()-start)/1000.0;
		
		System.out.println(numConfigs+", "+numObservables+", "+numSystems+", "+bestUtil+", "+runtime);
		
		w.println(numConfigs + ", " + numObservables + ", " + numSystems + ", " + bestUtil + ", "
				+ runtime);

		w.close();
		

		System.out.println();
	}

	public static void solveMILP(int numConfigs, int numSystems, int numObs, int numgames, int experimentnum) throws Exception {
		String dir = "C:/Users/Aaron Schlenker/workspace/CyberDeception/";
		
		for(int i=1; i<=numgames; i++){
			DeceptionGame game = new DeceptionGame();
			game.readInGame(dir, numConfigs, numObs, numSystems, i, experimentnum);

			// game.printGame();

			runMILP(game, numConfigs, numObs, numSystems, experimentnum);

			System.gc();
		}
	}

	public static void runMILP(DeceptionGame g, int numConfigs, int numObservables, int numSystems, int experimentnum)
			throws Exception {
		System.out.println("Running MILP");
		System.out.println();

		boolean verbose = false;

		// Need to load cplex libraries
		String cplexInputFile = "CplexConfig";

		DeceptionGameHelper.loadLibrariesCplex(cplexInputFile);

		// Solve the MILP
		GameSolver solver = new GameSolver(g);

		solver.solve();

		//version with cutoff
		
		String output = "experiments/MILP_" + experimentnum + "_" + numConfigs + "_" + numObservables + "_" + numSystems
				+ ".csv";

		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));

		double tUB = calculateUB(g);

		System.out.println(numConfigs + ", " + numObservables + ", " + numSystems + ", " + solver.getUtility() + ", "
				+ solver.getRuntime() + ", " + tUB);

		w.println(numConfigs + ", " + numObservables + ", " + numSystems + ", " + solver.getUtility() + ", "
				+ solver.getRuntime());

		w.close();

		// printCompactStrategy(solver.getDefenderStrategy(), g);

		System.out.println();
		System.out.println();
	}

	public static void runSampleGame(DeceptionGame g, int numConfigs, int numObservables, int numSystems,
			int experimentnum) throws Exception {
		System.out.println("Running MILP");
		System.out.println();

		boolean verbose = false;

		// Need to load cplex libraries
		String cplexInputFile = "CplexConfig";

		DeceptionGameHelper.loadLibrariesCplex(cplexInputFile);

		// Solve the MILP
		GameSolver solver = new GameSolver(g);

		solver.solve();

		String output = "experiments/CDG_" + experimentnum + "_" + numConfigs + "_" + numObservables + "_" + numSystems
				+ ".csv";

		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));

		double tUB = calculateUB(g);

		System.out.println(numConfigs + ", " + numObservables + ", " + numSystems + ", " + solver.getUtility() + ", "
				+ solver.getRuntime() + ", " + tUB);

		w.println(numConfigs + ", " + numObservables + ", " + numSystems + ", " + solver.getUtility() + ", "
				+ solver.getRuntime());

		w.close();

		printCompactStrategy(solver.getDefenderStrategy(), g);

		System.out.println();
		System.out.println();
	}

	public static double calculateUB(DeceptionGame g) {
		int totalU = 0;
		for (Systems k : g.machines) {
			totalU += k.f.utility;
		}
		return ((double) (totalU) / (double) g.machines.size());
	}

	public static void printCompactObsStrategy(Map<Systems, Map<ObservableConfiguration, Double>> strat,
			DeceptionGame g) {

		for (ObservableConfiguration o : g.obs) {
			double sum = 0;
			for (Systems k : strat.keySet()) {
				sum += strat.get(k).get(o);
			}
			System.out.println("O" + o.id + ": " + sum);
		}

	}

	public static void printCompactStrategy(Map<Systems, Map<ObservableConfiguration, Integer>> strat,
			DeceptionGame g) {

		for (ObservableConfiguration o : g.obs) {
			double sum = 0;
			for (Systems k : strat.keySet()) {
				sum += strat.get(k).get(o);
			}
			System.out.println("O" + o.id + ": " + sum);
		}

	}

	public static void printStrategy(Map<Systems, Map<ObservableConfiguration, Double>> strat) {
		for (Systems k : strat.keySet()) {
			System.out.print("K" + k.id + ": ");
			for (ObservableConfiguration o : strat.get(k).keySet()) {
				System.out.print("TF" + o.id + " : " + strat.get(k).get(o) + " ");
			}
			System.out.println();
		}
	}

}
