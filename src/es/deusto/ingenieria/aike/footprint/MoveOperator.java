package es.deusto.ingenieria.aike.footprint;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class MoveOperator extends Operator{
	//TO DO
	
	public static enum Feet
	{
		RIGHT,
		LEFT
	}
	
	public static enum Direction
	{
		RIGHT,
		LEFT,
		UP,
		DOWN
	}
	
	private Feet foot;
	private Direction direction;
	private Board board;
	
	public MoveOperator(Feet foot, Direction direction)
	{
		super(("Move " + direction.toString() + "- foot " + foot.toString()), 1d);
		this.foot = foot;
		this.direction = direction;
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
		//System.out.println("cp right: " + currPos.getY() + "," + currPos.getX());
		switch (this.foot)
		{
			case RIGHT:
				switch (this.direction)
				{
					case RIGHT:
						if(board.getTile(currPos.getY(), currPos.getX() + 1).isLeftFoot() &&
							!board.getTile(currPos.getY(), currPos.getX()).isRightWall() &&
							currPos.getX() < board.getTam()[1] - 2)
						{
							result = true;
						}
					case LEFT:
						if(board.getTile(currPos.getY(), currPos.getX() - 1).isLeftFoot() &&
								!board.getTile(currPos.getY(), currPos.getX() - 1).isRightWall() &&
								currPos.getX() > 1)
							{
								result = true;
							}
					case DOWN:
						if(board.getTile(currPos.getY() + 1, currPos.getX()).isLeftFoot() &&
								!board.getTile(currPos.getY() + 1, currPos.getX()).isBottomWall() &&
								currPos.getY() < board.getTam()[1] - 2) //¿?
							{
								result = true;
							}
					case UP:
						if(board.getTile(currPos.getY() - 1, currPos.getX()).isLeftFoot() &&
								!board.getTile(currPos.getY(), currPos.getX()).isBottomWall() &&
								currPos.getY() > 1) //¿?
							{
								result = true;
							}
				}
			case LEFT:
				switch (this.direction)
				{
					case RIGHT:
						if(board.getTile(currPos.getY(), currPos.getX() + 1).isRightFoot() &&
							!board.getTile(currPos.getY(), currPos.getX()).isRightWall() &&
							currPos.getX() < board.getTam()[1] - 2)
						{
							result = true;
						}
					case LEFT:
						if(board.getTile(currPos.getY(), currPos.getX() - 1).isRightFoot() &&
								!board.getTile(currPos.getY(), currPos.getX() - 1).isRightWall() &&
								currPos.getX() > 1)
							{
								result = true;
							}
					case DOWN:
						if(board.getTile(currPos.getY() + 1, currPos.getX()).isRightFoot() &&
								!board.getTile(currPos.getY() + 1, currPos.getX()).isBottomWall() &&
								currPos.getY() < board.getTam()[1] - 2) 
							{
								result = true;
							}
					case UP:
						if(board.getTile(currPos.getY() - 1, currPos.getX()).isRightFoot() &&
								!board.getTile(currPos.getY(), currPos.getX()).isBottomWall() &&
								currPos.getY() > 1) 
							{
								result = true;
							}
				}
		}
		
		return result;
	}
	
	protected State effect(State state) {		
		Environment environment = (Environment)state.getInformation();
		Environment newEnvironment = environment.clone();
		CurrentPosition cp = null;
		
		
		switch (this.direction)
		{
			case RIGHT: {cp = new CurrentPosition((environment.getCp().getX() + 1), environment.getCp().getY());}
			case LEFT: {cp = new CurrentPosition((environment.getCp().getX() - 1), environment.getCp().getY());}
			case DOWN: {cp = new CurrentPosition(environment.getCp().getX(), (environment.getCp().getY() + 1));}
			case UP: {cp = new CurrentPosition(environment.getCp().getX(), (environment.getCp().getY() - 1));}
		}
		
		newEnvironment.setBoard(environment.getBoard());
		newEnvironment.setGoal(environment.getGoal());
		newEnvironment.setCp(cp);
		return new State(newEnvironment);
		
	}

	
	
}
