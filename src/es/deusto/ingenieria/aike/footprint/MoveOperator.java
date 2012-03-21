package es.deusto.ingenieria.aike.footprint;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class MoveOperator extends Operator {

	public static enum Direction
	{
		RIGHT,
		LEFT,
		UP,
		DOWN
	}
	
	private Direction direction;
	
	public MoveOperator(Direction direction)
	{
		super("Move-" + direction.toString());
		this.direction = direction;
	}

	//TO CHECK
	//the adjacent tile has the contrary foot
	//there is no wall between the tiles
	//the next tile is inside the board
	protected boolean isApplicable(State state) 
	{
		Environment escenario = (Environment)state.getInformation();
		Position currPos = escenario.getCp();
		Tile[][] tiles = escenario.getTiles();

		switch (this.direction)
		{
			case RIGHT:
					return (currPos.getX() + 1 < tiles[0].length && 
						tiles[currPos.getY()][currPos.getX() + 1].getFoot() != 
								tiles[currPos.getY()][currPos.getX()].getFoot() &&
								!tiles[currPos.getY()][currPos.getX()].isRightWall());
			case LEFT:
					return (currPos.getX() > 0 && 
						tiles[currPos.getY()][currPos.getX() - 1].getFoot() != 
							tiles[currPos.getY()][currPos.getX()].getFoot() &&
								!tiles[currPos.getY()][currPos.getX() - 1].isRightWall());
			case DOWN:	
					return(currPos.getY() + 1 < tiles.length &&
						tiles[currPos.getY() + 1][currPos.getX()].getFoot() != 
							tiles[currPos.getY()][currPos.getX()].getFoot() &&
								!tiles[currPos.getY()][currPos.getX()].isBottomWall());
			case UP:
					return(currPos.getY() > 0 && 
						tiles[currPos.getY() - 1][currPos.getX()].getFoot() != 
							tiles[currPos.getY()][currPos.getX()].getFoot() &&
								!tiles[currPos.getY() - 1][currPos.getX()].isBottomWall());
		}
		return false;
	}
	
	protected State effect(State state) {		
		Environment environment = (Environment)state.getInformation();
		Environment newEnvironment = environment.clone();
		Position cp = environment.getCp();

		switch (this.direction)
		{
			case RIGHT:
				newEnvironment.setCp(new Position(cp.getX() + 1, cp.getY()));
				break;
			case LEFT:
				newEnvironment.setCp(new Position(cp.getX() - 1, cp.getY()));
				break;
			case DOWN:
				newEnvironment.setCp(new Position(cp.getX(), cp.getY() + 1));
				break;
			case UP:
				newEnvironment.setCp(new Position(cp.getX(), cp.getY() - 1));
				break;
		}

		return new State(newEnvironment);	
	}	
}