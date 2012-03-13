package es.deusto.ingenieria.aike.footprint;

public class Goal 
{
	private int y;
	private int x;
	
	public Goal (int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public Goal clone() 
	{
		return new Goal(this.getY(), this.getX());
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj != null	&& obj instanceof Goal) 
		{
			Goal gTemp = (Goal)obj;
			if (gTemp.getY() == this.getY() && 
					gTemp.getX() == this.getX())
				result = true;
		} 
		return result;
	}
	
	public String toString()
	{
		return "Goal [row=" + y + ", col=" + x + "]";
	}
}
