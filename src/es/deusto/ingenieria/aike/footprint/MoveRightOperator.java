package es.deusto.ingenieria.aike.footprint;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class MoveRightOperator extends Operator{
	//TO DO
	
	public static enum Foot
	{
		RIGHT,
		LEFT
	}
	
	private Foot foot;
	private Board board;
	
	public MoveRightOperator(Foot foot)
	{
		super(("Move Right - foot " + foot), 1d);
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
		System.out.println("cp right: " + currPos.getY() + "," + currPos.getX());
		switch (this.foot)
		{
			case RIGHT:
				if(board.getTile(currPos.getY(), currPos.getX() + 1).isLeftFoot() &&
					!board.getTile(currPos.getY(), currPos.getX()).isRightWall() &&
					currPos.getX() < board.getTam()[1] - 2)
				{
					result = true;
				}
			case LEFT:
				if(board.getTile(currPos.getY(), currPos.getX()  + 1).isRightFoot() &&
					!board.getTile(currPos.getY(), currPos.getX()).isRightWall() &&
					currPos.getX() < board.getTam()[1] - 2)
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
		
		CurrentPosition cp = new CurrentPosition(environment.getCp().getX() + 1, environment.getCp().getY());
		newEnvironment.setCp(cp);
		return new State(newEnvironment);
		
	}

	
	
}
