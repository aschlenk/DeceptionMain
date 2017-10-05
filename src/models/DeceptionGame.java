package models;

import java.util.ArrayList;
import java.util.Random;

public class DeceptionGame {

	public ArrayList<Configuration> configs;
	public ArrayList<ObservableConfiguration> obs;
	public ArrayList<Systems> machines;
	public ArrayList<Integer> numConfigs;
	public ArrayList<Integer> numObs;

	public DeceptionGame() {
		configs = new ArrayList<Configuration>();
		obs = new ArrayList<ObservableConfiguration>();
		machines = new ArrayList<Systems>();
	}

	public DeceptionGame(ArrayList<Configuration> configs, ArrayList<ObservableConfiguration> obs,
			ArrayList<Systems> machines) {
		this.configs = configs;
		this.obs = obs;
		this.machines = machines;
	}

	public void generateGame(int numConfigs, int numObs, int numSystems) {
		long seed = System.currentTimeMillis();
		Random r = new Random(seed);

		for (int i = 0; i < numConfigs; i++) {
			Configuration f1 = new Configuration(-1 * r.nextInt(11));
			configs.add(f1);
		}

		for (int i = 0; i < numObs; i++) {
			ObservableConfiguration o1 = new ObservableConfiguration();
			obs.add(o1);

			// add configurations that observable can mask
			for (int j = 0; j < configs.size(); j++) {
				if (r.nextInt(2) == 1) {
					o1.addConfiguration(configs.get(j));
				}
			}
		}
		
		//calculate backward mapping for observables that a configuration can be masked with
		for(ObservableConfiguration o : obs){
			for(Configuration f : o.configs){
				f.addObservable(o);
			}
		}

		// Create a random number for configuration and assign it to system k
		for (int i = 0; i < numSystems; i++) {
			int configNum = r.nextInt(numConfigs);
			Systems k1 = new Systems(configs.get(configNum));
			machines.add(k1);
		}

	}

	public void generateGame(int numConfigs, int numObs, int numSystems, long seed) {
		Random r = new Random(seed);

		for (int i = 0; i < numConfigs; i++) {
			Configuration f1 = new Configuration(-1 * r.nextInt(10)-1);
			configs.add(f1);
		}

		for (int i = 0; i < numObs; i++) {
			ObservableConfiguration f1 = new ObservableConfiguration();
			obs.add(f1);

			// add configurations that observable can mask
			for (int j = 0; j < configs.size(); j++) {
				if (r.nextInt(2) == 1) {
					f1.addConfiguration(configs.get(j));
				}
			}
		}
		
		//check to see if all of the configurations can be covered and cover them if not
		correctObservables(r);

		// Create a random number and assign it
		for (int i = 0; i < numSystems; i++) {
			int configNum = r.nextInt(numConfigs);
			Systems k1 = new Systems(configs.get(configNum));
			machines.add(k1);
		}

	}
	
	private void correctObservables(Random r){
		for(Configuration f : configs){
			int sum = 0;
			for(ObservableConfiguration o : obs){
				if(o.configs.contains(f)){
					sum++;
				}
			}
			if(sum == 0){
				int oNum = r.nextInt(obs.size());//new Random().nextInt(obs.size());
				obs.get(oNum).addConfiguration(f);
			}
			sum = 0;
		}
	}
	
	public void printGame(){
		for(Systems k : machines){
			System.out.println(k.toString());
		}
		
		for(ObservableConfiguration o : obs){
			System.out.println(o.toString());
		}
		
		for(Configuration f : configs){
			System.out.println(f.toString());
		}
	}

}
