package es.deusto.ingenieria.aike.footprint;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.blind.BreadthFSwithLog;
import es.deusto.ingenieria.aike.search.blind.DepthFSwithLog;
import es.deusto.ingenieria.aike.search.heuristic.BestFSwithLog;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class MainProgram {
	public static void main(String[] args) {
		try {	
			EnvironmentXMLReader environmentSAXParser = new EnvironmentXMLReader("data/feetmaze-1.xml");		
			State initialState = new State((Environment)environmentSAXParser.getInformation());
			FootProblem problem = new FootProblem(initialState);
			problem.addInitialState(initialState);
			SearchMethod search = DepthFSwithLog.getInstance();
			//SearchMethod search = new AStarWithLog(new ManhattanDistance());
			//SearchMethod search = new BestFSwithLog(new ManhattanDistance());
			problem.solve(search);
		} catch (Exception ex) {
			System.err.println("% [MainProgram] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
