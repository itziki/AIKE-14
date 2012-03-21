package es.deusto.ingenieria.aike.footprint;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.blind.BreadthFS;
import es.deusto.ingenieria.aike.search.blind.BreadthFSwithLog;
import es.deusto.ingenieria.aike.search.blind.DepthFS;
import es.deusto.ingenieria.aike.search.blind.DepthFSwithLog;
import es.deusto.ingenieria.aike.search.heuristic.BestFS;
import es.deusto.ingenieria.aike.search.heuristic.BestFSwithLog;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class MainProgram {
	public static void main(String[] args) {
		try {	
			FootProblem problem = new FootProblem();
			State initialState = new State(new EnvironmentXMLReader("data/feetmaze-1.xml").getInformation());
			problem.addInitialState(initialState);
			System.out.println(((Environment)initialState.getInformation()).toDrawString());
			
			//SearchMethod search = BreadthFS.getInstance();
			//SearchMethod search = DepthFS.getInstance();
			//SearchMethod search = new BestFS(new ManhattanDistance());
			//problem.solve(search);
			
			//SearchMethod search = BreadthFSwithLog.getInstance();
			//SearchMethod search = BreadthFS.getInstance();
			//SearchMethod search = DepthFSwithLog.getInstance();
			//
			SearchMethod search = new AStarWithLog(new ManhattanDistance());
			//SearchMethod search = new BestFSwithLog(new ManhattanDistance());
			//SearchMethod search = new BestFS(new ManhattanDistance());
			//SearchMethod search = new AStarCar(new ManhattanDistance());
			problem.solve(search);
		} catch (Exception ex) {
			System.err.println("% [MainProgram] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
