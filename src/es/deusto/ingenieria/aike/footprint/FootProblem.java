package es.deusto.ingenieria.aike.footprint;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.aike.footprint.MoveOperator.Direction;
import es.deusto.ingenieria.aike.formulation.Problem;
import es.deusto.ingenieria.aike.formulation.State;
//import es.deusto.ingenieria.aike.search.Node;
import es.deusto.ingenieria.ingenieria.search.Node;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class FootProblem extends Problem{
	//TO DO
	
	public FootProblem(State state)
	{
		super();
		this.addInitialState(state);
		this.createOperators();
	}
	
	protected void createOperators()
	{
	/*	super.addOperator(new MoveRightOperator(Foot.RIGHT));
		super.addOperator(new MoveRightOperator(Foot.LEFT));
		
		super.addOperator(new MoveLeftOperator(Foot.RIGHT));
		super.addOperator(new MoveLeftOperator(Foot.LEFT));
		
		super.addOperator(new MoveUpOperator(Foot.RIGHT));
		super.addOperator(new MoveUpOperator(Foot.LEFT));
		
		super.addOperator(new MoveDownOperator(Foot.RIGHT));
		super.addOperator(new MoveDownOperator(Foot.LEFT));
	*/
		super.addOperator(new MoveOperator(Direction.RIGHT));
		super.addOperator(new MoveOperator(Direction.LEFT));
		super.addOperator(new MoveOperator(Direction.DOWN));
		super.addOperator(new MoveOperator(Direction.UP));
	}
	
	public boolean isFinalState(State state)
	{
		boolean finalState = false;
		if (state != null && state.getInformation() != null && state.getInformation() instanceof Environment)
		{
			Environment environment = (Environment)state.getInformation();
			CurrentPosition cp = environment.getCp();
			Goal goal = environment.getGoal();
			
			finalState = ((cp.getX() == goal.getX()) && (cp.getY() == goal.getY()));
		}
		return finalState;
	}
	
	public void solve(SearchMethod searchMethod)
	{
		Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		
		if(finalNode != null)
		{
			System.out.println("Solution found!");
			List<String> operators = new ArrayList<String>();
			searchMethod.solutionPath(finalNode, operators);
			searchMethod.createSolutionLog(operators);
		}
		else
		{
			System.out.println("Unable to find the solution!");
		}
	}
}
