package es.deusto.ingenieria.aike.footprint;

public class Environment {
	private Tile[][] tiles;
	private Position cp;
	private Position goal;
	
	public Environment(Tile[][] tiles, Position cp, Position goal)
	{
		this.tiles = tiles;
		this.cp = new Position(cp.getX(), cp.getY());
		this.goal = new Position(goal.getX(), goal.getY());
	}

	public Tile getTile(int x, int y) {
		return tiles[y][x];
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public Position getCp() {
		return cp;
	}

	public void setCp(Position cp) {
		this.cp = cp;
	}

	public Position getGoal() {
		return goal;
	}

	public void setGoal(Position goal) {
		this.goal = goal;
	}

	public Environment clone() 
	{
		return new Environment(this.getTiles(), this.getCp(), this.getGoal());
	}
	
	public String toString()
	{
		return this.cp.toString();
	}
	
	public boolean equals(Object obj)
	{
		if (obj != null && obj instanceof Environment) {
			return ((Environment)obj).getCp().equals(this.cp);
		} else {
			return false;
		}
	}
	
	public String toDrawString() 
	{
		String boardString = "";	
		for (int i = 0; i < this.tiles.length; i++)
		{
			for (int j = 0; j < this.tiles[i].length; j++)
			{
				if (this.tiles[i][j].isRightFoot()) {
					boardString = boardString + "R";
				} else {
					boardString = boardString + "L";
				}
			}
			boardString = boardString + "\n";
		}
		return boardString;
	}
}