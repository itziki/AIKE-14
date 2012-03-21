package es.deusto.ingenieria.aike.footprint;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.aike.search.heuristic.EvaluationFunction;
import es.deusto.ingenieria.ingenieria.search.Node;

public class ManhattanDistance extends EvaluationFunction {

	public double calculateG(Node node) {
		List<String> operatorList = new ArrayList<String>();
		currentPath(node.getParent(), operatorList);
		return operatorList.size();
	}
	
	private void currentPath(Node node, List<String> operatorNames) {
		if (node == null || node.getParent() == null) {
			return;
		} else {
			operatorNames.add(0, node.getOperator());
			currentPath(node.getParent(), operatorNames);
		}
	}
	
	public double calculateH(Node node) {	
		Environment env = (Environment)node.getState().getInformation();	
		return Math.abs(env.getGoal().getY()-env.getCp().getY()) + Math.abs(env.getGoal().getX()-env.getCp().getX());
	}
}