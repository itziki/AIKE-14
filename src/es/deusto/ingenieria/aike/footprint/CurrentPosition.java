package es.deusto.ingenieria.aike.footprint;
import java.lang.String;

public class CurrentPosition 
{
	private int y; //ROW
	private int x; //COLUMN
	
	public CurrentPosition (int x, int y)
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
	
	public CurrentPosition clone() 
	{
		return new CurrentPosition(this.getY(), this.getX());
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj != null	&& obj instanceof CurrentPosition) 
		{
			CurrentPosition cpTemp = (CurrentPosition)obj;
			if (cpTemp.getY() == this.getY() && 
					cpTemp.getX() == this.getX())
				result = true;
		} 
		return result;
	}
	
	public String toString()
	{
		return "Current Position [row=" + y + ", col=" + x + "]";
	}
}
