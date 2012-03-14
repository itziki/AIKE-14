package es.deusto.ingenieria.aike.footprint;

import java.util.ArrayList;
import java.util.Collections;
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

		List<Node> frontier = new ArrayList<Node>();
		//List of states generated during the search process. This is used to check for repeated states.
		List<State> generatedStates = new ArrayList<State>();
		//List of states expended during the search process. This is used to check for repeated states.
		List<State> expandedStates = new ArrayList<State>();
		//First node in the list of generated nodes.
		Node firstNode = null;
		//successor nodes list.
		List<Node> successorNodes = null;
		//Flag that signals whether a solution has been found.
		boolean solutionFound = false;

		//Defines and initializes the search log.
		SearchLog searchLog = this.createLog();
		
		//Initialize the generated nodes list with a node containing the problem's initial state.
		frontier.add(new Node(initialState));

		//Loop until the problem is solved or the generated nodes list is empty
		while (!solutionFound && !frontier.isEmpty()) {			
			//write the content of the generated nodes list in the search log.
			this.writeLog(searchLog, frontier);			
			//remove the first node from the generated nodes list.
			firstNode = frontier.remove(0);
			
			//If the first node contains a problem's final state
			if (problem.isFinalState(firstNode.getState())) {
				//change the flag to signal that the problem is solved
				solutionFound = true;
			//If the first node doesn't contain a problem's final state				
			} else {
				//Expand the first node.
				successorNodes = this.expand(firstNode, problem, generatedStates, expandedStates);
				//If new successor nodes resulted from the expansion
				if (successorNodes != null && !successorNodes.isEmpty()) {
					//Add the successor nodes to the generated nodes list.
					frontier.addAll(successorNodes);
					//Sort the generated nodes list according to the evaluation function value
					// of the nodes. This comparison criteria is defined within the compareTo()
					// method of Node.
					Collections.sort(frontier);
				}
			}
		}
		
		this.closeLog(searchLog);
		// If the problem is solved
		if (solutionFound) {
			//Return the first node as it contains the problem's final state
			return firstNode;
		//If the problem is not solved
		} else {
			//return null
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
	protected List<Node> expandH(Node node, Problem problem, List<State> generatedStates, List<State> expandedStates) {
		List<Node> successorNodes = new ArrayList<Node>();
		Node successorNode = null;
		State currentState = null;
		State successorState = null;
		
		//If the current node and problem aren't null
		if (node != null && problem != null) {
			//Make the current state the state kept in the node.
			currentState = node.getState();
			//Remove current state from the list of generated states.
			generatedStates.remove(currentState);
			//Insert current state to the list of generated states.
			expandedStates.add(currentState);			
			//If current state is not null
			if (currentState != null) {
				//process the list of problem operators
				for (Operator operator : problem.getOperators()) {
					//Apply the operator to the current state
					successorState = operator.apply(currentState);
					//If the operator was applicable, a new successor state was generated
					if (successorState != null) {
						//make a new node to keep the new successor state
						successorNode = new Node(successorState);
						//If the new node hadn't been generated before nor expanded
						if (!generatedStates.contains(successorNode) && !expandedStates.contains(successorNode)) {
							//Set values for the various node's attributes
							successorNode.setOperator(operator.getName());
							successorNode.setParent(node);
							successorNode.setDepth(node.getDepth() + 1);
							//evaluation function = heuristic function
							successorNode.setG(this.getEvaluationFunction().calculateG(successorNode));
							successorNode.setH(this.getEvaluationFunction().calculateH(successorNode));
							//Add the new node to the list of successor nodes.
							successorNodes.add(successorNode);
							//Insert current successor State to the list of generated states
							generatedStates.add(successorState);
						}
					}
				}
			}
		}
		return successorNodes;
	}
}