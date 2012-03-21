package es.deusto.ingenieria.aike.footprint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import es.deusto.ingenieria.aike.footprint.MoveOperator.Direction;
import es.deusto.ingenieria.aike.formulation.Problem;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.ingenieria.search.Node;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class FootProblem extends Problem {
	
	protected void createOperators()
	{
		super.addOperator(new MoveOperator(Direction.RIGHT));
		super.addOperator(new MoveOperator(Direction.UP));
		super.addOperator(new MoveOperator(Direction.LEFT));
		super.addOperator(new MoveOperator(Direction.DOWN));
	}
	
	public boolean isFinalState(State state)
	{
		if (state != null) {
			Environment environment = (Environment)state.getInformation();			
			return environment.getCp().equals(environment.getGoal());			
		} else {
			return false;
		}
	}
	
	public void solve(SearchMethod searchMethod)
	{	
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
		System.out.println("\n* Begin '" + searchMethod.getClass().getCanonicalName() + "' (" + formatter.format(GregorianCalendar.getInstance().getTime()) + ")");
		Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		System.out.println("* End '" + searchMethod.getClass().getCanonicalName() + "' (" + formatter.format(GregorianCalendar.getInstance().getTime()) + ")");
		
		if (finalNode != null) {			
			List<String> operators = new ArrayList<String>();
			searchMethod.solutionPath(finalNode, operators);
			searchMethod.createSolutionLog(operators);
			System.out.println("* Solution found!!");
			System.out.println("  * Movements: " + finalNode.getDepth());
		} else {
			System.out.println("* Solution not found :-(");
		}
	}
}
