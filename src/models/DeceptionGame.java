package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
//				System.out.println("Config "+f.id+" not covered. Assigned obs "+obs.get(oNum).id);
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
		
//		for(Configuration f : configs){
//			System.out.println(f.toString());
//		}
	}

	public void readInGame(String dir, int numConfigs, int numObs, int numSystems, int gamenum, int experimentnum) throws FileNotFoundException{
		//Need to read in main file to set configurations, observables and systems
		Scanner s = new Scanner(new File("input/experiment"+experimentnum+"/GameFile_"+numConfigs+"_"+numObs+"_"+numSystems+"_"+gamenum+"_"+experimentnum+".txt"));
		
		//read in configurations file
		String configFile = s.nextLine();
		readInConfigurations(dir, configFile, gamenum, experimentnum);
			
		//System.out.println(configs);
		
		//read in observables
		String observableFile = s.nextLine();
		readInObservables(dir, observableFile, gamenum, experimentnum);
		
//		System.out.println(obs);
		
		//read in systems
		String systemfile = s.nextLine();
		readInSystems(dir, systemfile, gamenum, experimentnum);
		
//		System.out.println(machines);
		
	}
	
	private void readInSystems(String dir, String systemFile, int gamenum, int experimentnum) throws FileNotFoundException{
		int index = systemFile.lastIndexOf(' ');
		systemFile = dir+systemFile.substring(index+1);
//		System.out.println(systemFile);
		
		Scanner s1 = new Scanner(new File(systemFile));
		s1.useDelimiter(",");
		s1.nextLine(); //skip over header
		while(s1.hasNext()){
			int sysid = Integer.parseInt(s1.next());
			int conf = Integer.parseInt(s1.next());
			s1.nextLine();
			
			Systems k = new Systems(sysid, findConfiguration(conf));//configs.get(conf-1));
			machines.add(k);
		}
		
		s1.close();
		
		
	}
	
	private void readInObservables(String dir, String observableFile, int gamenum, int experimentnum) throws FileNotFoundException{
		int index = observableFile.lastIndexOf(' ');
		observableFile = dir+observableFile.substring(index+1);
//		System.out.println(observableFile);
		
		Scanner s1 = new Scanner(new File(observableFile));
		s1.useDelimiter(",");
		s1.nextLine(); //skip over header
		while(s1.hasNext()){
			int obsid = Integer.parseInt(s1.next());
			String maskable = s1.next();
			s1.nextLine();
			
			ObservableConfiguration o = new ObservableConfiguration(obsid);
			//Need to parse maskable
			Scanner s2 = new Scanner(maskable);
			s2.useDelimiter("-");
			while(s2.hasNext()){
				int f = s2.nextInt();
				o.addConfiguration(findConfiguration(f));
				//o.addConfiguration(configs.get(f-1));
			}
			obs.add(o);
			//configs.add(f);
		}
		
		s1.close();
	}
	
	private Configuration findConfiguration(int id){
		for(Configuration f : configs){
			if(f.id == id)
				return f;
		}
		return null; //not found
	}
	
	private void readInConfigurations(String dir, String configFile, int gamenum, int experimentnum) throws FileNotFoundException{
		int index = configFile.lastIndexOf(' ');
		configFile = dir+configFile.substring(index+1);
//		System.out.println(configFile);
		
		Scanner s1 = new Scanner(new File(configFile));
		s1.useDelimiter(",");
		s1.nextLine(); //skip over header
		while(s1.hasNext()){
			int configid = Integer.parseInt(s1.next());
			int util = Integer.parseInt(s1.next());
			s1.nextLine();
			Configuration f = new Configuration(configid, util); //config id starts at 1 as well!
			configs.add(f);
		}
		
		s1.close();
	}
	
	public void exportGame(int gamenum, int experimentnum) throws IOException{
		//Need to create file for configurations
		exportConfigurations(gamenum, experimentnum);
		
		//Need to create file for observables
		exportObservables(gamenum, experimentnum);
		
		//Need to create file for systems!
		exportSystems(gamenum, experimentnum);
		
		//Create main file that stores the names to lookup other files
		createMainFile(gamenum, experimentnum);
		
	}
	
	private void createMainFile(int gamenum, int experimentnum) throws IOException{
		String filename = "input/experiment"+experimentnum+"/GameFile_"+configs.size()+"_"+obs.size()+"_"+machines.size()+"_"+gamenum+"_"+experimentnum+".txt";
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		
		w.println("Configurations = input/experiment"+experimentnum+"/configurations/Configurations_"+configs.size()+"_"+obs.size()+"_"+machines.size()+"_"+gamenum+"_"+experimentnum+".csv");
		w.println("Observables = input/experiment"+experimentnum+"/observables/Observables_"+configs.size()+"_"+obs.size()+"_"+machines.size()+"_"+gamenum+"_"+experimentnum+".csv");
		w.println("Systems = input/experiment"+experimentnum+"/systems/Systems_"+configs.size()+"_"+obs.size()+"_"+machines.size()+"_"+gamenum+"_"+experimentnum+".csv");
		
		w.close();
		
	}
	
	private void exportSystems(int gamenum, int experimentnum) throws IOException{
		String filename = "input/experiment"+experimentnum+"/systems/Systems_"+configs.size()+"_"+obs.size()+"_"+machines.size()+"_"+gamenum+"_"+experimentnum+".csv";
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		
		//Header line
		w.println("System Num, Configuration");
		
		//Might print out of order, maybe want to change this so id = 1 first, id = 2 second...
		for(Systems k : machines)
			w.println(k.id+","+k.f.id+",");
		
		w.close();
		
	}
	
	private void exportObservables(int gamenum, int experimentnum) throws IOException{
		String filename = "input/experiment"+experimentnum+"/observables/Observables_"+configs.size()+"_"+obs.size()+"_"+machines.size()+"_"+gamenum+"_"+experimentnum+".csv";
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		
		//Header line
		w.println("Observable Num, Coverable Configurations");
		
		//Might print out of order, maybe want to change this so id = 1 first, id = 2 second...
		for(ObservableConfiguration o : obs){
			w.print(o.id+",");
			for(Configuration f : o.configs){
				w.print(f.id+"-");
			}
			w.println(",");
		}
		
		w.close();
		
	}
	
	private void exportConfigurations(int gamenum, int experimentnum) throws IOException{
		String filename = "input/experiment"+experimentnum+"/configurations/Configurations_"+configs.size()+"_"+obs.size()+"_"+machines.size()+"_"+gamenum+"_"+experimentnum+".csv";
		PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		
		//Header line
		w.println("Config Num, Utility");
		
		//Might print out of order, maybe want to change this so id = 1 first, id = 2 second...
		for(Configuration f : configs)
			w.println(f.id+","+f.utility+",");
		
		w.close();
		
	}

}
