package es.deusto.ingenieria.aike.footprint;

public class Environment {
	private Board board;
	private CurrentPosition cp;
	private Goal goal;
	
	public Environment(Board board, CurrentPosition cp, Goal goal)
	{
		this.board = new Board(board.getTam(),board.getTiles());
		this.cp = new CurrentPosition(cp.getX(), cp.getY());
		this.goal = new Goal(goal.getX(), goal.getY());
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public CurrentPosition getCp() {
		return cp;
	}

	public void setCp(CurrentPosition cp) {
		this.cp = cp;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public Environment clone() 
	{
		return new Environment(this.getBoard(), this.getCp(), this.getGoal());
	}
	
	public String toString()
	{
		return this.cp.toString();
	}
	
	public boolean equals(Environment env)
	{
		return (this.board.equals(env.getBoard()) && this.cp.equals(env.getCp()) 
				  && this.goal.equals(env.getGoal()));
	}
	
}