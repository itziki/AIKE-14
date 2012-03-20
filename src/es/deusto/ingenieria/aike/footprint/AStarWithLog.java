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

	/*
	1.Make a node with the initial problem state
	2.Insert node into the frontier data structure
	3.Initialize the list of EXPANDED nodes to the empty list
	4.WHILE final state not found AND the frontier is NOT empty DO:
		4.1 Remove first node from the frontier.
		4.2 IF node contains final state THEN final state found.
		4.3 IF node doesn’t contain final state THEN
		4.3.1 Insert node into the EXPANDED nodes list
		4.3.2 EXPAND node’s state AND Insert successor nodes into the frontier
		4.3.3 Sort the frontier in ascending order of f(n)
	5.IF final state found THEN return sequence of actions found
		ELSE return “solution not found” 
	 */
	
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
				successorNodes = this.expandH(firstNode, expandedNodes, frontier, problem);
			//	successorNodes = this.expand(firstNode, problem, generatedStates, expandedNodes);
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

	/**
	 * Expands the node's state thereby generating a list of successor nodes.
	 * Expansion takes place by invoking the problem's operators apply() method on the
	 * node's state.
	 * 
	 * @param node
	 *            node whose state is to be expanded.
	 * @param problem
	 *            problem to solve
	 * @param generatedStates
	 *            List<State> states generated along the search process.
	 * @param expandedStates
	 *            List<State> states expanded along the search process.
	 * @return List<Node> containing the successor nodes.
	 */
	
	/*
	 * Function Expand(node, expanded_nodes, frontier )
	1.FOR EACH of the node’s successors DO:
		1.1 IF successor NOT in frontier nor EXPANDED nodes THEN
			Compute f(successor) = g(successor) + h(successor)
			Create parent link between successor and node
			Add successor to list of successors
		1.2 IF successor is in frontier THEN
			Compute fnew(successor) = g(successor) + h(successor)
			IF fprevious(successor) “worse than” fnew(successor) THEN
			Remove previous parent link for successor and make parent link to node
			Make f(successor)= fnew(successor)
		1.3 IF successor is in EXPANDED nodes THEN
			Compute fnew(successor) = g(successor) + h(successor)
			IF fprevious(successor) “worse than” fnew(successor) THEN
			Remove previous parent link for successor and make parent link to node
			Make f(successor)= fnew(successor) and update f() for each of successor’s children
	2.return list of successors
	 * */
	protected List<Node> expandH(Node node, List<State> expandedNodes,List<Node> frontier, Problem problem) {
	
		List<Node> successorNodes = new ArrayList<Node>();
		Node successorNode = null;
		State successorState = null;
		State currentState = node.getState();
		//	1.FOR EACH of the node’s successors DO:
		for(Operator operator: problem.getOperators())
		{
			successorState = operator.apply(currentState);
			if(successorState != null)
			{
				successorNode = new Node(successorState);
				//1.1 IF successor NOT in frontier nor EXPANDED nodes THEN
				if(!frontier.contains(successorNode) && !expandedNodes.contains(successorNode))
				{
				//	Compute f(successor) = g(successor) + h(successor)
					successorNode.setOperator(operator.getName());
					successorNode.setParent(node);
					successorNode.setDepth(node.getDepth() + 1);
					//evaluation function = heuristic function
					successorNode.setG(this.getEvaluationFunction().calculateG(successorNode));
					successorNode.setH(this.getEvaluationFunction().calculateH(successorNode));
				//	Create parent link between successor and node
					successorNode.setParent(node);
				//	Add successor to list of successors
					successorNodes.add(successorNode);
				}
			//1.2 IF successor is in frontier THEN
				else if(frontier.contains(successorNode))
				{
			//	Compute fnew(successor) = g(successor) + h(successor)
					successorNode.setOperator(operator.getName());
					successorNode.setParent(node);
					successorNode.setDepth(node.getDepth() + 1);
					//evaluation function = heuristic function
					successorNode.setG(this.getEvaluationFunction().calculateG(successorNode));
					successorNode.setH(this.getEvaluationFunction().calculateH(successorNode));
			//	IF fprevious(successor) “worse than” fnew(successor) THEN
					Node previousNode = this.getNodeFrontier(frontier, successorNode);
					double fPreviousNode = (previousNode.getG() + previousNode.getH());
					double fsuccessorNode = (successorNode.getG() + successorNode.getH());
					if(fPreviousNode > fsuccessorNode)
					{
				//	Remove previous parent link for successor and make parent link to node
						successorNode.setParent(previousNode.getParent());
						frontier.remove(previousNode);
				//	Make f(successor)= fnew(successor)
					}
				}
			}
		//1.3 IF successor is in EXPANDED nodes THEN
			if(expandedNodes.contains(successorNode))
			{
		//	Compute fnew(successor) = g(successor) + h(successor)
				successorNode.setOperator(operator.getName());
				successorNode.setParent(node);
				successorNode.setDepth(node.getDepth() + 1);
				//evaluation function = heuristic function
				successorNode.setG(this.getEvaluationFunction().calculateG(successorNode));
				successorNode.setH(this.getEvaluationFunction().calculateH(successorNode));
//				IF fprevious(successor) “worse than” fnew(successor) THEN
				Node previousNode = this.getNodeFrontier(frontier, successorNode);
				double fPreviousNode = (previousNode.getG() + previousNode.getH());
				double fsuccessorNode = (successorNode.getG() + successorNode.getH());
				if(fPreviousNode > fsuccessorNode)
				{
			//	Remove previous parent link for successor and make parent link to node
					successorNode.setParent(previousNode.getParent());
					frontier.remove(previousNode);
			//	Make f(successor)= fnew(successor) and update f() for each of successor’s children
				}
			}
		
		}
		//2.return list of successors

		return successorNodes;
	}
	
	public Node getNodeFrontier(List<Node> frontier, Node node)
	{
		Node sameNode = null;
		Iterator<Node> iterator = frontier.iterator();
		while (iterator.hasNext())
		{
			if (node.equals(iterator))
			{
				sameNode = node;
			}
		}
		return sameNode;
	}
}