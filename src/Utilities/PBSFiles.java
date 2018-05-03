package Utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PBSFiles {
	
	public static void main(String [] args) throws IOException{
		String nameOfJar = "DeceptionMILPBisection.jar";
		
		int startConfigs = 50;
		int endConfigs = 50; 
		int startSystems = 20; 
		int endSystems = 20; 
		int startObs = 10;
		int endObs = 20;
		int interval = 2;
		int totalInstances = 30;
		int experimentNum = 101;
		double milpGap = 0.000;
		int timeCutoff = 14390;
		int intervalInt = 6;
		
		printMILPBisectionFiles(nameOfJar, startConfigs, endConfigs, startSystems, endSystems, startObs, endObs, interval, totalInstances, experimentNum, milpGap, timeCutoff, intervalInt);
		
//		printMILPFiles(nameOfJar, startConfigs, endConfigs, startSystems, endSystems, startObs, endObs, interval, totalInstances, experimentNum, milpGap, timeCutoff, intervalInt);
//
//		printMILPCFiles(nameOfJar, numConfigs, numSystems, numOfObs, interval, 1, totalInstances, experimentNum);

//		printDeceptionMILPExperimentFiles(nameOfJar, startConfigs, endConfigs, startSystems, endSystems, startObs, endObs, interval, 
//				totalInstances, experimentNum, milpGap, timeCutoff, intervalInt);
		
		printDeceptionMILPBisectionExperimentFiles(nameOfJar, startConfigs, endConfigs, startSystems, endSystems, startObs, endObs, interval, 
				totalInstances, experimentNum, milpGap, timeCutoff, intervalInt);
		
//		printDeceptionMILPCExperimentFiles(nameOfJar, numConfigs, numSystems, numOfObs, interval, 3, totalInstances, experimentNum);
		
//		printGMMFiles(nameOfJar, numConfigs, numSystems, numOfObs, interval, totalInstances, experimentNum, false, .01);
		
//		printGMMFiles();
		
	}
	
	public static void printDeceptionMILPCExperimentFiles(String nameOfJar, int startConfigs, int endConfigs, int startSystems, int endSystems, 
			int startObs, int endObs, int interval, int numCuts, int totalInstances, int experimentNum) throws IOException{
		String output = "";
		
		int index = 1;
		
		for(int con=startConfigs; con<=endConfigs; con+=interval){
			for(int sys=startSystems; sys<=endSystems; sys+=interval){
				for(int obs=startObs; obs<=endObs; obs+=interval){
					output = "RunDeceptionMILPC"+numCuts+"Experiments"+obs+"_"+index+".pbs";
					PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));
					
					w.print("#!/bin/bash\n");
					w.print("#PBS -l nodes=1:ppn=1\n");
					w.print("#PBS -l walltime=00:1:00\n");
					w.print("cd /home/rcf-40/aschlenk/Deception/PBS\n");
					w.print("\n");
					
					w.print("OBS="+obs+"\n");
					w.print("\n");
		
					w.print("for NUMBERS in 1 7 13 19 25 ; do\n");
					w.print("    qsub DeceptionMILPC"+numCuts+"_"+sys+"_"+obs+"_${NUMBERS}_"+experimentNum+".pbs\n");
					w.print("    echo \"Submitting ... \" ${OBS} ${START} ${END}\n");
					w.print("done\n");
					
								
					w.close();
					index++;
				}
			}
		}
	}
	
	public static void printDeceptionMILPBisectionExperimentFiles(String nameOfJar, int startConfigs, int endConfigs, int startSystems, int endSystems, int startObs, int endObs, 
			int interval, int totalInstances, int experimentNum, double milpGap, int timeCutoff, int instanceInt) throws IOException{
		String output = "";
		
		int index = 1;
		
		for(int con=startConfigs; con<=endConfigs; con+=interval){
			for(int sys=startSystems; sys<=endSystems; sys+=interval){
				for(int obs=startObs; obs<=endObs; obs+=interval){
					output = "RunDeceptionMILPBisectionExperiments_"+timeCutoff+"_"+con+"_"+sys+"_"+obs+"_"+index+"_"+experimentNum+".pbs";
			
					PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));
			
					w.print("#!/bin/bash\n");
					w.print("#PBS -l nodes=1:ppn=1\n");
					w.print("#PBS -l walltime=00:01:00\n");
					w.print("cd /home/rcf-40/aschlenk/Deception/PBS\n");
					w.print("\n");
					
					w.print("\n");
		
					w.print("for NUMBERS in 1 7 13 19 25 ; do\n");
					w.print("    qsub DeceptionMILPBisection"+timeCutoff+"_"+con+"_"+sys+"_"+obs+"_${NUMBERS}_"+experimentNum+".pbs\n");
					w.print("    echo \"Submitting ... \" ${OBS} ${START} ${END}\n");
					w.print("done\n");
					
								
					w.close();
					index++;
				}	
			}
		}
	}
	
	public static void printDeceptionMILPExperimentFiles(String nameOfJar, int startConfigs, int endConfigs, int startSystems, int endSystems, int startObs, int endObs, 
			int interval, int totalInstances, int experimentNum, double milpGap, int timeCutoff, int instanceInt) throws IOException{
		String output = "";
		
		int index = 1;
		
		for(int con=startConfigs; con<=endConfigs; con+=interval){
			for(int sys=startSystems; sys<=endSystems; sys+=interval){
				for(int obs=startObs; obs<=endObs; obs+=interval){
					output = "RunDeceptionMILPExperiments_"+timeCutoff+"_"+con+"_"+sys+"_"+obs+"_"+index+"_"+experimentNum+".pbs";
			
					PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));
			
					w.print("#!/bin/bash\n");
					w.print("#PBS -l nodes=1:ppn=1\n");
					w.print("#PBS -l walltime=00:01:00\n");
					w.print("cd /home/rcf-40/aschlenk/Deception/PBS\n");
					w.print("\n");
					
//					w.print("SYS="+sys+"\n");
					w.print("\n");
		
					w.print("for NUMBERS in 1 7 13 19 25 ; do\n");
					w.print("    qsub DeceptionMILP"+timeCutoff+"_"+con+"_"+sys+"_"+obs+"_${NUMBERS}_"+experimentNum+".pbs\n");
					w.print("    echo \"Submitting ... \" ${OBS} ${START} ${END}\n");
					w.print("done\n");
					
								
					w.close();
					index++;
				}	
			}
		}
	}
	
	private static void printGMMFiles(String nameOfJar, int numConfigs, int numSystems, int numOfObs, int intervalObs, int totalInstances, 
			int experimentNum, boolean hardGMM, double lambda) throws IOException {
		String output = "";

		for (int obs = numOfObs; obs <= 100; obs += intervalObs) {
			output = "DeceptionGMM_" + hardGMM + "_" + lambda + "_" + numSystems + "_" + obs + "_" + experimentNum + ".pbs";
			System.out.println(output);

			PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));

			w.print("#!/bin/bash\n");
			w.print("#PBS -l nodes=1:ppn=2\n");// :sl250s\n");
			w.print("#PBS -l walltime=2:00:00\n");
			w.print("cd /home/rcf-40/aschlenk/Deception\n");
			
			w.print("\n");
			w.print("for NUMBERS in 100 500 1000 2000 4000 8000 16000 ; do\n");
			w.print("	java -jar " + nameOfJar + " " + experimentNum + " " + numConfigs + " " + numSystems + " " + obs
					+ " " + 1 + " " + totalInstances + " " + 1800 + " " + false + " " + false + " " + 1
					+ " " + true + " " + hardGMM + " " + "${NUMBERS}"  + " " + lambda + "\n");
			w.print("done\n");
			
			w.close();
		}
	}
	
	private static void printMILPCFiles(String nameOfJar, int numConfigs, int numSystems, int numOfObs, int intervalObs, int numCuts, 
			int totalInstances, int experimentNum) throws IOException {
		String output = "";

		for (int instance = 1; instance <= totalInstances; instance += 15) {
			for (int obs = numOfObs; obs <= 100; obs += intervalObs) {
				output = "DeceptionMILPC"+numCuts+"_"+numSystems+"_"+obs+"_"+instance+"_"+ experimentNum + ".pbs";
				System.out.println(output);

				PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));

				w.print("#!/bin/bash\n");
				w.print("#PBS -l nodes=1:ppn=2\n");// :sl250s\n");
				w.print("#PBS -l walltime=8:00:00\n");
				w.print("cd /home/rcf-40/aschlenk/Deception\n");
				w.print("java -jar " + nameOfJar + " " + experimentNum + " " + numConfigs + " " + numSystems + " " + obs
						+ " " + instance + " " + (instance + 14) + " " + 1800 + " " + false + " " + true + " " + numCuts + " "
						+ false + " " + false + " " + 1000 + " " + .1 + "\n");

				w.close();
			}
		}
	}

	public static void printMILPFiles(String nameOfJar, int startConfigs, int endConfigs, int startSystems, int endSystems, int startObs, int endObs, 
			int interval, int totalInstances, int experimentNum, double milpGap, int timeCutoff, int instanceInt) throws IOException{
		//String nameOfJar = "JAIRFullExperiment.jar";
		
		String output="";
		
		for(int instance=1; instance<=totalInstances; instance+=instanceInt){
			for(int con=startConfigs; con<=endConfigs; con+=interval){
				for(int sys=startSystems; sys<=endSystems; sys+=interval){
					for(int obs=startObs; obs<=endObs; obs+=interval){
						output = "DeceptionMILP"+timeCutoff+"_"+con+"_"+sys+"_"+obs+"_"+instance+"_"+experimentNum+".pbs";
						System.out.println(output);
							
						PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));
			
						w.print("#!/bin/bash\n");
						w.print("#PBS -l nodes=1:ppn=4\n");// :sl250s\n");
						w.print("#PBS -l walltime=24:00:00\n");
						w.print("cd /home/rcf-40/aschlenk/Deception\n");
						w.print("java -jar " + nameOfJar + " " + experimentNum + " " + con +" "+ sys + " " + obs + " " +instance + " " +
								(instance+instanceInt-1) + " " + timeCutoff + " " + true + " " + false + " " + 1 + " " + milpGap + "\n");
			
						w.close();
					}
				}
			}
		}

	}
	
	public static void printMILPBisectionFiles(String nameOfJar, int startConfigs, int endConfigs, int startSystems, int endSystems, int startObs, int endObs, 
			int interval, int totalInstances, int experimentNum, double milpGap, int timeCutoff, int instanceInt) throws IOException{
		//String nameOfJar = "JAIRFullExperiment.jar";
		
		String output="";
		
		for(int instance=1; instance<=totalInstances; instance+=instanceInt){
			for(int con=startConfigs; con<=endConfigs; con+=interval){
				for(int sys=startSystems; sys<=endSystems; sys+=interval){
					for(int obs=startObs; obs<=endObs; obs+=interval){
						output = "DeceptionMILPBisection"+timeCutoff+"_"+con+"_"+sys+"_"+obs+"_"+instance+"_"+experimentNum+".pbs";
						System.out.println(output);
							
						PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));
			
						w.print("#!/bin/bash\n");
						w.print("#PBS -l nodes=1:ppn=4\n");// :sl250s\n");
						w.print("#PBS -l walltime=24:00:00\n");
						w.print("cd /home/rcf-40/aschlenk/Deception\n");
						w.print("java -jar " + nameOfJar + " " + experimentNum + " " + con +" "+ sys + " " + obs + " " +instance + " " +
								(instance+instanceInt-1) + " " + timeCutoff + " " + true + " " + milpGap + "\n");
			
						w.close();
					}
				}
			}
		}

	}
	
	
}
