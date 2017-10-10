package examples;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import Utilities.DeceptionGameHelper;
import models.DeceptionGame;
import models.Systems;
import solvers.GameSolver;
import solvers.GreedyMaxMinSolver;
import solvers.HeuristicSolver;

public class SimpleDeceptionAllSolvers {

	public static void main(String[] args) throws Exception {
		DeceptionGame g = new DeceptionGame();
		// g.generateGame(3, 2, 3);
		int numConfigs = 4;
		int numObs = 3;
		int numSystems = 10;
		//seed = 101 has some issues in returning the right strategy for numSystems = 5, numObs = 2, and numConfigs = 5
		//seed = 103 creates a perfect case for when heuristic doesn't run well, numS = 4, numO = 3, numC = 3
		//whenever all machines can be assigned to same observable, heuristic doesn't work, otherwise it seems to work
		long seed = 105; 
		g.generateGame(numConfigs, numObs, numSystems, seed);
		if (true)
			g.printGame();
		
		runSampleGame(g, numConfigs, numObs, numSystems, seed);
		
		System.out.println();
		System.out.println();
		//runHeuristicSolver(g);
		
		runGreedyMaxMinSolver(g);
		
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

	public static double calculateUB(DeceptionGame g){
		int totalU = 0;
		for(Systems k : g.machines){
			totalU += k.f.utility;
		}
		return ((double)(totalU)/(double)g.machines.size());
	}
}
