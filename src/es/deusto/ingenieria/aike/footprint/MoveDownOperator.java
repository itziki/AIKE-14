package es.deusto.ingenieria.aike.footprint;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class MoveDownOperator extends Operator{
	//TO DO
	
	public static enum Foot
	{
		RIGHT,
		LEFT
	}
	
	private es.deusto.ingenieria.aike.footprint.MoveRightOperator.Foot foot;
	private Board board;
	
	public MoveDownOperator(es.deusto.ingenieria.aike.footprint.MoveRightOperator.Foot foot)
	{
		super(("Move Down - foot " + foot), 1d);
		this.foot = foot;
	}

	//TO CHECK
	//the adjacent tile has the contrary foot
	//there is no wall between the tiles
	//the next tile is inside the board
	protected boolean isApplicable(State state) {
		Environment escenario = (Environment)state.getInformation();
		CurrentPosition currPos = escenario.getCp();
		board = escenario.getBoard();
		boolean result = false;
		System.out.println("cp down: " + currPos.getY() + "," + currPos.getX());
		switch (this.foot)
		{
			case RIGHT:
				if(board.getTile(currPos.getY() - 1, currPos.getX()).isLeftFoot() &&
					!board.getTile(currPos.getY(), currPos.getX()).isBottomWall() &&
					currPos.getY() > 1) //¿?
				{
					result = true;
				}
			case LEFT:
				if(board.getTile(currPos.getY() - 1, currPos.getX()).isRightFoot() &&
					!board.getTile(currPos.getY(), currPos.getX()).isBottomWall() &&
					currPos.getY() > 1) //¿?
				{
					result = true;
				}
		}
		
		return result;
	}
	
	protected State effect(State state) {
		Environment environment = (Environment)state.getInformation();
		Environment newEnvironment = environment.clone();
		
		newEnvironment.setBoard(environment.getBoard());
		newEnvironment.setGoal(environment.getGoal());
		
		CurrentPosition cp = new CurrentPosition(environment.getCp().getX(), (environment.getCp().getY() - 1));
		newEnvironment.setCp(cp);
		return new State(newEnvironment);
		
	}

	
	
}
