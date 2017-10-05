package solvers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import models.*;

public class GreedyMaxMinSolver {
	
	private DeceptionGame game;
	
	private Map<Systems, Map<ObservableConfiguration, Integer>> greedyStrategy;
	private Map<ObservableConfiguration, Double> euAllObs;
	
	public GreedyMaxMinSolver(DeceptionGame g){
		game = g;
		
	}
	
	public void solve(){
		//Calculate the f tildes which have the lowest expected utility
		euAllObs = calculateEUAllObs(game);
		for (ObservableConfiguration o : euAllObs.keySet()) {
			System.out.println("EU1(o" + o.id + "): " + euAllObs.get(o));
		}
		
		
		//Need to have an initial strategy set to all 0s
		greedyStrategy = new HashMap<Systems, Map<ObservableConfiguration, Integer>>();
		for(Systems k : game.machines){
			greedyStrategy.put(k, new HashMap<ObservableConfiguration, Integer>());
			for(ObservableConfiguration o : game.obs){
				greedyStrategy.get(k).put(o, 0);
			}
		}
		
		
		//Need a copy of the machines array for the game model, will keep track of machines left to assign
		ArrayList<Systems> machinesLeft = new ArrayList<Systems>();
		for(Systems k : game.machines)
			machinesLeft.add(k);
		
		//Should be sorted now
		Collections.sort(machinesLeft);
		//System.out.println(machinesLeft);

		//For all k \ in K, assign it to the possible \sigma_k,\tilde{f} s.t. max_\sigma min_{\tilde{f}} Eu(\tilde{f})
		while(!machinesLeft.isEmpty()){
			Systems k = machinesLeft.remove(0);
			//System.out.println("Assigning k"+k.id);
			ObservableConfiguration maxminConfig = assignMachineMaxMin(greedyStrategy, k);
			
			greedyStrategy.get(k).put(maxminConfig, greedyStrategy.get(k).get(maxminConfig)+1);
			
			//System.out.println("Best to assign k"+k.id+" to be covered by "+maxminConfig.id);
			//System.out.println();
		}
		
		printStrategy(greedyStrategy);
		printExpectedUtility(greedyStrategy);
		ObservableEU maxminUtil = calculateMaxMinUtility(greedyStrategy);
		//System.out.println(greedyStrategy);
		
		
		/**
		 * 
		 * Right now we will correct the strategy here with switches! 
		 * Later this could be a separate function which will locally maximize some strategy by switching machines
		 */
		locallyMaximizeSwitching();
		
		
	}

	public void locallyMaximizeSwitching(){
		//for all possible machines I could switch from one masking to another, switch if it improves the maxmin value
		//We are going to edit the greedy Strategy directly, this could be a bad idea
		
		//Need to find machine in all \tilde{f} that would increase the maxmin value by the most O(|K|)
		double maxImprove = 0;
		Systems kswitch = null;
		
		ObservableEU currentMaxMin = calculateMaxMinUtility(greedyStrategy);
		System.out.println("Current MaxMin: o"+currentMaxMin.o.id+" "+currentMaxMin.eu);
		
		for(ObservableConfiguration o : game.obs){
			ArrayList<Systems> sortedMachines = new ArrayList<>();
			for(Systems k : game.machines)
				if(greedyStrategy.get(k).get(o) != 0)	sortedMachines.add(k);
			Collections.sort(sortedMachines);
			
			for(int i=0; i<sortedMachines.size(); i++){
				Systems k = sortedMachines.get(i);
				
			}
			
			
		}
		
		
	}
	
	public ObservableConfiguration assignMachineMaxMin(Map<Systems, Map<ObservableConfiguration, Integer>> greedyStrategy, Systems k){
		ObservableConfiguration key = null;
		double maxmin = -1000;
		
		for(int i=0; i<game.obs.size(); i++){
			//System.out.println("Config: "+k.f.id);
			//System.out.println(game.obs.get(i).toString());
			
			if(!game.obs.get(i).configs.contains(k.f)) //Make this backwards checkable, i.e., for each configuration which observables can mask it
				continue;
			
			//assign it to \tilde{f}_i
			greedyStrategy.get(k).put(game.obs.get(i), greedyStrategy.get(k).get(game.obs.get(i))+1);
			ObservableEU o1 = calculateMinObservable(greedyStrategy);
			greedyStrategy.get(k).put(game.obs.get(i), greedyStrategy.get(k).get(game.obs.get(i))-1);
			
			//System.out.println("o"+game.obs.get(i).id+" "+o1.toString());
			
			if(o1.eu > maxmin && key == null){
				maxmin = o1.eu;
				key = game.obs.get(i);
			}else if(o1.eu > maxmin && euAllObs.get(key) < euAllObs.get(game.obs.get(i))){
				maxmin = o1.eu;
				key = game.obs.get(i);
			}
			//System.out.println();
		}
		
		return key;
	}
	
	public ObservableEU calculateMinObservable(Map<Systems, Map<ObservableConfiguration, Integer>> greedyStrategy){
		double min = 0;
		ObservableConfiguration minkey = null;
		
		for(ObservableConfiguration o : game.obs){
			double totUt = 0;
			double tot = 0;
			for(Systems k : greedyStrategy.keySet()){
				totUt += greedyStrategy.get(k).get(o)*k.f.utility;
				tot += greedyStrategy.get(k).get(o);
			}
			
			if((totUt/tot) < min){
				min = (totUt/tot);
				minkey = o;
			}
		}
		
		ObservableEU obsMin = new ObservableEU(minkey, min);
		
		return obsMin;
	}
	
	private ObservableEU calculateMaxMinUtility(Map<Systems, Map<ObservableConfiguration, Integer>> strategy) {
		double maxmin = 0;
		ObservableConfiguration key = null;
		
		for(ObservableConfiguration o : game.obs){
			double expectedU = 0;
			double total = 0;
			for(Systems k : strategy.keySet()){
				expectedU += strategy.get(k).get(o)*k.f.utility;
				total += strategy.get(k).get(o);
			}
			
			if(maxmin > (expectedU/total)){
				maxmin = (expectedU/total);
				key = o;
			}
		}
		ObservableEU o1 = new ObservableEU(key, maxmin);
		
		return o1;
	}
	
	public Map<ObservableConfiguration, Double> calculateEUAllObs(DeceptionGame g){
		Map<ObservableConfiguration, Double> euAllObs = new HashMap<ObservableConfiguration, Double>();
		
		for(ObservableConfiguration o : g.obs){
			double euObs = 0;
			double totalObs = 0;
			for(Systems k : g.machines){
				//If we can mask k with o then add it to set
				if(o.configs.contains(k.f)){
					euObs += k.f.utility;
					totalObs++;
				}
			}
			euAllObs.put(o, (euObs/totalObs));
		}
		
		return euAllObs;
	}
	
	public void printStrategy(Map<Systems, Map<ObservableConfiguration, Integer>> strat){
		for(Systems k : strat.keySet()){
			System.out.print("K"+k.id+": ");
			for(ObservableConfiguration o : strat.get(k).keySet()){
				System.out.print("TF"+o.id+" : "+strat.get(k).get(o)+" ");
			}
			System.out.println();
		}
	}
	public void printExpectedUtility(Map<Systems, Map<ObservableConfiguration, Integer>> strategy){
		double expectedU = 0;
		double total = 0;;
		for(ObservableConfiguration o : game.obs){
			for(Systems k : strategy.keySet()){
				expectedU += strategy.get(k).get(o)*k.f.utility;
				total += strategy.get(k).get(o);
			}
			System.out.println("EU(o"+o.id+"): "+(expectedU/total));
			expectedU = 0;
			total=0;
		}
		
	}
	
	public Map<Systems, Map<ObservableConfiguration, Integer>> getGreedyStrategy(){
		return greedyStrategy;
	}
	
}
