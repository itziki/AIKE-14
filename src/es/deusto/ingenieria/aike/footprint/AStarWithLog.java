package es.deusto.ingenieria.aike.footprint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.Problem;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.heuristic.EvaluationFunction;
import es.deusto.ingenieria.aike.search.heuristic.HeuristicSearchMethod;
import es.deusto.ingenieria.aike.search.log.SearchLog;
import es.deusto.ingenieria.ingenieria.search.Node;

public class AStarWithLog extends HeuristicSearchMethod {

	public AStarWithLog(EvaluationFunction function){
		super(function);
	}

	public Node search(Problem problem, State initialState) {
		List<Node> successorNodes = null;
		SearchLog searchLog = this.createLog();
		List<Node> frontier = new ArrayList<Node>();
		Node firstNode = null;
		//List of states generated during the search process. This is used to check for repeated states.
		List<State> generatedStates = new ArrayList<State>();
		//1.Make a node with the initial problem state
		Node initialNode = new Node(initialState);
		//2.Insert node into the frontier data structure
		frontier.add(initialNode);
		//3.Initialize the list of EXPANDED nodes to the empty list
		List<State> expandedNodes = new ArrayList<State>();
		//4.WHILE final state not found AND the frontier is NOT empty DO:
		boolean solutionFound = false;
		while (!solutionFound && !frontier.isEmpty())
		{
			this.writeLog(searchLog, frontier);	
			//4.1 Remove first node from the frontier.
			firstNode = frontier.remove(0);
			//4.2 IF node contains final state THEN final state found.
			if(problem.isFinalState(firstNode.getState()))
			{
				solutionFound = true;
			}
			//4.3 IF node doesn’t contain final state THEN
			else
			{
			//4.3.1 Insert node into the EXPANDED nodes list
				expandedNodes.add(firstNode.getState());
			//4.3.2 EXPAND node’s state AND Insert successor nodes into the frontier
				successorNodes = this.expand(firstNode, problem, generatedStates, expandedNodes);
				if(successorNodes != null && !successorNodes.isEmpty())
				{
					frontier.addAll(successorNodes);
				//4.3.3 Sort the frontier in ascending order of f(n)
					Collections.sort(frontier);
					Collections.reverse(frontier);
				}
			}
		}
		this.closeLog(searchLog);
		//5.IF final state found THEN return sequence of actions found
		if(solutionFound)
		{
			return firstNode;
		}
		//ELSE return “solution not found”
		else
		{
			return null;
		}
	}
}